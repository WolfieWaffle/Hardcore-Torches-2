package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.blocks.ModBlocks;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static ItemBlock torch_burnt = new ItemTorchBurnt(ModBlocks.torch_burnt);
	public static ItemBlock torch_lit = new ItemTorchLit(ModBlocks.torch_lit);
	public static ItemBlock torch_unlit = new ItemTorchUnlit(ModBlocks.torch_unlit);
	//public static ItemBlock torch_coke_lit = new ItemTorchCokeUnlit(ModBlocks.torch_coke_lit);
	//public static ItemBlock torch_coke_unlit = new ItemTorchCokeUnlit(ModBlocks.torch_coke_unlit);

	public static void createItems() {
		GameRegistry.register(torch_burnt);
		GameRegistry.register(torch_lit);
		GameRegistry.register(torch_unlit);
		//GameRegistry.register(torch_coke_unlit);
	}
}
