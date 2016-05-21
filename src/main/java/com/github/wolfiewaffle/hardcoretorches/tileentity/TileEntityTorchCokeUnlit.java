package com.github.wolfiewaffle.hardcoretorches.tileentity;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITorchUnlit;

import net.minecraft.tileentity.TileEntity;

public class TileEntityTorchCokeUnlit extends TileEntity implements ITorchUnlit {
	public static final String publicName = "tileEntityTorchCokeUnlit";
	private String name = "tileEntityTorchCokeUnlit";
	private int torchFuel = HardcoreTorches.configTorchCokeFuel;

	/**
	 * @return The name of this TileEntity
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The current fuel value of the TileEntity
	 */
	public int getFuelAmount() {
		return this.torchFuel;
	}

	/**
	 * Sets the fuel value of the TileEntity
	 * @param f The new fuel value
	 */
	public void setFuel(int f) {
		this.torchFuel = f;
	}

	/*// Needed for NBT
	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("torchFuelNBT", getFuelAmount());
	}

	// Needed for NBT
	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		this.torchFuel = par1.getInteger("torchFuelNBT");
	}*/
}
