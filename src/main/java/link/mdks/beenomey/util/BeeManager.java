package link.mdks.beenomey.util;

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
    		case ICE -> new ItemStack(ItemInit.ICEY_HONEYCOMB.get(), count);
    		case WATER -> new ItemStack(ItemInit.WATERY_HONEYCOMB.get(), count);
    		case SAND -> new ItemStack(ItemInit.SAND_HONEYCOMB.get(), count);
    		case STONE -> new ItemStack(ItemInit.STONE_HONEYCOMB.get(), count);
    		case NETHER -> new ItemStack(ItemInit.NETHER_HONEYCOMB.get(), count);
    		case ENDER -> new ItemStack(ItemInit.ENDER_HONEYCOMB.get(), count);
    		case LAVA -> new ItemStack(ItemInit.LAVA_HONEYCOMB.get(), count);
    		case PAPER -> new ItemStack(ItemInit.PAPER_HONEYCOMB.get(), count);
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
