package link.mdks.beenomey.apiculture.screen;

import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import link.mdks.beenomey.apiculture.screen.slots.BeeSlot;
import link.mdks.beenomey.apiculture.screen.slots.CombSlot;
import link.mdks.beenomey.apiculture.screen.slots.PrincessSlot;
import link.mdks.beenomey.init.BlockInit;
import link.mdks.beenomey.init.MenuTypeInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class ApiaryModBlockMenu extends AbstractContainerMenu{

	/* Fields */
	public final ApiaryModBlockEntity blockEntity;
	private final Level level;
	private final ContainerData data;
	
	private final static int apiarySlots = 11;
	
	public static int getApiarySlots() {
		return apiarySlots;
	}
	
	
	
	/* Constructor */
	
	public ApiaryModBlockMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));// how many variables are stored inside container -> progress / maxProgress
	}
	
	public ApiaryModBlockMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
		super(MenuTypeInit.APIARY_MOD_BLOCK_MENU.get(), id);
		checkContainerSize(inv, apiarySlots); //Size of Container (Slots)
		blockEntity = (ApiaryModBlockEntity) entity;
		this.level = inv.player.level;
		this.data = data;
		
		addPlayerInventory(inv);
		addPlayerHotbar(inv);
		
		//old
		this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
			//Comp Slots
			this.addSlot(new CombSlot(handler, 0, 28, 22));
			this.addSlot(new CombSlot(handler, 1, 132, 22));
			this.addSlot(new CombSlot(handler, 2, 28, 44));
			this.addSlot(new CombSlot(handler, 3, 132, 44));
			
			//Bee Slots
			this.addSlot(new BeeSlot(handler, 4, 68, 9));
			this.addSlot(new BeeSlot(handler, 5, 92, 9));
			this.addSlot(new BeeSlot(handler, 6, 56, 33));
			this.addSlot(new BeeSlot(handler, 7, 104, 33));
			this.addSlot(new BeeSlot(handler, 8, 68, 57));
			this.addSlot(new BeeSlot(handler, 9, 92, 57));
			
			//Princess Slot
			this.addSlot(new PrincessSlot(handler, 10, 80, 33));
		});
		
		addDataSlots(data);
		
	}
	
	public boolean isCrafting() {
		return data.get(0) > 0;
	}

	
	public int getScaledProgress() {
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);
		//int progressArrowSize = 58; // This is the height in pixels of the loading bar
		int progressArrowSize = 64; // This is the height in pixels of the loading bar
		
		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}

	// CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = apiarySlots;  // must be the number of slots !

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
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
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    
	@Override
	public boolean stillValid(Player player) {
		return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
				player, BlockInit.APIARY_MOD_BLOCK.get());
	}
	
	private void addPlayerInventory(Inventory playerInventory) {
		for (int i = 0; i < 3; i++)  {
			for (int j = 0; j < 9; j++) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8+ j * 18, 86 + i * 18));
			}
		
		}

	}

	private void addPlayerHotbar(Inventory playerInventory) {
		for (int i = 0; i < 9; i++) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
		}
	}
	
}
