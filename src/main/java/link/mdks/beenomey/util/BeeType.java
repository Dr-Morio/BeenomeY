package link.mdks.beenomey.util;

import net.minecraft.ChatFormatting;

public enum BeeType {
    EMPTY(1.0f, 1,1.0f, 0, 1, 1.0f, ChatFormatting.GRAY),
    FOREST(2.0f, 7, 1.0f, 0, 1, 1.0f, ChatFormatting.DARK_GREEN),
    ICE(3.0f, 8, 1.4f, -20, 1, 0.4f, ChatFormatting.AQUA),
    WATER(4.0f, 5, 0.6f, 10, 1, 1.6f, ChatFormatting.DARK_AQUA),
    SAND(5.0f, 6, 0.8f, 30, 1, 3f, ChatFormatting.GOLD),
    STONE(6.0f, 9, 1.6f, 0, 1, 1.0f, ChatFormatting.GRAY),
    NETHER(7.0f, 3, 1.5f, 35, 2, 3.0f, ChatFormatting.DARK_RED),
    ENDER(8.0f, 1, 1.3f, 50, 3, 4.0f, ChatFormatting.DARK_BLUE),
    LAVA(9.0f, 4, 1f, 30, 2, 2.0f, ChatFormatting.RED),
    PAPER(10f, 4, 1f, 10, 2, 1.8f, ChatFormatting.WHITE),
    LAPIS_LAZULI(10F, 5, 1.2f, 10, 1, 1.5f, ChatFormatting.DARK_BLUE),
    REDSTONE(10F, 4, 1.8f, 20, 2, 1.7f, ChatFormatting.RED),
    IRON(10F, 5, 1.9f, -5, 1, 1.5f,ChatFormatting.GRAY),
    GOLD(10F, 2, 1.1f, -10, 2, 1.4f,ChatFormatting.GOLD),
    COPPER(10F, 1, 2.2f, 5, 2, 1.8f, ChatFormatting.DARK_RED),
    DIAMOND(10F, 10, 2.4f, -30, 1, 0.5f, ChatFormatting.AQUA),
    OBSIDIAN(10F, 15, 3.5f, -50, 1, 0.5f, ChatFormatting.OBFUSCATED);


    public float textureTypeValue;
    public int lifecycle;
    public float lifecycleMultiplier;
    public int randomTickChance;

    public int compDrop;
    public float compDropMultiplier;

    public ChatFormatting textColor;

    BeeType(float textureTypeValue, int lifecycle, float lifecycleMultiplier, int randomTickChance, int compDrop, float compDropMultiplier, ChatFormatting textColor) {
        this.textureTypeValue = textureTypeValue;
        this.lifecycle = lifecycle;
        this.lifecycleMultiplier = lifecycleMultiplier;
        this.randomTickChance = randomTickChance;
        this.compDrop = compDrop;
        this.compDropMultiplier = compDropMultiplier;
        this.textColor = textColor;
    }
}