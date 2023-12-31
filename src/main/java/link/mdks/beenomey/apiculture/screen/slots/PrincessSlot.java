package link.mdks.beenomey.apiculture.screen.slots;

import org.jetbrains.annotations.NotNull;

import link.mdks.beenomey.apiculture.items.ItemPrincess;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class PrincessSlot extends SlotItemHandler{

	public PrincessSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
	
		if(stack.getItem().getClass() == ItemPrincess.class) {
			return true;
		} else {
			return false;
		}
	
	}
	
}
