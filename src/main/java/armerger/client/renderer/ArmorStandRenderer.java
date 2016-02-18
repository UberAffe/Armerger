package armerger.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import armerger.client.models.ArmorStandModel;
import armerger.items.TEArmorStand;
import armerger.lib.RefStrings;

public class ArmorStandRenderer extends TileEntitySpecialRenderer{
	
	public final ArmorStandModel model = new ArmorStandModel();
	
	public ArmorStandRenderer(){}

	@Override
	public void renderTileEntityAt(TileEntity ent, double x, double y, double z,
			float p_147500_8_) {
		//The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        //This is the texture of your block. It's pathed to be the same place as your other blocks here.
        ResourceLocation textures = (new ResourceLocation(RefStrings.MODID + ":" + "textures/blocks/armorstand.png"));
        //the ':' is very important
        //binding the textures
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                      
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(ent.blockMetadata * (90f), 0.0F, 1.0F, 0.0F); //rotates stand to face placer.
        //A reference to your Model file. Again, very important.
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        /*TEArmorStand teAS = (TEArmorStand) ent;
        for(int i = 0; i < 4; i++)
        {
        	ItemStack item = teAS.getStackInSlot(i);
        	if(item != null)
        		item.getItem().getArmorModel(entityLiving, item, i);
        }*/
        //Tell it to stop rendering for both the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();        
	}
}
