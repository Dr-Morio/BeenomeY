package link.mdks.beenomey.util;

import link.mdks.beenomey.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
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
    
    public static ItemStack getComb(BeeType type) {
    	return switch (type) {
    		case FOREST -> new ItemStack(ItemInit.HONEYCOMB.get()) ;
    		default -> new ItemStack(ItemInit.HONEYCOMB.get());
    	};
    	
    }
    
}
