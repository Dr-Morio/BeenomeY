package link.mdks.beenomey.datagen;


import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockRecipe;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockRecipe.Serializer;
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
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class ApiaryRecipeBuilder implements RecipeBuilder{
	

	//private final Ingredient ingredient;
	private final ItemStack result;
	private final ItemStack ingredientBee;
	private final ItemStack ingredientPrincess;
	private final int chance;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();
	
//	public ApiaryRecipeBuilder(ItemLike ingredient, ItemLike result, int count) {
//		this.ingredient = Ingredient.of(ingredient);
//		this.result = result.asItem();
//		this.count = count;
//	}
	public ApiaryRecipeBuilder(ItemStack ingredientBee, ItemStack ingredientPrincess, ItemStack result, int chance) {
		this.ingredientBee = ingredientBee;
		this.ingredientPrincess = ingredientPrincess;
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
		
//		pFinishedRecipeConsumer.accept(new ApiaryRecipeBuilder.Result(pRecipeId, this.result, this.count, this.ingredient, 
//				this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + 
//		this.result + "/" + pRecipeId.getPath())));
		
		pFinishedRecipeConsumer.accept(new ApiaryRecipeBuilder.Result(pRecipeId, this.result, this.chance, ingredientBee, ingredientPrincess, 
				this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + 
		this.result.getItem() + "/" + pRecipeId.getPath())));
		
	}
	
	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final ItemStack result;
		//private final Ingredient ingredient;
		private final ItemStack ingredientBee;
		private final ItemStack ingredientPrincess;
		private final int chance;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		
		public Result(ResourceLocation id, ItemStack pResult, int pChance, ItemStack ingredientBee, ItemStack ingredientPrincess, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
		
			this.id = id;
			this.result = pResult;
			this.ingredientBee = ingredientBee;
			this.ingredientPrincess = ingredientPrincess;
			this.chance = pChance;
			this.advancement = pAdvancement;
			this.advancementId = pAdvancementId;
			
		}
	
		@Override
		public void serializeRecipeData(JsonObject pJson) {
			//JsonArray jsonarray = new JsonArray();
			
			JsonArray ingredientsArray = new JsonArray();
			JsonArray outputArray = new JsonArray();
			JsonObject ingredientPrincessObject = new JsonObject();
			JsonObject ingredientBeeObject = new JsonObject();
			JsonObject outputOject = new JsonObject();
			
			// Add properties in reverse value
			// add Bee
			ingredientBeeObject.addProperty("secondType", ingredientPrincess.getTag().getString("SecondType").toString());
			ingredientBeeObject.addProperty("mainType", ingredientPrincess.getTag().getString("MainType").toString());
			ingredientBeeObject.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredientBee.getItem()).toString());
			ingredientsArray.add(ingredientBeeObject);
			// add Princess
			ingredientPrincessObject.addProperty("secondType", ingredientBee.getTag().getString("SecondType").toString());
			ingredientPrincessObject.addProperty("mainType", ingredientBee.getTag().getString("MainType").toString());
			ingredientPrincessObject.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredientPrincess.getItem()).toString());
			ingredientsArray.add(ingredientPrincessObject);

			
			pJson.add("ingredients", ingredientsArray);
			
			// add Output
			outputOject.addProperty("secondType", result.getTag().getString("SecondType").toString());
			outputOject.addProperty("mainType", result.getTag().getString("MainType").toString());
			outputOject.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
			//outputArray.add(outputOject);
			pJson.add("output", outputOject);
			
			
			
			
			
			
			
//			jsonarray.add(ingredient.toJson());
//			ArrayLiteralNode ingredientsArray = recipeNode.putArray("ingredients");
//			pJson.add("ingredients", jsonarray);
//			JsonObject jsonobject = new JsonObject();
//			
//			BeenomeY.LOGGER.debug("ARR: " + jsonarray + " ing: " + ingredient.toJson());
//			
//			//jsonobject.addProperty("item", this.result.asItem().toString());
//			jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
//			jsonobject.addProperty("TEST", "MY TEST");
//			// Here belongs count.... but its not used at the moment
//			
//			
//			pJson.add("output", jsonobject);
			
		}
	
		@Override
		public ResourceLocation getId() {
			
			String mTypeBee = ingredientBee.getTag().get("MainType").getAsString();
			String sTypeBee = ingredientBee.getTag().get("SecondType").getAsString();
			String mTypePrincess = ingredientPrincess.getTag().get("MainType").getAsString();
			String sTypePrincess = ingredientPrincess.getTag().get("SecondType").getAsString();
			String mTypeResult = result.getTag().get("MainType").getAsString();
			String sTypeResult = result.getTag().get("SecondType").getAsString();
			
			String path = (mTypeBee + "_" + sTypeBee + "_bee__" + 
					mTypePrincess + "_" + sTypePrincess + "_princess__" +
					mTypeResult + "_" + sTypeResult).toLowerCase() + "_bee";
			

			ResourceLocation name = new ResourceLocation(BeenomeY.MODID, path);
			
			return name;

		}
	
		@Override
		public RecipeSerializer<?> getType() {
			return ApiaryModBlockRecipe.Serializer.INSTANCE;
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


