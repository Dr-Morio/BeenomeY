package link.mdks.beenomey.client.model.block;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BreederBlockModel extends GeoModel<BreederBlockEntity>{

	@Override
	public ResourceLocation getModelResource(BreederBlockEntity animatable) {
		return new ResourceLocation(BeenomeY.MODID, "geo/block/breeder_block.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BreederBlockEntity animatable) {
		return new ResourceLocation(BeenomeY.MODID, "textures/block/breeder_block.png");
	}

	@Override
	public ResourceLocation getAnimationResource(BreederBlockEntity animatable) {
		return new ResourceLocation(BeenomeY.MODID, "animations/block/breeder_block.animation.json");
	}

}
