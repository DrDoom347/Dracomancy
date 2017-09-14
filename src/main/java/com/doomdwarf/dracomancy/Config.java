package com.doomdwarf.dracomancy;

import org.apache.logging.log4j.Level;

import com.doomdwarf.dracomancy.proxies.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_CRAFTING = "crafting";
	private static final String CATEGORY_MATERIALS = "materials";

	//Misc Variables
	public static boolean opMode = true;
	public static String welcomeMessage = "Welcome, thanks for using Dracomancy!";
	
	//Tool Material Variables
	public static String materialName = null;
	public static int materialHarvest = 0;
	public static int materialUses = 0;
	public static float materialEfficiency = 0F;
	public static float materialDamage = 0F;
	public static int materialEnchant = 0;
	
	//Armor Material Variables
	public static String armorName = null;
	public static int armorDurability = 0;
	public static int[] defaultReducAmnts = {0, 0, 0, 0};
	public static Property reductionAmountsProperty;
	public static int[] reductionAmounts = reductionAmountsProperty.getIntList();
	public static int armorEnch = 0;
	public static float armorTough = 0F;
	
	//Tool and Armor Variables
    public static String name = null;

	public static void readConfig()
	{
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initCraftingConfig(cfg);
			initMaterialConfig(cfg);
		} catch (Exception e1) {
			Dracomancy.logger.log(Level.ERROR, "Problem Loading config File!", e1);
		}finally{
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
	}
	private static void initGeneralConfig(Configuration cfg)
	{
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        opMode = cfg.getBoolean("opMode", CATEGORY_GENERAL, true, "Use to Enable OP Mode");
        welcomeMessage = cfg.getString("welcomeMessage", CATEGORY_GENERAL, "Welcome, thanks for using Dracomancy!", "Welcome Message");
	}
	private static void initCraftingConfig(Configuration cfg)
	{
		cfg.addCustomCategoryComment(CATEGORY_CRAFTING, "Crafting Configuration");
	}
	private static void initMaterialConfig(Configuration cfg)
	{
		cfg.addCustomCategoryComment(CATEGORY_MATERIALS, "Material Configuration");
		materialName = cfg.getString("materialName", CATEGORY_MATERIALS, "", "Material Name");
		materialHarvest = cfg.getInt("materialHarvest", CATEGORY_MATERIALS, 0, 0, 255, "Material Harvest Level");
		materialUses = cfg.getInt("materialUses", CATEGORY_MATERIALS, 0, 0, 255, "Material MaxUses");
		materialEfficiency = cfg.getFloat("materialEfficiency", CATEGORY_MATERIALS, 0, 0, 255, "Material Efficiency");
		materialDamage = cfg.getFloat("materialDamage", CATEGORY_MATERIALS, 0, 0, 255, "Material Damage");
		materialEnchant = cfg.getInt("materialEnchant", CATEGORY_MATERIALS, 0, 0, 255, "Material Enchantability");
		
		armorName = cfg.getString("armorName", CATEGORY_MATERIALS, "", "Armor Material Name");
		armorDurability = cfg.getInt("armorDurability", CATEGORY_MATERIALS, 0, 0, 255, "Armor Base Durability (This changes based on reductionAmounts)");
		reductionAmountsProperty = cfg.get(CATEGORY_MATERIALS, "reductionAmounts", defaultReducAmnts, "Armor ReductionAmounts", 0, 255, true, 4);
		armorEnch = cfg.getInt("armorEnch", CATEGORY_MATERIALS, 0, 0, 255, "Armor Enchantability");
		armorTough = cfg.getFloat("armorTough", CATEGORY_MATERIALS, 0F, 0F, 255F, "Armor Toughness");
	}
}
