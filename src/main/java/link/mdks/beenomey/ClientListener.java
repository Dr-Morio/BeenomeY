package link.mdks.beenomey;

import link.mdks.beenomey.client.renderer.block.ApiaryModBlockRenderer;
import link.mdks.beenomey.client.renderer.block.BreederBlockRenderer;
import link.mdks.beenomey.init.BlockEntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BeenomeY.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

	
	@SubscribeEvent
	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
			event.registerBlockEntityRenderer(BlockEntityInit.APIARY_MOD_BLOCK.get(), context -> new ApiaryModBlockRenderer());
			event.registerBlockEntityRenderer(BlockEntityInit.BREEDER_BLOCK.get(), context -> new BreederBlockRenderer());

	}
}
