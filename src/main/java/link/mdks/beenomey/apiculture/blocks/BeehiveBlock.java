package link.mdks.beenomey.apiculture.blocks;

import java.util.Random;

import link.mdks.beenomey.apiculture.items.ItemBee;
import link.mdks.beenomey.apiculture.items.ItemScoop;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.BlockInit;
import link.mdks.beenomey.util.BeeManager;
import link.mdks.beenomey.util.BeeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.extensions.IForgeBlock;

public class BeehiveBlock extends Block implements IForgeBlock{
	
	private  BeeType beeType;
	
    public BeehiveBlock(Properties prop, BeeType type) {
        super(prop);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        this.beeType = type;
    }
    
    /* Shaping */
	
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
    	if (blockState.getBlock() == BlockInit.SAND_BEEHIVE_BLOCK.get() |
			blockState.getBlock() == BlockInit.STONE_BEEHIVE_BLOCK.get() |
			blockState.getBlock() == BlockInit.ENDER_BEEHIVE_BLOCK.get() |
			blockState.getBlock() == BlockInit.ICE_BEEHIVE_BLOCK.get() |
			blockState.getBlock() == BlockInit.NETHER_BEEHIVE_BLOCK.get() |
			blockState.getBlock() == BlockInit.WATER_BEEHIVE_BLOCK.get()) {
    		return makeBottomShape();
    	} else {
        	return makeTopShape();
    	}
    }
    
    public static VoxelShape makeTopShape(){
    	    	
    	VoxelShape shape = Shapes.empty();
    	shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.3125, 0.375, 0.3125, 0.6875, 0.4375, 0.6875), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.25, 0.4375, 0.25, 0.75, 0.875, 0.75), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.3125, 0.875, 0.3125, 0.6875, 0.9375, 0.6875), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.375, 0.9375, 0.375, 0.625, 1, 0.625), BooleanOp.OR);

    	return shape;
    }
    
    public static VoxelShape makeBottomShape(){
    	VoxelShape shape = Shapes.empty();
    	shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 0.0625, 0.625), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.6875, 0.125, 0.6875), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.25, 0.75, 0.5625, 0.75), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.3125, 0.5625, 0.3125, 0.6875, 0.625, 0.6875), BooleanOp.OR);
    	shape = Shapes.join(shape, Shapes.box(0.375, 0.625, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);

    	return shape;
    }
    
    /* FACING */

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(FACING);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
     /* Made by Prysm */
    
    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    /* Changed by Dr.Morio*/
    
    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
    	if (player.getInventory().getSelected().getItem() instanceof ItemScoop) {
			return true;
    	} else {
			return false;
    	}
    }
    
    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest,	FluidState fluid) {
    	if (!level.isClientSide & canHarvestBlock(state, level, pos, player)) {
    		Random rnd = new Random();
    		int commonDrops = rnd.nextInt(1,5);
    		int princessDropChance = rnd.nextInt(0,10);
    		
    		//drop commonBee
    		ItemEntity commonBeeStack = new ItemEntity(level, 1, 1, 1, BeeManager.getBee(beeType, beeType, new ItemStack(BeeInit.getCommonBee(), commonDrops)));
    		commonBeeStack.setPos(pos.getCenter());
    		level.addFreshEntity(commonBeeStack);
    		
			// Drop princessBee with chance
    		if (princessDropChance == 0) {
        		ItemEntity princessBeeStack = new ItemEntity(level, 1, 1, 1, BeeManager.getBee(beeType, beeType, new ItemStack(BeeInit.getPrincessBee(), 1)));
        		princessBeeStack.setPos(pos.getCenter());
        		level.addFreshEntity(princessBeeStack);
    		}
    	}
    	return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}