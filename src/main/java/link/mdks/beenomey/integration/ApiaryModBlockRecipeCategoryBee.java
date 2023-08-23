package link.mdks.beenomey.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockBeeRecipeReader;
import link.mdks.beenomey.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import oshi.util.tuples.Pair;

public class ApiaryModBlockRecipeCategoryBee implements IRecipeCategory<ApiaryModBlockBeeRecipeReader>{

    public final static ResourceLocation UID = new ResourceLocation(BeenomeY.MODID, "apiary_recipe_bee");
    public final static ResourceLocation DEFAULT_BACKGROUND =
            new ResourceLocation(BeenomeY.MODID, "textures/gui/apiary_mod_block_menu_jei.png");

    private IDrawable background;
    private final IDrawable icon;

    private IGuiHelper helper;

    public ApiaryModBlockRecipeCategoryBee(IGuiHelper helper) {
        this.background = helper.createDrawable(DEFAULT_BACKGROUND, 0, 0, 176, 178); //85
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.APIARY_MOD_BLOCK.get()));
        this.helper = helper;
    }
    
	@Override
	public RecipeType<ApiaryModBlockBeeRecipeReader> getRecipeType() {
		return JEIBeenomeYModPlugin.APIARY_BEE_TYPE;
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

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ApiaryModBlockBeeRecipeReader recipe, IFocusGroup focuses) {
		
		
		int offsetHight = 100;
		
		@SuppressWarnings("serial")
		List<Pair<Integer, Integer>> beeSlots = new ArrayList<Pair<Integer,Integer>>() {{
		add(new Pair<>(68, 9));
		add(new Pair<>(92, 9));
		add(new Pair<>(56, 33));
		add(new Pair<>(104, 33));
		add(new Pair<>(68, 57));
		add(new Pair<>(92, 57));
		}};
		
		List<Pair<Integer, Integer>> possibleOutputSlot = new ArrayList<Pair<Integer,Integer>>();
		
		Random rnd = new Random();
		while(beeSlots.size() > 3) {
			int remove = rnd.nextInt(0,beeSlots.size() - 1);
			try {
				possibleOutputSlot.add(beeSlots.get(remove));
				beeSlots.remove(remove);
			} catch (Exception e) {
				BeenomeY.LOGGER.error("Failed to remove slot" + beeSlots.size());
			}

		}
		
		//Input
		for(int i = 0; i < beeSlots.size(); i++) {
			//Input Top Tile
			builder.addSlot(RecipeIngredientRole.INPUT, beeSlots.get(i).getA(), beeSlots.get(i).getB()).addItemStack(recipe.getIngredientsAsItemStacks().get(i));
			//Input Bottom tile
			builder.addSlot(RecipeIngredientRole.INPUT, beeSlots.get(i).getA(), beeSlots.get(i).getB() + offsetHight).addItemStack(recipe.getIngredientsAsItemStacks().get(i));
		}
		
		//Place Princess
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 33).addItemStack(recipe.getIngredientsAsItemStacks().get(3));
		builder.addSlot(RecipeIngredientRole.INPUT, 80, 33 + offsetHight).addItemStack(recipe.getIngredientsAsItemStacks().get(3));
   
		// Output
		IDrawableBuilder frameBuilder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_output_frame_bee.png"), 0, 0, 16, 16);
		frameBuilder.setTextureSize(16, 16);
		IDrawableStatic frame = frameBuilder.build();
		
		IDrawableAnimated frameAnimated = helper.createAnimatedDrawable(frame, 32, StartDirection.TOP, false);
		
		int outputSlot = rnd.nextInt(0,1);
        builder.addSlot(RecipeIngredientRole.OUTPUT,
        		possibleOutputSlot.get(outputSlot).getA(),
        		possibleOutputSlot.get(outputSlot).getB() + offsetHight)
        .addItemStack(recipe.getResultItem(null))
        .addTooltipCallback(creationTooltip()).setSlotName("OutputSlot").setOverlay(frameAnimated, 0, 0);
	}

	

	private IRecipeSlotTooltipCallback creationTooltip() {
		return new IRecipeSlotTooltipCallback() {
			@Override
			public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
				tooltip.add(Component.literal("Will be Created").withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.ITALIC));
			}
		};
	}	
}
