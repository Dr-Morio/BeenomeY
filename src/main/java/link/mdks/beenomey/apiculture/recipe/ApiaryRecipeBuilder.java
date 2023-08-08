package link.mdks.beenomey.apiculture.recipe;

import java.util.function.Consumer;import org.antlr.v4.parse.ANTLRParser.prequelConstruct_return;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.BeenomeY;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class ApiaryRecipeBuilder implements RecipeBuilder{
	
	private final Item result;
	private final Ingredient ingredient;
	private final int count;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();
	
	public ApiaryRecipeBuilder(ItemLike ingredient, ItemLike result, int count) {
		this.ingredient = Ingredient.of(ingredient);
		this.result = result.asItem();
		this.count = count;
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
		return result;
	}

	@Override
	public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
		this.advancement.parent(new ResourceLocation("recipes/root"))
		.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
		.rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
		
		pFinishedRecipeConsumer.accept(new ApiaryRecipeBuilder.Result(pRecipeId, this.result, this.count, this.ingredient, 
				this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + 
		this.result + "/" + pRecipeId.getPath())));
		
	}
	
	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final Ingredient ingredient;
		private final int count;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		
		public Result(ResourceLocation id, Item pResult, int pCount, Ingredient ingredient, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
		
			this.id = id;
			this.result = pResult;
			this.ingredient = ingredient;
			this.count = pCount;
			this.advancement = pAdvancement;
			this.advancementId = pAdvancementId;
			
		}
	
		@Override
		public void serializeRecipeData(JsonObject pJson) {
			JsonArray jsonarray = new JsonArray();
			jsonarray.add(ingredient.toJson());
			jsonarray.add("DAFUQ");
			
			pJson.add("ingredients", jsonarray);
			JsonObject jsonobject = new JsonObject();
			
			BeenomeY.LOGGER.debug("ARR: " + jsonarray + " ing: " + ingredient.toJson());
			
			//jsonobject.addProperty("item", this.result.asItem().toString());
			jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
			jsonobject.addProperty("TEST", "MY TEST");
			// Here belongs count.... but its not used at the moment
			
			
			pJson.add("output", jsonobject);
			
		}
	
		@Override
		public ResourceLocation getId() {
			return new ResourceLocation(BeenomeY.MODID, 
					this.result.asItem().toString() + "_from_apiary");
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


