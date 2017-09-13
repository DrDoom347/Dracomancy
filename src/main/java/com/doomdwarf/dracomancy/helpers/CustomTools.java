package com.doomdwarf.dracomancy.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.doomdwarf.dracomancy.Reference;
import com.doomdwarf.dracomancy.items.DracomancyCustomTool;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class CustomTools
{
	private static File jsonFile = new File(Reference.CONFIG_DIR, "customTools.json");

    /**
     * Reads the custom tools' json file and returns them
     */
    public static List<DracomancyCustomTool> read()
    {
        LogHelper.info("Reading custom tools from json file...");

        if(Reference.CONFIG_DIR.mkdirs())
            LogHelper.info("Created config directory");

        JsonArray jsonData;
        if(jsonFile.exists())
        {
            try
            {
                jsonData = new JsonParser().parse(new JsonReader(new FileReader(jsonFile))).getAsJsonArray();
            }
            catch(FileNotFoundException e)
            {
                return createDefault();
            }
        }
        else
        {
            return createDefault();
        }

        List<DracomancyCustomTool> tools = new ArrayList<DracomancyCustomTool>();

        //Read json data and populate tools array
        for(JsonElement toolElement : jsonData)
        {
            JsonObject toolObj = toolElement.getAsJsonObject();

            //Get localised name
            String name = getJsonString(toolObj.get("LocalisedName"), null);
            if(name == null)
            {
                LogHelper.warn("A material in the custom tools json was found without a name! Material will not be added.");
                continue;
            }

            //Get material name, default to using the local name if it doesn't exist
            String materialName = getJsonString(toolObj.get("MaterialName"), null);
            if(materialName == null)
                materialName = name.toLowerCase().replaceAll("\\s", "");

            //Get the dependant item if exists
            Object dependant = null;
            {
                if((dependant = getJsonString(toolObj.get("DependantOreDic"), null)) == null)
                {
                    String id = getJsonString(toolObj.get("DependantItemId"), "");
                    int meta = getJsonInt(toolObj.get("DependantItemMeta"), OreDictionary.WILDCARD_VALUE);
                    if(!id.equals(""))
                        dependant = new ItemStack(CommonUtils.getRegisteredItem(id), 1, meta);
                    else
                    {
                        LogHelper.warn("No dependant entry for material " + name + " in json file! Material will not be added.");
                        continue;
                    }
                }
            }

            Item.ToolMaterial material = EnumHelper.addToolMaterial(
                    name,
                    getJsonInt(toolObj.get("HarvestLevel"), 1),
                    getJsonInt(toolObj.get("Durability"), 500),
                    getJsonFloat(toolObj.get("Efficiency"), 1f),
                    getJsonFloat(toolObj.get("AttackDamage"), 2f),
                    getJsonInt(toolObj.get("Enchantability"), 0));
            //Get the tool colour
            int colourI = 0;
            //Create the Tool and add it to the list
            DracomancyCustomTool tool = new DracomancyCustomTool(
                    name,
                    material,
                    colourI,
                    dependant);
            tools.add(tool);
        }

        LogHelper.info("Finished reading custom tools");
        return tools;
    }

    private static String getJsonString(JsonElement element, String defaultValue)
    {
        return element == null ? defaultValue : element.getAsString();
    }

    private static int getJsonInt(JsonElement element, int defaultValue)
    {
        return element == null ? defaultValue : element.getAsInt();
    }

    private static float getJsonFloat(JsonElement element, float defaultValue)
    {
        return element == null ? defaultValue : element.getAsFloat();
    }

    /**
     * Creates the default json file with all tools I've added
     */
    private static List<DracomancyCustomTool> createDefault()
    {
        LogHelper.info("File not found, creating default json file...");

        List<DracomancyCustomTool> tools = new ArrayList<DracomancyCustomTool>();

        //Populate tools array
        for(MaterialNames.EnumMaterials material : MaterialNames.EnumMaterials.values())
            tools.add(new DracomancyCustomTool(material));

        //Create file from tools array
        try
        {
            JsonWriter writer = new JsonWriter(new FileWriter(jsonFile));
            writer.setIndent("    ");
            writer.beginArray();
            for(DracomancyCustomTool tool : tools)
            {
                writer.beginObject();
                writer.name("MaterialName").value(tool.name);
                writer.name("LocalisedName").value(tool.localName);
                writer.name("HarvestLevel").value(tool.material.getHarvestLevel());
                writer.name("Durability").value(tool.material.getMaxUses());
                writer.name("Efficiency").value(tool.material.getEfficiencyOnProperMaterial());
                writer.name("AttackDamage").value(tool.material.getDamageVsEntity());
                writer.name("Enchantability").value(tool.material.getEnchantability());
                if(tool.toolColour >= 0)
                    writer.name("TextureColour").value(tool.toolColour);
                if(tool.dependantOreDic != null)
                    writer.name("DependantOreDic").value(tool.dependantOreDic);
                else if(tool.dependantStack != null)
                {
                    String itemId = CommonUtils.getRegisteredId(tool.dependantStack.getItem());
                    if(itemId != null)
                    {
                        writer.name("DependantItemId").value(itemId);
                        writer.name("DependantItemMeta").value(tool.dependantStack.getMetadata());
                    }
                    else
                        LogHelper.warn("Id not found for stack " + tool.dependantStack.toString() + "! Dependant item not being saved for material " + tool.name);
                }
                writer.endObject();
            }
            writer.endArray();
            writer.close();
        }
        catch(IOException e)
        {
            LogHelper.error("Error creating default custom tool json file!");
            e.printStackTrace();
        }

        LogHelper.info("Finished creating default json file");
        return tools;
    }
}
