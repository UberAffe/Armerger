package armerger.items;

import armerger.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class UnlinkedBlock extends Block{

	private static final String name = "unlinkedblock";
	public static final UnlinkedBlock UnlinkedBlock = new UnlinkedBlock();
	
	public UnlinkedBlock() {
		super(Material.rock);
		setBlockName(RefStrings.MODID + "_" + name);
		setBlockTextureName(RefStrings.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2F);
		setResistance(5F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 2);
	}
	
	/*@Override 
	@SideOnly (Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon(RefStrings.MODID + ":" + name);
	}*/
}
