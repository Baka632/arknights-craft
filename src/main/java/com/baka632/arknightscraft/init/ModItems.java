package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.items.eggs.EurekaMedal;
import com.baka632.arknightscraft.items.weapons.SurtrSword;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {
    //Mod icon item
    public static final Item MOD_ICON_ITEM = new Item(new FabricItemSettings().rarity(Rarity.RARE));

    public static final ItemGroup ARKNIGHTSCRAFT_ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(ArknightsCraft.MODID, "arknightscraftitemgroup"), () -> new ItemStack(MOD_ICON_ITEM));

	public static final EurekaMedal EUREKA_MEDAL = new EurekaMedal(new Item.Settings().maxCount(1).rarity(Rarity.EPIC));

	public static final Item ORIROCK = new Item(new FabricItemSettings().group(ARKNIGHTSCRAFT_ITEM_GROUP));

    public static final Item SURTR_SWORD = new SurtrSword();

    public static void init(){
        Registry.register(Registry.ITEM, new Identifier(ArknightsCraft.MODID, "mod_icon"), MOD_ICON_ITEM);

        //Register egg items
		Registry.register(Registry.ITEM, new Identifier(ArknightsCraft.MODID,"eureka_medal"), EUREKA_MEDAL);

		//Register materials item
		Registry.register(Registry.ITEM, new Identifier(ArknightsCraft.MODID,"orirock"), ORIROCK);
		Registry.register(Registry.ITEM, new Identifier(ArknightsCraft.MODID,"orirock_cube"), new BlockItem(ModBlocks.ORIROCK_CUBE, new Item.Settings().group(ARKNIGHTSCRAFT_ITEM_GROUP)));

		//Register weapons item
		Registry.register(Registry.ITEM, new Identifier(ArknightsCraft.MODID,"surtr_sword"), SURTR_SWORD);
    }

    private ModItems(){
    }
}
