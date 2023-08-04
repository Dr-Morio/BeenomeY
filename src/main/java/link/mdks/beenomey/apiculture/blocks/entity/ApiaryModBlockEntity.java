package link.mdks.beenomey.apiculture.blocks.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import link.mdks.beenomey.apiculture.blocks.ApiaryModBlock;
import link.mdks.beenomey.init.BlockEntityInit;
import link.mdks.beenomey.sceen.ApiaryModBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ApiaryModBlockEntity extends BlockEntity implements GeoBlockEntity, MenuProvider{

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	// We statically instantiate our RawAnimations for efficiency, consistency, and error-proofing
	@SuppressWarnings("unused")
	private static final RawAnimation CLOSE_IDLE = RawAnimation.begin().thenLoop("apiary_mod_block.closeIdle");
	@SuppressWarnings("unused")
	private static final RawAnimation OPEN_IDLE = RawAnimation.begin().thenLoop("apiary_mod_block.openIdle");
	private static final RawAnimation OPEN = RawAnimation.begin().thenPlay("apiary_mod_block.open").thenLoop("apiary_mod_block.openIdle");
	private static final RawAnimation CLOSE = RawAnimation.begin().thenPlay("apiary_mod_block.close").thenLoop("apiary_mod_block.closeIdle");
	
	// Inventory Stuff
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	private final ContainerData data;
	private int progress = 0;
	private int maxProgress = 20;
	
	public ApiaryModBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.APIARY_MOD_BLOCK.get(), pos, state);
		this.data = new ContainerData() {
			
			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0 -> ApiaryModBlockEntity.this.progress = value;
				case 1 -> ApiaryModBlockEntity.this.maxProgress = value;
				};
			}
			
			@Override
			public int getCount() {
				return 2; // how many variables are stored inside container -> progress / maxProgress
			}
			
			@Override
			public int get(int index) {
				return switch (index) {
				case 0 -> ApiaryModBlockEntity.this.progress;
				case 1 -> ApiaryModBlockEntity.this.maxProgress;
				default -> 0;
				};
			}
		};
	}


	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, state -> {
			if (((ApiaryModBlock) state.getAnimatable().getBlockState().getBlock()).isInteractedWith()) {
				return state.setAndContinue(OPEN);
			} else {
				return state.setAndContinue(CLOSE);
			}
		}));		
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	/* Inventory System */

	public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
		protected void onContentsChanged(int slot) {
			setChanged();
		};
	};
	

	
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new ApiaryModBlockMenu(id, inventory, this, this.data);
	}


	@Override
	public Component getDisplayName() {
		return Component.literal("Apiary");
	}
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		
		//if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return lazyItemHandler.cast();
		}
		
		return super.getCapability(cap, side);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		nbt.put("inventory", itemHandler.serializeNBT());
		super.saveAdditional(nbt);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		super.load(nbt);
	}
	
	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i =0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		}
		
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}
	
	public static void tick(Level level, BlockPos blockPos, BlockState blockState, ApiaryModBlockEntity pEntity) {
		if(level.isClientSide()) {
			((ApiaryModBlock) blockState.getBlock()).checkCurrentInteractions(); // updates Animation based on Interactions
			return;
		}
		
		if(hasRecipe(pEntity)) {
			pEntity.progress++;
			setChanged(level, blockPos, blockState);
			
			if(pEntity.progress >= pEntity.maxProgress) {
				craftItem(pEntity);
			}
		} else {
			pEntity.resetProgress();
			setChanged(level, blockPos, blockState);
		}
		
	}


	private void resetProgress() {
		this.progress = 0;
	}


	private static void craftItem(ApiaryModBlockEntity pEntity) {
		
		if(hasRecipe(pEntity)) {
			pEntity.itemHandler.extractItem(1, 1, false);
			pEntity.itemHandler.setStackInSlot(2, new ItemStack(Items.STONE,
					pEntity.itemHandler.getStackInSlot(2).getCount() + 1));
			pEntity.resetProgress();
		}
		
	}


	private static boolean hasRecipe(ApiaryModBlockEntity entity) {
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		boolean hasDirtInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem()	 == Items.DIRT;
		
		return hasDirtInFirstSlot;
		
		
	}
	
}
