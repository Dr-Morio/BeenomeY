package link.mdks.beenomey.apiculture.blocks;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class BreederBlock extends BaseEntityBlock implements EntityBlock{

	public static boolean shouldBeOpend = false;
	
	public BreederBlock() {
		super(Properties.of(Material.WOOD).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return BlockEntityInit.BREEDER_BLOCK.get().create(pPos, pState);
	}

	
	@Override
	public RenderShape getRenderShape(BlockState pState) {
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
	
	public boolean isInteractedWith() {
		return shouldBeOpend;
	}
	
	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
			BlockHitResult pHit) {
		if (!pLevel.isClientSide()) {
			BeenomeY.LOGGER.debug("HIT");
			shouldBeOpend = !shouldBeOpend;
		}
		return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
	}
	
	
}
