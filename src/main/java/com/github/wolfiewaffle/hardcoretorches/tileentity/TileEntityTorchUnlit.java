package com.github.wolfiewaffle.hardcoretorches.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTorchUnlit extends TileEntity {
	public static final String publicName = "tileEntityTorchUnlit";
	private int fuel = 0;

	/**
	 * @return The current fuel value of the TileEntity
	 */
	public int getFuelAmount() {
		return this.fuel;
	}

	/**
	 * Sets the fuel value of the TileEntity
	 * @param f The new fuel value
	 */
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	// Needed for NBT
	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("torchFuelNBT", getFuelAmount());
	}

	// Needed for NBT
	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		this.fuel = par1.getInteger("torchFuelNBT");
	}
}
