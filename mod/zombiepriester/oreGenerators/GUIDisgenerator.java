package mod.zombiepriester.oreGenerators;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiProgress;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class GUIDisgenerator extends GuiContainer{

	Container DisCon;
	int x;
	int y;
	
	public GUIDisgenerator(InventoryPlayer inventory, TileDisgenerator tileD) {
		super(new ContainerDisgenerator(inventory,tileD));
		DisCon = new ContainerDisgenerator(inventory,tileD);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Ore Disgenerator", x+10, y+10, 0xffffff);
		}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;
		int i = mc.renderEngine.getTexture("/modTextures/GUIDisgen.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(i);
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		}

    @Override
    public void initGui() {
            super.initGui();
            controlList.add(new GuiButton(1, x + (width -150)/2, y + 95, 150, 20, "Disgenerate"));//id,x,y,w,h,n
    }

    protected void actionPerformed(GuiButton guibutton) {
            System.out.println("geht");
    }
}
