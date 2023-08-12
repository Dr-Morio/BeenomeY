package link.mdks.beenomey.client.renderer.block;

import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import link.mdks.beenomey.client.model.block.BreederBlockModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BreederBlockRenderer extends GeoBlockRenderer<BreederBlockEntity>{

	public BreederBlockRenderer() {
		super(new BreederBlockModel());
	}

}
