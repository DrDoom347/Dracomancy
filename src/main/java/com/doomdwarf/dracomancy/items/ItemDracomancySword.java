package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.item.ItemSword;

public class ItemDracomancySword extends ItemSword
{
	public final ToolMaterial toolMaterial;
    protected String localName;
    
    public ItemDracomancySword(DracomancyCustomTool tool)
    {
    	super(tool.material);
    	this.toolMaterial = tool.material;
    	setRegistryName(tool.name);
        setUnlocalizedName(this.getRegistryName().toString());
        setCreativeTab(Dracomancy.DRACO_TAB);
    	localName = tool.localName;
    }
}
