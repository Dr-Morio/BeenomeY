package link.mdks.beenomey;

import com.mojang.logging.LogUtils;

import link.mdks.beenomey.apiculture.screen.ApiaryModBlockScreen;
import link.mdks.beenomey.apiculture.screen.BreederBlockScreen;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.apiculture.util.ItemBeeTextureProperties;
import link.mdks.beenomey.core.BeenomeYTabs;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.BlockEntityInit;
import link.mdks.beenomey.init.BlockInit;
import link.mdks.beenomey.init.FluidInit;
import link.mdks.beenomey.init.FluidTypeInit;
import link.mdks.beenomey.init.ItemInit;
import link.mdks.beenomey.init.MenuTypeInit;
import link.mdks.beenomey.init.RecipeInit;
import link.mdks.beenomey.networking.NetworkMessages;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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


/**
* Some classes and methods are heavily influenced by Kaupenjoe.
* I started this mod and used his tutorial series to grasp the concepts of Minecraft modding.
* A big thanks to him for providing such an amazing video series!
*/


// Infused Honey with different burn properties
// Electric Bees

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BeenomeY.MODID)
public class BeenomeY
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "beenomey";
    // Directly reference a slf4j logger
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
        
        // Register FluidTypes
        FluidTypeInit.register(modEventBus);
        
        // Register Fluids
        FluidInit.register(modEventBus);
       
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        // Register Creative Tab Items
        modEventBus.addListener(this::addCreative);
        
        // Common Setup with Networking
        modEventBus.addListener(this::commonSetup);
                
        GeckoLib.initialize();
        
    }
    
    
    
	private void commonSetup(final FMLCommonSetupEvent event) {
    	NetworkMessages.register();
    }
    
	private void addCreative(CreativeModeTabEvent.BuildContents event) {
    	if(event.getTab() == BeenomeYTabs.BEENOMEY_TAB) {
    		
    		/* Register all Honeycombs*/
    		for(RegistryObject<Item> comb : ItemInit.getHoneycombItems()) {
    			event.accept(comb);
    		}
    		
    		/* Register all Structureombs*/
    		for(RegistryObject<Item> comb : ItemInit.getStructurecombItems()) {
    			event.accept(comb);
    		}
    		
    		/* Register all Buckets*/
    		for(RegistryObject<Item> cell : ItemInit.getCellItems()) {
    			event.accept(cell);
    		}

    		/* Register every clean breed Bee*/
    		for(BeeType type : BeeType.values()) {
    			if(type != BeeType.EMPTY) {
    				event.accept(BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())));
    			}
    		}
    		
//    		/* Register every cross breed Bee*/
//    		for(BeeType type : BeeType.values()) {
//    			if(type != BeeType.EMPTY) {
//        			for (BeeType type2 : BeeType.values()) {
//        				if(type2 != BeeType.EMPTY) {
//        					event.accept(BeeManager.getBee(type, type2, new ItemStack(BeeInit.getCommonBee())));
//        				}
//        			}
//    			}
//   		}
    		
    		/* Register every clean breed Princess*/
    		for(BeeType type : BeeType.values()) {
    			event.accept(BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())));
    		}
    		
    		
    		event.accept(ItemInit.WOODEN_SCOOP);
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
    		MenuScreens.register(MenuTypeInit.BREEDER_BLOCK_MENU.get(), BreederBlockScreen::new);
    		
    		event.enqueueWork(() -> ItemBeeTextureProperties.addCustomTextureVariantPropertie()); // someone said it's not Thread save :)
    	}
    }
    
}
