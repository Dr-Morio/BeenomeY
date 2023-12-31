package link.mdks.beenomey.apiculture.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
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

public class ApiaryModBlockCombRecipeReader implements Recipe<SimpleContainer>{
	
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<ItemStack> inputs;
	

	public ApiaryModBlockCombRecipeReader(ResourceLocation id, ItemStack output, NonNullList<ItemStack> inputs) {
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
		return false;

	}

	@Override
	public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pLevel) {
		return output;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}
	
	public ItemStack getResultItem(@Nullable RegistryAccess p_267052) {
		return output.copy();
	}

	@Override
	public boolean isSpecial() {
		return true;
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
		NonNullList<Ingredient> ingredients = NonNullList.withSize(4, Ingredient.EMPTY); // Hardcoded... that could cause problems
		for (int i = 0; i < inputs.size(); i++) {
		    ingredients.set(i, Ingredient.of(inputs.get(i)));
		}
		return ingredients;
	}
	
	public static class Type implements RecipeType<ApiaryModBlockCombRecipeReader> {
		private Type() {}
		public static final Type INSTANCE = new  Type();
		public static final String ID = "apiary_recipe_comb";
	}
	
	public static class Serializer implements RecipeSerializer<ApiaryModBlockCombRecipeReader> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(BeenomeY.MODID, "apiary_recipe_comb");
		
		@Override
		public ApiaryModBlockCombRecipeReader fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

			JsonObject outputObject = pSerializedRecipe.getAsJsonObject("output");
            ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(outputObject.get("item").getAsString())));
            
            JsonArray ingredientsArray = pSerializedRecipe.getAsJsonArray("ingredients");
            NonNullList<ItemStack> inputs = NonNullList.withSize(ingredientsArray.size(), ItemStack.EMPTY);
            
			for (int i = 0; i < ingredientsArray.size(); i++) {
				JsonObject ingredientObject = ingredientsArray.get(i).getAsJsonObject();

				String jItem = ingredientObject.get("item").getAsString();
				String mainType = ingredientObject.get("mainType").getAsString();
				String secondType = ingredientObject.get("secondType").getAsString();
				
				/* Item to ItemStack to attach Types*/
				ItemStack input = BeeManager.getBee(BeeType.valueOf(mainType), BeeType.valueOf(secondType),
						new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(jItem))));
				inputs.set(i, input);
				
		
			}
            
            return new ApiaryModBlockCombRecipeReader(pRecipeId, output, inputs);
			
		}
		
		@Override
		public @Nullable ApiaryModBlockCombRecipeReader fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
	           NonNullList<ItemStack> inputs = NonNullList.withSize(buf.readInt(), ItemStack.EMPTY);

	            for (int i = 0; i < inputs.size(); i++) {
	                inputs.set(i, buf.readItem());
	            }

	            ItemStack output = buf.readItem();
	            return new ApiaryModBlockCombRecipeReader(id, output, inputs);
			
		}
		
		@Override
		public void toNetwork(FriendlyByteBuf buf, ApiaryModBlockCombRecipeReader recipe) {
			
            buf.writeInt(recipe.getIngredients().size());

            for (ItemStack is : recipe.getIngredientsAsItemStacks()) {
                buf.writeItemStack(is, false);
            }
            buf.writeItemStack(recipe.getResultItem(null), false);
			
		}
	}
	
}
