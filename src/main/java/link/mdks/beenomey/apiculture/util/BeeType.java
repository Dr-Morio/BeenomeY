package link.mdks.beenomey.apiculture.util;

import java.util.HashMap;

import link.mdks.beenomey.init.FluidInit;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public enum BeeType {
	/**Lifecycle value is provided by Main Type 
	 * LifecycleMultiplayer is provided by Second Type
	 * RandomTickChance is provided by Main Type
	 * CompDrop only occurs in Apiary and is provided by chance of 50% of Main- or SecondType
	 * CompDropMultiplayer is provided by SecondType
	*/
	
	/* Default -> Just for initialization */
	EMPTY(			1f,		5,	1.0f,	0,		1,		1.0f,	Ecosystem.UNKNOWN,   ChatFormatting.GRAY),

	/* World Spawnable */
	/* Default Live of 5 */
	FOREST(			2f,		5, 	1.0f,	0,		1,		1.0f,	Ecosystem.WARM,      ChatFormatting.DARK_GREEN),
    FROZEN(			3f,		1, 	5.0f,	-20,	2,		0.4f,	Ecosystem.COLD,      ChatFormatting.AQUA),
    OCEAN(			4f,		5, 	1.0f,	5,		1,		2.0f,	Ecosystem.HUMID,     ChatFormatting.DARK_AQUA),
    DESERT(			5f,		6, 	0.9f,	30,		1,		3.0f,	Ecosystem.DRY,       ChatFormatting.GOLD),
    ROCK(			6f,		9, 	0.6f,	-10,	3,		1.0f,	Ecosystem.DARK,      ChatFormatting.GRAY),
    INFERNO(		7f,		1,	5.0f,	35,		2,		2.0f,	Ecosystem.INFERNO,   ChatFormatting.DARK_RED),
	/* Special Default Live */	
    VOID(			8f,		2,	1.3f,	50,		10,		0.1f,	Ecosystem.VOID,      ChatFormatting.DARK_BLUE),

	/* Breeding Types */
	/* Default Live of 5 */
	COAL(			9f,		5,	1.0f,	12,		1,		1.0f,	Ecosystem.DARK,      ChatFormatting.DARK_GRAY),
	GUNPOWDER(		10f,	5,	1.0f,	28,		2,		0.5f,	Ecosystem.DRY,       ChatFormatting.DARK_GRAY),
	FLINT(			11f,	5,	1.0f,	6,		1,		1.0f,	Ecosystem.WARM,      ChatFormatting.DARK_GRAY),
	SLIME(			12f,	5,	1.0f,	-11,	1,		1.9f,	Ecosystem.HUMID,     ChatFormatting.GREEN),
    SUGAR(			13f,	5, 	1.0f,	3,		1,		1.8f,	Ecosystem.DRY,       ChatFormatting.WHITE),
	GLASS(			14f,	3,	1.0f,	0,		3,		0.6f,	Ecosystem.WARM,      ChatFormatting.LIGHT_PURPLE),
	CLAY(			15f,	5,	1.0f,	0,		2,		2.2f,	Ecosystem.HUMID,     ChatFormatting.GRAY),
	STRING(			16f,	5,	1.0f,	2,		5,		0.1f,	Ecosystem.WARM,      ChatFormatting.WHITE),
	NETHER_WART(	17f,	5,	1.0f,	-4,		1,		3.0f,	Ecosystem.INFERNO,   ChatFormatting.RED),
	SOUL_SAND(		18f,	5,	1.0f,	-11,	1,		10.0f,	Ecosystem.INFERNO,   ChatFormatting.DARK_RED),
	MUSHROOM(		19f,	5,	1.0f,	4,		10,		0.1f,	Ecosystem.DARK,      ChatFormatting.RED),
	/* Special Default Live */
    LAVA(			20f,	4, 	1.0f,	30,		2,		2.0f,	Ecosystem.WARM,      ChatFormatting.RED),
    LAPIS_LAZULI(	21f, 	4, 	1.2f,	5,		1,		1.5f,	Ecosystem.DARK,      ChatFormatting.DARK_BLUE),
    REDSTONE(		22f, 	4, 	1.8f,	15,		2,		1.7f,	Ecosystem.DARK,      ChatFormatting.RED),
    IRON(			23f, 	6, 	1.9f,	-5,		1,		1.5f,	Ecosystem.DARK,      ChatFormatting.GRAY),
    GOLD(			24f, 	3, 	1.1f,	-10,	1,		1.4f,	Ecosystem.DARK,      ChatFormatting.GOLD),
    COPPER(			25f, 	3, 	2.0f,	5,		1,		1.8f,	Ecosystem.DARK,      ChatFormatting.DARK_RED),
    DIAMOND(		26f, 	10,	1.0f,	-30,	14,		0.1f,	Ecosystem.DARK,      ChatFormatting.AQUA),
    OBSIDIAN(		27f, 	15,	1.2f,	-50,	1,		0.5f,	Ecosystem.DARK,      ChatFormatting.DARK_GRAY),
	GLOWSTONE(		28f,	4,	2.2f,	33,		64,		0.05f,	Ecosystem.INFERNO,   ChatFormatting.YELLOW),
	BLAZE(			29f,	1,	4.0f,	55,		32,		0.05f,	Ecosystem.INFERNO,   ChatFormatting.YELLOW),
	EMERALD(		30f,	3,	1.3f,	-5,		1,		1.0f,	Ecosystem.DARK,      ChatFormatting.GREEN),
	NETEHR_STAR(	31f,	2,	2.2f,	-22,	1,		1.0f,	Ecosystem.INFERNO,   ChatFormatting.WHITE),
	NETHER_QUARZ(	32f,	3,	1.1f,	-3,		1,		1.2f,	Ecosystem.INFERNO,   ChatFormatting.WHITE),
	PRISMARINE(		33f,	3,	1.0f,	-17,	1,		1.0f,	Ecosystem.HUMID,     ChatFormatting.WHITE),
	CHORUS(			34f,	3,	1.1f,	-28,	1,		1.0f,	Ecosystem.DARK,      ChatFormatting.WHITE),
	NETHERITE(		35f,	3,	0.5f,	-53,	100,	0.01f,	Ecosystem.INFERNO,   ChatFormatting.DARK_RED),
	EXP(			36f,	1,	1.0f,	-38,	128,	0.01f,	Ecosystem.WARM,      ChatFormatting.GREEN),
	ENDER_PEARL(	37f,	2,	1.1f,	80,		13,		0.15f,	Ecosystem.VOID,      ChatFormatting.DARK_BLUE),
	AMETHYST(		38f,	3,	1.1f,	-22,	22,		0.02f,	Ecosystem.DARK,      ChatFormatting.WHITE),
    OBFUSCATED(		99f,	20,	1.0f,	50,	    0,		0.0f,	Ecosystem.UNKNOWN,   ChatFormatting.OBFUSCATED);

	//kakao
	
    public float textureTypeValue;
    public int lifecycle;
    public float lifecycleMultiplier;
    public int randomTickChance;

    public int compDrop;
    public float compDropMultiplier;

    public Ecosystem ecosystem;
    public ChatFormatting textColor;

    BeeType(float textureTypeValue, int lifecycle, float lifecycleMultiplier, int randomTickChance, int compDrop, float compDropMultiplier, Ecosystem ecosystem, ChatFormatting textColor) {
        this.textureTypeValue = textureTypeValue;
        this.lifecycle = lifecycle;
        this.lifecycleMultiplier = lifecycleMultiplier;
        this.randomTickChance = randomTickChance;
        this.compDrop = compDrop;
        this.compDropMultiplier = compDropMultiplier;
        this.ecosystem = ecosystem;
        this.textColor = textColor;
    }
    
    @SuppressWarnings("serial")
	private static final HashMap<String, BeeType> fluidToTypeMap = new HashMap<String, BeeType>(){{
    	put(FluidInit.SOURCE_HONEY.getId().toString(), FOREST);
    	put(FluidInit.SOURCE_FROZEN_HONEY.getId().toString(), FROZEN);
    	put(FluidInit.SOURCE_INFERNO_HONEY.getId().toString(), INFERNO);
    }};
    
    private static BeeType getBeeType(String string) {
    	return fluidToTypeMap.get(string);
    }

	public static BeeType getBeeType(Fluid fluid) {
		return getBeeType(ForgeRegistries.FLUIDS.getKey(fluid).toString());
	}
	
    
    
    
}