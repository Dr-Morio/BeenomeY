package link.mdks.beenomey.util;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.BeeInit;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class beeItemProperties {

	public static void addCustomTextureVariant() {
		setTextureVariant(BeeInit.COMMON_BEE.get());
	}
	
	public static void setTextureVariant(Item item) {
		ItemProperties.register(item, new ResourceLocation(BeenomeY.MODID, "typeTexture"), (stack, level, living, id) -> {
            // Prevent errors that occour on gamestart. (no loaded static enum)
            if (stack.getTag().getString("MainType") == "") {
                return 1.0f;
            } else {
                return BeeType.valueOf(stack.getTag().getString("MainType")).textureTypeValue;
            }
        });
	}
	
}
