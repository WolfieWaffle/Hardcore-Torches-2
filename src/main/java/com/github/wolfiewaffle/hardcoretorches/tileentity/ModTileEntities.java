package com.github.wolfiewaffle.hardcoretorches.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityTorchLit.class, "tileentity_torch_lit");
		// GameRegistry.registerTileEntity(TileEntityTorchLit.class, "tileentity_torch_coke_lit");
		GameRegistry.registerTileEntity(TileEntityTorchUnlit.class, "tileentity_torch_unlit");
		// GameRegistry.registerTileEntity(TileEntityTorchUnlit.class, "tileentity_torch_coke_unlit");
	}
}
