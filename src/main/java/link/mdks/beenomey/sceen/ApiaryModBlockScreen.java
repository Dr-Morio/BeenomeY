package link.mdks.beenomey.sceen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import link.mdks.beenomey.BeenomeY;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ApiaryModBlockScreen extends AbstractContainerScreen<ApiaryModBlockMenu>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(BeenomeY.MODID, "textures/gui/apiary_mod_block_menu.png");
	
	public ApiaryModBlockScreen(ApiaryModBlockMenu menu, Inventory inventory, Component component) {
		super(menu, inventory, component);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialtick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		
		this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
		renderProgressBar(pPoseStack, x, y);
		
	}

	private void renderProgressBar(PoseStack pPoseStack, int x, int y) {
		if(menu.isCrafting()) {
			//blit(pPoseStack,x + 164,y + 11,176,0,4,  menu.getScaledProgress());
			blit(pPoseStack,x + 56,y + 13,176,0,64,  menu.getScaledProgress()); 
		}
	}

	@Override
	public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, mouseX,mouseY,delta);
		renderTooltip(pPoseStack, mouseX, mouseY);
	}
	
	@Override
	protected void init() {
		super.init();
	}
	
	
	
	
}
