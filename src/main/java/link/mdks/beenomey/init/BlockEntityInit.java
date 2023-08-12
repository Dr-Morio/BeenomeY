package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,BeenomeY.MODID);

	public static final RegistryObject<BlockEntityType<ApiaryModBlockEntity>> APIARY_MOD_BLOCK = 
			BLOCK_ENTITIES.register("apiary_mod_block",
					() -> BlockEntityType.Builder.of(ApiaryModBlockEntity::new, BlockInit.APIARY_MOD_BLOCK.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<BreederBlockEntity>> BREEDER_BLOCK = 
			BLOCK_ENTITIES.register("breeder_block",
					() -> BlockEntityType.Builder.of(BreederBlockEntity::new, BlockInit.BREEDER_BLOCK.get()).build(null));
	
			
			//OLD
//			BLOCK_ENTITIES
//			.register("apiary_mod_block", () -> BlockEntityType.Builder
//					.of(ApiaryModBlockEntity::new, BlockInit.apiary_mod_block.get()).build(null));
//	
//	
	
    public static void register(IEventBus eventBus) {
    	BLOCK_ENTITIES.register(eventBus);
    }
	
}
