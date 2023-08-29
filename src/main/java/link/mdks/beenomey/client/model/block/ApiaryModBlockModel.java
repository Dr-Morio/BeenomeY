package link.mdks.beenomey.client.model.block;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ApiaryModBlockModel extends GeoModel<ApiaryModBlockEntity>{

    @Override
    public ResourceLocation getModelResource(ApiaryModBlockEntity animatable) {
        return new ResourceLocation(BeenomeY.MODID, "geo/block/apiary_mod_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ApiaryModBlockEntity animatable) {
        return new ResourceLocation(BeenomeY.MODID, "textures/block/apiary_mod_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ApiaryModBlockEntity animatable) {
        return new ResourceLocation(BeenomeY.MODID, "animations/block/apiary_mod_block.animation.json");
    }
	
	
	
	
	
	
	
	
	
	/** Version of GeckoLib **/
  //public class ApiaryModBlockModel extends DefaultedBlockGeoModel<ApiaryModBlockEntity>{	
	
	
//	private final ResourceLocation apiary_mod_block_MODEL = buildFormattedModelPath(new ResourceLocation(BeenomeY.MODID, "apiary_mod_block"));
//	private final ResourceLocation apiary_mod_block_TEXTURE = buildFormattedTexturePath(new ResourceLocation(BeenomeY.MODID, "apiary_mod_block"));
//	private final ResourceLocation apiary_mod_block_ANIMATIONS = buildFormattedAnimationPath(new ResourceLocation(BeenomeY.MODID, "apiary_mod_block"));
//	
//	public ApiaryModBlockModel() {
//		super(new ResourceLocation(BeenomeY.MODID, "apiary_mod_block"));
//	}
//	
//	@Override
//	public ResourceLocation getAnimationResource(ApiaryModBlockEntity animatable) {
//		if (animatable.getLevel().isRaining()) {
//			return super.getAnimationResource(animatable);
//		}
//		else {
//			return apiary_mod_block_ANIMATIONS;
//		}
//	}
//	
//	@Override
//	public ResourceLocation getModelResource(ApiaryModBlockEntity animatable) {
//		if (animatable.getLevel().isRaining()) {
//			return super.getModelResource(animatable);
//		}
//		else {
//			return apiary_mod_block_MODEL;
//		}
//	}
//	
//	@Override
//	public ResourceLocation getTextureResource(ApiaryModBlockEntity animatable) {
//		if (animatable.getLevel().isRaining()) {
//			return super.getTextureResource(animatable);
//		}
//		else {
//			return apiary_mod_block_TEXTURE;
//		}
//	}
//	
//	@Override
//	public RenderType getRenderType(ApiaryModBlockEntity animatable, ResourceLocation texture) {
//		return RenderType.entityTranslucent(getTextureResource(animatable));
//	}
	
	
}
