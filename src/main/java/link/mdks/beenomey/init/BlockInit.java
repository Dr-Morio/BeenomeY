package link.mdks.beenomey.init;

import com.google.common.base.Supplier;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.ApiaryModBlock;
import link.mdks.beenomey.apiculture.blocks.BeehiveBlock;
import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import link.mdks.beenomey.util.BeeType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

	public BlockInit() {}

	public static final DeferredRegister<Block> HIVE_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BeenomeY.MODID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BeenomeY.MODID);

	/* Hive Blocks */
	
	public static final RegistryObject<Block> OAKWOOD_BEEHIVE_BLOCK = registerHiveBlock("oakwood_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.FOREST));
	
	public static final RegistryObject<Block> BIRCHWOOD_BEEHIVE_BLOCK = registerHiveBlock("birchwood_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.FOREST));
	
	public static final RegistryObject<Block> SAND_BEEHIVE_BLOCK = registerHiveBlock("sand_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.STONE).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.SAND));
	
	public static final RegistryObject<Block> STONE_BEEHIVE_BLOCK = registerHiveBlock("stone_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.STONE).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.STONE));
	
	public static final RegistryObject<Block> JUNGLEWOOD_BEEHIVE_BLOCK = registerHiveBlock("junglewood_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.FOREST));
	
	public static final RegistryObject<Block> ICE_BEEHIVE_BLOCK = registerHiveBlock("ice_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.ICE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.ICE));
	
	public static final RegistryObject<Block> ENDER_BEEHIVE_BLOCK = registerHiveBlock("ender_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.STONE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.ENDER));
	
	public static final RegistryObject<Block> NETHER_BEEHIVE_BLOCK = registerHiveBlock("nether_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.STONE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.NETHER));
	
	public static final RegistryObject<Block> WATER_BEEHIVE_BLOCK = registerHiveBlock("water_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.STONE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.WATER));
	
		/* Blocks */
	
	public static final RegistryObject<ApiaryModBlock> apiary_mod_block = BLOCKS.register("apiary_mod_block", 
			() -> new ApiaryModBlock());
			
	
	private static <T extends Block> RegistryObject<T> registerHiveBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = HIVE_BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	
	@SuppressWarnings("unused")
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	
    public static void register(IEventBus eventBus) {
    	HIVE_BLOCKS.register(eventBus);
    	BLOCKS.register(eventBus);
    }
	
}
