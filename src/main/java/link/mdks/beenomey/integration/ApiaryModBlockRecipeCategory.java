package link.mdks.beenomey.integration;


import com.mojang.blaze3d.vertex.PoseStack;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockRecipe;
import link.mdks.beenomey.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ApiaryModBlockRecipeCategory implements IRecipeCategory<ApiaryModBlockRecipe>{

    public final static ResourceLocation UID = new ResourceLocation(BeenomeY.MODID, "apiary_recipe");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(BeenomeY.MODID, "textures/gui/apiary_mod_block_menu_jei.png");

    private final IDrawable background;
    private final IDrawable icon;


    public ApiaryModBlockRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.APIARY_MOD_BLOCK.get()));
    }
    
	@Override
	public RecipeType<ApiaryModBlockRecipe> getRecipeType() {
		return JEIBeenomeYModPlugin.APIARY_TYPE;
	}

	@Override
	public Component getTitle() {
        return Component.literal("Apiary");
	}

	@Override
	public IDrawable getBackground() {
        return this.background;
	}

	@Override
	public IDrawable getIcon() {
        return this.icon;
	}

	@SuppressWarnings("resource")
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ApiaryModBlockRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 27, 13).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 37).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 13).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
	
		}

	

//	@Override
//	public void draw(ApiaryModBlockRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrix, double mouseX,
//			double mouseY) {
//		
//		
//		/* Determin if recipe is clean breeding or cross breeding recipe*/
//		BeeType bMainType = recipe.getIngredients().get(0).
//		
//		
//		Component MainType = Component.literal(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getTag().get("MainType").getAsString())
//				.withStyle(ChatFormatting.AQUA);
//		
//		Component explenation = Component.literal("Breeds Hybrid of Bee and Princess");
//		
//		Minecraft.getInstance().font.draw(matrix, explenation, 103, 33, 1);
//		
//		
//	}
	
	
}
