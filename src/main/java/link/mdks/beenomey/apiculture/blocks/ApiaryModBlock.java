package link.mdks.beenomey.apiculture.blocks;

import java.util.HashMap;
import java.util.Map;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
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


public class ApiaryModBlock extends BaseEntityBlock implements EntityBlock{
	
    Map<Player, BlockPos> currentInteractions = new HashMap<Player, BlockPos>();
    
	public ApiaryModBlock() {
		super(Properties.of(Material.WOOD).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityInit.APIARY_MOD_BLOCK.get().create(pos, state);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_60550_) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
		}
	
	/* Inventory System */
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState,
			boolean pIsMoving) {
		
		if(pState.getBlock() != pNewState.getBlock()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if (blockEntity instanceof ApiaryModBlockEntity) {
				((ApiaryModBlockEntity) blockEntity).drops();
			}
		}
		
		
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}
	
	public boolean isInteractedBlock(BlockPos pPos) {
		for (BlockPos pos : currentInteractions.values()) {
			if (pos.asLong() == pPos.asLong()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
			InteractionHand pHand, BlockHitResult pHit) {
		
		if (!pLevel.isClientSide()) {
			BlockEntity entity = pLevel.getBlockEntity(pPos);
			if (entity instanceof ApiaryModBlockEntity) {
				NetworkHooks.openScreen((ServerPlayer)pPlayer, (ApiaryModBlockEntity)entity, pPos);
				// Set Last Player Interaction for Entity Animation
				currentInteractions.put(pPlayer, pPos.immutable());
				
			} else {
				throw new IllegalStateException("Our Container provider is missing!");
			}
		}
		return InteractionResult.sidedSuccess(pLevel.isClientSide());


	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> type) {
		return  createTickerHelper(type, BlockEntityInit.APIARY_MOD_BLOCK.get(), ApiaryModBlockEntity::tick);
	}
	
	public void checkCurrentInteractions() {
		if(!currentInteractions.isEmpty()) {
			for(Player p : currentInteractions.keySet()) {
				if(!p.hasContainerOpen()) {
					currentInteractions.remove(p);
				}
			}
		}
	}	
}
