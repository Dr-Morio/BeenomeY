package link.mdks.beenomey.integration;

import java.util.List;
import java.util.Random;


import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import link.mdks.beenomey.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
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

public class BreederBlockRecipeCategory implements IRecipeCategory<BreederBlockRecipe>{

    public final static ResourceLocation UID = new ResourceLocation(BeenomeY.MODID, "breeder_recipe");
    
    public final static ResourceLocation DEFAULT_BACKGROUND =
            new ResourceLocation(BeenomeY.MODID, "textures/gui/breeder_block_menu_dark_jei.png");
    
    private IDrawable background;
    
    private final IDrawable icon;
    

    @SuppressWarnings("unused")
	private IGuiHelper helper;
    ITickTimer tickProvider;
	
    
    public BreederBlockRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(DEFAULT_BACKGROUND, 0, 0, 176, 118); //85
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.BREEDER_BLOCK.get()));
        this.helper = helper;
		tickProvider = helper.createTickTimer(16, 16, false);
	}
    
	@Override
	public RecipeType<BreederBlockRecipe> getRecipeType() {
		return JEIBeenomeYModPlugin.BREEDER_TYPE;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Breeder");
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
	public void setRecipe(IRecipeLayoutBuilder builder, BreederBlockRecipe recipe, IFocusGroup focuses) {
		
		Random rnd = new Random();
		
		if(rnd.nextBoolean() == true) {
			builder.addSlot(RecipeIngredientRole.INPUT, 46, 15).addItemStack(recipe.getIngredientsAsItemStacks().get(0)).addTooltipCallback(consumerTooltip());
			builder.addSlot(RecipeIngredientRole.INPUT, 114, 15).addItemStack(recipe.getIngredientsAsItemStacks().get(1)).addTooltipCallback(consumerTooltip());
			builder.addSlot(RecipeIngredientRole.INPUT, 46, 71).addItemStack(recipe.getIngredientsAsItemStacks().get(2)).addTooltipCallback(consumerTooltip());
			builder.addSlot(RecipeIngredientRole.INPUT, 114, 71).addItemStack(recipe.getIngredientsAsItemStacks().get(3)).addTooltipCallback(consumerTooltip());
		} else {
			
			builder.addSlot(RecipeIngredientRole.INPUT, 46, 15).addItemStack(recipe.getIngredientsAsItemStacks().get(3)).addTooltipCallback(consumerTooltip());
			builder.addSlot(RecipeIngredientRole.INPUT, 114, 15).addItemStack(recipe.getIngredientsAsItemStacks().get(2)).addTooltipCallback(consumerTooltip());
			builder.addSlot(RecipeIngredientRole.INPUT, 46, 71).addItemStack(recipe.getIngredientsAsItemStacks().get(1)).addTooltipCallback(consumerTooltip());
			builder.addSlot(RecipeIngredientRole.INPUT, 114, 71).addItemStack(recipe.getIngredientsAsItemStacks().get(0)).addTooltipCallback(consumerTooltip());
			
		}

		builder.addSlot(RecipeIngredientRole.INPUT, 94, 99)
		.addFluidStack(recipe.getFluidStack().getFluid(), recipe.getFluidStack().getAmount()).setFluidRenderer(16000, true, 50, 12);
		
		builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 43).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess())).addTooltipCallback(creationTooltip());
		
	}
	
	
	private static IRecipeSlotTooltipCallback consumerTooltip() {
		return new IRecipeSlotTooltipCallback() {
			@Override
			public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
				tooltip.add(Component.literal("Will be Consumed").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
			}
		};
		
	}
	
	private static IRecipeSlotTooltipCallback creationTooltip() {
		return new IRecipeSlotTooltipCallback() {
			@Override
			public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
				tooltip.add(Component.literal("Will be Created").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.ITALIC));
			}
		};
		
	}
	
}
