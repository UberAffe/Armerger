package armerger.proxy;

import armerger.items.TEArmorStand;
import armerger.items.UnlinkedIngot;
import armerger.items.armor.LinkedBoots;
import armerger.items.armor.LinkedChest;
import armerger.items.armor.LinkedHelm;
import armerger.items.armor.LinkedLegs;
import armerger.client.ArmergerGUI;
import armerger.items.ArmorStand;
import armerger.lib.RefStrings;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public abstract class CommonProxy implements IGuiHandler{

	public void preInit()
	{
		registerMaterials();
		registerItems();
		registerBlocks();
		registerTileEntitys();
	}
	
	public void init()
	{
		registerRecipes();
	}
	
	public void postInit()
	{
		
	}
	
	public void registerMaterials()
	{
		EnumHelper.addArmorMaterial(RefStrings.ARMORMATERIAL, 100, new int[]{0,0,0,0}, 0);
	}
	
	public void registerBlocks()
	{
		GameRegistry.registerBlock(ArmorStand.armorstand, RefStrings.MODID + "_" + "armorstand");
	}
	
	public void registerTileEntitys()
	{
		GameRegistry.registerTileEntity(TEArmorStand.class, RefStrings.MODID + "_" + "armorstand");
	}
	
	public void registerItems()
	{
		GameRegistry.registerItem(UnlinkedIngot.unlinkedingot, RefStrings.MODID + "_" + "unlinkedingot");
		GameRegistry.registerItem(LinkedHelm.linkedhelm, RefStrings.MODID + "_" + "linkedhelm");
		GameRegistry.registerItem(LinkedChest.linkedchest, RefStrings.MODID + "_" + "linkedchest");
		GameRegistry.registerItem(LinkedLegs.linkedlegs, RefStrings.MODID + "_" + "linkedlegs");
		GameRegistry.registerItem(LinkedBoots.linkedboots, RefStrings.MODID + "_" + "linkedboots");
	}
	
	public void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(UnlinkedIngot.unlinkedingot, 6, 0), "BEB","BNB","BEB",'B',Blocks.iron_block,'E',Items.ender_pearl,'N',Items.nether_star);
		GameRegistry.addRecipe(new ItemStack(LinkedHelm.linkedhelm), "UUU","U U", 'U', UnlinkedIngot.unlinkedingot);
		GameRegistry.addRecipe(new ItemStack(LinkedChest.linkedchest), "U U","UUU","UUU", 'U', UnlinkedIngot.unlinkedingot);
		GameRegistry.addRecipe(new ItemStack(LinkedLegs.linkedlegs), "UUU","U U","U U", 'U', UnlinkedIngot.unlinkedingot);
		GameRegistry.addRecipe(new ItemStack(LinkedBoots.linkedboots), "U U","U U", 'U', UnlinkedIngot.unlinkedingot);
	}
	
	public abstract void registerRenderers();

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if( ID == ArmergerGUI.GUI_ID)
			return new ArmergerGUI();
		return null;
	}
}
