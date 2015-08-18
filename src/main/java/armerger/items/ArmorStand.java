package armerger.items;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

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
		return new TEArmorStand(world);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float RoX, float RoY, float RoZ)
	{
		TileEntity tEnt = world.getTileEntity(x, y, z);
		if(tEnt instanceof TEArmorStand)
		{
			TEArmorStand self = (TEArmorStand)tEnt;
			ItemStack heldStack = player.getHeldItem();
			if(heldStack == null || self.containsType(heldStack))
				return self.onActivate(world, x, y, z, player);
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
	{
		int facing = getFacing((double)(placer.rotationYaw));// + 0.5D)) + 7) % 8;
		world.setBlockMetadataWithNotify(x, y, z, facing, 2);
		
	}
	
	private int getFacing(double yaw)
	{
		while(yaw < 0)
			yaw += 360;
		int angle = (int)yaw %360;
		int facing = 0;
		if(angle <= 135 && angle > 45)
			facing += 1;
		else if(angle <= 225 && angle > 135)
			facing += 2;
		else if(angle <= 315 && angle > 225)
			facing += 3;
		System.out.println(facing);
		return facing;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int mdata = world.getBlockMetadata(x, y, z);
		switch(mdata)
		{
		case 0:
			setBlockBounds(0f, 0f, 0.125f, 1f, 2f, 0.75f);//0.33f, 0.33f, 0.33f, 0.66f, 0.66f, 0.66f
			break;
		case 1:
			setBlockBounds(0.125f, 0f, 0f, 0.75f, 2f, 1f);//0.33f, 0.33f, 0.33f, 0.66f, 0.66f, 0.66f
			break;
		case 2:
			setBlockBounds(0f, 0f, 0.75f, 1f, 2f, 0.125f);//0.33f, 0.33f, 0.33f, 0.66f, 0.66f, 0.66f
			break;
		case 3:
			setBlockBounds(0.75f, 0f, 0f, 0.125f, 2f, 1f);//0.33f, 0.33f, 0.33f, 0.66f, 0.66f, 0.66f
			break;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB alignment, List list, Entity ent)
	{
		super.addCollisionBoxesToList(world, x, y, z, alignment, list, ent);
		super.addCollisionBoxesToList(world, x, y + 1, z, alignment, list, ent);
		setBlockBounds(0f, 0f, 0.125f, 1f, 2f, 0.75f);
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
		blockIcon = ireg.registerIcon(RefStrings.MODID + ":" + name);
	}
}
