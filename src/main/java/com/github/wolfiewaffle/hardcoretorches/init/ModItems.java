package com.github.wolfiewaffle.hardcoretorches.init;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.items.ItemTorchBasic;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	private static int FUEL_REGULAR = ModConfig.configTorchFuel;
	private static int FUEL_COKE = ModConfig.configTorchCokeFuel;

	// public static final CreativeTabs tabTorches = new
	// CreativeTab("tabTorches", new ItemStack(Blocks.TORCH));

	// Torches
	public static ItemBlock torch_burnt = new ItemTorchBasic(ModBlocks.torch_burnt, "torch_burnt", FUEL_REGULAR, null);
	public static ItemBlock torch_lit = new ItemTorchBasic(ModBlocks.torch_lit, "torch_lit", FUEL_REGULAR, 1, null);
	public static ItemBlock torch_unlit = new ItemTorchBasic(ModBlocks.torch_unlit, "torch_unlit", FUEL_REGULAR, ModItems.torch_lit);

	// Coke torches
	public static ItemBlock torch_coke_lit = new ItemTorchBasic(ModBlocks.torch_coke_lit, "torch_coke_lit", FUEL_COKE, 1, null);
	public static ItemBlock torch_coke_unlit = new ItemTorchBasic(ModBlocks.torch_coke_unlit, "torch_coke_unlit", FUEL_COKE, ModItems.torch_coke_lit);

	// Stone torches
	public static ItemBlock torch_stone_burnt = new ItemTorchBasic(ModBlocks.torch_stone_burnt, "torch_stone_burnt", FUEL_REGULAR, null);
	public static ItemBlock torch_stone_lit = new ItemTorchBasic(ModBlocks.torch_stone_lit, "torch_stone_lit", FUEL_REGULAR, 1, null);
	public static ItemBlock torch_stone_unlit = new ItemTorchBasic(ModBlocks.torch_stone_unlit, "torch_stone_unlit", FUEL_REGULAR, ModItems.torch_stone_lit);

	// Stone coke torches
	public static ItemBlock torch_stone_coke_lit = new ItemTorchBasic(ModBlocks.torch_stone_coke_lit, "torch_stone_coke_lit", FUEL_COKE, 1, null);
	public static ItemBlock torch_stone_coke_unlit = new ItemTorchBasic(ModBlocks.torch_stone_coke_unlit, "torch_stone_coke_unlit", FUEL_COKE, ModItems.torch_stone_coke_lit);

	public static void createItems() {
		// Torches
		GameRegistry.register(torch_burnt);
		GameRegistry.register(torch_lit);
		GameRegistry.register(torch_unlit);

		// Coke torches
		GameRegistry.register(torch_coke_lit);
		GameRegistry.register(torch_coke_unlit);

		// Stone torches
		GameRegistry.register(torch_stone_burnt);
		GameRegistry.register(torch_stone_lit);
		GameRegistry.register(torch_stone_unlit);

		// Stone coke torches
		GameRegistry.register(torch_stone_coke_lit);
		GameRegistry.register(torch_stone_coke_unlit);
	}
}
