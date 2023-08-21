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
    
    public static final RegistryObject<FluidType> INFERNO_HONEY_FLUID_TYPE = registerHot("inferno_honey_fluid",
    		FluidResourceProvider.INFERNO_HONEY_TINT_COLOR, FluidResourceProvider.INFERNO_HONEY_FOG_COLOR,
            FluidType.Properties.create().lightLevel(2).density(155).viscosity(5).sound(SoundAction.get("drink"),
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
