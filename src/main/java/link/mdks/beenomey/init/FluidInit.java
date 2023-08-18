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


    public static final ForgeFlowingFluid.Properties HONEY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypeInit.HONEY_FLUID_TYPE, SOURCE_HONEY, FLOWING_HONEY)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(BlockInit.HONEY_FLUID_BLOCK)
            .bucket(ItemInit.HONEY_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
    
}
  