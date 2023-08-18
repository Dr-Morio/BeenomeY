package link.mdks.beenomey.apiculture.blocks;

import java.util.HashMap;
import java.util.Map;

import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import link.mdks.beenomey.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class BreederBlock extends BaseEntityBlock implements EntityBlock{

	/* Fields */
	
    Map<Player, BlockPos> currentInteractions = new HashMap<Player, BlockPos>();
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
	public static boolean shouldBeOpend = false;
	
	/* Constructor */
	
	public BreederBlock() {
		super(Properties.of(Material.WOOD).noOcclusion());
	}

	/* Entity Functions */
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return BlockEntityInit.BREEDER_BLOCK.get().create(pPos, pState);
	}

	
	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
		}
	
	
	/* Helper Functions */
	
	public void checkCurrentInteractions() {
		if(!currentInteractions.isEmpty()) {
			for(Player p : currentInteractions.keySet()) {
				if(!p.hasContainerOpen()) {
					currentInteractions.remove(p);
				}
			}
		}
	}	
	
	public boolean isInteractedBlock(BlockPos pPos) {
		for (BlockPos pos : currentInteractions.values()) {
			if (pos.asLong() == pPos.asLong()) {
				return true;
			}
		}
		return false;
	}
	
	/* Inventory System */
	
	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
			InteractionHand pHand, BlockHitResult pHit) {
		
		if (!pLevel.isClientSide()) {
			BlockEntity entity = pLevel.getBlockEntity(pPos);
			if (entity instanceof BreederBlockEntity) {
				NetworkHooks.openScreen((ServerPlayer)pPlayer, (BreederBlockEntity)entity, pPos);
				// Set Last Player Interaction for Entity Animation
				currentInteractions.put(pPlayer, pPos.immutable());
				
			} else {
				throw new IllegalStateException("Our Container provider is missing!");
			}
		}
		return InteractionResult.sidedSuccess(pLevel.isClientSide());


	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState,
			boolean pIsMoving) {
		
		if(pState.getBlock() != pNewState.getBlock()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if (blockEntity instanceof BreederBlockEntity) {
				((BreederBlockEntity) blockEntity).drops();
			}
		}
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}
	
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> type) {
		return  createTickerHelper(type, BlockEntityInit.BREEDER_BLOCK.get(), BreederBlockEntity::tick);
	}

}
