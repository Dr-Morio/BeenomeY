package link.mdks.beenomey.apiculture.screen.slots;

import org.jetbrains.annotations.NotNull;

import link.mdks.beenomey.apiculture.items.ItemCell;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CellSlot extends SlotItemHandler{

	public CellSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
	
		if(stack.getItem().getClass() == ItemCell.class) {
			return true;
		} else {
			return false;
		}
	
	}
	
}
