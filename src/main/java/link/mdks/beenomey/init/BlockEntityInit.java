package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,BeenomeY.MODID);

	public static final RegistryObject<BlockEntityType<ApiaryModBlockEntity>> apiary_mod_block = BLOCK_ENTITIES
			.register("apiary_mod_block", () -> BlockEntityType.Builder
					.of(ApiaryModBlockEntity::new, BlockInit.apiary_mod_block.get()).build(null));
	
}
