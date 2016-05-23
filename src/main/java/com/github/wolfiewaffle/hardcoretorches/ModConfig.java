package com.github.wolfiewaffle.hardcoretorches;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {

	// Config options
	public static boolean configDebug;
	public static int configTorchFuel;
	public static boolean configRegCokeTorches;
	public static boolean configRegStoneTorches;
	public static int configTorchCokeFuel;
	public static String[] configLightItems;
	public static String[] configFreeLightItems;
	public static int configTorchDropMode;
	public static boolean configLightInInventory;

	// Set of resource locations to store for crafting
	public static Set<ResourceLocation> lightItems = new HashSet<ResourceLocation>();
	public static Set<ResourceLocation> freeLightItems = new HashSet<ResourceLocation>();

	// Event to create config file
	public static void init(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/hardcoretorches.cfg"));

		config.load();

		// Clear the set
		freeLightItems.clear();

		// Get config options
		configDebug = config.getBoolean("debug output", Configuration.CATEGORY_GENERAL, false, "Output debug info. Not useful for players.");
		configTorchFuel = config.getInt("regular torch fuel", Configuration.CATEGORY_GENERAL, 1000, 1, Integer.MAX_VALUE, "The max duration of a basic torch in seconds. Might not update already placed torches.");
		configRegCokeTorches = config.getBoolean("enable coke torches", Configuration.CATEGORY_GENERAL, false, "Enable coke torches. Requires a mod that adds oreDictionary coal coke.");
		configRegStoneTorches = config.getBoolean("enable stone torches", Configuration.CATEGORY_GENERAL, false, "Enable stone torches. Requires Tinker's Construct.");
		configTorchCokeFuel = config.getInt("coke torch fuel", Configuration.CATEGORY_GENERAL, 2000, 1, Integer.MAX_VALUE, "The max duration of a coke torch in seconds. Might not update already placed torches. (Requires enable coke torches: true)");
		configLightItems = config.getStringList("consumed lighter items", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:flint", "minecraft:flint_and_steel", "fire_charge"}, "A list of items that can be used to light a torch. If the item is not damageable, it will be consumed.");
		configFreeLightItems = config.getStringList("free lighter items", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:torch", "minecraft:lava_bucket", "hardcoretorches:torch_lit", "hardcoretorches:torch_coke_lit"}, "A list of items that can be used to light a torch. These will not be damaged or consumed.");
		configTorchDropMode = config.getInt("torch drop mode", Configuration.CATEGORY_GENERAL, 0, 0, 2, "0: Torches drop as lit torches when broken\n1: Torches drop as unlit torches when broken\n2: Torches burn out completely when broken");
		configLightInInventory = config.getBoolean("light torches in inventory", Configuration.CATEGORY_GENERAL, true, "Allow lighting torches in the crafting grid.");

		// Add all the items to the resource location set
		for (String string : configLightItems) {
			ResourceLocation rl = new ResourceLocation(string);
			lightItems.add(rl);
		}

		// Add all the items to the resource location set
		for (String string : configFreeLightItems) {
			ResourceLocation rl = new ResourceLocation(string);
			freeLightItems.add(rl);
		}

		config.save();
	}
}
