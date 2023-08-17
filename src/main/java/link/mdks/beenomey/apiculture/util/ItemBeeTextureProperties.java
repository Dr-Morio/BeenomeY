package link.mdks.beenomey.util;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.init.BeeInit;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ItemBeeTextureProperties {

    public static void addCustomTextureVariantPropertie(){
        setTextureVariant(BeeInit.COMMON_BEE.get());
        setTextureVariant(BeeInit.PRINCESS_BEE.get());

    }
	
    private static void setTextureVariant(Item item) {
        ItemProperties.register(item, new ResourceLocation(BeenomeY.MODID,"texture_variant"), (stack, level, living, id) -> {
            // Prevent errors that occour on gamestart. (no loaded static enum)
            if (stack.getTag().getString("MainType") == "") {
                return 1.0f;
            } else {
                return BeeType.valueOf(stack.getTag().getString("MainType")).textureTypeValue;
            }
        });
    }
    
}
