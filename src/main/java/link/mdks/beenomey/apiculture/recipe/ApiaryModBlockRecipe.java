package link.mdks.beenomey.apiculture.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.BeeInit;
import link.mdks.beenomey.util.BeeManager;
import link.mdks.beenomey.util.BeeType;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class ApiaryModBlockRecipe implements Recipe<SimpleContainer>{
	
	private final ResourceLocation id;
	private final ItemStack output;
	private ItemStack inputBee;
	private ItemStack inputPrincess;
	private final NonNullList<Ingredient> recipeItems;
	

	public ApiaryModBlockRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
	}

	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		
		// Proceed crafting by matching
		
		if(pLevel.isClientSide) {
			return false;
		}
		BeenomeY.LOGGER.debug("MATCHES GOAT-FINDER: " + recipeItems.get(0) + " matching " + pContainer.getItem(0));
		//return recipeItems.get(0).test(pContainer.getItem(1)); //compares to slot one(1)
		return recipeItems.get(0).test(pContainer.getItem(0));
	}

	@Override
	public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pLevel) {
		return output;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess p_267052_) {
		return output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return recipeItems;
	}
	
	public static class Type implements RecipeType<ApiaryModBlockRecipe> {
		private Type() {}
		public static final Type INSTANCE = new  Type();
		public static final String ID = "apiary_recipe";
	}
	
	public static class Serializer implements RecipeSerializer<ApiaryModBlockRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		
		
		@Override
		public ApiaryModBlockRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

			JsonObject outputObject = pSerializedRecipe.getAsJsonObject("output");
			//String outputItem = outputObject.get("item").getAsString();
			String outputMainType = outputObject.get("mainType").getAsString();
			String outputSecondType = outputObject.get("secondType").getAsString();
            ItemStack output = BeeManager.getBee(BeeType.valueOf(outputMainType), BeeType.valueOf(outputSecondType), new ItemStack(BeeInit.getCommonBee()));
            
            JsonArray ingredientsArray = pSerializedRecipe.getAsJsonArray("ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(ingredientsArray.size(), Ingredient.EMPTY);
            
			for (int i = 0; i < ingredientsArray.size(); i++) {
				JsonObject ingredientObject = ingredientsArray.get(i).getAsJsonObject();
				//Ingredient jItem = Ingredient.fromJson(ingredientObject.get("item").getAsJsonObject());

				String jItem = ingredientObject.get("item").getAsString();
				String mainType = ingredientObject.get("mainType").getAsString();
				String secondType = ingredientObject.get("secondType").getAsString();
				
				/* Get ingredients as Item -> ItemStack -> Ingredient to get hold of Types*/
				ItemStack input = BeeManager.getBee(BeeType.valueOf(mainType), BeeType.valueOf(secondType),
						new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(jItem))));
				inputs.set(i, Ingredient.of(input));
				
				//TODO: Get hold if ingredient as ItemStacks to access them from JEIPlugin
				
			}
            
//            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
//            
//            
//            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
//            for (int i = 0; i < inputs.size(); i++) {
//            	inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
//            }
            
            return new ApiaryModBlockRecipe(pRecipeId, output, inputs);
			
		}
		
		@Override
		public @Nullable ApiaryModBlockRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
	           NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

	            for (int i = 0; i < inputs.size(); i++) {
	                inputs.set(i, Ingredient.fromNetwork(buf));
	            }

	            ItemStack output = buf.readItem();
	            return new ApiaryModBlockRecipe(id, output, inputs);
			
		}
		
		@Override
		public void toNetwork(FriendlyByteBuf buf, ApiaryModBlockRecipe recipe) {
			
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(null), false);
			
		}
	}
	
}
