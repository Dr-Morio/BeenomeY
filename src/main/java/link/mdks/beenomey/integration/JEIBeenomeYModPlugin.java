package link.mdks.beenomey.integration;

import java.util.List;
import java.util.Objects;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.ItemBee;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockBeeRecipeReader;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockCombRecipeReader;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockPrincessRecipeReader;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.init.BeeInit;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import mezz.jei.api.recipe.RecipeType;

@JeiPlugin
public class JEIBeenomeYModPlugin implements IModPlugin{
	
	/* Fields */
    public static RecipeType<ApiaryModBlockBeeRecipeReader> APIARY_BEE_TYPE =
            new RecipeType<>(ApiaryModBlockRecipeCategoryBee.UID, ApiaryModBlockBeeRecipeReader.class);
    
    public static RecipeType<ApiaryModBlockPrincessRecipeReader> APIARY_PRINCESS_TYPE =
            new RecipeType<>(ApiaryModBlockRecipeCategoryPrincess.UID, ApiaryModBlockPrincessRecipeReader.class);
    
    public static RecipeType<ApiaryModBlockCombRecipeReader> APIARY_COMB_TYPE =
            new RecipeType<>(ApiaryModBlockRecipeCategoryComb.UID, ApiaryModBlockCombRecipeReader.class);
    
    public static RecipeType<BreederBlockRecipe> BREEDER_TYPE =
            new RecipeType<>(BreederBlockRecipeCategory.UID, BreederBlockRecipe.class);
	
    
    /* Methods */
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(BeenomeY.MODID, "jei_plugin");
	}
	
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ApiaryModBlockRecipeCategoryBee(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ApiaryModBlockRecipeCategoryPrincess(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ApiaryModBlockRecipeCategoryComb(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new BreederBlockRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        IIngredientSubtypeInterpreter<ItemStack> beeTypeInterpreter = (ingredient, context) -> {
			return ingredient.getTag().getAsString();
        };

        registration.registerSubtypeInterpreter(BeeManager.getBee(BeeType.EMPTY, BeeType.EMPTY, new ItemStack(BeeInit.getCommonBee())).getItem(), beeTypeInterpreter);
        registration.registerSubtypeInterpreter(BeeManager.getBee(BeeType.EMPTY, BeeType.EMPTY, new ItemStack(BeeInit.getPrincessBee())).getItem(), beeTypeInterpreter);

    }
    
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        @SuppressWarnings("resource")
		RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<ApiaryModBlockBeeRecipeReader> apiaryBeeRecipes = rm.getAllRecipesFor(ApiaryModBlockBeeRecipeReader.Type.INSTANCE);
        registration.addRecipes(APIARY_BEE_TYPE, apiaryBeeRecipes);
        
        List<ApiaryModBlockPrincessRecipeReader> apiaryPrincessRecipes = rm.getAllRecipesFor(ApiaryModBlockPrincessRecipeReader.Type.INSTANCE);
        registration.addRecipes(APIARY_PRINCESS_TYPE, apiaryPrincessRecipes);
        
        List<ApiaryModBlockCombRecipeReader> apiaryCombRecipes = rm.getAllRecipesFor(ApiaryModBlockCombRecipeReader.Type.INSTANCE);
        registration.addRecipes(APIARY_COMB_TYPE, apiaryCombRecipes);
        
        List<BreederBlockRecipe> breederRecipes = rm.getAllRecipesFor(BreederBlockRecipe.Type.INSTANCE);
        registration.addRecipes(BREEDER_TYPE, breederRecipes);
    }
	
}
