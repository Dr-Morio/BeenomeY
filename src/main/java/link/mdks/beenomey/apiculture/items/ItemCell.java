package link.mdks.beenomey.apiculture.items;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.RegistryObject;

public class ItemCell extends BucketItem{

	public ItemCell(RegistryObject<FlowingFluid> sourceHoney, Properties pProperties) {
		super(sourceHoney, pProperties);
	}


	@Override
	public Fluid getFluid() {
		// TODO Auto-generated method stub
		return super.getFluid();
	}
	
}
