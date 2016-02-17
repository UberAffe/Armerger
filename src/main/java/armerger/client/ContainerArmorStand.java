package armerger.client;

import armerger.items.TEArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerArmorStand extends Container{

	protected TEArmorStand armorStand;
	
	public ContainerArmorStand(InventoryPlayer pInv, TEArmorStand teStand)
	{
		armorStand = teStand;
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 4; j++)
				addSlotToContainer(new Slot(teStand, j + (i * 4), 8 + (i * 73), 8 + (j * 18)));
		bindPlayerInventory(pInv);
	}
	
	protected void bindPlayerInventory(InventoryPlayer pInv) 
	{
		//add inventory
		for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 9; j++)
                    addSlotToContainer(new Slot(pInv, j + (i * 9) + 8, 8 + j * 18, 84 + i * 18));
        //add hotbar
		for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(pInv, i + 26, 8 + (i * 18), 142));
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) 
	{
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                if (slot < armorStand.getSizeInventory()) {
                        if (!this.mergeItemStack(stackInSlot, armorStand.getSizeInventory(), 36 + armorStand.getSizeInventory(), true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                else if (!this.mergeItemStack(stackInSlot, 0, armorStand.getSizeInventory(), false)) {
                        return null;
                }

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return armorStand.isUseableByPlayer(player);
	}

}
