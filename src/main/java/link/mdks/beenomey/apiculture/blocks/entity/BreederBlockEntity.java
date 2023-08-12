package link.mdks.beenomey.apiculture.blocks.entity;

import link.mdks.beenomey.apiculture.blocks.BreederBlock;
import link.mdks.beenomey.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BreederBlockEntity extends BlockEntity implements GeoBlockEntity{

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	private static final RawAnimation OPEN = RawAnimation.begin().thenPlayAndHold("breeder_block.open");
	private static final RawAnimation CLOSE = RawAnimation.begin().thenPlayAndHold("breeder_block.close");
	
	
	public BreederBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityInit.BREEDER_BLOCK.get(), pPos, pBlockState);
	}

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

}
