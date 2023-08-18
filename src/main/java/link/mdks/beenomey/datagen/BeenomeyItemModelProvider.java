package link.mdks.beenomey.datagen;

import java.util.Collection;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BeenomeyItemModelProvider extends ItemModelProvider{

	public BeenomeyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, BeenomeY.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		
		/* Register Honeycombs */
		Collection<RegistryObject<Item>> combs = ItemInit.getHoneycombItems();
		for(RegistryObject<Item> comb: combs) {
			simpleComb(comb);
		}
		
		/* Register Structurecombs */
		Collection<RegistryObject<Item>> structurecombs = ItemInit.getStructurecombItems();
		for(RegistryObject<Item> comb: structurecombs) {
			simpleStructureComb(comb);
		}
		
		/* Register Buckets */
		Collection<RegistryObject<Item>> buckets = ItemInit.getBucketItems();
		for(RegistryObject<Item> bucket: buckets) {
			simpleBucket(bucket);
		}
		
	}
	
	private ItemModelBuilder simpleComb(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(),
				new ResourceLocation("item/generated")).texture("layer0", 
						new ResourceLocation(BeenomeY.MODID, "item/combs/" + item.getId().getPath()));
	}

	private ItemModelBuilder simpleStructureComb(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(),
				new ResourceLocation("item/generated")).texture("layer0", 
						new ResourceLocation(BeenomeY.MODID, "item/structurecombs/" + item.getId().getPath()));
	}
	
	private ItemModelBuilder simpleBucket(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(),
				new ResourceLocation("item/generated")).texture("layer0", 
						new ResourceLocation(BeenomeY.MODID, "item/bucket/" + item.getId().getPath()));
	}
	
	@SuppressWarnings("unused")
	private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(), 
				new ResourceLocation("item/handheld")).texture("layer0",
						new ResourceLocation(BeenomeY.MODID, "item/" + item.getId().getPath()));
	}
}
