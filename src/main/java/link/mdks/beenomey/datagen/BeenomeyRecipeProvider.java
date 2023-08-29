package link.mdks.beenomey.datagen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.antlr.v4.runtime.misc.Triple;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import link.mdks.beenomey.apiculture.recipehandler.BreederBlockRecipeHandler;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.datagen.machines.ApiaryBeeRecipeBuilder;
import link.mdks.beenomey.datagen.machines.ApiaryCombRecipeBuilder;
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
import oshi.util.tuples.Triplet;

public class BeenomeyRecipeProvider extends RecipeProvider implements IConditionBuilder{

	public BeenomeyRecipeProvider(PackOutput output) {
		super(output);
	}
	
	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		
		/* Recipe Set for Clean Breed via Apiary */
		for (BeeType type : BeeType.values()) {
			if(type != BeeType.EMPTY) {
				new ApiaryBeeRecipeBuilder(
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())),
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())),
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())))
				.unlockedBy("has_bee", inventoryTrigger(ItemPredicate.Builder.item()
						.of(BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())).getItem()).build()))
				.save(consumer);
			}
		}
		
		/* Recipe Set for Princess Breeding via Apiary */
		for (BeeType type : BeeType.values()) {
			if(type != BeeType.EMPTY) {
				new ApiaryPrincessRecipeBuilder(
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())),
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())))
				.unlockedBy("has_princess", inventoryTrigger(ItemPredicate.Builder.item()
						.of(BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())).getItem()).build()))
				.save(consumer);
			}
		}
		
		/* Recipe Set for Honeycombs */
		for (BeeType type : BeeType.values()) {
			if(type != BeeType.EMPTY) {
				new ApiaryCombRecipeBuilder(
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())),
						BeeManager.getBee(type, type, new ItemStack(BeeInit.getPrincessBee())),
						BeeManager.getComb(type, 1))
				.unlockedBy("has_bee", inventoryTrigger(ItemPredicate.Builder.item()
						.of(BeeManager.getBee(type, type, new ItemStack(BeeInit.getCommonBee())).getItem()).build()))
				.save(consumer);
			}
		}
		
		/* Recipe set for Breeder */
		for(Triplet<BeeType, BeeType, BeeType> recipe  : BreederBlockRecipeHandler.getRecipes()) {
			List<ItemStack> bees = new ArrayList<ItemStack>();
			bees.add(BeeManager.getBee(recipe.getA(), recipe.getA(), new ItemStack(BeeInit.getCommonBee())));
			bees.add(BeeManager.getBee(recipe.getA(), recipe.getA(), new ItemStack(BeeInit.getCommonBee())));
			bees.add(BeeManager.getBee(recipe.getB(), recipe.getB(), new ItemStack(BeeInit.getCommonBee())));
			bees.add(BeeManager.getBee(recipe.getB(), recipe.getB(), new ItemStack(BeeInit.getCommonBee())));
			
			new BreederBlockRecipeBuilder(
					bees,
					new FluidStack(BeeManager.getFluid(recipe.getC()), 4000), 
					BeeManager.getBee(recipe.getC(), recipe.getC(), new ItemStack(BeeInit.getCommonBee())), 20)
			.unlockedBy("has_bees", inventoryTrigger(ItemPredicate.Builder.item().of(BeeInit.getCommonBee()).build()))
			.save(consumer);;
		};
		
//		new BreederBlockRecipeBuilder(
//				dummyBeeIngredientBreederRecipeBuilder(BeeType.FOREST,  BeeType.FOREST, 4),
//				new FluidStack(FluidInit.SOURCE_INFERNO_HONEY.get(), 4000),
//				BeeManager.getBee(BeeType.FOREST, BeeType.INFERNO, new ItemStack(BeeInit.getCommonBee())),
//				5)
//		.unlockedBy("has_bees", inventoryTrigger(ItemPredicate.Builder.item().of(BeeInit.getCommonBee()).build()))
//		.save(consumer);
//		
//		new BreederBlockRecipeBuilder(
//				dummyBeeIngredientBreederRecipeBuilder(BeeType.FROZEN,  BeeType.INFERNO, 4),
//				new FluidStack(FluidInit.SOURCE_FROZEN_HONEY.get(), 4000),
//				BeeManager.getBee(BeeType.FROZEN, BeeType.FROZEN, new ItemStack(BeeInit.getCommonBee())),
//				5)
//		.unlockedBy("has_bees", inventoryTrigger(ItemPredicate.Builder.item().of(BeeInit.getCommonBee()).build()))
//		.save(consumer);
	}
}

	

