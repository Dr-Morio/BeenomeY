package link.mdks.beenomey.apiculture.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.init.BeeInit;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class BreederBlockRecipe implements Recipe<SimpleContainer>{
	
	private final int recipeId;
	private final ResourceLocation id;
	private final NonNullList<ItemStack> inputBees;
	private final FluidStack catalysator;
	private final ItemStack output;
	private final int chance;
	
	public BreederBlockRecipe(int recipeId, ResourceLocation id, NonNullList<ItemStack> inputBees, FluidStack catalysator, ItemStack output, int chance) {
		this.recipeId = recipeId;
		this.id = id;
		this.inputBees = inputBees;
		this.catalysator = catalysator;
		this.output = output;
		this.chance = chance;
	}

	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
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

	@Override
	public ItemStack getResultItem(@Nullable RegistryAccess p_267052_) {
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
	

	public int getRecieId() {
		return this.recipeId;
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
		return inputBees;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		NonNullList<Ingredient> ingredients = NonNullList.withSize(inputBees.size(), Ingredient.EMPTY);
		for (int i = 0; i < inputBees.size(); i++) {
			ingredients.set(i, Ingredient.of(inputBees.get(i)));
		}
		return ingredients;
	}
	
	public FluidStack getFluidStack() {
		return catalysator;
	}
	
	public int getChance() {
		return chance;
	}
	
	public static class Type implements RecipeType<BreederBlockRecipe> {
		private Type() {}
		public static final Type INSTANCE = new  Type();
		public static final String ID = "breeder_recipe";
	}
	
	public static class Serializer implements RecipeSerializer<BreederBlockRecipe> {
	
		public static final Serializer INSTANCE = new Serializer();
		
		@Override
		public BreederBlockRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
		
			/* Output */
			JsonObject outputObject = pSerializedRecipe.getAsJsonObject("output");
			String outputMainType = outputObject.get("mainType").getAsString();
			String outputSecondType = outputObject.get("secondType").getAsString();
			int outputChance = outputObject.get("chance").getAsInt();
            ItemStack output = BeeManager.getBee(BeeType.valueOf(outputMainType), BeeType.valueOf(outputSecondType), new ItemStack(BeeInit.getCommonBee()));
            
            
            /* Ingredient Bees */
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
			
			/* Fluid -> Catalysator */
			JsonObject fluidObject = pSerializedRecipe.getAsJsonObject("fluid");
			FluidStack catalysator = new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidObject.get("FluidName").getAsString())), fluidObject.get("Amount").getAsInt());

			
			/* Unique Data -> Recipe ID */
			JsonObject data = pSerializedRecipe.getAsJsonObject("data");
			int recipeId = data.get("recipeId").getAsInt();
			
			return new BreederBlockRecipe(recipeId, pRecipeId, inputs, catalysator, output, outputChance);
			
            
            
			
		}

		@Override
		public @Nullable BreederBlockRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {

			NonNullList<ItemStack> inputs = NonNullList.withSize(pBuffer.readInt(), ItemStack.EMPTY);
			FluidStack catalysator;
			ItemStack output;
			int chance;
			int recipeId;
			 
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, pBuffer.readItem());
            }
            
            catalysator = pBuffer.readFluidStack();
            output = pBuffer.readItem();
            chance = pBuffer.readInt();
            recipeId = pBuffer.readInt();
            
            return new BreederBlockRecipe(recipeId, pRecipeId, inputs, catalysator, output, chance);
		}

		@Override
		public void toNetwork(FriendlyByteBuf pBuffer, BreederBlockRecipe pRecipe) {
			
			pBuffer.writeInt(pRecipe.getIngredients().size());
			
			for (ItemStack is : pRecipe.getIngredientsAsItemStacks()) {
				pBuffer.writeItemStack(is, false);
			}
			
			pBuffer.writeFluidStack(pRecipe.getFluidStack());
			
			pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
			
			pBuffer.writeInt(pRecipe.getChance());
			
			pBuffer.writeInt(pRecipe.getRecieId());
		}
		
	}

}
