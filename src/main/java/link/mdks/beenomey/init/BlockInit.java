package link.mdks.beenomey.init;

import com.google.common.base.Supplier;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.ApiaryModBlock;
import link.mdks.beenomey.apiculture.blocks.BeehiveBlock;
import link.mdks.beenomey.apiculture.blocks.BreederBlock;
import link.mdks.beenomey.apiculture.fluids.FluidResourceProvider;
import link.mdks.beenomey.apiculture.util.BeeType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
	
	public static final RegistryObject<Block> DESERT_BEEHIVE_BLOCK = registerHiveBlock("desert_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.STONE).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.DESERT));
	
	public static final RegistryObject<Block> ROCK_BEEHIVE_BLOCK = registerHiveBlock("rock_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.STONE).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.ROCK));
	
	public static final RegistryObject<Block> JUNGLEWOOD_BEEHIVE_BLOCK = registerHiveBlock("junglewood_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.FOREST));
	
	public static final RegistryObject<Block> FROZEN_BEEHIVE_BLOCK = registerHiveBlock("frozen_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.ICE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.FROZEN));
	
	public static final RegistryObject<Block> VOID_BEEHIVE_BLOCK = registerHiveBlock("void_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.STONE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.VOID));
	
	public static final RegistryObject<Block> INFERNO_BEEHIVE_BLOCK = registerHiveBlock("inferno_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.STONE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.INFERNO));
	
	public static final RegistryObject<Block> OCEAN_BEEHIVE_BLOCK = registerHiveBlock("ocean_beehive_block",
			() -> new BeehiveBlock(Properties.of(Material.STONE).sound(SoundType.WOOD).strength(1f, 3f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false), BeeType.OCEAN));
	
		/* Blocks */
	
	public static final RegistryObject<ApiaryModBlock> APIARY_MOD_BLOCK = BLOCKS.register("apiary_mod_block", 
			() -> new ApiaryModBlock());
	
	public static final RegistryObject<BreederBlock> BREEDER_BLOCK = BLOCKS.register("breeder_block", 
			() -> new BreederBlock());
			
	/* Liquid Blocks*/

//    public static final RegistryObject<LiquidBlock> HONEY_FLUID_BLOCK = BLOCKS.register("honey_fluid_block",
//            () -> new LiquidBlock(FluidInit.SOURCE_HONEY, BlockBehaviour.Properties.of(Material.WATER).jumpFactor(0.3F).noCollission().speedFactor(0.3F).friction(1.0f).strength(100.0F)));
//    public static final RegistryObject<LiquidBlock> FROZEN_HONEY_FLUID_BLOCK = BLOCKS.register("frozen_honey_fluid_block",
//            () -> new LiquidBlock(FluidInit.SOURCE_FROZEN_HONEY, BlockBehaviour.Properties.of(Material.WATER).jumpFactor(0.3F).noCollission().speedFactor(0.3F).friction(1.0f).strength(100.0F)));
//    public static final RegistryObject<LiquidBlock> INFERNO_HONEY_FLUID_BLOCK = BLOCKS.register("inferno_honey_fluid_block",
//            () -> new LiquidBlock(FluidInit.SOURCE_INFERNO_HONEY, BlockBehaviour.Properties.of(Material.LAVA).jumpFactor(0.3F).noCollission().speedFactor(0.3F).friction(1.0f).strength(100.0F)));

	
//    public static final RegistryObject<LiquidBlock> HONEY = BLOCKS.register("honey",
//            () -> new LiquidBlock(FluidInit.HONEY, BlockBehaviour.Properties.copy(Blocks.WATER)
//                    .noCollission()
//                    .strength(100.0F)
//                    .noLootTable()
//                    //.color(FluidInit.MATERIAL_HONEY.getColor())
//                    //.color(HoneyFluid.getTintColor())
//                    .speedFactor(0.3F)
//                    .jumpFactor(0.3F)
//                    .friction(1.0f)
//            		));


	
	
	
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
