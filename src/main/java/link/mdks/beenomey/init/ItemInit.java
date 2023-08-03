package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.ItemScoop;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	private ItemInit() {}
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);

	public static final RegistryObject<Item> WOODEN_SCOOP = ITEMS.register("wooden_scoop",
			() -> new ItemScoop(null));
	
	public static final RegistryObject<BlockItem> apiary_mod_block = ITEMS.register("apiary_mod_block",
			() -> new BlockItem(BlockInit.apiary_mod_block.get(),
					new Item.Properties()));
	
    public static void register(IEventBus eventBus) {
    	ITEMS.register(eventBus);
    }
	
}
