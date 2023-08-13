package link.mdks.beenomey;

import com.mojang.logging.LogUtils;

import link.mdks.beenomey.core.BeenomeYTabs;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.BlockEntityInit;
import link.mdks.beenomey.init.BlockInit;
import link.mdks.beenomey.init.ItemInit;
import link.mdks.beenomey.init.MenuTypeInit;
import link.mdks.beenomey.init.RecipeInit;
import link.mdks.beenomey.sceen.ApiaryModBlockScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.GeckoLib;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BeenomeY.MODID)
public class BeenomeY
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "beenomey";
    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
	public static final Logger LOGGER = LogUtils.getLogger();
// Some Shit ive Done
    
    public BeenomeY()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //beeInit.ITEMS.register(modEventBus);

        // Register Items
        ItemInit.register(modEventBus);
        // Register Bee Items
        BeeInit.register(modEventBus);
        
        // Register Blocks
        BlockInit.register(modEventBus);

        // Register Block Entites
        BlockEntityInit.register(modEventBus);
        
        // Register Menu Types
        MenuTypeInit.register(modEventBus);
        
        // Register Recipes
        RecipeInit.register(modEventBus);
       
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        // Register Creative Tab Items
        modEventBus.addListener(this::addCreative);
                
        GeckoLib.initialize();
        
    }
    
    
    
    @SuppressWarnings("unused")
	private void commonSetup(final FMLCommonSetupEvent event) {
    	
    }
    
	private void addCreative(CreativeModeTabEvent.BuildContents event) {
    	if(event.getTab() == BeenomeYTabs.BEENOMEY_TAB) {
    		
    		/* Register all Combs*/
    		for(RegistryObject<Item> comb : ItemInit.getHoneyCombItems()) {
    			event.accept(comb);
    		}
    		
    		event.accept(ItemInit.WOODEN_SCOOP);
    		event.accept(BeeInit.COMMON_BEE);
    		event.accept(BeeInit.PRINCESS_BEE);
    		event.accept(BeeInit.QUEEN_BEE);
    		event.accept(BlockInit.OAKWOOD_BEEHIVE_BLOCK);
    		event.accept(BlockInit.BIRCHWOOD_BEEHIVE_BLOCK);
    		event.accept(BlockInit.DESERT_BEEHIVE_BLOCK);
    		event.accept(BlockInit.ROCK_BEEHIVE_BLOCK);
    		event.accept(BlockInit.JUNGLEWOOD_BEEHIVE_BLOCK);
    		event.accept(BlockInit.FROZEN_BEEHIVE_BLOCK);
    		event.accept(BlockInit.VOID_BEEHIVE_BLOCK);
    		event.accept(BlockInit.INFERNO_BEEHIVE_BLOCK);
    		event.accept(BlockInit.OCEAN_BEEHIVE_BLOCK);
    		event.accept(BlockInit.APIARY_MOD_BLOCK);
    		event.accept(BlockInit.BREEDER_BLOCK);
    	}
    }
    
    
    
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscirbeEVent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
    	@SubscribeEvent
    	public static void onClientSetup(FMLClientSetupEvent event) {
    		MenuScreens.register(MenuTypeInit.APIARY_MOD_BLOCK_MENU.get(), ApiaryModBlockScreen::new);
    	}
    }
    
}
