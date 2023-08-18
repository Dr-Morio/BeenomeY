package link.mdks.beenomey.datagen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.init.BeeInit;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class BeenomeyRecipeProvider extends RecipeProvider implements IConditionBuilder{

	public BeenomeyRecipeProvider(PackOutput output) {
		super(output);
	}

	
	private List<ItemStack> dummyRecipeBuilder(BeeType bT1, BeeType bT2, BeeType pT1, BeeType pT2, BeeType oT1, BeeType oT2) {
		List<ItemStack> holder = new ArrayList<ItemStack>();
		holder.add(BeeManager.getBee(bT1, bT2, new ItemStack(BeeInit.getCommonBee())));
		holder.add(BeeManager.getBee(pT1, pT2, new ItemStack(BeeInit.getPrincessBee())));
		holder.add(BeeManager.getBee(oT1, oT2, new ItemStack(BeeInit.getCommonBee())));
		return holder;
	}
	
	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		
		// Hardcoded Dummy Recipe Set
		List<ItemStack> cleanDummyRecipe_1 = dummyRecipeBuilder(BeeType.FOREST,BeeType.FOREST,BeeType.FOREST,BeeType.FOREST,BeeType.FOREST,BeeType.FOREST); //Holds 3 Item Stacks // Input + Input = Output
		List<ItemStack> cleanDummyRecipe_2 = dummyRecipeBuilder(BeeType.INFERNO,BeeType.INFERNO,BeeType.INFERNO,BeeType.INFERNO,BeeType.INFERNO,BeeType.INFERNO);
		
		List<ItemStack> crossDummyRecipe_1 = dummyRecipeBuilder(BeeType.FOREST,BeeType.FOREST,BeeType.OCEAN,BeeType.OCEAN,BeeType.OCEAN,BeeType.FOREST);
		List<ItemStack> crossDummyRecipe_2 = dummyRecipeBuilder(BeeType.INFERNO,BeeType.INFERNO,BeeType.OCEAN,BeeType.OCEAN,BeeType.OCEAN,BeeType.INFERNO);
		
		List<ItemStack> crossDummyRecipe_3v1 = dummyRecipeBuilder(BeeType.FOREST,BeeType.INFERNO,BeeType.OCEAN,BeeType.OCEAN,BeeType.OCEAN,BeeType.FOREST);
		List<ItemStack> crossDummyRecipe_3v2 = dummyRecipeBuilder(BeeType.FOREST,BeeType.INFERNO,BeeType.OCEAN,BeeType.OCEAN,BeeType.OCEAN,BeeType.INFERNO);
		
		List<List<ItemStack>> recipes = new ArrayList<List<ItemStack>>();
		recipes.add(cleanDummyRecipe_1);
		recipes.add(cleanDummyRecipe_2);
		recipes.add(crossDummyRecipe_1);
		recipes.add(crossDummyRecipe_2);
		recipes.add(crossDummyRecipe_3v1);
		recipes.add(crossDummyRecipe_3v2);
		
		for(List<ItemStack> recipe : recipes) {
			new ApiaryRecipeBuilder(recipe.get(0), recipe.get(1), recipe.get(2), 50)
			.unlockedBy("has_bee", inventoryTrigger(ItemPredicate.Builder.item()
					.of(Items.DIRT).build()))
			.save(consumer);
		}
		
//		ItemStack bee = BeeManager.getBee(BeeType.COPPER, BeeType.DIAMOND, new ItemStack(BeeInit.getCommonBee()));
//		ItemStack princess = BeeManager.getBee(BeeType.FOREST, BeeType.ENDER, new ItemStack(BeeInit.getPrincessBee()));
//		ItemStack resultBee = BeeManager.getBee(BeeType.FOREST, BeeType.DIAMOND, new ItemStack(BeeInit.getCommonBee()));
//		
//		
//		
//		new ApiaryRecipeBuilder(bee, princess, resultBee, 50)
//		.unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item()
//				.of(Items.DIRT).build()))
//		.save(consumer);
		//beeCombinationBuilder(consumer);
	}
}

	

