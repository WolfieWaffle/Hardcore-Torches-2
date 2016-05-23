package com.github.wolfiewaffle.hardcoretorches.client.render.items;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderRegister {
	public static String modid = HardcoreTorches.MODID;

	public static void registerItemRenderer() {
		// Torches
		reg(ModItems.torch_burnt, "torch_burnt");
		reg(ModItems.torch_lit, "torch_lit");
		reg(ModItems.torch_unlit, "torch_unlit");

		// Coke torches
		if (ModConfig.configRegCokeTorches) {
			reg(ModItems.torch_coke_lit, "torch_coke_lit");
			reg(ModItems.torch_coke_unlit, "torch_coke_unlit");
		}

		// Stone torches
		if (ModConfig.configRegStoneTorches) {
			reg(ModItems.torch_stone_burnt, "torch_stone_burnt");
		}
	}

	public static void reg(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("hardcoretorches:" + name, "inventory"));
	}
}
