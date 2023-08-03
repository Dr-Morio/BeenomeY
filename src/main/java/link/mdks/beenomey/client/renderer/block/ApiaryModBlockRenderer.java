package link.mdks.beenomey.client.renderer.block;

import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import link.mdks.beenomey.client.model.block.ApiaryModBlockModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ApiaryModBlockRenderer extends GeoBlockRenderer<ApiaryModBlockEntity>{

	public ApiaryModBlockRenderer() {
		super(new ApiaryModBlockModel());
	}

}
