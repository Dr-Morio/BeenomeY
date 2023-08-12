package link.mdks.beenomey.init;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.ItemHoneycomb;
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
	public static final DeferredRegister<Item> HONEYCOMBS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);

	/* Util Items*/
	public static final RegistryObject<Item> WOODEN_SCOOP = ITEMS.register("wooden_scoop",
			() -> new ItemScoop(null));
	
	/* Honeycombs */
	public static final RegistryObject<Item> HONEYCOMB = HONEYCOMBS.register("honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> ICEY_HONEYCOMB = HONEYCOMBS.register("ice_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> WATERY_HONEYCOMB = HONEYCOMBS.register("watery_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> SAND_HONEYCOMB = HONEYCOMBS.register("sand_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> STONE_HONEYCOMB = HONEYCOMBS.register("stone_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> NETHER_HONEYCOMB = HONEYCOMBS.register("nether_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> ENDER_HONEYCOMB = HONEYCOMBS.register("ender_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> LAVA_HONEYCOMB = HONEYCOMBS.register("lava_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> PAPER_HONEYCOMB = HONEYCOMBS.register("paper_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> LAPIS_HONEYCOMB = HONEYCOMBS.register("lapis_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> REDSTONE_HONEYCOMB = HONEYCOMBS.register("redstone_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> IRON_HONEYCOMB = HONEYCOMBS.register("iron_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> GOLD_HONEYCOMB = HONEYCOMBS.register("gold_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> COPPER_HONEYCOMB = HONEYCOMBS.register("copper_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> DIAMOND_HONEYCOMB = HONEYCOMBS.register("diamond_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> OBSIDIAN_HONEYCOMB = HONEYCOMBS.register("obsidian_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	
	
	/* BlockItems */
	public static final RegistryObject<BlockItem> APIARY_MOD_BLOCK = ITEMS.register("apiary_mod_block",
			() -> new BlockItem(BlockInit.APIARY_MOD_BLOCK.get(),
					new Item.Properties()));
	
	public static final RegistryObject<BlockItem> BREEDER_BLOCK = ITEMS.register("breeder_block",
			() -> new BlockItem(BlockInit.BREEDER_BLOCK.get(),
					new Item.Properties()));
	
    public static void register(IEventBus eventBus) {
    	ITEMS.register(eventBus);
    	HONEYCOMBS.register(eventBus);
    }
    
    public static Collection<RegistryObject<Item>> getHoneyCombItems() {
    	return HONEYCOMBS.getEntries();
    }
	
}
