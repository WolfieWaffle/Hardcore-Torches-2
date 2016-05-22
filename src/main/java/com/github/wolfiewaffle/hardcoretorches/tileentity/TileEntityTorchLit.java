package com.github.wolfiewaffle.hardcoretorches.tileentity;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.init.ModItems;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITileEntityTorchLit;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTorchLit extends TileEntity implements net.minecraft.util.ITickable, ITileEntityTorchLit {
	public static final String publicName = "tileEntityTorchLit";
	private int fuel = ModItems.torch_lit.getMaxDamage();
	private int tickCounter = 0; // Used to count seconds

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

	@Override
	public void update() {
		// Don't update on the client
		if (worldObj.isRemote) {
			return;
		}

		tickCounter++;

		// If one second has passed
		if (tickCounter == 20) {
			fuel --;

			//Mark that the value has changed
			markDirty();

			//If the new fuel value is less than 0, replace the block with a Burnt Torch.
			if (fuel < 0) {
				if (HardcoreTorches.configDebug)
					System.out.printf("Torch at %d, %d, %d has burnt (fuel %d)\n", pos.getX(), pos.getY(), pos.getZ(), fuel);
				worldObj.setBlockState(pos, ModBlocks.torch_burnt.getDefaultState());
			}

			tickCounter = 0;
		}
	}
}
