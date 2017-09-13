package com.doomdwarf.dracomancy.helpers;

import com.doomdwarf.dracomancy.Config;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialNames
{
	public enum EnumMaterials
	{
		//Name, harvest level, durability, mining speed, damage versus entities, enchantability
		
		WOOD(0x866526, "plankWood", EnumHelper.addToolMaterial("Wood", 0, 354, 1.2f, 3f, 15)),
        STONE(0x9A9A9A, "cobblestone", EnumHelper.addToolMaterial("Stone", 1, 786, 2.4f, 4f, 5)),
        IRON(0xFFFFFF, "ingotIron", EnumHelper.addToolMaterial("Iron", 2, 1500, 3.6f, 5f, 14)),
        GOLD(0xEAEE57, "ingotGold", EnumHelper.addToolMaterial("Gold", 0, 192, 7.2f, 3f, 22)),
        DIAMOND(0x33EBCB, "gemDiamond", EnumHelper.addToolMaterial("Diamond", 3, 9366, 4.8f, 6f, 10)),
        
		FAERUM(0xECEF59, "faerumingot", EnumHelper.addToolMaterial(Config.materialName, Config.materialHarvest, Config.materialUses, Config.materialEfficiency, Config.materialDamage, Config.materialEnchant));
		
		public int colour = -1;
        public Item.ToolMaterial material;
        public String dependantOreDic = null;
        public static EnumMaterials[] VANILLA;

        static
        {
            VANILLA = new EnumMaterials[]{WOOD, STONE, IRON, GOLD, DIAMOND};
        }

        EnumMaterials(Item.ToolMaterial material)
        {
            this.material = material;
        }

        EnumMaterials(int colour, Item.ToolMaterial material)
        {
            this.colour = colour;
            this.material = material;
        }

        EnumMaterials(int colour, String dependantOreDic, Item.ToolMaterial material)
        {
            this(colour, material);
            this.dependantOreDic = dependantOreDic;
        }

		public String getMaterialName()
		{
			return this.material.toString();
		}
	}
}
