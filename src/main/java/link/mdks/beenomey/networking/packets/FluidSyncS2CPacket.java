package link.mdks.beenomey.networking.packets;

import java.util.function.Supplier;

import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import link.mdks.beenomey.apiculture.screen.BreederBlockMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

public class FluidSyncS2CPacket {

	private final FluidStack fluidStack;
	private final BlockPos pos;

	public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
		this.fluidStack = fluidStack;
		this.pos = pos;
	}

	public FluidSyncS2CPacket(FriendlyByteBuf buf) {
		this.fluidStack = buf.readFluidStack();
		this.pos = buf.readBlockPos();
	}
	
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeFluidStack(fluidStack);
		buf.writeBlockPos(pos);
	}
	
	@SuppressWarnings("resource")
	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		
		context.enqueueWork(() -> {
			// HERE IS CLIENT SIDE
			if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof BreederBlockEntity blockEntity) {
				blockEntity.setFluid(this.fluidStack);
				
				if(Minecraft.getInstance().player.containerMenu instanceof BreederBlockMenu menu &&
					menu.getBlockEntity().getBlockPos().equals(pos)) {
					//blockEntity.setFluid(fluidStack);
					menu.setFluid(this.fluidStack);
					
				}
			}
		});
		return true;
	}
}