package link.mdks.beenomey.sceen.slots;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CombSlot extends SlotItemHandler{

	public CombSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
		return false;
	}

}
