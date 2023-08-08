package link.mdks.beenomey.datagen;

import java.util.function.Consumer;


import link.mdks.beenomey.apiculture.recipe.ApiaryRecipeBuilder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class BeenomeyRecipeProvider extends RecipeProvider implements IConditionBuilder{

	public BeenomeyRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		
		//ApiaryRecipeBuilder builder = 
			new ApiaryRecipeBuilder(Items.DIAMOND, Items.DIRT, 1)
			.unlockedBy("has_dirt", inventoryTrigger(ItemPredicate.Builder.item()
					.of(Items.DIRT).build()))
			.save(consumer);
				
		
		
//		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DIAMOND)
//				.requires(Items.DIRT)
//				.unlockedBy("has_dirt_block", inventoryTrigger(ItemPredicate.Builder.item()
//						.of(Items.DIRT).build()))
//				.save(consumer, new ResourceLocation(BeenomeY.MODID, Items.DIAMOND.toString()));;
//		
		// Shaped or Shapeless recipes
		// oreSmelting Recipes
		
		
		
//		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, 
//				BeeManager.getBee(BeeType.FOREST, BeeType.FOREST, new ItemStack(BeeInit.getCommonBee())).getItem())
//		.requires(BeeManager.getBee(BeeType.FOREST, BeeType.FOREST, new ItemStack(BeeInit.getCommonBee())).getItem())
//		.save(consumer);;
	}

}
