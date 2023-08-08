package link.mdks.beenomey.datagen;

import link.mdks.beenomey.BeenomeY;
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
		//simpleItem(ItemInit.WOODEN_SCOOP);
	}
	
	@SuppressWarnings("unused")
	private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(),
				new ResourceLocation("item/generated")).texture("layer0", 
						new ResourceLocation(BeenomeY.MODID, "item/" + item.getId().getPath()));
	}

	@SuppressWarnings("unused")
	private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(), 
				new ResourceLocation("item/handheld")).texture("layer0",
						new ResourceLocation(BeenomeY.MODID, "item/" + item.getId().getPath()));
	}
}
