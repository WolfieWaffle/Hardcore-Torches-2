package com.github.wolfiewaffle.hardcoretorches.client.render.items;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.blocks.ModBlocks;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderRegister {
	public static String modid = HardcoreTorches.MODID;

    public static void registerItemRenderer() {
    	//ModelLoader.setCustomModelResourceLocation(ModItems.animal_fat, 0, new ModelResourceLocation(modid + ":" + "animal_fat", "inventory"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.torch_burnt), 0, new ModelResourceLocation(modid + ":" + "torch_burnt", "inventory"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.torch_lit), 0, new ModelResourceLocation(modid + ":" + "torch_lit", "inventory"));
    }
}
