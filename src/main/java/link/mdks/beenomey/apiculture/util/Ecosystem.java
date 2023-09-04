package link.mdks.beenomey.apiculture.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public enum Ecosystem {
	
	UNKNOWN(),
	WARM(),
	COLD(),
	HUMID(),
	DRY(),
	DARK(),
	INFERNO(),
	VOID();
	
	
	
	@SuppressWarnings("serial")
	private static Map<ResourceKey<Biome>, Ecosystem> temps = new HashMap<ResourceKey<Biome>, Ecosystem>(){{
        put(Biomes.THE_VOID, VOID);
        put(Biomes.PLAINS, WARM);
        put(Biomes.SUNFLOWER_PLAINS, WARM);
        put(Biomes.SNOWY_PLAINS, COLD);
        put(Biomes.ICE_SPIKES, COLD);
        put(Biomes.DESERT, DRY);
        put(Biomes.SWAMP, HUMID);
        put(Biomes.MANGROVE_SWAMP, HUMID);
        put(Biomes.FOREST, WARM);
        put(Biomes.FLOWER_FOREST, WARM);
        put(Biomes.BIRCH_FOREST, WARM);
        put(Biomes.DARK_FOREST, WARM);
        put(Biomes.OLD_GROWTH_BIRCH_FOREST, WARM);
        put(Biomes.OLD_GROWTH_PINE_TAIGA, WARM);
        put(Biomes.OLD_GROWTH_SPRUCE_TAIGA, WARM);
        put(Biomes.TAIGA, WARM);
        put(Biomes.SNOWY_TAIGA, COLD);
        put(Biomes.SAVANNA, DRY);
        put(Biomes.SAVANNA_PLATEAU, DRY);
        put(Biomes.WINDSWEPT_HILLS, COLD);
        put(Biomes.WINDSWEPT_GRAVELLY_HILLS, COLD);
        put(Biomes.WINDSWEPT_FOREST, COLD);
        put(Biomes.WINDSWEPT_SAVANNA, DRY);
        put(Biomes.JUNGLE, HUMID);
        put(Biomes.SPARSE_JUNGLE, HUMID);
        put(Biomes.BAMBOO_JUNGLE, HUMID);
        put(Biomes.BADLANDS, DRY);
        put(Biomes.ERODED_BADLANDS, DRY);
        put(Biomes.WOODED_BADLANDS, DRY);
        put(Biomes.MEADOW, WARM);
        put(Biomes.CHERRY_GROVE, WARM);
        put(Biomes.GROVE, COLD);
        put(Biomes.SNOWY_SLOPES, COLD);
        put(Biomes.FROZEN_PEAKS, COLD);
        put(Biomes.JAGGED_PEAKS, COLD);
        put(Biomes.STONY_PEAKS, COLD);
        put(Biomes.RIVER, HUMID);
        put(Biomes.FROZEN_RIVER, COLD);
        put(Biomes.BEACH, WARM);
        put(Biomes.SNOWY_BEACH, COLD);
        put(Biomes.STONY_SHORE, COLD);
        put(Biomes.WARM_OCEAN, HUMID);
        put(Biomes.LUKEWARM_OCEAN, HUMID);
        put(Biomes.DEEP_LUKEWARM_OCEAN, HUMID);
        put(Biomes.OCEAN, HUMID);
        put(Biomes.DEEP_OCEAN, HUMID);
        put(Biomes.COLD_OCEAN, HUMID);
        put(Biomes.DEEP_COLD_OCEAN, HUMID);
        put(Biomes.FROZEN_OCEAN, COLD);
        put(Biomes.DEEP_FROZEN_OCEAN, COLD);
        put(Biomes.MUSHROOM_FIELDS, DARK);
        put(Biomes.DRIPSTONE_CAVES, DARK);
        put(Biomes.LUSH_CAVES, DARK);
        put(Biomes.DEEP_DARK, DARK);
        put(Biomes.NETHER_WASTES, INFERNO);
        put(Biomes.WARPED_FOREST, INFERNO);
        put(Biomes.CRIMSON_FOREST, INFERNO);
        put(Biomes.SOUL_SAND_VALLEY, INFERNO);
        put(Biomes.BASALT_DELTAS, INFERNO);
        put(Biomes.THE_END, VOID);
        put(Biomes.END_HIGHLANDS, VOID);
        put(Biomes.END_MIDLANDS, VOID);
        put(Biomes.SMALL_END_ISLANDS, VOID);
        put(Biomes.END_BARRENS, VOID);
	}};
	
	public static Ecosystem getBiomeTemp(ResourceKey<Biome> biome) {
		return temps.get(biome);
	}
}
