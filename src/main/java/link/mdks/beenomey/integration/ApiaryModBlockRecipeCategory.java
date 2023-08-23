package link.mdks.beenomey.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockRecipe;
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

public class ApiaryModBlockRecipeCategory implements IRecipeCategory<ApiaryModBlockRecipe>{

    public final static ResourceLocation UID = new ResourceLocation(BeenomeY.MODID, "apiary_recipe");
    public final static ResourceLocation DEFAULT_BACKGROUND =
            new ResourceLocation(BeenomeY.MODID, "textures/gui/apiary_mod_block_menu_jei_v3.png");

    private IDrawable background;
    private final IDrawable icon;

    private IGuiHelper helper;

    public ApiaryModBlockRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(DEFAULT_BACKGROUND, 0, 0, 176, 178); //85
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
		IDrawableBuilder frameBuilder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_output_frame.png"), 0, 0, 16, 16);
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
	
	

	@SuppressWarnings("resource")
	@Override
	public void draw(ApiaryModBlockRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrix, double mouseX,
			double mouseY) {
		
//		IDrawableBuilder builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_water_nether_icon.png"), 0, 0, 16, 16);
//		builder.setTextureSize(16, 16);
//		IDrawable icon = builder.build();
//		
//		recipeSlotsView.findSlotByName("OutputSlot").get
//		
//		icon.draw(matrix, 145, 15);
//		
		//TODO: Fix This
		//boolean cleanBreed = false;
//		Component example = Component.literal("Example of").withStyle(ChatFormatting.WHITE);
//		Component information;
//		
//		/* Determin if recipe is clean breeding or cross breeding recipe */
//		BeeType bMainType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(0).getTag().get("MainType").getAsString());
//		BeeType bSecondType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(0).getTag().get("SecondType").getAsString());
//		BeeType pMainType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(1).getTag().get("MainType").getAsString());
//		BeeType pSecondType = BeeType.valueOf(recipe.getIngredientsAsItemStacks().get(1).getTag().get("SecondType").getAsString());
//		BeeType oMainType = BeeType.valueOf(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getTag().get("MainType").getAsString());
//		BeeType oSecondType = BeeType.valueOf(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).getTag().get("SecondType").getAsString());
//		
//		
//		/* Create Icon based on Type Breeding */
//		IDrawableBuilder builder = null; //Not error save... 
//		if(oMainType == BeeType.FOREST && oSecondType == BeeType.FOREST) {
//			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_forest_forest_icon.png"), 0, 0, 24, 24);
//		}
//		if(oMainType == BeeType.OCEAN && oSecondType == BeeType.FOREST) {
//			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_water_forest_icon.png"), 0, 0, 24, 24);
//		}
//		if(oMainType == BeeType.OCEAN && oSecondType == BeeType.INFERNO) {
//			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_water_nether_icon.png"), 0, 0, 24, 24);
//		}
//		if(oMainType == BeeType.INFERNO && oSecondType == BeeType.INFERNO) {
//			builder = helper.drawableBuilder(new ResourceLocation(BeenomeY.MODID, "textures/gui/icons/apiary_mod_block_menu_jei_nether_nether_icon.png"), 0, 0, 24, 24);
//		}
//		builder.setTextureSize(24, 24);
//		IDrawable icon = builder.build();
		
		
		//Set Texts
//		if(bMainType == pMainType && bSecondType == pSecondType && bMainType == pSecondType && bSecondType == pMainType) {
//			cleanBreed = true;
//		}
//		if(cleanBreed) {
//			information = Component.literal("Clean Breeding").withStyle(ChatFormatting.WHITE);
//		} else {
//			information = Component.literal("Cross Breeding").withStyle(ChatFormatting.WHITE);
//		}
//		information = Component.literal("Cross Breeding").withStyle(ChatFormatting.WHITE);
//		Minecraft.getInstance().font.draw(matrix, example, 85, 51, 1);
//		Minecraft.getInstance().font.drawShadow(matrix, example, 85, 51, 1);
//		Minecraft.getInstance().font.draw(matrix, information, 85, 63, 1);
//		Minecraft.getInstance().font.drawShadow(matrix, information, 85, 63, 1);
		
		// Set Icon
		//icon.draw(matrix, 145, 15);
		
	}
	
	
}
