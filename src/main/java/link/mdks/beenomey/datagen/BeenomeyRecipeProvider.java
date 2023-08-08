package link.mdks.beenomey.datagen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.BeeInit;
import link.mdks.beenomey.apiculture.recipe.ApiaryRecipeBuilder;
import link.mdks.beenomey.util.BeeManager;
import link.mdks.beenomey.util.BeeType;
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

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		
		
		ItemStack bee = BeeManager.getBee(BeeType.COPPER, BeeType.DIAMOND, new ItemStack(BeeInit.getCommonBee()));
		ItemStack princess = BeeManager.getBee(BeeType.FOREST, BeeType.ENDER, new ItemStack(BeeInit.getPrincessBee()));
		ItemStack resultBee = BeeManager.getBee(BeeType.FOREST, BeeType.DIAMOND, new ItemStack(BeeInit.getCommonBee()));
		
		
		
		new ApiaryRecipeBuilder(bee, princess, resultBee, 50)
		.unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item()
				.of(Items.DIRT).build()))
		.save(consumer);
		//beeCombinationBuilder(consumer);
	}
	
	private void beeCombinationBuilder(Consumer<FinishedRecipe> consumer){
		
		// Create all possible combinations
		List<List<BeeType>> typeCombinations = new ArrayList<List<BeeType>>();
		List<BeeType> beeTypes = new ArrayList<BeeType>();
		beeTypes = List.of(BeeType.values());
		
		// Creates a list which holds lists with 2 entrys -> MainType, SecondType -> stored in typeCombinations
		for (BeeType mT : beeTypes) {
			for (BeeType sT : beeTypes) {
				List<BeeType> typeCombination = new ArrayList<BeeType>();
				typeCombination.add(mT);
				typeCombination.add(sT);
				typeCombinations.add(typeCombination);
			};
		};	
				
		// All Recipe Combinations Bee(type, type) + Princess(type, type) = bee(type, type) x 3
		List<RecipeTypeHolder> recipes = new ArrayList<RecipeTypeHolder>();
		
		for (List<BeeType> lBT : typeCombinations) {
			for (List<BeeType> lPT : typeCombinations) {
				
				List<BeeType> result1 = new ArrayList<BeeType>();
				result1.add(lPT.get(0)); // Princess Type 
				result1.add(lBT.get(0)); // Bee Type
				RecipeTypeHolder Comb1 = new RecipeTypeHolder(lBT, lPT, result1, 5); // Chance 50 %
				
				List<BeeType> result2 = new ArrayList<BeeType>();
				result2.add(lPT.get(0)); // Princess Type 
				result2.add(lBT.get(1)); // Bee Type
				RecipeTypeHolder Comb2 = new RecipeTypeHolder(lBT, lPT, result2, 5);
				
				// Prevent adding of Duplicate Recipes
				if (result1.get(0) == result2.get(0) && result1.get(1) == result2.get(1)) {
					BeenomeY.LOGGER.debug("DEBUG DUP: Duplicate Found");
					recipes.add(Comb1);
				} else {
					recipes.add(Comb1);
					recipes.add(Comb2);
				}

			}
		}
		
		//JUST DEBUG
//		for (RecipeTypeHolder l : recipes) {
//			BeenomeY.LOGGER.debug("DEBUG REC: " + l.getBeeType() + " + " + l.getPrincessType() + " = " + l.getResult());
//			
//			ItemStack bee = BeeManager.getBee(l.getBeeType().get(0), l.getBeeType().get(1), new ItemStack(BeeInit.getCommonBee()));
//			ItemStack princess = BeeManager.getBee(l.getPrincessType().get(0), l.getPrincessType().get(1), new ItemStack(BeeInit.getPrincessBee()));
//			ItemStack resultBee = BeeManager.getBee(l.getResult().get(0), l.getResult().get(1), new ItemStack(BeeInit.getCommonBee()));
//			
//			new ApiaryRecipeBuilder(bee, princess, resultBee, l.getResultChance())
//			.unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item()
//					.of(Items.DIRT).build()))
//					.save(consumer);
			
			
//			new ApiaryRecipeBuilder(BeeManager.getBee(BeeType.FOREST, BeeType.FOREST, new ItemStack(BeeInit.getCommonBee())),
//			BeeManager.getBee(BeeType.ENDER, BeeType.ENDER, new ItemStack(BeeInit.getPrincessBee())),
//			Items.DIRT, 1)
//	.unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item()
//			.of(Items.DIRT).build()))
//	.save(consumer);
			
		}
	
	private class RecipeTypeHolder {
		private final List<BeeType> beeType;
		private final List<BeeType> princessType;
		private final List<BeeType> result;
		private final int chance;
		
		public RecipeTypeHolder(List<BeeType> beeType, List<BeeType> princessType, List<BeeType> result, int chance) {
			this.beeType = beeType;
			this.princessType = princessType;
			this.result = result;
			this.chance = chance;
		}
		
		public List<BeeType> getResult() {
			return this.result;
		}
		public int getResultChance() {
			return this.chance;
		}
		
		public List<BeeType> getBeeType() {
			return this.beeType;
		}
		
		public List<BeeType> getPrincessType() {
			return this.princessType;
		}
		
	}
		
	}



	

