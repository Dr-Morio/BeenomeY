package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.fluids.BaseFluidType;
import link.mdks.beenomey.apiculture.fluids.HoneyFluid;
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
    
    public static final RegistryObject<FluidType> HONEY_FLUID_TYPE = register("honey_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    
    //Register Methods
    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(HoneyFluid.STILL, HoneyFluid.FLOWING, HoneyFluid.OVERLAY,
                HoneyFluid.getTintColor(), HoneyFluid.getFogColor(), properties));
    }
    
    //EvbentBus Register Method
    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
