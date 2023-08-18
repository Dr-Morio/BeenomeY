package link.mdks.beenomey.networking;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.networking.packets.EnergySyncS2CPacket;
import link.mdks.beenomey.networking.packets.FluidSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkMessages {

	private static SimpleChannel INSTANCE;
	private static int packetId = 0;
	
	private static int id() {
		return packetId++; 
	}
	
	public static void register() {
		SimpleChannel net = NetworkRegistry.ChannelBuilder
				.named( new ResourceLocation(BeenomeY.MODID, "messages"))
				.networkProtocolVersion(() -> "1.0")
				.clientAcceptedVersions(s -> true)
				.serverAcceptedVersions(s -> true)
				.simpleChannel();
		

		
		net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
		.decoder(EnergySyncS2CPacket::new)
		.encoder(EnergySyncS2CPacket::toBytes)
		.consumerMainThread(EnergySyncS2CPacket::handle)
		.add();
		
		net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
		.decoder(FluidSyncS2CPacket::new)
		.encoder(FluidSyncS2CPacket::toBytes)
		.consumerMainThread(FluidSyncS2CPacket::handle)
		.add();
		
		INSTANCE = net;
	}
	
	
	public static <MSG> void sentToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}
	
	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
	
	public static <MSG> void sendToClients(MSG message) {
		INSTANCE.send(PacketDistributor.ALL.noArg(), message);
		
	}
}
