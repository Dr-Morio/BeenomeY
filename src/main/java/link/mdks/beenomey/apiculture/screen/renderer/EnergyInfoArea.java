package link.mdks.beenomey.apiculture.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.List;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  Details can be found in the license file in the root folder of this project
 */
public class EnergyInfoArea extends InfoArea {
    private final IEnergyStorage energy;

    public EnergyInfoArea(int xMin, int yMin)  {
        this(xMin, yMin, null,8,64); //Maybe need to be changed
    }

    public EnergyInfoArea(int xMin, int yMin, IEnergyStorage energy)  {
        this(xMin, yMin, energy,8,64); //Maybe need to be changed
    }

    public EnergyInfoArea(int xMin, int yMin, IEnergyStorage energy, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.getEnergyStored()+"/"+energy.getMaxEnergyStored()+" FE"));
    }

    /* Default draw function TOP->DOWN */
//    @Override
//    public void draw(PoseStack transform) {
//        final int height = area.getHeight();
//        int stored = (int)(height*(energy.getEnergyStored()/(float)energy.getMaxEnergyStored()));
//        fillGradient(
//                transform,
//                area.getX(), area.getY()+(height-stored),
//                area.getX() + area.getWidth(), area.getY() +area.getHeight(),
//                0xffb51500, 0xff600b00
//        );
//    }
    /* Custom draw function LEFT->RIGHT */
    @Override
    public void draw(PoseStack transform) {
        final int width = area.getWidth();
        int stored = (int)(width*(energy.getEnergyStored()/(float)energy.getMaxEnergyStored()));
        fillGradient(
                transform,
                area.getX(), //Start Point
                area.getY(),		 //Start Point
                area.getX() + stored, //End Point
            	area.getY() + area.getHeight(), //End Point
                0xffb51500, 0xff600b00
        );
    }
}