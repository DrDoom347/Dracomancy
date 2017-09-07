package com.doomdwarf.dracomancy;

import org.apache.logging.log4j.Level;

import com.doomdwarf.dracomancy.proxies.CommonProxy;

import net.minecraftforge.common.config.Configuration;

public class Config
{
	private static final String CATEGORY_GENERAL = "general";
	@SuppressWarnings("unused")
	private static final String CATEGORY_CRAFTING = "crafting";
	
	public static boolean opMode = true;
	public static String welcomeMessage = "Welcome, thanks for using Dracomancy!";
	
	public static void readConfig()
	{
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
			initCraftingConfig(cfg);
		} catch (Exception e1) {
			Dracomancy.logger.log(Level.ERROR, "Problem Loading config File!", e1);
		}
	}
	private static void initGeneralConfig(Configuration cfg)
	{
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        opMode = cfg.getBoolean("opMode", CATEGORY_GENERAL, true, "Use to Enable OP Mode", welcomeMessage);
	}
	private static void initCraftingConfig(Configuration cfg)
	{
		
	}
}
