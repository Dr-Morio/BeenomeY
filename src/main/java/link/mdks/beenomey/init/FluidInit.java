package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;


@EventBusSubscriber(modid = BeenomeY.MODID, bus = EventBusSubscriber.Bus.MOD)
public class FluidInit {
	
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,  BeenomeY.MODID);
    
    public static final RegistryObject<FlowingFluid> SOURCE_HONEY = FLUIDS.register("honey_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.HONEY_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_HONEY = FLUIDS.register("flowing_honey",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.HONEY_FLUID_PROPERTIES));

    
    public static final RegistryObject<FlowingFluid> SOURCE_FROZEN_HONEY = FLUIDS.register("frozen_honey_fluid",
    		() -> new ForgeFlowingFluid.Source(FluidInit.FROZEN_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_FROZEN_HONEY = FLUIDS.register("frozen_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.FROZEN_HONEY_FLUID_PROPERTIES));
	            

//	public static final RegistryObject<FlowingFluid> SOURCE_OCEAN_HONEY = FLUIDS.register("ocean_honey_fluid",
//	    () -> new ForgeFlowingFluid.Source(FluidInit.OCEAN_HONEY_FLUID_PROPERTIES));
//	public static final RegistryObject<FlowingFluid> FLOWING_OCEAN_HONEY = FLUIDS.register("ocean_flowing_honey",
//	    () -> new ForgeFlowingFluid.Flowing(FluidInit.OCEAN_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_DESERT_HONEY = FLUIDS.register("desert_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.DESERT_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_DESERT_HONEY = FLUIDS.register("desert_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.DESERT_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_ROCK_HONEY = FLUIDS.register("rock_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.ROCK_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_ROCK_HONEY = FLUIDS.register("rock_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.ROCK_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_INFERNO_HONEY = FLUIDS.register("inferno_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.INFERNO_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_INFERNO_HONEY = FLUIDS.register("inferno_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.INFERNO_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_VOID_HONEY = FLUIDS.register("void_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.VOID_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_VOID_HONEY = FLUIDS.register("void_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.VOID_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_COAL_HONEY = FLUIDS.register("coal_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.COAL_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_COAL_HONEY = FLUIDS.register("coal_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.COAL_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_GUNPOWDER_HONEY = FLUIDS.register("gunpowder_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.GUNPOWDER_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_GUNPOWDER_HONEY = FLUIDS.register("gunpowder_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.GUNPOWDER_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_FLINT_HONEY = FLUIDS.register("flint_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.FLINT_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_FLINT_HONEY = FLUIDS.register("flint_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.FLINT_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_SLIME_HONEY = FLUIDS.register("slime_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.SLIME_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_SLIME_HONEY = FLUIDS.register("slime_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.SLIME_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_SUGAR_HONEY = FLUIDS.register("sugar_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.SUGAR_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_SUGAR_HONEY = FLUIDS.register("sugar_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.SUGAR_HONEY_FLUID_PROPERTIES));
	            
	public static final RegistryObject<FlowingFluid> SOURCE_GLASS_HONEY = FLUIDS.register("glass_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.GLASS_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_GLASS_HONEY = FLUIDS.register("glass_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.GLASS_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_CLAY_HONEY = FLUIDS.register("clay_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.CLAY_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_CLAY_HONEY = FLUIDS.register("clay_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.CLAY_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_STRING_HONEY = FLUIDS.register("string_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.STRING_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_STRING_HONEY = FLUIDS.register("string_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.STRING_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_NETHER_WART_HONEY = FLUIDS.register("nether_wart_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.NETHER_WART_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_NETHER_WART_HONEY = FLUIDS.register("nether_wart_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.NETHER_WART_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_SOUL_SAND_HONEY = FLUIDS.register("soul_sand_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.SOUL_SAND_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_SOUL_SAND_HONEY = FLUIDS.register("soul_sand_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.SOUL_SAND_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_MUSHROOM_HONEY = FLUIDS.register("mushroom_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.MUSHROOM_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_MUSHROOM_HONEY = FLUIDS.register("mushroom_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.MUSHROOM_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_LAVA_HONEY = FLUIDS.register("lava_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.LAVA_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_LAVA_HONEY = FLUIDS.register("lava_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.LAVA_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_LAPIS_LAZULI_HONEY = FLUIDS.register("lapis_lazuli_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.LAPIS_LAZULI_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_LAPIS_LAZULI_HONEY = FLUIDS.register("lapis_lazuli_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.LAPIS_LAZULI_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_REDSTONE_HONEY = FLUIDS.register("redstone_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.REDSTONE_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_REDSTONE_HONEY = FLUIDS.register("redstone_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.REDSTONE_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_IRON_HONEY = FLUIDS.register("iron_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.IRON_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_IRON_HONEY = FLUIDS.register("iron_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.IRON_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_GOLD_HONEY = FLUIDS.register("gold_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.GOLD_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_GOLD_HONEY = FLUIDS.register("gold_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.GOLD_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_COPPER_HONEY = FLUIDS.register("copper_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.COPPER_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_COPPER_HONEY = FLUIDS.register("copper_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.COPPER_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_DIAMOND_HONEY = FLUIDS.register("diamond_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.DIAMOND_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_DIAMOND_HONEY = FLUIDS.register("diamond_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.DIAMOND_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_OBSIDIAN_HONEY = FLUIDS.register("obsidian_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.OBSIDIAN_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_OBSIDIAN_HONEY = FLUIDS.register("obsidian_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.OBSIDIAN_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_GLOWSTONE_HONEY = FLUIDS.register("glowstone_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.GLOWSTONE_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_GLOWSTONE_HONEY = FLUIDS.register("glowstone_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.GLOWSTONE_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_BLAZE_HONEY = FLUIDS.register("blaze_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.BLAZE_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_BLAZE_HONEY = FLUIDS.register("blaze_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.BLAZE_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_EMERALD_HONEY = FLUIDS.register("emerald_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.EMERALD_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_EMERALD_HONEY = FLUIDS.register("emerald_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.EMERALD_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_NETEHR_STAR_HONEY = FLUIDS.register("netehr_star_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.NETEHR_STAR_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_NETEHR_STAR_HONEY = FLUIDS.register("netehr_star_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.NETEHR_STAR_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_NETHER_QUARZ_HONEY = FLUIDS.register("nether_quarz_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.NETHER_QUARZ_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_NETHER_QUARZ_HONEY = FLUIDS.register("nether_quarz_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.NETHER_QUARZ_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_PRISMARINE_HONEY = FLUIDS.register("prismarine_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.PRISMARINE_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_PRISMARINE_HONEY = FLUIDS.register("prismarine_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.PRISMARINE_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_CHORUS_HONEY = FLUIDS.register("chorus_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.CHORUS_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_CHORUS_HONEY = FLUIDS.register("chorus_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.CHORUS_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_NETHERITE_HONEY = FLUIDS.register("netherite_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.NETHERITE_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_NETHERITE_HONEY = FLUIDS.register("netherite_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.NETHERITE_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_EXP_HONEY = FLUIDS.register("exp_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.EXP_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_EXP_HONEY = FLUIDS.register("exp_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.EXP_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_ENDER_PEARL_HONEY = FLUIDS.register("ender_pearl_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.ENDER_PEARL_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_ENDER_PEARL_HONEY = FLUIDS.register("ender_pearl_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.ENDER_PEARL_HONEY_FLUID_PROPERTIES));
	            

	public static final RegistryObject<FlowingFluid> SOURCE_AMETHYST_HONEY = FLUIDS.register("amethyst_honey_fluid",
	    () -> new ForgeFlowingFluid.Source(FluidInit.AMETHYST_HONEY_FLUID_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_AMETHYST_HONEY = FLUIDS.register("amethyst_flowing_honey",
	    () -> new ForgeFlowingFluid.Flowing(FluidInit.AMETHYST_HONEY_FLUID_PROPERTIES));
    
    
    

    public static final ForgeFlowingFluid.Properties HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.HONEY_FLUID_TYPE, SOURCE_HONEY, FLOWING_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.HONEY_CELL);
    
    public static final ForgeFlowingFluid.Properties FROZEN_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.FROZEN_HONEY_FLUID_TYPE, SOURCE_FROZEN_HONEY, FLOWING_FROZEN_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.FROZEN_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.FROZEN_HONEY_CELL);
                

//    public static final ForgeFlowingFluid.Properties OCEAN_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
//            FluidTypeInit.OCEAN_HONEY_FLUID_TYPE, SOURCE_OCEAN_HONEY, FLOWING_OCEAN_HONEY)
//            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.OCEAN_HONEY_FLUID_BLOCK)*/
//            .bucket(ItemInit.OCEAN_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties DESERT_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.DESERT_HONEY_FLUID_TYPE, SOURCE_DESERT_HONEY, FLOWING_DESERT_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.DESERT_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.DESERT_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties ROCK_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.ROCK_HONEY_FLUID_TYPE, SOURCE_ROCK_HONEY, FLOWING_ROCK_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.ROCK_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.ROCK_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties INFERNO_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.INFERNO_HONEY_FLUID_TYPE, SOURCE_INFERNO_HONEY, FLOWING_INFERNO_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.INFERNO_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.INFERNO_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties VOID_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.VOID_HONEY_FLUID_TYPE, SOURCE_VOID_HONEY, FLOWING_VOID_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.VOID_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.VOID_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties COAL_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.COAL_HONEY_FLUID_TYPE, SOURCE_COAL_HONEY, FLOWING_COAL_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.COAL_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.COAL_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties GUNPOWDER_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.GUNPOWDER_HONEY_FLUID_TYPE, SOURCE_GUNPOWDER_HONEY, FLOWING_GUNPOWDER_HONEY)
    		.slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.GUNPOWDER_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.GUNPOWDER_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties FLINT_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.FLINT_HONEY_FLUID_TYPE, SOURCE_FLINT_HONEY, FLOWING_FLINT_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.FLINT_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.FLINT_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties SLIME_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.SLIME_HONEY_FLUID_TYPE, SOURCE_SLIME_HONEY, FLOWING_SLIME_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.SLIME_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.SLIME_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties SUGAR_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.SUGAR_HONEY_FLUID_TYPE, SOURCE_SUGAR_HONEY, FLOWING_SUGAR_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.SUGAR_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.SUGAR_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties GLASS_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.GLASS_HONEY_FLUID_TYPE, SOURCE_GLASS_HONEY, FLOWING_GLASS_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.GLASS_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.GLASS_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties CLAY_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.CLAY_HONEY_FLUID_TYPE, SOURCE_CLAY_HONEY, FLOWING_CLAY_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.CLAY_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.CLAY_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties STRING_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.STRING_HONEY_FLUID_TYPE, SOURCE_STRING_HONEY, FLOWING_STRING_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.STRING_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.STRING_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties NETHER_WART_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.NETHER_WART_HONEY_FLUID_TYPE, SOURCE_NETHER_WART_HONEY, FLOWING_NETHER_WART_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.NETHER_WART_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.NETHER_WART_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties SOUL_SAND_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.SOUL_SAND_HONEY_FLUID_TYPE, SOURCE_SOUL_SAND_HONEY, FLOWING_SOUL_SAND_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.SOUL_SAND_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.SOUL_SAND_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties MUSHROOM_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.MUSHROOM_HONEY_FLUID_TYPE, SOURCE_MUSHROOM_HONEY, FLOWING_MUSHROOM_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.MUSHROOM_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.MUSHROOM_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties LAVA_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.LAVA_HONEY_FLUID_TYPE, SOURCE_LAVA_HONEY, FLOWING_LAVA_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.LAVA_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.LAVA_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties LAPIS_LAZULI_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.LAPIS_LAZULI_HONEY_FLUID_TYPE, SOURCE_LAPIS_LAZULI_HONEY, FLOWING_LAPIS_LAZULI_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.LAPIS_LAZUL_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.LAPIS_LAZULI_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties REDSTONE_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.REDSTONE_HONEY_FLUID_TYPE, SOURCE_REDSTONE_HONEY, FLOWING_REDSTONE_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.REDSTONE_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.REDSTONE_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties IRON_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.IRON_HONEY_FLUID_TYPE, SOURCE_IRON_HONEY, FLOWING_IRON_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.IRON_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.IRON_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties GOLD_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.GOLD_HONEY_FLUID_TYPE, SOURCE_GOLD_HONEY, FLOWING_GOLD_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.GOLD_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.GOLD_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties COPPER_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.COPPER_HONEY_FLUID_TYPE, SOURCE_COPPER_HONEY, FLOWING_COPPER_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.COPPER_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.COPPER_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties DIAMOND_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.DIAMOND_HONEY_FLUID_TYPE, SOURCE_DIAMOND_HONEY, FLOWING_DIAMOND_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.DIAMOND_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.DIAMOND_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties OBSIDIAN_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.OBSIDIAN_HONEY_FLUID_TYPE, SOURCE_OBSIDIAN_HONEY, FLOWING_OBSIDIAN_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.OBSIDIAN_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.OBSIDIAN_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties GLOWSTONE_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.GLOWSTONE_HONEY_FLUID_TYPE, SOURCE_GLOWSTONE_HONEY, FLOWING_GLOWSTONE_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.GLOWSTONE_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.GLOWSTONE_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties BLAZE_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.BLAZE_HONEY_FLUID_TYPE, SOURCE_BLAZE_HONEY, FLOWING_BLAZE_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.BLAZE_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.BLAZE_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties EMERALD_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.EMERALD_HONEY_FLUID_TYPE, SOURCE_EMERALD_HONEY, FLOWING_EMERALD_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.EMERALD_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.EMERALD_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties NETEHR_STAR_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.NETEHR_STAR_HONEY_FLUID_TYPE, SOURCE_NETEHR_STAR_HONEY, FLOWING_NETEHR_STAR_HONEY)
    .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.NETEHR_STAR_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.NETEHR_STAR_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties NETHER_QUARZ_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.NETHER_QUARZ_HONEY_FLUID_TYPE, SOURCE_NETHER_QUARZ_HONEY, FLOWING_NETHER_QUARZ_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.NETHER_QUARZ_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.NETHER_QUARZ_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties PRISMARINE_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.PRISMARINE_HONEY_FLUID_TYPE, SOURCE_PRISMARINE_HONEY, FLOWING_PRISMARINE_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.PRISMARINE_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.PRISMARINE_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties CHORUS_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.CHORUS_HONEY_FLUID_TYPE, SOURCE_CHORUS_HONEY, FLOWING_CHORUS_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.CHORUS_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.CHORUS_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties NETHERITE_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.NETHERITE_HONEY_FLUID_TYPE, SOURCE_NETHERITE_HONEY, FLOWING_NETHERITE_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.NETHERITE_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.NETHERITE_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties EXP_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.EXP_HONEY_FLUID_TYPE, SOURCE_EXP_HONEY, FLOWING_EXP_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.EXP_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.EXP_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties ENDER_PEARL_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.ENDER_PEARL_HONEY_FLUID_TYPE, SOURCE_ENDER_PEARL_HONEY, FLOWING_ENDER_PEARL_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.ENDER_PEARL_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.ENDER_PEARL_HONEY_CELL);
                

    public static final ForgeFlowingFluid.Properties AMETHYST_HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.AMETHYST_HONEY_FLUID_TYPE, SOURCE_AMETHYST_HONEY, FLOWING_AMETHYST_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).tickRate(15)/*.block(BlockInit.AMETHYST_HONEY_FLUID_BLOCK)*/
            .bucket(ItemInit.AMETHYST_HONEY_CELL);



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
    
}
  