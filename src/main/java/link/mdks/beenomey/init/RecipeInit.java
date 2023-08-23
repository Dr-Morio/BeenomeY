package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockBeeRecipeReader;
import link.mdks.beenomey.apiculture.recipe.ApiaryModBlockPrincessRecipeReader;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BeenomeY.MODID);

    public static final RegistryObject<RecipeSerializer<ApiaryModBlockBeeRecipeReader>> APIARY_BEE_RECIPE_SERIALIZER =
            SERIALIZERS.register("apiary_recipe_bee", () -> ApiaryModBlockBeeRecipeReader.Serializer.INSTANCE);
    
    public static final RegistryObject<RecipeSerializer<ApiaryModBlockPrincessRecipeReader>> APIARY_PRINCESS_RECIPE_SERIALIZER =
            SERIALIZERS.register("apiary_recipe_princess", () -> ApiaryModBlockPrincessRecipeReader.Serializer.INSTANCE);
    
    public static final RegistryObject<RecipeSerializer<BreederBlockRecipe>> BREEDER_RECIPE_SERIALIZER =
            SERIALIZERS.register("breeder_recipe", () -> BreederBlockRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
