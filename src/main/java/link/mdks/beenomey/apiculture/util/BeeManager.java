package link.mdks.beenomey.apiculture.util;

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
    
    public static ItemStack getComb(BeeType type, @Nullable int count) {
    	return switch (type) {
    		case FOREST -> new ItemStack(ItemInit.HONEYCOMB.get(), count);
    		case FROZEN -> new ItemStack(ItemInit.FROZEN_HONEYCOMB.get(), count);
    		case OCEAN -> new ItemStack(ItemInit.OCEAN_HONEYCOMB.get(), count);
    		case DESERT -> new ItemStack(ItemInit.DESERT_HONEYCOMB.get(), count);
    		case ROCK -> new ItemStack(ItemInit.ROCK_HONEYCOMB.get(), count);
    		case INFERNO -> new ItemStack(ItemInit.INFERNO_HONEYCOMB.get(), count);
    		case VOID -> new ItemStack(ItemInit.VOID_HONEYCOMB.get(), count);
    		case LAVA -> new ItemStack(ItemInit.MAGMA_HONEYCOMB.get(), count);
    		case SUGAR -> new ItemStack(ItemInit.PAPER_HONEYCOMB.get(), count);
    		case LAPIS_LAZULI -> new ItemStack(ItemInit.LAPIS_HONEYCOMB.get(), count);
    		case REDSTONE -> new ItemStack(ItemInit.REDSTONE_HONEYCOMB.get(), count);
    		case IRON -> new ItemStack(ItemInit.IRON_HONEYCOMB.get(), count);
    		case GOLD -> new ItemStack(ItemInit.GOLD_HONEYCOMB.get(), count);
    		case COPPER -> new ItemStack(ItemInit.COPPER_HONEYCOMB.get(), count);
    		case DIAMOND -> new ItemStack(ItemInit.DIAMOND_HONEYCOMB.get(), count);
    		case OBSIDIAN -> new ItemStack(ItemInit.OBSIDIAN_HONEYCOMB.get(), count);
    		default -> new ItemStack(ItemInit.HONEYCOMB.get(), count);
    	};
    	
    }
    
    
}
