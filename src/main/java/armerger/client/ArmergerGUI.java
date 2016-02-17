package armerger.client;

import armerger.items.TEArmorStand;
import armerger.lib.RefStrings;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class ArmergerGUI extends GuiContainer{

	public static int GUI_ID = RefStrings.ARMORSTANDGUIID;

	public ArmergerGUI(InventoryPlayer pInv, TEArmorStand armorStand)
	{
		super(new ContainerArmorStand(pInv, armorStand));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) 
	{
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        drawString(fontRendererObj, "Armor Stand", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        drawString(fontRendererObj, StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) 
	{
		//draw your Gui here, only thing you need to change is the path
        //ITextureObject texture = mc.renderEngine.getTexture(null);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation(RefStrings.MODID + ":" + "textures/gui/armor_stand.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
