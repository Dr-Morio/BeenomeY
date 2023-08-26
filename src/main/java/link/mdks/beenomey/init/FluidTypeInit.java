package link.mdks.beenomey.init;

import org.joml.Vector3f;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.fluids.BaseFluidType;
import link.mdks.beenomey.apiculture.fluids.FluidResourceProvider;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BeenomeY.MODID, bus = EventBusSubscriber.Bus.MOD)
public class FluidTypeInit {
	
    
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES,  BeenomeY.MODID);
    
    public static final RegistryObject<FluidType> HONEY_FLUID_TYPE = registerNeutral("honey_fluid",
    		FluidResourceProvider.HONEY_TINT_COLOR, FluidResourceProvider.HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> FROZEN_HONEY_FLUID_TYPE = registerNeutral("frozen_honey_fluid",
    		FluidResourceProvider.FROZEN_HONEY_TINT_COLOR, FluidResourceProvider.FROZEN_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> OCEAN_HONEY_FLUID_TYPE = registerNeutral("ocean_honey_fluid",
    		FluidResourceProvider.OCEAN_HONEY_TINT_COLOR, FluidResourceProvider.OCEAN_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> DESERT_HONEY_FLUID_TYPE = registerNeutral("deserthoney_fluid",
        	FluidResourceProvider.DESERT_HONEY_TINT_COLOR, FluidResourceProvider.DESERT_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                SoundEvents.HONEY_DRINK));
                

    public static final RegistryObject<FluidType> ROCK_HONEY_FLUID_TYPE = registerNeutral("rockhoney_fluid",
        	FluidResourceProvider.ROCK_HONEY_TINT_COLOR, FluidResourceProvider.ROCK_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> INFERNO_HONEY_FLUID_TYPE = registerHot("inferno_honey_fluid",
    		FluidResourceProvider.INFERNO_HONEY_TINT_COLOR, FluidResourceProvider.INFERNO_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> VOID_HONEY_FLUID_TYPE = registerNeutral("voidhoney_fluid",
        	FluidResourceProvider.VOID_HONEY_TINT_COLOR, FluidResourceProvider.VOID_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> COAL_HONEY_FLUID_TYPE = registerHot("coal_honey_fluid",
    		FluidResourceProvider.COAL_HONEY_TINT_COLOR, FluidResourceProvider.COAL_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> GUNPOWDER_HONEY_FLUID_TYPE = registerHot("gunpowder_honey_fluid",
    		FluidResourceProvider.GUNPOWDER_HONEY_TINT_COLOR, FluidResourceProvider.GUNPOWDER_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> FLINT_HONEY_FLUID_TYPE = registerHot("flint_honey_fluid",
    		FluidResourceProvider.FLINT_HONEY_TINT_COLOR, FluidResourceProvider.FLINT_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> SLIME_HONEY_FLUID_TYPE = registerHot("slime_honey_fluid",
    		FluidResourceProvider.SLIME_HONEY_TINT_COLOR, FluidResourceProvider.SLIME_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> SUGAR_HONEY_FLUID_TYPE = registerHot("sugar_honey_fluid",
    		FluidResourceProvider.SUGAR_HONEY_TINT_COLOR, FluidResourceProvider.SUGAR_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> GLASS_HONEY_FLUID_TYPE = registerHot("glass_honey_fluid",
    		FluidResourceProvider.GLASS_HONEY_TINT_COLOR, FluidResourceProvider.GLASS_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> CLAY_HONEY_FLUID_TYPE = registerHot("clay_honey_fluid",
    		FluidResourceProvider.CLAY_HONEY_TINT_COLOR, FluidResourceProvider.CLAY_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> STRING_HONEY_FLUID_TYPE = registerHot("string_honey_fluid",
    		FluidResourceProvider.STRING_HONEY_TINT_COLOR, FluidResourceProvider.STRING_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> NETHER_WART_HONEY_FLUID_TYPE = registerHot("nether_wart_honey_fluid",
    		FluidResourceProvider.NETHER_WART_HONEY_TINT_COLOR, FluidResourceProvider.NETHER_WART_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> SOUL_SAND_HONEY_FLUID_TYPE = registerHot("soul_sand_honey_fluid",
    		FluidResourceProvider.SOUL_SAND_HONEY_TINT_COLOR, FluidResourceProvider.SOUL_SAND_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> MUSHROOM_HONEY_FLUID_TYPE = registerHot("mushroom_honey_fluid",
    		FluidResourceProvider.MUSHROOM_HONEY_TINT_COLOR, FluidResourceProvider.MUSHROOM_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    
    public static final RegistryObject<FluidType> LAVA_HONEY_FLUID_TYPE = registerHot("lava_honey_fluid",
    		FluidResourceProvider.LAVA_HONEY_TINT_COLOR, FluidResourceProvider.LAVA_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> LAPIS_LAZULI_HONEY_FLUID_TYPE = registerHot("lapis_lazuli_honey_fluid",
    		FluidResourceProvider.LAPIS_LAZULI_HONEY_TINT_COLOR, FluidResourceProvider.LAPIS_LAZULI_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> REDSTONE_HONEY_FLUID_TYPE = registerHot("redstone_honey_fluid",
    		FluidResourceProvider.REDSTONE_HONEY_TINT_COLOR, FluidResourceProvider.REDSTONE_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> IRON_HONEY_FLUID_TYPE = registerHot("iron_honey_fluid",
    		FluidResourceProvider.IRON_HONEY_TINT_COLOR, FluidResourceProvider.IRON_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> GOLD_HONEY_FLUID_TYPE = registerHot("gold_honey_fluid",
    		FluidResourceProvider.GOLD_HONEY_TINT_COLOR, FluidResourceProvider.GOLD_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> COPPER_HONEY_FLUID_TYPE = registerHot("copper_honey_fluid",
    		FluidResourceProvider.COPPER_HONEY_TINT_COLOR, FluidResourceProvider.COPPER_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> DIAMOND_HONEY_FLUID_TYPE = registerHot("diamond_honey_fluid",
    		FluidResourceProvider.DIAMOND_HONEY_TINT_COLOR, FluidResourceProvider.DIAMOND_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> OBSIDIAN_HONEY_FLUID_TYPE = registerHot("obsidian_honey_fluid",
    		FluidResourceProvider.OBSIDIAN_HONEY_TINT_COLOR, FluidResourceProvider.OBSIDIAN_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> GLOWSTONE_HONEY_FLUID_TYPE = registerHot("glowstone_honey_fluid",
    		FluidResourceProvider.GLOWSTONE_HONEY_TINT_COLOR, FluidResourceProvider.GLOWSTONE_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> BLAZE_HONEY_FLUID_TYPE = registerHot("blaze_honey_fluid",
    		FluidResourceProvider.BLAZE_HONEY_TINT_COLOR, FluidResourceProvider.BLAZE_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> EMERALD_HONEY_FLUID_TYPE = registerHot("emerald_honey_fluid",
    		FluidResourceProvider.EMERALD_HONEY_TINT_COLOR, FluidResourceProvider.EMERALD_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> NETEHR_STAR_HONEY_FLUID_TYPE = registerHot("nether_star_honey_fluid",
    		FluidResourceProvider.NETHER_STAR_HONEY_TINT_COLOR, FluidResourceProvider.NETHER_STAR_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> NETHER_QUARZ_HONEY_FLUID_TYPE = registerHot("nether_quarz_honey_fluid",
    		FluidResourceProvider.NETHER_QUARZ_HONEY_TINT_COLOR, FluidResourceProvider.NETHER_QUARZ_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> PRISMARINE_HONEY_FLUID_TYPE = registerHot("prismarine_honey_fluid",
    		FluidResourceProvider.PRISMARINE_HONEY_TINT_COLOR, FluidResourceProvider.PRISMARINE_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> CHORUS_HONEY_FLUID_TYPE = registerHot("chorus_honey_fluid",
    		FluidResourceProvider.CHORUS_HONEY_TINT_COLOR, FluidResourceProvider.CHORUS_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));                    

    public static final RegistryObject<FluidType> NETHERITE_HONEY_FLUID_TYPE = registerHot("netherite_honey_fluid",
    		FluidResourceProvider.NETHERITE_HONEY_TINT_COLOR, FluidResourceProvider.NETHERITE_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> EXP_HONEY_FLUID_TYPE = registerHot("exp_honey_fluid",
    		FluidResourceProvider.EXP_HONEY_TINT_COLOR, FluidResourceProvider.EXP_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> ENDER_PEARL_HONEY_FLUID_TYPE = registerHot("ender_pearl_honey_fluid",
    		FluidResourceProvider.ENDER_PEARL_HONEY_TINT_COLOR, FluidResourceProvider.ENDER_PEARL_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> AMETHYST_HONEY_FLUID_TYPE = registerHot("amethyst_honey_fluid",
    		FluidResourceProvider.AMETHYST_HONEY_TINT_COLOR, FluidResourceProvider.AMETHYST_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
                    
    
    
    //Register Methods
    private static RegistryObject<FluidType> registerNeutral(String name, int tintColor, Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(FluidResourceProvider.NEUTRAL_STILL, FluidResourceProvider.NEUTRAL_FLOWING, FluidResourceProvider.DEFAULT_OVERLAY,
        		tintColor, fogColor, properties));
    }
    
    private static RegistryObject<FluidType> registerHot(String name, int tintColor, Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(FluidResourceProvider.HOT_STILL, FluidResourceProvider.HOT_FLOWING, FluidResourceProvider.DEFAULT_OVERLAY,
        		tintColor, fogColor, properties));
    }
    
    //EvbentBus Register Method
    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
