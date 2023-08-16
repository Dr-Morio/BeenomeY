package link.mdks.beenomey.integration;

import com.mojang.blaze3d.vertex.PoseStack;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockRecipe;
import link.mdks.beenomey.init.BlockInit;
import link.mdks.beenomey.util.BeeType;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ApiaryModBlockRecipeCategory implements IRecipeCategory<ApiaryModBlockRecipe>{

    public final static ResourceLocation UID = new ResourceLocation(BeenomeY.MODID, "apiary_recipe");
    public final static ResourceLocation DEFAULT_BACKGROUND =
            new ResourceLocation(BeenomeY.MODID, "textures/gui/apiary_mod_block_menu_jei.png");

    private IDrawable background;
    private final IDrawable icon;

    private IGuiHelper helper;

    public ApiaryModBlockRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(DEFAULT_BACKGROUND, 0, 0, 176, 78); //85
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.APIARY_MOD_BLOCK.get()));
        this.helper = helper;
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
		//builder.setShapeless(162,5);
        builder.addSlot(RecipeIngredientRole.INPUT, 27, 9).addItemStack(recipe.getIngredientsAsItemStacks().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 33).addItemStack(recipe.getIngredientsAsItemStacks().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 101, 9).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
		}

	

	@SuppressWarnings("resource")
	@Override
	public void draw(ApiaryModBlockRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrix, double mouseX,
			double mouseY) {

		boolean cleanBreed = false;
		Component example = Component.literal("Example of").withStyle(ChatFormatting.WHITE);
		Component information;
		
		/* Determin if recipe is clean breeding or cross breeding recipe */
		BeeType bMainType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(0).getTag().get("MainType").getAsString());
		BeeType bSecondType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(0).getTag().get("SecondType").getAsString());
		BeeType pMainType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(1).getTag().get("MainType").getAsString());
		BeeType pSecondType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(1).getTag().get("SecondType").getAsString());
		BeeType oMainType = BeeType.valueOf(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getTag().get("MainType").getAsString());
		BeeType oSecondType = BeeType.valueOf(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getTag().get("SecondType").getAsString());
		
		
		/* Create Icon based on Type Breeding */
		IDrawableBuilder builder = null; //Not error save... 
		if(oMainType == BeeType.FOREST && oSecondType == BeeType.FOREST) {
			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_forest_forest_icon.png"), 0, 0, 24, 24);
		}
		if(oMainType == BeeType.OCEAN && oSecondType == BeeType.FOREST) {
			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_water_forest_icon.png"), 0, 0, 24, 24);
		}
		if(oMainType == BeeType.OCEAN && oSecondType == BeeType.INFERNO) {
			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_water_nether_icon.png"), 0, 0, 24, 24);
		}
		if(oMainType == BeeType.INFERNO && oSecondType == BeeType.INFERNO) {
			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_nether_nether_icon.png"), 0, 0, 24, 24);
		}
		builder.setTextureSize(24, 24);
		IDrawable icon = builder.build();
		
		
		//Set Texts
		if(bMainType == pMainType && bSecondType == pSecondType && bMainType == pSecondType && bSecondType == pMainType) {
			cleanBreed = true;
		}
		if(cleanBreed) {
			information = Component.literal("Clean Breeding").withStyle(ChatFormatting.WHITE);
		} else {
			information = Component.literal("Cross Breeding").withStyle(ChatFormatting.WHITE);
		}
		
		Minecraft.getInstance().font.draw(matrix, example, 85, 51, 1);
		Minecraft.getInstance().font.drawShadow(matrix, example, 85, 51, 1);
		Minecraft.getInstance().font.draw(matrix, information, 85, 63, 1);
		Minecraft.getInstance().font.drawShadow(matrix, information, 85, 63, 1);
		
		// Set Icon
		icon.draw(matrix, 145, 15);
		
	}
	
	
}
