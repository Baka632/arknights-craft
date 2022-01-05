package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;

public class ModLootTable {
    //Loot table we want to modify
	private static final Identifier BURIED_TREASURE_LOOT_TABLE_ID = LootTables.BURIED_TREASURE_CHEST;

    private ModLootTable(){

    }

    public static void init(){
        //Listen to loot table events
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
			if (BURIED_TREASURE_LOOT_TABLE_ID.equals(id)) {
                LootTable customTable = lootManager.getTable(new Identifier("arknightscraft:chests/buried_treasure_addition"));
				if (customTable != null) {
                    table.copyFrom(customTable);
                }
			}
		});
    }

    /**
	 * Makes loot entry from item provided
	 *
	 * @param item Item to include into LootEntry
	 * @return LootEntry for item provided
	 */
	private static LootPoolEntry makeEntry(ItemConvertible item) {
		return makeEntry(item, 5);
	}

	/**
	 * Makes loot entry from item provided with weight provided
	 *
	 * @param item   Item to include into LootEntry
	 * @param weight Weight of that item
	 * @return LootEntry for item and weight provided
	 */
	private static LootPoolEntry makeEntry(ItemConvertible item, int weight) {
		return ItemEntry.builder(item).weight(weight)
				.apply(SetCountLootFunction.builder(UniformLootTableRange.between(0.0f, 1.0f))).build();
	}
}
