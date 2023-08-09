package link.mdks.beenomey.integration;

import java.util.List;
import java.util.Objects;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import mezz.jei.api.recipe.RecipeType;

@JeiPlugin
public class JEIBeenomeYModPlugin implements IModPlugin{

    public static RecipeType<ApiaryModBlockRecipe> APIARY_TYPE =
            new RecipeType<>(ApiaryModBlockRecipeCategory.UID, ApiaryModBlockRecipe.class);
	
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(BeenomeY.MODID, "jei_plugin");
	}

	
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
        		ApiaryModBlockRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<ApiaryModBlockRecipe> apiaryRecipes = rm.getAllRecipesFor(ApiaryModBlockRecipe.Type.INSTANCE);
        registration.addRecipes(APIARY_TYPE, apiaryRecipes);
        
        
    }
	
}
