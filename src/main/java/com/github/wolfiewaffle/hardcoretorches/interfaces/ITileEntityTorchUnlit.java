package com.github.wolfiewaffle.hardcoretorches.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ITileEntityTorchUnlit {

	public int getFuelAmount();

	public default ITileEntityTorchUnlit getTileEntity(World world, BlockPos pos){
		return (ITileEntityTorchUnlit) world.getTileEntity(pos);
	}

	public void setFuel(int i);
}
