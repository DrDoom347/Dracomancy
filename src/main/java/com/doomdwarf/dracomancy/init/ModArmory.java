package com.doomdwarf.dracomancy.init;

import com.doomdwarf.dracomancy.Config;
import com.doomdwarf.dracomancy.items.DracomancyCustomArmor;
import com.doomdwarf.dracomancy.items.DracomancyCustomTool;
import com.doomdwarf.dracomancy.items.ItemDracomancyArmor;
import com.doomdwarf.dracomancy.items.ItemDracomancyAxe;
import com.doomdwarf.dracomancy.items.ItemDracomancyHoe;
import com.doomdwarf.dracomancy.items.ItemDracomancyPickaxe;
import com.doomdwarf.dracomancy.items.ItemDracomancyShovel;
import com.doomdwarf.dracomancy.items.ItemDracomancySword;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmory
{
	//Pull Values from config file to create the Tool Material
	static Item.ToolMaterial MATERIAL = EnumHelper.addToolMaterial(Config.materialName, Config.materialHarvest, Config.materialUses, Config.materialEfficiency, Config.materialDamage, Config.materialEnchant);
	//Pull Values from config file to create the Armor Material
	static ArmorMaterial AMATERIAL = EnumHelper.addArmorMaterial(Config.armorName, Config.armorName, Config.armorDurability, Config.reductionAmounts, Config.armorEnch, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Config.armorTough);
	
	static String materialName = Config.materialName;
	static String armorName = Config.armorName;
	static DracomancyCustomTool tool = new DracomancyCustomTool(materialName, MATERIAL);
	static DracomancyCustomArmor armor = new DracomancyCustomArmor(armorName, AMATERIAL);
	
	//Tools
	static Item sword = new ItemDracomancySword(tool);
	static Item pickaxe = new ItemDracomancyPickaxe(tool);
	static Item shovel = new ItemDracomancyShovel(tool);
	static Item axe = new ItemDracomancyAxe(tool);
	static Item hoe = new ItemDracomancyHoe(tool);
	//Armors
	static Item helmet = new ItemDracomancyArmor(AMATERIAL, 0, EntityEquipmentSlot.HEAD, armorName);
	static Item chestplate = new ItemDracomancyArmor(AMATERIAL, 0, EntityEquipmentSlot.CHEST, armorName);
	static Item leggings = new ItemDracomancyArmor(AMATERIAL, 1, EntityEquipmentSlot.LEGS, armorName);
	static Item boots = new ItemDracomancyArmor(AMATERIAL, 0, EntityEquipmentSlot.FEET, armorName);

	public static void registerItems()
	{
		
	}
}
