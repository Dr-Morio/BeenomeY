package link.mdks.beenomey.init;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.stringtemplate.v4.ST;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.ItemHoneycomb;
import link.mdks.beenomey.apiculture.items.ItemScoop;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	private ItemInit() {}
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);
	public static final DeferredRegister<Item> HONEYCOMBS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);
	public static final DeferredRegister<Item> STRUCTURECOMBS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);

	public static final DeferredRegister<Item> BUCKETS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);

	/* Util Items*/
	public static final RegistryObject<Item> WOODEN_SCOOP = ITEMS.register("wooden_scoop",
			() -> new ItemScoop(null));
	
	/* Honeycombs */
	public static final RegistryObject<Item> HONEYCOMB = HONEYCOMBS.register("honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> FROZEN_HONEYCOMB = HONEYCOMBS.register("frozen_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> OCEAN_HONEYCOMB = HONEYCOMBS.register("ocean_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> DESERT_HONEYCOMB = HONEYCOMBS.register("desert_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> ROCK_HONEYCOMB = HONEYCOMBS.register("rock_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> INFERNO_HONEYCOMB = HONEYCOMBS.register("inferno_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> VOID_HONEYCOMB = HONEYCOMBS.register("void_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> COAL_HONEYCOMB = HONEYCOMBS.register("coal_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> GUNPOWDER_HONEYCOMB = HONEYCOMBS.register("gunpowder_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> FLINT_HONEYCOMB = HONEYCOMBS.register("flint_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> SLIME_HONEYCOMB = HONEYCOMBS.register("slime_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> SUGAR_HONEYCOMB = HONEYCOMBS.register("sugar_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> GLASS_HONEYCOMB = HONEYCOMBS.register("glass_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_HONEYCOMB = HONEYCOMBS.register("clay_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> STRING_HONEYCOMB = HONEYCOMBS.register("string_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> NETHER_WART_HONEYCOMB = HONEYCOMBS.register("nether_wart_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> SOUL_SAND_HONEYCOMB = HONEYCOMBS.register("soul_sand_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> MUSHROOM_HONEYCOMB = HONEYCOMBS.register("mushroom_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> LAVA_HONEYCOMB = HONEYCOMBS.register("lava_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> LAPIS_LAZULI_HONEYCOMB = HONEYCOMBS.register("lapis_lazuli_honeycomb",
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
	public static final RegistryObject<Item> GLOWSTONE_HONEYCOMB = HONEYCOMBS.register("glowstone_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> BLAZE_HONEYCOMB = HONEYCOMBS.register("blaze_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> EMERALD_HONEYCOMB = HONEYCOMBS.register("emerald_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> NETHER_STAR_HONEYCOMB = HONEYCOMBS.register("nether_star_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> NETHER_QUARZ_HONEYCOMB = HONEYCOMBS.register("nether_quarz_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> PRISMARINE_HONEYCOMB = HONEYCOMBS.register("prismarine_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> CHORUS_HONEYCOMB = HONEYCOMBS.register("chorus_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> NETHERITE_HONEYCOMB = HONEYCOMBS.register("netherite_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> EXP_HONEYCOMB = HONEYCOMBS.register("exp_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> ENDER_PEARL_HONEYCOMB = HONEYCOMBS.register("ender_pearl_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	public static final RegistryObject<Item> AMETHYST_HONEYCOMB = HONEYCOMBS.register("amethyst_honeycomb",
			() -> new ItemHoneycomb(new Item.Properties()));
	
	
	/* Buckets */

    public static final RegistryObject<Item> HONEY_BUCKET = BUCKETS.register("honey_bucket", 
    		() -> new BucketItem(FluidInit.SOURCE_HONEY, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    
    public static final RegistryObject<Item> FROZEN_HONEY_BUCKET = BUCKETS.register("frozen_honey_bucket", 
    		() -> new BucketItem(FluidInit.SOURCE_FROZEN_HONEY, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    
    public static final RegistryObject<Item> OCEAN_HONEY_BUCKET = BUCKETS.register("ocean_honey_bucket", 
    		() -> new BucketItem(FluidInit.SOURCE_OCEAN_HONEY, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    
    public static final RegistryObject<Item> INFERNO_HONEY_BUCKET = BUCKETS.register("inferno_honey_bucket", 
    		() -> new BucketItem(FluidInit.SOURCE_INFERNO_HONEY, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    

	
	/* BlockItems */
	public static final RegistryObject<BlockItem> APIARY_MOD_BLOCK = ITEMS.register("apiary_mod_block",
			() -> new BlockItem(BlockInit.APIARY_MOD_BLOCK.get(),
					new Item.Properties()));
	
	public static final RegistryObject<BlockItem> BREEDER_BLOCK = ITEMS.register("breeder_block",
			() -> new BlockItem(BlockInit.BREEDER_BLOCK.get(),
					new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	
    public static void register(IEventBus eventBus) {
    	ITEMS.register(eventBus);
    	HONEYCOMBS.register(eventBus);
    	STRUCTURECOMBS.register(eventBus);
    	BUCKETS.register(eventBus);
    }
    
    public static Collection<RegistryObject<Item>> getHoneycombItems() {
    	return HONEYCOMBS.getEntries();
    }
    
    public static Collection<RegistryObject<Item>> getStructurecombItems() {
    	return STRUCTURECOMBS.getEntries();
    }
    
    public static Collection<RegistryObject<Item>> getBucketItems() {
    	return BUCKETS.getEntries();
    }
	
}
