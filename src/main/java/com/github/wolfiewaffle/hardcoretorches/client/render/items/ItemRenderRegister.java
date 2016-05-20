package com.github.wolfiewaffle.hardcoretorches.client.render.items;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.items.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderRegister {
	public static String modid = HardcoreTorches.MODID;

    public static void registerItemRenderer() {
    	reg(ModItems.torch_burnt, "torch_burnt");
    	System.out.println("Made it to item render");
    }

	public static void reg(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("hardcoretorches:torch_burnt", "inventory"));
		System.out.println("Made it to reg");
	}
}
