package com.github.wolfiewaffle.hardcoretorches.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTest extends Block {

	public BlockTest(String name) {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

	@Override
	  public boolean isOpaqueCube() {
	    return false;
	  }
}
