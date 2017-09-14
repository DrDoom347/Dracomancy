package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.item.ItemHoe;

public class ItemDracomancyHoe extends ItemHoe
{
	public final ToolMaterial toolMaterial;

    protected String localName;
    
    public ItemDracomancyHoe(DracomancyCustomTool tool)
    {
    	super(tool.material);
    	this.toolMaterial = tool.material;
    	setRegistryName(tool.name);
        setUnlocalizedName(this.getRegistryName().toString());
        setCreativeTab(Dracomancy.DRACO_TAB);
    	localName = tool.localName;
    }
}
