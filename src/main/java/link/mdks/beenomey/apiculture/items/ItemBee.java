package link.mdks.beenomey.apiculture.items;

import java.util.List;

import javax.annotation.Nullable;

import link.mdks.beenomey.util.BeeType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

public class ItemBee extends Item implements IForgeItem{

	public ItemBee(Properties properties) {
		super(new Item.Properties().durability(2).rarity(Rarity.COMMON));
	}


	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
		BeeType mainBeeType;
		BeeType secondBeeType;

		// Prevent errors when function is called without a loaded enum state
		if (itemStack.getTag().getString("MainType") == "") {
			mainBeeType = BeeType.EMPTY;
			secondBeeType = BeeType.EMPTY;
		} else {
			mainBeeType = BeeType.valueOf(itemStack.getTag().getString("MainType"));
			secondBeeType = BeeType.valueOf(itemStack.getTag().getString("SecondType"));
		}

		ChatFormatting mainColor = mainBeeType.textColor;
		ChatFormatting secondColor = secondBeeType.textColor;

		components.add(Component.literal("Main Type: " + itemStack.getTag().getString("MainType")).withStyle(mainColor));
		components.add(Component.literal("Second Type: " + itemStack.getTag().getString("SecondType")).withStyle(secondColor));

		if (Screen.hasShiftDown()) {
			components.add(Component.literal("Life: " + itemStack.getTag().getInt("EffectiveLifecycleAD")).withStyle(ChatFormatting.RED)); //After Damage
			components.add(Component.literal("Comp Drop : " + itemStack.getTag().getInt("EffectiveCompDrop") + "%"));
			if (Screen.hasAltDown()) {
				components.add(Component.literal("------------").withStyle(ChatFormatting.AQUA));
				components.add(Component.literal("LifeCycle: " + itemStack.getTag().getInt("LifeCycle")).withStyle(mainColor));
				components.add(Component.literal("CompDrop: " + itemStack.getTag().getInt("CompDrop")).withStyle(mainColor));
				components.add(Component.literal("LifecycleMultiplier: " + itemStack.getTag().getFloat("LifecycleMultiplier")).withStyle(secondColor));
				components.add(Component.literal("TikMultiplier: " + itemStack.getTag().getFloat("TikMultiplier")).withStyle(secondColor));
				components.add(Component.literal("CompDropMultiplier: " + itemStack.getTag().getFloat("CompDropMultiplier")).withStyle(secondColor));
				components.add(Component.literal("EffectiveLifecycle: " + itemStack.getTag().getInt("EffectiveLifecycle")));
				components.add(Component.literal("EffectiveLifecycleAD: " + itemStack.getTag().getInt("EffectiveLifecycleAD"))); //After Damage
				components.add(Component.literal("EffectiveCompDrop: " + itemStack.getTag().getInt("EffectiveCompDrop")));
				components.add(Component.literal("DEBUG: " + itemStack.getMaxDamage()));
				components.add(Component.literal("DEBUG: " + itemStack.getDamageValue()));
			}
		}
	}
}


