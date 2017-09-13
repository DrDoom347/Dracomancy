package com.doomdwarf.dracomancy.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.doomdwarf.dracomancy.Config;
import com.doomdwarf.dracomancy.helpers.CustomTools;
import com.doomdwarf.dracomancy.items.DracomancyCustomArmor;
import com.doomdwarf.dracomancy.items.DracomancyCustomTool;
import com.doomdwarf.dracomancy.items.IColourable;
import com.doomdwarf.dracomancy.items.ItemDracomancyArmor;
import com.doomdwarf.dracomancy.items.ItemDracomancyAxe;
import com.doomdwarf.dracomancy.items.ItemDracomancyHoe;
import com.doomdwarf.dracomancy.items.ItemDracomancyPickaxe;
import com.doomdwarf.dracomancy.items.ItemDracomancyShovel;
import com.doomdwarf.dracomancy.items.ItemDracomancySword;

import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModArmory
{
	//List of all Items
	public static List<Item> ITEMS = new ArrayList<>();
	//Contains all of the items which use a basic coloured texture
    private static List<Item> COLOURED_ITEMS = new ArrayList<>();
    private static List<DracomancyCustomArmor> ARMOURS = new ArrayList<>();
    
	//Pull Values from config file to create the Tool Material
	static Item.ToolMaterial material = EnumHelper.addToolMaterial(Config.materialName, Config.materialHarvest, Config.materialUses, Config.materialEfficiency, Config.materialDamage, Config.materialEnchant);
	//Pull Values from config file to create the Armor Material
	static ArmorMaterial amaterial = EnumHelper.addArmorMaterial(Config.armorName, Config.armorName, Config.armorDurability, Config.reductionAmounts, Config.armorEnch, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Config.armorTough);
	
	//Add each item to the List to be registered
	@SuppressWarnings("unchecked")
	public static void registerItem(Item item)
	{
		ITEMS.add(item);
		if(item instanceof IColourable && ((IColourable)item).getTextureColour() >= 0)
            COLOURED_ITEMS.add(item);
		if(item instanceof ItemDracomancyArmor)
			ARMOURS.addAll((Collection<? extends DracomancyCustomArmor>) item);
	}
	
	//Register tools
	public static void registerTool(DracomancyCustomTool tool)
	{
		//Only attempt to register if the configurations have set values!
		if (Config.materialName !=null)
		{
			String name = tool.name;
			switch(name)
			{			
			default:
				registerItem(new ItemDracomancySword(tool));
				registerItem(new ItemDracomancyPickaxe(tool));
				registerItem(new ItemDracomancyShovel(tool));
				registerItem(new ItemDracomancyAxe(tool));
				registerItem(new ItemDracomancyHoe(tool));
			}
		}
	}
	
	public static void registerArmor(DracomancyCustomArmor armor)
	{
		if(Config.armorName !=null)
		{
			String name = armor.name;
			switch(name)
			{
			default:
				registerItem(new ItemDracomancyArmor(amaterial, 0, EntityEquipmentSlot.HEAD, armor.name + "_helmet"));
				registerItem(new ItemDracomancyArmor(amaterial, 0, EntityEquipmentSlot.CHEST, armor.name + "_chestplate"));
				registerItem(new ItemDracomancyArmor(amaterial, 1, EntityEquipmentSlot.LEGS, armor.name + "_leggings"));
				registerItem(new ItemDracomancyArmor(amaterial, 0, EntityEquipmentSlot.FEET, armor.name + "_boots"));
			}
		}
	}
	
	public static void registerItems()
	{
		if(!ITEMS.isEmpty()) return;
		
		//Gets tools from json file
        List<DracomancyCustomTool> tools = CustomTools.read();
        
        //Register tools from json
        tools.forEach(ModArmory::registerTool);
        /*
        *List<DracomancyCustomArmor> armours = ARMOURS;
        *armours.forEach(ModArmory::registerArmor);
        */
	}
	
	@SideOnly(Side.CLIENT)
    public static void regColours()
    {
        //Register item colours
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? ((IColourable)stack.getItem()).getTextureColour() : -1, COLOURED_ITEMS.toArray(new Item[]{}));
    }
}
