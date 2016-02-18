package armerger.items.interfaces;

import armerger.api.IArmerger;
import armerger.items.TEArmorStand;
import net.minecraft.nbt.NBTTagCompound;

public interface ILinked extends IArmerger{
	
	public abstract void acceptInfo(NBTTagCompound tickInfo);

	public abstract boolean createLink(TEArmorStand parentStand);
	
	public abstract void removeLink();
	
	public abstract boolean isLinked();
}
