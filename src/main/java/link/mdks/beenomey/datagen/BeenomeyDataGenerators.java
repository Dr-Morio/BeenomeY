package link.mdks.beenomey.datagen;

import link.mdks.beenomey.BeenomeY;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BeenomeY.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeenomeyDataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHeler = event.getExistingFileHelper();
		
		generator.addProvider(true, new BeenomeyRecipeProvider(packOutput));
		//generator.addProvider(true, new BeenomeyLootTableProvider().create(packOutput));
		//generator.addProvider(true, new BeenomeyBlockstateProvider(packOutput, existingFileHeler));
		//generator.addProvider(true, new BeenomeyItemModelProvider(packOutput, existingFileHeler));

	}
	
}
