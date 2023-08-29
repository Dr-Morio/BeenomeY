package link.mdks.beenomey.datagen;


import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;


public class BeenomeyLootTableProvider {
	
	public  LootTableProvider create(PackOutput output) {
		return null;
				/*new LootTableProvider(output, Set.of(),
				List.of(new LootTableProvider.SubProviderEntry(BeenomeyBlockLootTables::new, LootContextParamSets.BLOCK))); */
	}

}
