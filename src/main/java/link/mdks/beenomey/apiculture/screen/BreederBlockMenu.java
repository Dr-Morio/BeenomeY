package link.mdks.beenomey.apiculture.screen;

import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import link.mdks.beenomey.apiculture.screen.slots.BeeSlot;
import link.mdks.beenomey.init.BlockInit;
import link.mdks.beenomey.init.MenuTypeInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class BreederBlockMenu extends AbstractContainerMenu{
	
	/* Fields */
	
	public final BreederBlockEntity blockEntity;
	private final Level level;
	private final ContainerData data;
	private final static int breederSlots = 6;
	
	@SuppressWarnings("unused")
	private int screenHightOffset = BreederBlockScreen.getHightOffset();

	/* Constructor */
	
	public BreederBlockMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));// how many variables are stored inside container -> progress / maxProgress

	}
	
	public BreederBlockMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
		super(MenuTypeInit.BREEDER_BLOCK_MENU.get(), id);
		checkContainerSize(inv, breederSlots); //Size of Container (Slots)
		blockEntity = (BreederBlockEntity) entity;
		this.level = inv.player.level;
		this.data = data;
		
		addPlayerInventory(inv);
		addPlayerHotbar(inv);
		

		this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
			//Bee Slots
			this.addSlot(new BeeSlot(handler, 0, 46, -6)); // Slot yPosition -21 (Half of additional image Size)
			this.addSlot(new BeeSlot(handler, 1, 114, -6)); // Slot yPosition -21 (Half of additional image Size)
			this.addSlot(new BeeSlot(handler, 2, 46, 50)); // Slot yPosition -21 (Half of additional image Size)
			this.addSlot(new BeeSlot(handler, 3, 114, 50)); // Slot yPosition -21 (Half of additional image Size)
			
			//Result Slot
			this.addSlot(new SlotItemHandler(handler, 4, 80, 22)); // Slot yPosition -21 (Half of additional image Size)
			
			//Hones Slot
			this.addSlot(new SlotItemHandler(handler, 5, 155, 76)); // Slot yPosition -21 (Half of additional image Size)

		});
		
		addDataSlots(data);
		
	}
	
	/* Helper Functions */
	
	public static int getBreederSlots() {
		return breederSlots;
	}
	
	public boolean isCrafting() {
		return data.get(0) > 0;
	}

	
	public int getScaledProgress() {
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);
		int progressArrowSize = 64; // This is the height in pixels of the loading bar
		
		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}
	
	/* Inventory System */

	// CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = breederSlots;  // must be the number of slots !

	
	@Override
	public ItemStack quickMoveStack(Player pPlayer, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(pPlayer, sourceStack);
        return copyOfSourceStack;
    }
	
	

	@Override
	public boolean stillValid(Player player) {
		return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
				player, BlockInit.BREEDER_BLOCK.get());
	}
	
	private void addPlayerInventory(Inventory playerInventory) {
		for (int i = 0; i < 3; i++)  {
			for (int j = 0; j < 9; j++) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8+ j * 18, 105 + i * 18));
			}
		
		}

	}

	private void addPlayerHotbar(Inventory playerInventory) {
		for (int i = 0; i < 9; i++) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 163));
		}
	}

	/* used for Energy System*/
	public BreederBlockEntity getBlockEntity() {
		return this.blockEntity;
	}

}
