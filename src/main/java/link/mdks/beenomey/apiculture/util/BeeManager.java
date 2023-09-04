package link.mdks.beenomey.apiculture.util;

import java.util.HashMap;

import link.mdks.beenomey.init.FluidInit;
import link.mdks.beenomey.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public class BeeManager {
    private static BeeType mainBeeType;
    private static BeeType secondBeeType;
    private static CompoundTag tag;
    private static ItemStack itemStack;
    
    public static ItemStack getBee(BeeType MainBeeType, BeeType SecondBeeType, ItemStack ItemStack) {
        mainBeeType = MainBeeType;
        secondBeeType = SecondBeeType;
        tag = ItemStack.getTag();
        itemStack = ItemStack;

        int lifeCycle = mainBeeType.lifecycle;
        float lifecycleMultiplier = secondBeeType.lifecycleMultiplier;
        int randomTickChance = mainBeeType.randomTickChance;
        int compDrop = mainBeeType.compDrop;
        float compDropMultiplier = secondBeeType.compDropMultiplier;

        tag.putString("MainType", mainBeeType.name());
        tag.putString("SecondType", secondBeeType.name());

        tag.putInt("LifeCycle", lifeCycle);
        tag.putFloat("LifecycleMultiplier", lifecycleMultiplier);
        tag.putInt("EffectiveLifecycle", CalculateEffectiveValue(lifeCycle, lifecycleMultiplier));
        tag.putInt("EffectiveLifecycleAD", CalculateEffectiveValue(lifeCycle, lifecycleMultiplier));

        tag.putInt("RandomTickChance", randomTickChance);

        tag.putFloat("CompDrop", compDrop);
        tag.putFloat("CompDropMultiplier", compDropMultiplier);
        tag.putInt("EffectiveCompDrop", CalculateEffectiveValue(compDrop, compDropMultiplier));
        tag.putString("Ecosystem", MainBeeType.ecosystem.toString());

        itemStack.setTag(tag);

        // Check if maxDamage is higher than EffectiveLifecycle
        if (itemStack.getMaxDamage() > (lifeCycle * lifecycleMultiplier)) {
            itemStack.setDamageValue(itemStack.getMaxDamage() - Math.round(lifeCycle * lifecycleMultiplier));
        }
        return itemStack;
    }
    

    private static int CalculateEffectiveValue(float value, float multiplier) {
        return Math.round(value * multiplier);
    }
    
    @SuppressWarnings("serial")
	private static HashMap<BeeType, Item> beeCombs = new HashMap<BeeType, Item>() {{
    	put(BeeType.EMPTY, ItemInit.HONEYCOMB.get());
    	put(BeeType.FOREST, ItemInit.HONEYCOMB.get());
    	put(BeeType.FROZEN, ItemInit.FROZEN_HONEYCOMB.get());
    	put(BeeType.OCEAN, ItemInit.OCEAN_HONEYCOMB.get());
    	put(BeeType.DESERT, ItemInit.DESERT_HONEYCOMB.get());
    	put(BeeType.ROCK, ItemInit.ROCK_HONEYCOMB.get());
    	put(BeeType.INFERNO, ItemInit.INFERNO_HONEYCOMB.get());
    	put(BeeType.VOID, ItemInit.VOID_HONEYCOMB.get());
    	put(BeeType.COAL, ItemInit.COAL_HONEYCOMB.get());
    	put(BeeType.GUNPOWDER, ItemInit.GUNPOWDER_HONEYCOMB.get());
    	put(BeeType.FLINT, ItemInit.FLINT_HONEYCOMB.get());
    	put(BeeType.SLIME, ItemInit.SLIME_HONEYCOMB.get());
    	put(BeeType.SUGAR, ItemInit.SUGAR_HONEYCOMB.get());
    	put(BeeType.GLASS, ItemInit.GLASS_HONEYCOMB.get());
    	put(BeeType.CLAY, ItemInit.CLAY_HONEYCOMB.get());
    	put(BeeType.STRING, ItemInit.STRING_HONEYCOMB.get());
    	put(BeeType.NETHER_WART, ItemInit.NETHER_WART_HONEYCOMB.get());
    	put(BeeType.SOUL_SAND, ItemInit.SOUL_SAND_HONEYCOMB.get());
    	put(BeeType.MUSHROOM, ItemInit.MUSHROOM_HONEYCOMB.get());
    	put(BeeType.LAVA, ItemInit.LAVA_HONEYCOMB.get());
    	put(BeeType.LAPIS_LAZULI, ItemInit.LAPIS_LAZULI_HONEYCOMB.get());
    	put(BeeType.REDSTONE, ItemInit.REDSTONE_HONEYCOMB.get());
    	put(BeeType.IRON, ItemInit.IRON_HONEYCOMB.get());
    	put(BeeType.GOLD, ItemInit.GOLD_HONEYCOMB.get());
    	put(BeeType.COPPER, ItemInit.COPPER_HONEYCOMB.get());
    	put(BeeType.DIAMOND, ItemInit.DIAMOND_HONEYCOMB.get());
    	put(BeeType.OBSIDIAN, ItemInit.OBSIDIAN_HONEYCOMB.get());
    	put(BeeType.GLOWSTONE, ItemInit.GLOWSTONE_HONEYCOMB.get());
    	put(BeeType.BLAZE, ItemInit.BLAZE_HONEYCOMB.get());
    	put(BeeType.EMERALD, ItemInit.EMERALD_HONEYCOMB.get());
    	put(BeeType.NETEHR_STAR, ItemInit.NETHER_STAR_HONEYCOMB.get());
    	put(BeeType.NETHER_QUARZ, ItemInit.NETHER_QUARZ_HONEYCOMB.get());
    	put(BeeType.PRISMARINE, ItemInit.PRISMARINE_HONEYCOMB.get());
    	put(BeeType.CHORUS, ItemInit.CHORUS_HONEYCOMB.get());
    	put(BeeType.NETHERITE, ItemInit.NETHERITE_HONEYCOMB.get());
    	put(BeeType.EXP, ItemInit.EXP_HONEYCOMB.get());
    	put(BeeType.ENDER_PEARL, ItemInit.ENDER_PEARL_HONEYCOMB.get());
    	put(BeeType.AMETHYST, ItemInit.AMETHYST_HONEYCOMB.get());
    }};
    
    public static ItemStack getComb(BeeType type, int count) {
    	return new ItemStack(beeCombs.get(type), count);
    }
 
    @SuppressWarnings("serial")
	private static HashMap<BeeType, ResourceLocation> beeFluids = new HashMap<BeeType, ResourceLocation>() {{
    	put(BeeType.FOREST,             FluidInit.SOURCE_HONEY.getId());
    	put(BeeType.FROZEN,             FluidInit.SOURCE_FROZEN_HONEY.getId());
    	put(BeeType.DESERT,             FluidInit.SOURCE_DESERT_HONEY.getId());
    	put(BeeType.ROCK,               FluidInit.SOURCE_ROCK_HONEY.getId());
    	put(BeeType.INFERNO,            FluidInit.SOURCE_INFERNO_HONEY.getId());
    	put(BeeType.VOID,               FluidInit.SOURCE_VOID_HONEY.getId());
    	put(BeeType.COAL,               FluidInit.SOURCE_COAL_HONEY.getId());
    	put(BeeType.GUNPOWDER,          FluidInit.SOURCE_GUNPOWDER_HONEY.getId());
    	put(BeeType.FLINT,              FluidInit.SOURCE_FLINT_HONEY.getId());
    	put(BeeType.SLIME,              FluidInit.SOURCE_SLIME_HONEY.getId());
    	put(BeeType.SUGAR,              FluidInit.SOURCE_SUGAR_HONEY.getId());
    	put(BeeType.GLASS,              FluidInit.SOURCE_GLASS_HONEY.getId());
    	put(BeeType.CLAY,               FluidInit.SOURCE_CLAY_HONEY.getId());
    	put(BeeType.STRING,             FluidInit.SOURCE_STRING_HONEY.getId());
    	put(BeeType.NETHER_WART,        FluidInit.SOURCE_NETHER_WART_HONEY.getId());
    	put(BeeType.SOUL_SAND,          FluidInit.SOURCE_SOUL_SAND_HONEY.getId());
    	put(BeeType.MUSHROOM,           FluidInit.SOURCE_MUSHROOM_HONEY.getId());
    	put(BeeType.LAVA,               FluidInit.SOURCE_LAVA_HONEY.getId());
    	put(BeeType.LAPIS_LAZULI,       FluidInit.SOURCE_LAPIS_LAZULI_HONEY.getId());
    	put(BeeType.REDSTONE,           FluidInit.SOURCE_REDSTONE_HONEY.getId());
    	put(BeeType.IRON,               FluidInit.SOURCE_IRON_HONEY.getId());
    	put(BeeType.GOLD,               FluidInit.SOURCE_GOLD_HONEY.getId());
    	put(BeeType.COPPER,             FluidInit.SOURCE_COPPER_HONEY.getId());
    	put(BeeType.DIAMOND,            FluidInit.SOURCE_DIAMOND_HONEY.getId());
    	put(BeeType.OBSIDIAN,           FluidInit.SOURCE_OBSIDIAN_HONEY.getId());
    	put(BeeType.GLOWSTONE,          FluidInit.SOURCE_GLOWSTONE_HONEY.getId());
    	put(BeeType.BLAZE,              FluidInit.SOURCE_BLAZE_HONEY.getId());
    	put(BeeType.EMERALD,            FluidInit.SOURCE_EMERALD_HONEY.getId());
    	put(BeeType.NETEHR_STAR,        FluidInit.SOURCE_NETHER_STAR_HONEY.getId());
    	put(BeeType.NETHER_QUARZ,       FluidInit.SOURCE_NETHER_QUARZ_HONEY.getId());
    	put(BeeType.PRISMARINE,         FluidInit.SOURCE_PRISMARINE_HONEY.getId());
    	put(BeeType.CHORUS,             FluidInit.SOURCE_CHORUS_HONEY.getId());
    	put(BeeType.NETHERITE,          FluidInit.SOURCE_NETHERITE_HONEY.getId());
    	put(BeeType.EXP,                FluidInit.SOURCE_EXP_HONEY.getId());
    	put(BeeType.ENDER_PEARL,        FluidInit.SOURCE_ENDER_PEARL_HONEY.getId());
    	put(BeeType.AMETHYST,           FluidInit.SOURCE_AMETHYST_HONEY.getId());
    }};
    
    public static Fluid getFluid(BeeType type) {
    	return ForgeRegistries.FLUIDS.getValue(beeFluids.get(type));
    }
    
}
