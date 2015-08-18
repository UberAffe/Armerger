package armerger.items.armor;

import net.minecraft.item.ItemArmor;
import net.minecraft.nbt.NBTTagCompound;

public abstract class LinkedArmor extends ItemArmor{

	public LinkedArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void acceptInfo(NBTTagCompound[] info);
}
