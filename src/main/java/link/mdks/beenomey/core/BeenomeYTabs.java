package link.mdks.beenomey.core;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.init.BeeInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BeenomeY.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeenomeYTabs {

	public static CreativeModeTab BEENOMEY_TAB;
	
	@SubscribeEvent
	public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
		BEENOMEY_TAB = event.registerCreativeModeTab(new ResourceLocation(BeenomeY.MODID, "beenomey_tab"),
				builder -> builder.icon(() -> new ItemStack(BeeInit.getCommonBee()))
				.title(Component.translatable("creativemodetab.beenomey_tab")));
	}
	
	
}