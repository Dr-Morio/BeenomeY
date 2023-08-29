package link.mdks.beenomey.apiculture.blocks.entity;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.BreederBlock;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import link.mdks.beenomey.apiculture.recipehandler.BreederBlockRecipeHandler;
import link.mdks.beenomey.apiculture.screen.BreederBlockMenu;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.BlockEntityInit;
import link.mdks.beenomey.init.FluidInit;
import link.mdks.beenomey.init.FluidTypeInit;
import link.mdks.beenomey.init.ItemInit;
import link.mdks.beenomey.networking.NetworkMessages;
import link.mdks.beenomey.networking.packets.EnergySyncS2CPacket;
import link.mdks.beenomey.networking.packets.FluidSyncS2CPacket;
import link.mdks.beenomey.util.ModEnergyStorage;
import link.mdks.beenomey.util.WrappedItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Triplet;
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
	
	// Crafting System
	public Map<Integer, ItemStack> lastInventory = new HashMap<Integer, ItemStack>();
	public int lastFluidAmount = 0;
	public int lastEnergyAmount = 0;
	public boolean isCrafting = false;
	public Triplet<BeeType, BeeType, BeeType> loadedRecipe;
	
	
	// Inventory Stuff
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	private final ContainerData data;
	private int progress = 0;
	private int maxProgress = 100;
	
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
		
		// Save inventory for hasChanged Checkup
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			lastInventory.put(i, itemHandler.getStackInSlot(i));
		}
	}

	
	/* Animation */
	
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, state -> {
			
			return state.setAndContinue(
				    state.getAnimatable().getLevel().getBlockState(state.getAnimatable().getBlockPos().above()).isAir() &&
				    ((BreederBlock) state.getAnimatable().getBlockState().getBlock()).isInteractedBlock(worldPosition) ? OPEN : CLOSE
				);
			
//			if(state.getAnimatable().getLevel().getBlockState(state.getAnimatable().getBlockPos().above()).getBlock() == Blocks.AIR) {
//				if (((BreederBlock) state.getAnimatable().getBlockState().getBlock()).isInteractedBlock(worldPosition)) {
//					return state.setAndContinue(OPEN);
//				} else {
//					return state.setAndContinue(CLOSE);
//				}
//			} else {
//				return state.setAndContinue(CLOSE);
//			}
		}));	
	}
	
	/* Inventory System */

	
	public final ItemStackHandler itemHandler = new ItemStackHandler(BreederBlockMenu.getBreederSlots()) { // was 3 before. Get information von ModBlockMenu
		protected void onContentsChanged(int slot) {
			setChanged();
		};
		
		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			return switch(slot) {
			case 0 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 1 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 2 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 3 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 4 -> false;
			case 5 -> stack.getItem().getClass() == ItemInit.EMPTY_CELL.get().getClass();
			case 6 -> stack.getItem().getClass() == ItemInit.EMPTY_CELL.get().getClass();
			default -> false;
			};
		};
	};
	

	
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		//Updates/Syncs Energy and Fluid on menu load
		NetworkMessages.sendToClients(new EnergySyncS2CPacket(this.ENERGY_STORAGE.getEnergyStored(), getBlockPos()));
		NetworkMessages.sendToClients(new FluidSyncS2CPacket(this.getFluidStack(), getBlockPos()));
		return new BreederBlockMenu(id, inventory, this, this.data);
	}


	@Override
	public Component getDisplayName() {
		return Component.literal("Breeder");
	}
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		
		if(cap == ForgeCapabilities.ENERGY) {
			return lazyEnergyHandler.cast();
		}
		
		if(cap == ForgeCapabilities.FLUID_HANDLER) {
			return lazyFluidHandler.cast();
		}
		
		
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			if(side == null) {
				return lazyItemHandler.cast();
			} else {
				return LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
						(index) -> index == 4,
						(index, stack) -> 
						itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack) ||
						itemHandler.isItemValid(2, stack) || itemHandler.isItemValid(3, stack)))
						.cast();
			}
			
		}
		
		return super.getCapability(cap, side);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
		lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
		lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
		lazyEnergyHandler.invalidate();
		lazyFluidHandler.invalidate();
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		nbt.put("inventory", itemHandler.serializeNBT());
		nbt.putInt("breeder_block.progress", this.progress);
		nbt.putInt("breeder_block.energy", ENERGY_STORAGE.getEnergyStored());
		nbt = FLUID_TANK.writeToNBT(nbt);
		super.saveAdditional(nbt);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("breeder_block.progress");
		ENERGY_STORAGE.setEnergy(nbt.getInt("breeder_block.energy"));
		FLUID_TANK.readFromNBT(nbt);
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
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
			
			//Check for fluid updates if slot has change
			if(pEntity.lastInventory.get(6) != pEntity.itemHandler.getStackInSlot(6) || pEntity.lastInventory.get(5) != pEntity.itemHandler.getStackInSlot(5)) {
				BreederBlockRecipeHandler.fluidTick(pEntity);
				BreederBlockRecipeHandler.saveInventory(pEntity);
				setChanged(level, blockPos, blockState);
			}
			
			//check for valid recipe if inventory has changed
			if(BreederBlockRecipeHandler.hasInventoryChanged(pEntity)) {
				BeenomeY.LOGGER.debug("Breeder: INVENTORY CHANGED -- Try to Load Recipe");
				pEntity.resetProgress();
				pEntity.isCrafting = false;
				BreederBlockRecipeHandler.fluidTick(pEntity);
				pEntity.setChanged();
				BreederBlockRecipeHandler.loadRecipe(pEntity);
			}
			
			//check for valid recipe if fluid has changed 
			if(BreederBlockRecipeHandler.hasFluidChanged(pEntity)) {
				BeenomeY.LOGGER.debug("Breeder: FLUID CHANGED -- Try to load Recipe");
				pEntity.lastFluidAmount = pEntity.getFluidTank().getFluidAmount();
				BreederBlockRecipeHandler.loadRecipe(pEntity);
				BreederBlockRecipeHandler.fluidTick(pEntity);
			}
			
			//check for valid recipe if Energy has changed 
			if(BreederBlockRecipeHandler.hasEnergyChanged(pEntity)) {
				BeenomeY.LOGGER.debug("Breeder: ENERGY CHANGED -- Try to load recipe");
				pEntity.lastEnergyAmount = pEntity.getEnergyStorage().getEnergyStored();
				BreederBlockRecipeHandler.loadRecipe(pEntity);
				BreederBlockRecipeHandler.fluidTick(pEntity);
			}
			
			//if recipe was valid and energy and fluid is enough add progress
			if (pEntity.isCrafting) {
				if(BreederBlockRecipeHandler.hasStillEnoughEnergy(pEntity) && BreederBlockRecipeHandler.hasStillEnoughFluid(pEntity)) {
					pEntity.progress++;
					BreederBlockRecipeHandler.consumePartialEnergy(pEntity);
					pEntity.setChanged();
					BreederBlockRecipeHandler.comsumePartialFluid(pEntity);
					pEntity.setChanged();
				} else if (!BreederBlockRecipeHandler.hasStillEnoughEnergy(pEntity) || !BreederBlockRecipeHandler.hasStillEnoughFluid(pEntity)) {
					BeenomeY.LOGGER.debug("Breeder: SOMETHING CHANGED - CANCLE CRAFTING " + BreederBlockRecipeHandler.hasEnoughEnergy(pEntity) + " " + !BreederBlockRecipeHandler.hasEnoughFluid(pEntity));
					pEntity.resetProgress();
					pEntity.isCrafting = false;
					pEntity.setChanged();
				}
			}
			
			//check if crafting is done
			if(pEntity.progress >= pEntity.maxProgress) {
				BeenomeY.LOGGER.debug("Breeder: CRAFTING STARTS");
				BreederBlockRecipeHandler.craft(pEntity);
				pEntity.resetProgress();
				pEntity.isCrafting = false;
				BreederBlockRecipeHandler.fluidTick(pEntity);
				pEntity.setChanged();
			}
			
			
			// ENERGY INPUT FOR DEVELOPMENT
			if(level.getBlockState(blockPos.above()).getBlock() == Blocks.DIRT) {
				pEntity.ENERGY_STORAGE.receiveEnergy(100, false);
			}
			
			// FLUID INPUT FOR DEVELOPMENT
			if(level.getBlockState(blockPos.above()).getBlock() == Blocks.STONE) {
				//pEntity.FLUID_TANK.fill(new FluidStack(FluidInit.SOURCE_INFERNO_HONEY.get(), 100), FluidAction.EXECUTE);
				pEntity.FLUID_TANK.fill(new FluidStack(ForgeRegistries.FLUIDS.getValue(FluidInit.SOURCE_ROCK_HONEY.getId()), 100), FluidAction.EXECUTE);
			}
			
			if(level.getBlockState(blockPos.above()).getBlock() == Blocks.COBBLESTONE) {
				pEntity.FLUID_TANK.fill(new FluidStack(FluidInit.SOURCE_FROZEN_HONEY.get(), 100), FluidAction.EXECUTE);
			}
			
			if(level.getBlockState(blockPos.above()).getBlock() == Blocks.DIRT) {
				pEntity.FLUID_TANK.fill(new FluidStack(FluidInit.SOURCE_ENDER_PEARL_HONEY.get(), 100), FluidAction.EXECUTE);
			}
			
			if(level.getBlockState(blockPos.above()).getBlock() == Blocks.OBSIDIAN) {
				pEntity.FLUID_TANK.fill(new FluidStack(FluidInit.SOURCE_MUSHROOM_HONEY.get(), 100), FluidAction.EXECUTE);
			}
		}
	}
	
	
	/* Energy System */
	
	private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
	private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(60000, 256) {
		
		@Override
		public void onEnergyChanged() {
			setChanged();
			NetworkMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
			
		}

	};
	
	/* Helper Functions */
	
	private static final int ENERGY_REQ = 1200;

	public IEnergyStorage getEnergyStorage() {
		return ENERGY_STORAGE;
	}


	public void setEnergyLevel(int energy) {
		this.ENERGY_STORAGE.setEnergy(energy);
	}
	
	public int getReqEnergy() {
		return ENERGY_REQ;
	}
	
	/* Fliud System */
	
	private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
	
	private final FluidTank FLUID_TANK = new FluidTank(16000) {
		
		@Override
		protected void onContentsChanged() {
			setChanged();
			if(!level.isClientSide) {
				NetworkMessages.sendToClients(new FluidSyncS2CPacket(this.fluid, worldPosition));
			}
		};
		
		@Override
		public boolean isFluidValid(FluidStack stack) {
			return FluidInit.FLUIDS.getEntries().stream().map(RegistryObject::get).anyMatch(fluid -> fluid == stack.getFluid());
		};
	};
	
	/* Helper Functions */
	
	public void setFluid(FluidStack stack) {
		this.FLUID_TANK.setFluid(stack);
	}
	
	public FluidStack getFluidStack() {
		return this.FLUID_TANK.getFluid();
	}
	
	public FluidTank getFluidTank() {
		return FLUID_TANK;
	}
	
	public Map<Integer, ItemStack> getLastInventory() {
		return lastInventory;
	}
	
	public int getMaxProgress() {
		return maxProgress;
	}
	
	public int getProgress() {
		return progress;
	}
}

