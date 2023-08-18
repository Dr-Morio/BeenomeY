package link.mdks.beenomey.datagen;

import link.mdks.beenomey.BeenomeY;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BeenomeyBlockstateProvider extends BlockStateProvider{

	public BeenomeyBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, BeenomeY.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
//		blockWithItem(BlockInit.OAKWOOD_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.BIRCHWOOD_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.SAND_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.STONE_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.JUNGLEWOOD_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.ICE_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.ENDER_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.NETHER_BEEHIVE_BLOCK);
//		blockWithItem(BlockInit.WATER_BEEHIVE_BLOCK);
		
	}
	
	@SuppressWarnings("unused")
	private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
		simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
		
	}
	
}
