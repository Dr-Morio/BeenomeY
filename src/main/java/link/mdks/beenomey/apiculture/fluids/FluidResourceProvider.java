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
    
	public static Vector3f FROZEN_HONEY_FOG_COLOR = new Vector3f(14f / 255f, 219f / 255f, 240f / 255f);
    public static int FROZEN_HONEY_TINT_COLOR = 0xFFEDBF0;
    
	public static Vector3f OCEAN_HONEY_FOG_COLOR = new Vector3f(219f / 255f, 200f / 255f, 172f / 255f);
    public static int OCEAN_HONEY_TINT_COLOR = 0xFFdbc8ac;
    
    public static Vector3f INFERNO_HONEY_FOG_COLOR = new Vector3f(223f / 255f, 75f / 255f, 3f / 255f);
    public static int INFERNO_HONEY_TINT_COLOR = 0xFFdf4b03;

    
    
}