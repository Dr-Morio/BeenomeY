package link.mdks.beenomey.apiculture.fluids;

import org.joml.Vector3f;

import link.mdks.beenomey.BeenomeY;
import net.minecraft.resources.ResourceLocation;


/* Holds values for different Honey Fluids */
public class FluidResourceProvider {
    
    public static final ResourceLocation NEUTRAL_STILL = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_still");
    public static final ResourceLocation HOT_STILL = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_still");
    public static final ResourceLocation NEUTRAL_FLOWING = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_flow");
    public static final ResourceLocation HOT_FLOWING = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_flow");
    public static final ResourceLocation DEFAULT_OVERLAY = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_overlay");
    
	public static Vector3f HONEY_FOG_COLOR = new Vector3f(223f / 255f, 136f / 255f, 3f / 255f);
    public static int HONEY_TINT_COLOR = 0xFFdf8803;
    
	public static Vector3f FROZEN_HONEY_FOG_COLOR = new Vector3f(163f / 255f, 203f / 255f, 209f / 255f);
    public static int FROZEN_HONEY_TINT_COLOR = 0xFFa3cbd1;
    
	public static Vector3f OCEAN_HONEY_FOG_COLOR = new Vector3f(219f / 255f, 200f / 255f, 172f / 255f);
    public static int OCEAN_HONEY_TINT_COLOR = 0xFFdbc8ac;
    
	public static Vector3f DESERT_HONEY_FOG_COLOR = new Vector3f(204f / 255f, 171f / 255f, 77f / 255f);
    public static int DESERT_HONEY_TINT_COLOR = 0xFFccab4d;
    
	public static Vector3f ROCK_HONEY_FOG_COLOR = new Vector3f(153f / 255f, 153f / 255f, 153f / 255f);
    public static int ROCK_HONEY_TINT_COLOR = 0xFF999999;
    
    public static Vector3f INFERNO_HONEY_FOG_COLOR = new Vector3f(223f / 255f, 75f / 255f, 3f / 255f);
    public static int INFERNO_HONEY_TINT_COLOR = 0xFFdf4b03;

    public static Vector3f VOID_HONEY_FOG_COLOR = new Vector3f(218f / 255f, 216f / 255f, 165f / 255f);
    public static int VOID_HONEY_TINT_COLOR = 0xFFdad8a5;

    public static Vector3f COAL_HONEY_FOG_COLOR = new Vector3f(73f / 255f, 64f / 255f, 37f / 255f);
    public static int COAL_HONEY_TINT_COLOR = 0xFF494025;

    public static Vector3f GUNPOWDER_HONEY_FOG_COLOR = new Vector3f(121f / 255f, 117f / 255f, 107f / 255f);
    public static int GUNPOWDER_HONEY_TINT_COLOR = 0xFF79756b;

    public static Vector3f FLINT_HONEY_FOG_COLOR = new Vector3f(73f / 255f, 64f / 255f, 35f / 255f);
    public static int FLINT_HONEY_TINT_COLOR = 0xFF494023;

    public static Vector3f SLIME_HONEY_FOG_COLOR = new Vector3f(189f / 255f, 222f / 255f, 113f / 255f);
    public static int SLIME_HONEY_TINT_COLOR = 0xFFbdde71;

    public static Vector3f SUGAR_HONEY_FOG_COLOR = new Vector3f(239f / 255f, 233f / 255f, 226f / 255f);
    public static int SUGAR_HONEY_TINT_COLOR = 0xFFefe9e2;

    public static Vector3f GLASS_HONEY_FOG_COLOR = new Vector3f(207f / 255f, 223f / 255f, 228f / 255f);
    public static int GLASS_HONEY_TINT_COLOR = 0xFFcfdfe4;

    public static Vector3f CLAY_HONEY_FOG_COLOR = new Vector3f(141f / 255f, 146f / 255f, 153f / 255f);
    public static int CLAY_HONEY_TINT_COLOR = 0xFF8d9299;

    public static Vector3f STRING_HONEY_FOG_COLOR = new Vector3f(237f / 255f, 231f / 255f, 224f / 255f);
    public static int STRING_HONEY_TINT_COLOR = 0xFFede7e0;

    public static Vector3f NETHER_WART_HONEY_FOG_COLOR = new Vector3f(133f / 255f, 20f / 255f, 22f / 255f);
    public static int NETHER_WART_HONEY_TINT_COLOR = 0xFF851416;

    public static Vector3f SOUL_SAND_HONEY_FOG_COLOR = new Vector3f(70f / 255f, 56f / 255f, 47f / 255f);
    public static int SOUL_SAND_HONEY_TINT_COLOR = 0xFF56382f;

