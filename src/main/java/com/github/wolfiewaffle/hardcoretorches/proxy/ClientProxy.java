package com.github.wolfiewaffle.hardcoretorches.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.github.wolfiewaffle.hardcoretorches.client.render.blocks.BlockRenderRegister;
import com.github.wolfiewaffle.hardcoretorches.client.render.items.ItemRenderRegister;

public class ClientProxy extends CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    	super.preInit(e);

        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();
    }

    public void init(FMLInitializationEvent e) {
    	super.init(e);
    }

    public void postInit(FMLPostInitializationEvent e) {
    	super.postInit(e);
    }
}
