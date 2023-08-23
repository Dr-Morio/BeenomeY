package link.mdks.beenomey.datagen;

import java.util.Collection;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.init.BeeInit;
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
		
		/* Register Buckets */
		Collection<RegistryObject<Item>> buckets = ItemInit.getBucketItems();
		for(RegistryObject<Item> bucket: buckets) {
			simpleBucket(bucket);
		}
		
		/* Register Bees */
		simpleBee(BeeInit.COMMON_BEE, "forest",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "frozen",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "ocean",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "desert",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "rock",		    "bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "inferno",	    "bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "void",		    "bee_base_0", "bee_body_color_3");
		simpleBee(BeeInit.COMMON_BEE, "coal",		    "bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "gunpowder",		"bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "flint",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "slime",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "sugar",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "glass",	        "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "clay",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "string",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "nether_wart",	"bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "soul_sand",		"bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "mushroom",		"bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "lava",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "lapis_lazuli",	"bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "redstone",		"bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "iron",		    "bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "gold",		    "bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "copper",		    "bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "diamond",		"bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "obsidian",		"bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "glowstone",		"bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "blaze",		    "bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "emerald",		"bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "nether_star",	"bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "nether_quarz",	"bee_base_0", "bee_body_color_1");
		simpleBee(BeeInit.COMMON_BEE, "prismarine",		"bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "chorius",		"bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "netherite",		"bee_base_0", "bee_body_color_2");
		simpleBee(BeeInit.COMMON_BEE, "exp",		    "bee_base_0", "bee_body_color_0");
		simpleBee(BeeInit.COMMON_BEE, "ender_pearl",	"bee_base_0", "bee_body_color_3");
		simpleBee(BeeInit.COMMON_BEE, "amethyst",		"bee_base_0", "bee_body_color_0");
		

		
		/* Register Princess */
		simplePrincess(BeeInit.COMMON_BEE, "forest",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "frozen",        "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "ocean",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "desert",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "rock",		    "bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "inferno",	    "bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "void",		    "bee_base_0", "bee_body_color_3", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "coal",		    "bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "gunpowder",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "flint",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "slime",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "sugar",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "glass",	        "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "clay",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "string",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "nether_wart",	"bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "soul_sand",		"bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "mushroom",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "lava",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "lapis_lazuli",	"bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "redstone",		"bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "iron",		    "bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "gold",		    "bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "copper",		"bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "diamond",		"bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "obsidian",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "glowstone",		"bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "blaze",		    "bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "emerald",		"bee_base_0", "bee_body_color_2", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "nether_star",	"bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "nether_quarz",	"bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "prismarine",	"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "chorius",		"bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "netherite",		"bee_base_0", "bee_body_color_1", "princess_inferno");
		simplePrincess(BeeInit.COMMON_BEE, "exp",		    "bee_base_0", "bee_body_color_0", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "ender_pearl",	"bee_base_0", "bee_body_color_3", "princess");
		simplePrincess(BeeInit.COMMON_BEE, "amethyst",		"bee_base_0", "bee_body_color_0", "princess");
		
	}
	
	private ItemModelBuilder simpleBee(RegistryObject<Item> item, String type, String base, String bodyColor) {
		return withExistingParent("item/bee/" + type + "_bee",
				new ResourceLocation("item/generated"))
				.texture("layer0", new ResourceLocation(BeenomeY.MODID, "item/bee/body/" + base))
				.texture("layer1", new ResourceLocation(BeenomeY.MODID, "item/bee/body/" + bodyColor))
				.texture("layer2", new ResourceLocation(BeenomeY.MODID, "item/bee/type_frame/" + type));
	}
	
	private ItemModelBuilder simplePrincess(RegistryObject<Item> item, String type, String base, String bodyColor, String crown) {
		return withExistingParent("item/bee/" + type + "_princess",
				new ResourceLocation("item/generated"))
				.texture("layer0", new ResourceLocation(BeenomeY.MODID, "item/bee/body/" + base))
				.texture("layer1", new ResourceLocation(BeenomeY.MODID, "item/bee/body/" + bodyColor))
				.texture("layer2", new ResourceLocation(BeenomeY.MODID, "item/bee/type_frame_princess/" + type))
				.texture("layer3", new ResourceLocation(BeenomeY.MODID, "item/bee/crown/" + crown));
	}
	
	private ItemModelBuilder simpleComb(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(),
				new ResourceLocation("item/generated")).texture("layer0", 
						new ResourceLocation(BeenomeY.MODID, "item/comb/" + item.getId().getPath()));
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
