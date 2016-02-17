package armerger.client;

import armerger.items.TEArmorStand;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{
	
	public GuiHandler()
	{
		super();
		
	}	
	
	//returns an instance of the Container
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TEArmorStand)
            return new ContainerArmorStand(player.inventory, (TEArmorStand) tileEntity);
        return null;
    }
    
    //returns an instance of the Gui
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TEArmorStand)
                return new ArmergerGUI(player.inventory, (TEArmorStand) tileEntity);
        return null;
    }

}
