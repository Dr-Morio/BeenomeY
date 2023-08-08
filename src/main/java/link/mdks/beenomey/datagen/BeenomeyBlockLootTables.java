package link.mdks.beenomey.datagen;

import java.util.Set;

import link.mdks.beenomey.init.BlockInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BeenomeyBlockLootTables extends BlockLootSubProvider{

	public BeenomeyBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate() {
		//dropSelf(BLOCK.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
	
}
