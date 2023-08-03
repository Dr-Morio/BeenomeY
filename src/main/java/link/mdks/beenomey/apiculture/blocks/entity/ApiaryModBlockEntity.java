package link.mdks.beenomey.apiculture.blocks.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import link.mdks.beenomey.apiculture.blocks.ApiaryModBlock;
import link.mdks.beenomey.init.BlockEntityInit;
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
import net.minecraft.world.inventory.MenuConstructor;
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

public class ApiaryModBlockEntity extends BlockEntity implements GeoBlockEntity {// MenuProvider{

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	// We statically instantiate our RawAnimations for efficiency, consistency, and error-proofing
	private static final RawAnimation CLOSE_IDLE = RawAnimation.begin().thenLoop("apiary_mod_block.closeIdle");
	private static final RawAnimation OPEN_IDLE = RawAnimation.begin().thenLoop("apiary_mod_block.openIdle");
	private static final RawAnimation OPEN = RawAnimation.begin().thenPlay("apiary_mod_block.open").thenLoop("apiary_mod_block.openIdle");
	private static final RawAnimation CLOSE = RawAnimation.begin().thenPlay("apiary_mod_block.close").thenLoop("apiary_mod_block.closeIdle");
	
	public ApiaryModBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.apiary_mod_block.get(), pos, state);
	}


	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		
		
		controllers.add(new AnimationController<>(this, state -> {
			return state.setAndContinue(OPEN_IDLE);
		}));
		
//		controllers.add(new AnimationController<>(this, state -> {
//			if (state.getAnimatable().getLevel().isRaining()) {
//				return state.setAndContinue(OPEN);
//			}
//			else {
//				return state.setAndContinue(OPEN);
//			}
//		}));
		
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	/* Inventory System */

//	public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
//		protected void onContentsChanged(int slot) {
//			setChanged();
//		};
//	};
//	
//	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
//	
//	@Override
//	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
//		return
//	}
//
//
//	@Override
//	public Component getDisplayName() {
//		return Component.literal("Apiary");
//	}
//	
//	@Override
//	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//		
//		//if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//		if (cap == ForgeCapabilities.ITEM_HANDLER) {
//			return lazyItemHandler.cast();
//		}
//		
//		return super.getCapability(cap, side);
//	}
//	
//	@Override
//	public void onLoad() {
//		super.onLoad();
//		lazyItemHandler = LazyOptional.of(() -> itemHandler);
//	}
//	
//	@Override
//	public void invalidateCaps() {
//		super.invalidateCaps();
//		lazyItemHandler.invalidate();
//	}
//	
//	@Override
//	protected void saveAdditional(CompoundTag nbt) {
//		nbt.put("inventory", itemHandler.serializeNBT());
//		super.saveAdditional(nbt);
//	}
//	
//	@Override
//	public void load(CompoundTag nbt) {
//		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
//		super.load(nbt);
//	}
//	
//	public void drops() {
//		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
//		for (int i =0; i < itemHandler.getSlots(); i++) {
//			inventory.setItem(i, itemHandler.getStackInSlot(i));
//		}
//		
//		Containers.dropContents(this.level, this.worldPosition, inventory);
//	}
}
