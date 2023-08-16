package link.mdks.beenomey.sceen.slots;

import org.jetbrains.annotations.NotNull;

import link.mdks.beenomey.apiculture.items.ItemBee;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BeeSlot extends SlotItemHandler{

	public BeeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
	
		if(stack.getItem().getClass() == ItemBee.class) {
			return true;
		} else {
			return false;
		}
	
	}
	
}
