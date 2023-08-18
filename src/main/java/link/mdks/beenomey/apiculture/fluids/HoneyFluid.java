package link.mdks.beenomey.apiculture.fluids;

import org.joml.Vector3f;

import link.mdks.beenomey.BeenomeY;
import net.minecraft.resources.ResourceLocation;


/* Holds values for Honey Fluid */
public class HoneyFluid {
    //public static final ResourceLocation STILL = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_still");
    //public static final ResourceLocation FLOWING = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_flow");
    
    public static final ResourceLocation STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOWING = new ResourceLocation("block/water_flow");
    public static final ResourceLocation OVERLAY = new ResourceLocation(BeenomeY.MODID, "block/honey/honey_overlay");
	private static Vector3f fogColor = new Vector3f(217f / 255f, 182f / 255f, 16f / 255f);
    private static int tintColor = 0x1AF3CE1E;

    
    public static int getTintColor() {
    	return tintColor;
    }
    
    public static Vector3f getFogColor() {
    	return fogColor;
    }
    
    
}