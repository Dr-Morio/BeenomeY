package link.mdks.beenomey.init;

import java.util.Collection;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.ItemCell;
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

	public static final DeferredRegister<Item> CELLS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);

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
	
	
	/* Cells */
	public static final RegistryObject<Item> EMPTY_CELL = CELLS.register("empty_honey_cell", 
			() -> new ItemCell( null, new Item.Properties().stacksTo(1)));
	
    public static final RegistryObject<Item> HONEY_CELL = CELLS.register("honey_cell", 
    		() -> new ItemCell(FluidInit.SOURCE_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
    
    public static final RegistryObject<Item> FROZEN_HONEY_CELL = CELLS.register("frozen_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_FROZEN_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
//    public static final RegistryObject<Item> OCEAN_HONEY_CELL = CELLSS.register("ocean_honey_cell", 
//        	() -> new ItemCell(FluidInit.SOURCE_OCEAN_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> DESERT_HONEY_CELL = CELLS.register("desert_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_DESERT_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> ROCK_HONEY_CELL = CELLS.register("rock_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_ROCK_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> INFERNO_HONEY_CELL = CELLS.register("inferno_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_INFERNO_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> VOID_HONEY_CELL = CELLS.register("void_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_VOID_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> COAL_HONEY_CELL = CELLS.register("coal_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_COAL_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> GUNPOWDER_HONEY_CELL = CELLS.register("gunpowder_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_GUNPOWDER_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> FLINT_HONEY_CELL = CELLS.register("flint_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_FLINT_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));

    public static final RegistryObject<Item> SLIME_HONEY_CELL = CELLS.register("slime_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_SLIME_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));

    public static final RegistryObject<Item> SUGAR_HONEY_CELL = CELLS.register("sugar_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_SUGAR_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> GLASS_HONEY_CELL = CELLS.register("glass_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_GLASS_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> CLAY_HONEY_CELL = CELLS.register("clay_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_CLAY_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> STRING_HONEY_CELL = CELLS.register("string_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_STRING_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> NETHER_WART_HONEY_CELL = CELLS.register("nether_wart_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_NETHER_WART_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> SOUL_SAND_HONEY_CELL = CELLS.register("soul_sand_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_SOUL_SAND_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> MUSHROOM_HONEY_CELL = CELLS.register("mushroom_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_MUSHROOM_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> LAVA_HONEY_CELL = CELLS.register("lava_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_LAVA_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> LAPIS_LAZULI_HONEY_CELL = CELLS.register("lapis_lazuli_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_LAPIS_LAZULI_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> REDSTONE_HONEY_CELL = CELLS.register("redstone_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_REDSTONE_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> IRON_HONEY_CELL = CELLS.register("iron_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_IRON_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> GOLD_HONEY_CELL = CELLS.register("gold_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_GOLD_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> COPPER_HONEY_CELL = CELLS.register("copper_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_COPPER_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> DIAMOND_HONEY_CELL = CELLS.register("diamond_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_DIAMOND_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> OBSIDIAN_HONEY_CELL = CELLS.register("obsidian_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_OBSIDIAN_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> GLOWSTONE_HONEY_CELL = CELLS.register("glowstone_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_GLOWSTONE_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> BLAZE_HONEY_CELL = CELLS.register("blaze_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_BLAZE_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> EMERALD_HONEY_CELL = CELLS.register("emerald_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_EMERALD_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> NETHER_STAR_HONEY_CELL = CELLS.register("nether_star_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_NETHER_STAR_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> NETHER_QUARZ_HONEY_CELL = CELLS.register("nether_quarz_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_NETHER_QUARZ_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> PRISMARINE_HONEY_CELL = CELLS.register("prismarine_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_PRISMARINE_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> CHORUS_HONEY_CELL = CELLS.register("chorus_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_CHORUS_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> NETHERITE_HONEY_CELL = CELLS.register("netherite_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_NETHERITE_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> EXP_HONEY_CELL = CELLS.register("exp_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_EXP_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> ENDER_PEARL_HONEY_CELL = CELLS.register("ender_pearl_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_ENDER_PEARL_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
                
    public static final RegistryObject<Item> AMETHYST_HONEY_CELL = CELLS.register("amethyst_honey_cell", 
        	() -> new ItemCell(FluidInit.SOURCE_AMETHYST_HONEY, new Item.Properties().craftRemainder(EMPTY_CELL.get()).stacksTo(1)));
	
    
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
    	CELLS.register(eventBus);
    }
    
    public static Collection<RegistryObject<Item>> getHoneycombItems() {
    	return HONEYCOMBS.getEntries();
    }
    
    public static Collection<RegistryObject<Item>> getCellItems() {
    	return CELLS.getEntries();
    }
	
}
