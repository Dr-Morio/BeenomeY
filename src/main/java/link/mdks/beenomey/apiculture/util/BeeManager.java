package link.mdks.beenomey.apiculture.util;

import java.util.HashMap;

import javax.annotation.Nullable;

import link.mdks.beenomey.init.ItemInit;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
    
    public static ItemStack getComb(BeeType type, @Nullable int count) {
    	return new ItemStack(beeCombs.get(type), count);
    }
    
    
}
