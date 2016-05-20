package com.github.wolfiewaffle.hardcoretorches;

import java.io.File;

import com.github.wolfiewaffle.hardcoretorches.crafting.RecipeRemover;
import com.github.wolfiewaffle.hardcoretorches.crafting.Recipes;
import com.github.wolfiewaffle.hardcoretorches.proxy.CommonProxy;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HardcoreTorches.MODID, name = HardcoreTorches.MODNAME, version = HardcoreTorches.VERSION)
public class HardcoreTorches {
	public static final String MODID = "hardcoretorches";
	public static final String MODNAME = "Hardcore Torches";
	public static final String VERSION = "@VERSION@";

    // Config options
    public static boolean configDebug;
    public static int configTorchFuel;
    public static int configTorchCokeFuel;
    public static int configTorchDropMode;
    public static String[] configLightItems;
    public static String[] configFreeLightItems;

    @SidedProxy(clientSide="com.github.wolfiewaffle.hardcoretorches.proxy.ClientProxy", serverSide="com.github.wolfiewaffle.hardcoretorches.proxy.ServerProxy")
    public static CommonProxy proxy;

    // Instance so we can refer to the mod later
    @Instance
    public static HardcoreTorches instance = new HardcoreTorches();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/hardcoretorches.cfg"));

		config.load();

		configDebug = config.getBoolean("configDebug", Configuration.CATEGORY_GENERAL, false, "Output debug info. Not useful for players.");
		configTorchFuel = config.getInt("configTorchFuel", Configuration.CATEGORY_GENERAL, 1000, 1, Integer.MAX_VALUE, "The max duration of a basic torch in seconds. Might not update already placed torches.");
		//configTorchCokeFuel = config.getInt("configTorchCokeFuel", Configuration.CATEGORY_GENERAL, 2000, 1, Integer.MAX_VALUE, "The max duration of a coke torch in seconds");
		configTorchDropMode = config.getInt("configTorchDropMode", Configuration.CATEGORY_GENERAL, 0, 0, 2, "0: Torches drop as lit torches when broken\n1: Torches drop as unlit torches when broken\n2: Torches burn out completely when broken");
		configLightItems = config.getStringList("configLightItems", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:flint", "minecraft:flint_and_steel", "fire_charge"}, "A list of items that can be used to light a torch. If the item is not damageable, it will be consumed.");
		configFreeLightItems = config.getStringList("configFreeLightItems", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:torch", "minecraft:lava_bucket", "hardcoretorches:torch_lit", "hardcoretorches:torch_coke_lit"}, "A list of items that can be used to light a torch. These will not be damaged or consumed.");

		config.save();

		HardcoreTorches.proxy.preInit(event);
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		HardcoreTorches.proxy.init(event);
		Recipes.init();
		RecipeRemover.removeAnyRecipe(Item.getItemFromBlock(Blocks.torch));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		HardcoreTorches.proxy.postInit(event);
	}
}
