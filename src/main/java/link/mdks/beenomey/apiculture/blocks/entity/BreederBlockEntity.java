package link.mdks.beenomey.apiculture.blocks.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import link.mdks.beenomey.apiculture.blocks.BreederBlock;
import link.mdks.beenomey.init.BlockEntityInit;
import link.mdks.beenomey.sceen.BreederBlockMenu;
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
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BreederBlockEntity extends BlockEntity implements GeoBlockEntity, MenuProvider{

	/* Fields */
	
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	// We statically instantiate our RawAnimations for efficiency, consistency, and error-proofing

	private static final RawAnimation OPEN = RawAnimation.begin().thenPlayAndHold("breeder_block.open");
	private static final RawAnimation CLOSE = RawAnimation.begin().thenPlayAndHold("breeder_block.close");
	
	
	// Inventory Stuff
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	private final ContainerData data;
	private int progress = 0;
	private int maxProgress = 58;
	
	/* Constructor */
	
	public BreederBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityInit.BREEDER_BLOCK.get(), pPos, pBlockState);
		this.data = new ContainerData() {
			
			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0 -> BreederBlockEntity.this.progress = value;
				case 1 -> BreederBlockEntity.this.maxProgress = value;
				};
			}
			
			@Override
			public int getCount() {
				return 2; // how many variables are stored inside container -> progress / maxProgress
			}
			
			@Override
			public int get(int index) {
				return switch (index) {
				case 0 -> BreederBlockEntity.this.progress;
				case 1 -> BreederBlockEntity.this.maxProgress;
				default -> 0;
				};
			}
		};
	}

	
	/* Animation */
	
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, state -> {
			if (((BreederBlock) state.getAnimatable().getBlockState().getBlock()).isInteractedWith()) {
				return state.setAndContinue(OPEN);
			} else {
				return state.setAndContinue(CLOSE);
			}
		}));	
	}
	
	/* Inventory System */

	
	public final ItemStackHandler itemHandler = new ItemStackHandler(BreederBlockMenu.getBreederSlots()) { // was 3 before. Get information von ModBlockMenu
		protected void onContentsChanged(int slot) {
			setChanged();
		};
	};
	

	
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new BreederBlockMenu(id, inventory, this, this.data);
	}


	@Override
	public Component getDisplayName() {
		return Component.literal("Breeder");
	}
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		
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
	
	/* Helper Functions */
	
	private void resetProgress() {
		this.progress = 0;
	}

	/* Updates */
	
	public static void tick(Level level, BlockPos blockPos, BlockState blockState, BreederBlockEntity pEntity) {
		if(level.isClientSide()) {
			((BreederBlock) blockState.getBlock()).checkCurrentInteractions(); // updates BlockAnimation based on Interactions
			return;
		}
	}
}
