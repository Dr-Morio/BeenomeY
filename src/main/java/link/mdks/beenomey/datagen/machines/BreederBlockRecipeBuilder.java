package link.mdks.beenomey.datagen.machines;

import java.util.List;
import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class BreederBlockRecipeBuilder implements RecipeBuilder{
	
	private final List<ItemStack> bees;
	private final FluidStack catalysator;
	private final ItemStack result;
	private final int chance;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	public BreederBlockRecipeBuilder(List<ItemStack> bees, FluidStack catalysator, ItemStack result, int chance) {
		this.bees = bees;
		this.catalysator = catalysator;
		this.result = result;
		this.chance = chance;
	}
	
	@Override
	public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
		this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
		return this;
	}

	@Override
	public RecipeBuilder group(String pGroupName) {
		return this;
	}

	@Override
	public Item getResult() {
		return result.getItem();
	}

	@Override
	public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
		this.advancement.parent(new ResourceLocation("recipes/root"))
		.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
		.rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
		pFinishedRecipeConsumer.accept(new BreederBlockRecipeBuilder.Result(pRecipeId, bees, catalysator, result, chance, advancement,
				new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + this.result.getItem() + "/" + pRecipeId.getPath())));
	}
	
	public static class Result implements FinishedRecipe {

		@SuppressWarnings("unused")
		private final ResourceLocation id;
		private final List<ItemStack> bees;
		private final FluidStack catalysator;
		private final ItemStack result;
		private final int chance;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		
		public Result(ResourceLocation id, List<ItemStack> bees, FluidStack catalysator, ItemStack result, int chance, Advancement.Builder advancement, ResourceLocation advancementId) {
			this.id = id;
			this.bees = bees;
			this.catalysator = catalysator;
			this.result = result;
			this.chance = chance;
			this.advancement = advancement;
			this.advancementId = advancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject pJson) {
			JsonArray ingredientsArray = new JsonArray();
			JsonObject fluidObject = new JsonObject();
			JsonObject outputOject = new JsonObject();
			
			for(ItemStack bee : bees) {
				JsonObject ingredientBeeObject = new JsonObject();
				ingredientBeeObject.addProperty("secondType", bee.getTag().getString("SecondType").toString());
				ingredientBeeObject.addProperty("mainType", bee.getTag().getString("MainType").toString());
				ingredientBeeObject.addProperty("item", ForgeRegistries.ITEMS.getKey(bee.getItem()).toString());
				ingredientsArray.add(ingredientBeeObject);
			}
			
			pJson.add("ingredients", ingredientsArray);
			
			//fluidObject.addProperty("FluidName", catalysator.getTranslationKey());
			fluidObject.addProperty("FluidName", ForgeRegistries.FLUIDS.getKey(catalysator.getFluid()).toString());
			fluidObject.addProperty("Amount", catalysator.getAmount());
			pJson.add("fluid", fluidObject);
			
			outputOject.addProperty("chance", this.chance);
			outputOject.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
			//outputOject.addProperty("mainType", bees.get(0).getTag().getString("MainType").toString());
			outputOject.addProperty("mainType", result.getTag().getString("MainType").toString());
			outputOject.addProperty("secondType", result.getTag().getString("SecondType").toString());
			pJson.add("output", outputOject);
			
			
		}

		@Override
		public ResourceLocation getId() {
			String resultBeeMainType = this.result.getTag().get("MainType").getAsString();
			String path = ("breeder_" + resultBeeMainType.toLowerCase() + "_bee");
			ResourceLocation name = new ResourceLocation(BeenomeY.MODID, path);
			return name;
		}

		@Override
		public RecipeSerializer<?> getType() {
			return BreederBlockRecipe.Serializer.INSTANCE;
		}

		@Override
		public JsonObject serializeAdvancement() {
			return this.advancement.serializeToJson();
		}
	
		@Override
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
		
	}

}
