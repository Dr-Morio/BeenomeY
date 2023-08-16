package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.screen.ApiaryModBlockMenu;
import link.mdks.beenomey.apiculture.screen.BreederBlockMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypeInit {

	public static DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BeenomeY.MODID);
	
	public static final RegistryObject<MenuType<ApiaryModBlockMenu>> APIARY_MOD_BLOCK_MENU =
			registerMenuType(ApiaryModBlockMenu::new, "apiary_mod_block_menu");
	
	public static final RegistryObject<MenuType<BreederBlockMenu>> BREEDER_BLOCK_MENU =
			registerMenuType(BreederBlockMenu::new, "breeder_block_menu");
	
	
	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
		return MENUS.register(name, () -> IForgeMenuType.create(factory));
	}
	
	
    public static void register(IEventBus eventBus) {
    	MENUS.register(eventBus);
    }
	
}
