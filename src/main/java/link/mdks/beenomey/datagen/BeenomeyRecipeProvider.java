package link.mdks.beenomey.datagen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.datagen.machines.ApiaryBeeRecipeBuilder;
import link.mdks.beenomey.datagen.machines.ApiaryPrincessRecipeBuilder;
import link.mdks.beenomey.datagen.machines.BreederBlockRecipeBuilder;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.FluidInit;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fluids.FluidStack;

public class BeenomeyRecipeProvider extends RecipeProvider implements IConditionBuilder{

	public BeenomeyRecipeProvider(PackOutput output) {
		super(output);
	}
	
	private List<ItemStack> dummyBeeIngredientBreederRecipeBuilder(BeeType bT1, BeeType bT2, int beeAmount) {
		List<ItemStack> ingredients = new ArrayList<ItemStack>();
		for (int i = 0; i < beeAmount; i++) {
			ingredients.add(BeeManager.getBee(bT1, bT2, new ItemStack(BeeInit.getCommonBee())));
		}
		return ingredients;
	}
	
	
	
	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		
		//TODO: Add recipes for JEI... the right ones
		
		// Hardcoded Recipe Set for Clean Breed via Apiary
		for (BeeType type : BeeType.values()) {
			new ApiaryBeeRecipeBuilder(
					BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())),
					BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())),
					BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())))
			.unlockedBy("has_bee", inventoryTrigger(ItemPredicate.Builder.item()
					.of(BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())).getItem()).build()))
			.save(consumer);
		}
		
		// Hardcoded Recipe Set for Princess Breeding via Apiary
		for (BeeType type : BeeType.values()) {
			new ApiaryPrincessRecipeBuilder(
					BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())),
					BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())))
			.unlockedBy("has_princess", inventoryTrigger(ItemPredicate.Builder.item()
					.of(BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())).getItem()).build()))
			.save(consumer);
		}
		
		// Hardcoded Dummy Recipe set for Breeder
		new BreederBlockRecipeBuilder(
				dummyBeeIngredientBreederRecipeBuilder(BeeType.FOREST,  BeeType.FOREST, 4),
				new FluidStack(FluidInit.SOURCE_INFERNO_HONEY.get(), 4000),
				BeeManager.getBee(BeeType.FOREST, BeeType.INFERNO, new ItemStack(BeeInit.getCommonBee())),
				5)
		.unlockedBy("has_bees", inventoryTrigger(ItemPredicate.Builder.item().of(BeeInit.getCommonBee()).build()))
		.save(consumer);
		
		new BreederBlockRecipeBuilder(
				dummyBeeIngredientBreederRecipeBuilder(BeeType.FROZEN,  BeeType.INFERNO, 4),
				new FluidStack(FluidInit.SOURCE_FROZEN_HONEY.get(), 4000),
				BeeManager.getBee(BeeType.FROZEN, BeeType.FROZEN, new ItemStack(BeeInit.getCommonBee())),
				5)
		.unlockedBy("has_bees", inventoryTrigger(ItemPredicate.Builder.item().of(BeeInit.getCommonBee()).build()))
		.save(consumer);
	}
}

	

