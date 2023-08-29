package link.mdks.beenomey.datagen.machines;


import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockCombRecipeReader;
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
import net.minecraftforge.registries.ForgeRegistries;

public class ApiaryCombRecipeBuilder implements RecipeBuilder{
	
	private final ItemStack result;
	private final ItemStack ingredientBee;
	private final ItemStack ingredientPrincess;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	public ApiaryCombRecipeBuilder(ItemStack ingredientBee, ItemStack ingredientPrincess, ItemStack result) {
		this.ingredientBee = ingredientBee;
		this.ingredientPrincess = ingredientPrincess;
		this.result = result;
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
		.addCriterion("has_comb_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
		.rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
;
		
		pFinishedRecipeConsumer.accept(new ApiaryCombRecipeBuilder.Result(pRecipeId, this.result, ingredientBee, ingredientPrincess, 
				this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + 
		this.result.getItem() + "/" + pRecipeId.getPath())));
		
	}
	
	public static class Result implements FinishedRecipe {
		
		@SuppressWarnings("unused")
		private final ResourceLocation id;
		private final ItemStack result;
		private final ItemStack ingredientBee;
		private final ItemStack ingredientPrincess;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		
		public Result(ResourceLocation id, ItemStack pResult, ItemStack ingredientBee, ItemStack ingredientPrincess, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
		
			this.id = id;
			this.result = pResult;
			this.ingredientBee = ingredientBee;
			this.ingredientPrincess = ingredientPrincess;
			this.advancement = pAdvancement;
			this.advancementId = pAdvancementId;
			
		}
	
		@Override
		public void serializeRecipeData(JsonObject pJson) {
			JsonArray ingredientsArray = new JsonArray();
			JsonObject ingredientPrincessObject = new JsonObject();
			JsonObject ingredientBeeObject = new JsonObject();
			JsonObject outputOject = new JsonObject();
			
			// Add properties of Item in Reverse Order

			// add 1. Bee
			ingredientBeeObject.addProperty("secondType", ingredientBee.getTag().getString("SecondType").toString());
			ingredientBeeObject.addProperty("mainType", ingredientBee.getTag().getString("MainType").toString());
			ingredientBeeObject.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredientBee.getItem()).toString());
			ingredientsArray.add(ingredientBeeObject);
			// add 2. Bee
			ingredientBeeObject.addProperty("secondType", ingredientBee.getTag().getString("SecondType").toString());
			ingredientBeeObject.addProperty("mainType", ingredientBee.getTag().getString("MainType").toString());
			ingredientBeeObject.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredientBee.getItem()).toString());
			ingredientsArray.add(ingredientBeeObject);
			// add 3. Bee
			ingredientBeeObject.addProperty("secondType", ingredientBee.getTag().getString("SecondType").toString());
			ingredientBeeObject.addProperty("mainType", ingredientBee.getTag().getString("MainType").toString());
			ingredientBeeObject.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredientBee.getItem()).toString());
			ingredientsArray.add(ingredientBeeObject);
			// add Princess
			ingredientPrincessObject.addProperty("secondType", ingredientPrincess.getTag().getString("SecondType").toString());
			ingredientPrincessObject.addProperty("mainType", ingredientPrincess.getTag().getString("MainType").toString());
			ingredientPrincessObject.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredientPrincess.getItem()).toString());
			ingredientsArray.add(ingredientPrincessObject);
			
			pJson.add("ingredients", ingredientsArray);
			
			// add Output
			outputOject.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
			pJson.add("output", outputOject);
	
		}
	
		@Override
		public ResourceLocation getId() {
			
			String mTypeBee = ingredientBee.getTag().get("MainType").getAsString();
					
			String path = ("apiary_" + mTypeBee.toLowerCase() + "_comb");
			

			ResourceLocation name = new ResourceLocation(BeenomeY.MODID, path);
			
			return name;

		}
	
		@Override
		public RecipeSerializer<?> getType() {
			return ApiaryModBlockCombRecipeReader.Serializer.INSTANCE;
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


