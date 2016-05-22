package com.github.wolfiewaffle.hardcoretorches.tileentity;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITileEntityTorchUnlit;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTorchUnlit extends TileEntity implements ITileEntityTorchUnlit {
	public static final String publicName = "tileEntityTorchUnlit";
	private int fuel = HardcoreTorches.configTorchFuel;

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
	public void setFuel(int f) {
		this.fuel = f;
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
