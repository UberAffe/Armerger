package armerger.api;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IArmerger {

	/*
	 * gets called whenever a living hurt event is called
	 */
	public void doLivingHurtEvent(LivingHurtEvent event);
	
	/*
	 * This entity will give you access to an inventory
	 *  of armor(0-3) and baubles(4-7), helm- 0, amulet- 4
	 */
	public void setGhost(EntityLiving ghost);
	
	/*
	 * return a new NBTTagCompound
	 * can be null if you have no onTickEffects
	 */
	public NBTTagCompound sendOnTickEffects();
	/*{
	 /*
	  * 
	  /
		 * example tag
		 /
		NBTTagCompound toReturn = new NBTTagCompound();
		NBTTagCompound subTag = new NBTTagCompound();
		/*
		 * if you have potion effects to apply
		 /
		subTag.setIntArray("potion_id", new int[]{19});
		subTag.setIntArray("potion_duration", new int[]{40});
		subTag.setIntArray("potion_amplifier", new int[]{4});
		toReturn.setTag("potions", subTag);
		/*
		 * You can find a full list of tag naming schemes here:
		 * 
		 /
		return toReturn;
	}*/
}
