package com.github.wolfiewaffle.hardcoretorches.client.render.items;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.items.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderRegister {
	public static String modid = HardcoreTorches.MODID;

    public static void registerItemRenderer() {
    	//ModelLoader.setCustomModelResourceLocation(ModItems.animal_fat, 0, new ModelResourceLocation(modid + ":" + "animal_fat", "inventory"));
    	ModelLoader.setCustomModelResourceLocation(ModItems.torch_burnt, 0, new ModelResourceLocation(modid + ":" + "torch_burnt", "inventory"));
    	//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.torch_lit), 0, new ModelResourceLocation(modid + ":" + "torch_lit", "inventory"));
    }
}
