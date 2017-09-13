package com.doomdwarf.dracomancy;

import org.apache.logging.log4j.Logger;

import com.doomdwarf.dracomancy.init.ModItems;
import com.doomdwarf.dracomancy.proxies.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, useMetadata = true)
public class Dracomancy
{
	@SidedProxy(clientSide = "com.doomdwarf.dracomancy.proxies.ClientProxy", serverSide = "com.doomdwarf.dracomancy.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
    public static Dracomancy instance;

    public static Logger logger;
    
    public static final CreativeTabs DRACO_TAB = new CreativeTabs(Reference.MODID)
    {
    	@Override
    	public ItemStack getTabIconItem()
    	{
    		return new ItemStack(ModItems.faerumingot);
    	}
    	
    	@Override
    	public String getTranslatedTabLabel()
    	{
    		return Reference.NAME;
    	}
    }
    ;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
