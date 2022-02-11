package com.baka632.arknightscraft.init;

import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
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
}
