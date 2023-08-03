package link.mdks.beenomey.apiculture.blocks;

import link.mdks.beenomey.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;

public class ApiaryModBlock extends BaseEntityBlock implements EntityBlock{
	
	public ApiaryModBlock() {
		super(Properties.of(Material.WOOD).noOcclusion());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityInit.apiary_mod_block.get().create(pos, state);
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
	
}
