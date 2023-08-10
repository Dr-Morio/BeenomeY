package link.mdks.beenomey.apiculture.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.util.BeeManager;
import link.mdks.beenomey.util.BeeType;
import net.minecraft.client.Minecraft;
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
	private final NonNullList<ItemStack> inputs;
	

	public ApiaryModBlockRecipe(ResourceLocation id, ItemStack output, NonNullList<ItemStack> inputs) {
		this.id = id;
		this.output = output;
		this.inputs = inputs;
	}

	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		
		// Proceed crafting by matching
		
		if(pLevel.isClientSide) {
			return false;
		}
		return false; //TODO: FIX? - Maybe i dont need that because of my own RecipeManager class
		//BeenomeY.LOGGER.debug("MATCHES GOAT-FINDER: " + recipeItems.get(0) + " matching " + pContainer.getItem(0));
		//return recipeItems.get(0).test(pContainer.getItem(1)); //compares to slot one(1)
		//return recipeItems.get(0).test(pContainer.getItem(0));
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


	public NonNullList<ItemStack> getIngredientsAsItemStacks() {
		return inputs;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.withSize(2, Ingredient.EMPTY); // Static...
		for (int i = 0; i < inputs.size(); i++) {
		    ingredients.set(i, Ingredient.of(inputs.get(i)));
		}
		return ingredients;
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
            NonNullList<ItemStack> inputs = NonNullList.withSize(ingredientsArray.size(), ItemStack.EMPTY);
            
			for (int i = 0; i < ingredientsArray.size(); i++) {
				JsonObject ingredientObject = ingredientsArray.get(i).getAsJsonObject();
				//Ingredient jItem = Ingredient.fromJson(ingredientObject.get("item").getAsJsonObject());

				String jItem = ingredientObject.get("item").getAsString();
				String mainType = ingredientObject.get("mainType").getAsString();
				String secondType = ingredientObject.get("secondType").getAsString();
				
				/* Item to ItemStack to attach Types*/
				ItemStack input = BeeManager.getBee(BeeType.valueOf(mainType), BeeType.valueOf(secondType),
						new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(jItem))));
				inputs.set(i, input);
				
		
			}
            
            return new ApiaryModBlockRecipe(pRecipeId, output, inputs);
			
		}
		
		@Override
		public @Nullable ApiaryModBlockRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
	           NonNullList<ItemStack> inputs = NonNullList.withSize(buf.readInt(), ItemStack.EMPTY);

	            for (int i = 0; i < inputs.size(); i++) {
	                inputs.set(i, buf.readItem());
	            }

	            ItemStack output = buf.readItem();
	            return new ApiaryModBlockRecipe(id, output, inputs);
			
		}
		
		@SuppressWarnings("resource")
		@Override
		public void toNetwork(FriendlyByteBuf buf, ApiaryModBlockRecipe recipe) {
			
            buf.writeInt(recipe.getIngredients().size());

            for (ItemStack is : recipe.getIngredientsAsItemStacks()) {
                buf.writeItemStack(is, false);
            }
            buf.writeItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()), false);
			
		}
	}
	
}
