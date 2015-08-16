package armerger.items;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import armerger.lib.RefStrings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmorStand extends BlockContainer{

	private static final String name = "armorstand";
	
	public static final ArmorStand armorstand = new ArmorStand();
	
	public ArmorStand() {
		super(Material.rock);
		// TODO Auto-generated constructor stub
		setBlockName(RefStrings.MODID + "_" + name);
		setBlockTextureName(RefStrings.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2F);
		setResistance(5F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		// TODO Auto-generated method stub
		return new TEArmorStand();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float RoX, float RoY, float RoZ)
	{
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		setBlockBounds(0f, 0f, 0.125f, 1f, 2f, 0.75f);//0.33f, 0.33f, 0.33f, 0.66f, 0.66f, 0.66f
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;//ArmorStandRenderer.renderID;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	//shows in hand
	public void registerBlockIcons(IIconRegister ireg)
	{
		blockIcon = ireg.registerIcon(RefStrings.MODID + ":" + name + "icon");
	}
}
