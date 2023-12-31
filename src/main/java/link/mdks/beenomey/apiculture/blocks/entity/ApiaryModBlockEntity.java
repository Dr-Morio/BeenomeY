package link.mdks.beenomey.apiculture.blocks.entity;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.ApiaryModBlock;
import link.mdks.beenomey.apiculture.recipehandler.ApiaryModBlockRecipeHandler;
import link.mdks.beenomey.apiculture.recipehandler.ApiaryModBlockRecipeHandler.ApiaryMode;
import link.mdks.beenomey.apiculture.screen.ApiaryModBlockMenu;
import link.mdks.beenomey.apiculture.util.Ecosystem;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.BlockEntityInit;
import link.mdks.beenomey.init.ItemInit;
import link.mdks.beenomey.util.WrappedItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ApiaryModBlockEntity extends BlockEntity implements  GeoBlockEntity, MenuProvider{

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	// We statically instantiate our RawAnimations for efficiency, consistency, and error-proofing
	@SuppressWarnings("unused")
	private static final RawAnimation CLOSE_IDLE = RawAnimation.begin().thenLoop("apiary_mod_block.closeIdle");
	@SuppressWarnings("unused")
	private static final RawAnimation OPEN_IDLE = RawAnimation.begin().thenLoop("apiary_mod_block.openIdle");
	private static final RawAnimation OPEN = RawAnimation.begin().thenPlayAndHold("apiary_mod_block.open");//.thenLoop("apiary_mod_block.openIdle");
	private static final RawAnimation CLOSE = RawAnimation.begin().thenPlayAndHold("apiary_mod_block.close");//.thenLoop("apiary_mod_block.closeIdle");
	
	// Inventory Stuff
	private final ContainerData data;
	public int progress = 0;
	public int maxProgress = 58;
	
	public Map<Integer, ItemStack> lastInventory = new HashMap<Integer, ItemStack>();
	public ApiaryModBlockRecipeHandler.ApiaryMode mode;
	
	
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
		
		// Save inventory
		for (int i = 4; i < 11; i++) { // Only slot 4 - 10 are Bee Slots and relevant
			lastInventory.put(i, itemHandler.getStackInSlot(i));
		}
		
		// Set default Mode
		this.mode = ApiaryMode.IDLE;
		
	}


	@Override
	public void registerControllers(ControllerRegistrar controllers) {
			
		controllers.add(new AnimationController<>(this, state -> {
			if(state.getAnimatable().getLevel().getBlockState(state.getAnimatable().getBlockPos().above()).getBlock() == Blocks.AIR) {
				if (((ApiaryModBlock) state.getAnimatable().getBlockState().getBlock()).isInteractedBlock(worldPosition) ) {
					return state.setAndContinue(OPEN);
				} else {
					return state.setAndContinue(CLOSE);
				}
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


	public final ItemStackHandler itemHandler = new ItemStackHandler(ApiaryModBlockMenu.getApiarySlots()) { // was 3 before. Get information von ModBlockMenu
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		};
		
		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			return switch(slot) {
			case 0 -> stack.getItem().getClass() == ItemInit.HONEYCOMB.get().getClass();
			case 1 -> stack.getItem().getClass() == ItemInit.HONEYCOMB.get().getClass();
			case 2 -> stack.getItem().getClass() == ItemInit.HONEYCOMB.get().getClass();
			case 3 -> stack.getItem().getClass() == ItemInit.HONEYCOMB.get().getClass();
			case 4 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 5 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 6 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 7 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 8 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 9 -> stack.getItem().getClass() == BeeInit.COMMON_BEE.get().getClass();
			case 10 -> stack.getItem().getClass() == BeeInit.PRINCESS_BEE.get().getClass();
			default -> false;  
			};
		};
			
	};
	
	
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new ApiaryModBlockMenu(id, inventory, this, this.data);
	}

	
	public void opener() {
		
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Apiary");
	}

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    /*
    private final Map<Direction, LazyOptional<WrappedItemHandler>> directionWrappedHandlerMap =
            Map.of(
//            		Direction.UP, LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
//            		(index) -> index == 0 || index == 1 || index == 2 || index == 3,
//            		(index, stack) ->
//           		 	itemHandler.isItemValid(4, stack) || itemHandler.isItemValid(5, stack)
//                 || itemHandler.isItemValid(6, stack) || itemHandler.isItemValid(7, stack) 
//                 || itemHandler.isItemValid(8, stack) || itemHandler.isItemValid(9, stack)
//                 || itemHandler.isItemValid(10, stack) )),
            		
            		Direction.DOWN, LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
                    		(index) -> index == 0 || index == 1 || index == 2 || index == 3,
                          (index, stack) ->
                    		 itemHandler.isItemValid(4, stack) || itemHandler.isItemValid(5, stack)
                          || itemHandler.isItemValid(6, stack) || itemHandler.isItemValid(7, stack) 
                          || itemHandler.isItemValid(8, stack) || itemHandler.isItemValid(9, stack)
                          || itemHandler.isItemValid(10, stack) ))
            		);
            		

    
//                    Direction.NORTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
//                    		(index) -> index == 1,
//                            (index, stack) -> itemHandler.isItemValid(1, stack))),
//                    
//                    Direction.SOUTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
//                    		(i) -> i == 2,
//                    		(i, s) -> false)),
//                    
//                    Direction.EAST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
//                    		(i) -> i == 1,
//                            (index, stack) -> itemHandler.isItemValid(1, stack))),
//                    
//                    Direction.WEST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
//                    		(index) -> index == 0 || index == 1,
//                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack))));

*/
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            } else {
            	return LazyOptional.of(() -> new WrappedItemHandler(itemHandler,
                		(index) -> index == 0 || index == 1 || index == 2 || index == 3,
                      (index, stack) ->
                		 itemHandler.isItemValid(4, stack) || itemHandler.isItemValid(5, stack)
                      || itemHandler.isItemValid(6, stack) || itemHandler.isItemValid(7, stack) 
                      || itemHandler.isItemValid(8, stack) || itemHandler.isItemValid(9, stack)
                      || itemHandler.isItemValid(10, stack) )).cast();
            }

            
//            if(directionWrappedHandlerMap.containsKey(side)) {
//                Direction localDir = this.getBlockState().getValue(ApiaryModBlock.FACING);
//
//                if(side == Direction.UP || side == Direction.DOWN) {
//                    return directionWrappedHandlerMap.get(side).cast();
//                }
//
//                return switch (localDir) {
//                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
////                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast(); 
////                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
////                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
//                };
//            }
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
	
	public ApiaryMode getActiveMode() {
		return this.mode;
		
	}
	
	public static void tick(Level level, BlockPos blockPos, BlockState blockState, ApiaryModBlockEntity pEntity) {
		if(level.isClientSide()) {
			((ApiaryModBlock) blockState.getBlock()).checkCurrentInteractions(); // updates BlockAnimation based on Interactions
			return;
		}
		//The fllowing should only be called on server side
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
			
			//Check if inventory has changed
			if(ApiaryModBlockRecipeHandler.hasInventoryChanged(pEntity)) {
				BeenomeY.LOGGER.debug("CHANGED!!!!!!");
				pEntity.resetProgress();
				pEntity.setChanged();
			}
			
			//Check if recipe should start
			if (ApiaryModBlockRecipeHandler.hasValidRecipe(pEntity)) {
				BeenomeY.LOGGER.debug("IsValid");
				pEntity.mode = ApiaryModBlockRecipeHandler.getMode(pEntity);
				pEntity.setChanged();
			}
			
			//Add Progress if it should
			if(ApiaryModBlockRecipeHandler.isWorking(pEntity)) {
				BeenomeY.LOGGER.debug("STILL HERE :D");
				ApiaryModBlockRecipeHandler.addProgress(pEntity);
				pEntity.setChanged();
			}
			
			//Check if progress is 100%
			if(pEntity.progress >= pEntity.maxProgress) {
				ApiaryModBlockRecipeHandler.startCrafting(pEntity);
				pEntity.resetProgress();
				pEntity.setChanged();
			}
			
		}
	}

	private void resetProgress() {
		this.progress = 0;
		this.mode = ApiaryMode.IDLE;
	}
}