    public static Vector3f MUSHROOM_HONEY_FOG_COLOR = new Vector3f(212f / 255f, 57f / 255f, 57f / 255f);
    public static int MUSHROOM_HONEY_TINT_COLOR = 0xFFd43939;

    public static Vector3f LAVA_HONEY_FOG_COLOR = new Vector3f(213f / 255f, 92f / 255f, 46f / 255f);
    public static int LAVA_HONEY_TINT_COLOR = 0xFFd55c2e;

    public static Vector3f LAPIS_LAZULI_HONEY_FOG_COLOR = new Vector3f(76f / 255f, 114f / 255f, 207f / 255f);
    public static int LAPIS_LAZULI_HONEY_TINT_COLOR = 0xFF4c72cf;

    public static Vector3f REDSTONE_HONEY_FOG_COLOR = new Vector3f(171f / 255f, 37f / 255f, 37f / 255f);
    public static int REDSTONE_HONEY_TINT_COLOR = 0xFFab2525;

    public static Vector3f IRON_HONEY_FOG_COLOR = new Vector3f(206f / 255f, 206f / 255f, 206f / 255f);
    public static int IRON_HONEY_TINT_COLOR = 0xFFcecece;

    public static Vector3f GOLD_HONEY_FOG_COLOR = new Vector3f(228f / 255f, 196f / 255f, 54f / 255f);
    public static int GOLD_HONEY_TINT_COLOR = 0xFFc4c436;

    public static Vector3f COPPER_HONEY_FOG_COLOR = new Vector3f(205f / 255f, 91f / 255f, 45f / 255f);
    public static int COPPER_HONEY_TINT_COLOR = 0xFFcd5b2d;
    
    public static Vector3f DIAMOND_HONEY_FOG_COLOR = new Vector3f(124f / 255f, 223f / 255f, 223f / 255f);
    public static int DIAMOND_HONEY_TINT_COLOR = 0xFF7cdfdf;

    public static Vector3f OBSIDIAN_HONEY_FOG_COLOR = new Vector3f(64f / 255f, 25f / 255f, 115f / 255f);
    public static int OBSIDIAN_HONEY_TINT_COLOR = 0xFF401973;
    
    public static Vector3f GLOWSTONE_HONEY_FOG_COLOR = new Vector3f(221f / 255f, 160f / 255f, 19f / 255f);
    public static int GLOWSTONE_HONEY_TINT_COLOR = 0xFFdda013;

    public static Vector3f BLAZE_HONEY_FOG_COLOR = new Vector3f(255f / 255f, 197f / 255f, 24f / 255f);
    public static int BLAZE_HONEY_TINT_COLOR = 0xFFffc518;

    public static Vector3f EMERALD_HONEY_FOG_COLOR = new Vector3f(9f / 255f, 191f / 255f, 15f / 255f);
    public static int EMERALD_HONEY_TINT_COLOR = 0xFF09bf0f;

    public static Vector3f NETHER_STAR_HONEY_FOG_COLOR = new Vector3f(219f / 255f, 201f / 255f, 228f / 255f);
    public static int NETHER_STAR_HONEY_TINT_COLOR = 0xFFdbc9e4;

    public static Vector3f NETHER_QUARZ_HONEY_FOG_COLOR = new Vector3f(253f / 255f, 248f / 255f, 231f / 255f);
    public static int NETHER_QUARZ_HONEY_TINT_COLOR = 0xFFfdf8e7;

    public static Vector3f PRISMARINE_HONEY_FOG_COLOR = new Vector3f(99f / 255f, 170f / 255f, 139f / 255f);
    public static int PRISMARINE_HONEY_TINT_COLOR = 0xFF63aa8b;

    public static Vector3f CHORUS_HONEY_FOG_COLOR = new Vector3f(133f / 255f, 59f / 255f, 223f / 255f);
    public static int CHORUS_HONEY_TINT_COLOR = 0xFF853bdf;

    public static Vector3f NETHERITE_HONEY_FOG_COLOR = new Vector3f(106f / 255f, 68f / 255f, 68f / 255f);
    public static int NETHERITE_HONEY_TINT_COLOR = 0xFF6a4444;

    public static Vector3f EXP_HONEY_FOG_COLOR = new Vector3f(94f / 255f, 169f / 255f, 67f / 255f);
    public static int EXP_HONEY_TINT_COLOR = 0xFF5ea943;

    public static Vector3f ENDER_PEARL_HONEY_FOG_COLOR = new Vector3f(13f / 255f, 72f / 255f, 62f / 255f);
    public static int ENDER_PEARL_HONEY_TINT_COLOR = 0xFF0d483e;

    public static Vector3f AMETHYST_HONEY_FOG_COLOR = new Vector3f(117f / 255f, 16f / 255f, 218f / 255f);
    public static int AMETHYST_HONEY_TINT_COLOR = 0xFF7510da;
    
}