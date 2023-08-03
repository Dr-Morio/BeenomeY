package link.mdks.beenomey.datagen;


import java.util.List;
import java.util.Set;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;


public class BeenomeyLootTableProvider {
	
	public  LootTableProvider create(PackOutput output) {
		return null;
				/*new LootTableProvider(output, Set.of(),
				List.of(new LootTableProvider.SubProviderEntry(BeenomeyBlockLootTables::new, LootContextParamSets.BLOCK))); */
	}

}
