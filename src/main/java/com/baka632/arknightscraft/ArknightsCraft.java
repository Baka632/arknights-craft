package com.baka632.arknightscraft;

import java.util.Calendar;

import com.baka632.arknightscraft.effect.Oripathy;
import com.baka632.arknightscraft.items.eggs.EurekaMedal;
import com.baka632.arknightscraft.items.weapons.SurtrSword;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootTableRanges;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ArknightsCraft implements ModInitializer {
	public static final String MODID = "arknightscraft";
	public static final ItemGroup ARKNIGHTSCRAFT_ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "arknightscraftitemgroup"), () -> new ItemStack(ArknightsCraft.ORIROCK));

	//Loot table we want to modify
	private static final Identifier BURIED_TREASURE_LOOT_TABLE_ID = LootTables.BURIED_TREASURE_CHEST;
	
	//Egg item
	public static final EurekaMedal EUREKA_MEDAL = new EurekaMedal(new Item.Settings().maxCount(1).rarity(Rarity.EPIC));

	//Materials item
	public static final Item ORIROCK = new Item(new FabricItemSettings().group(ArknightsCraft.ARKNIGHTSCRAFT_ITEM_GROUP));

	//Materials block
	public static final Block ORIROCK_CUBE = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,0).requiresTool().strength(0.6f));

	//Effects
	public static final StatusEffect ORIPATHY = new Oripathy();

	//Weapons
	public static final Item SURTR_SWORD = new SurtrSword();

	//Ore generation
	public static final ConfiguredFeature<?,?> ORIROCK_CUBE_ORES = Feature.ORE
		.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			ArknightsCraft.ORIROCK_CUBE.getDefaultState(),
			9))
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			40, 0, 64)))
		.spreadHorizontally()
		.repeat(16);

	@Override
	public void onInitialize() {
		System.out.println("[ArknightsCraft]欢迎游玩ArknightsCraft!\t作者:Baka632");
		Calendar todayCalender = Calendar.getInstance();
		if ((todayCalender.get(Calendar.MONTH) == Calendar.MAY && todayCalender.get(Calendar.DATE) == 1) || (todayCalender.get(Calendar.MONTH) == Calendar.APRIL && todayCalender.get(Calendar.DATE) == 30)) {
			System.out.println("[ArknightsCraft]Happy birthday,Arknights!");
		}
		else if(todayCalender.get(Calendar.MONTH) == Calendar.MARCH && todayCalender.get(Calendar.DATE) == 14){
			System.out.println("[ArknightsCraft]I miss you,AcademyCraft...");
		}

		//Register egg items
		Registry.register(Registry.ITEM, new Identifier(MODID,"eureka_medal"), EUREKA_MEDAL);

		//Register blocks
		Registry.register(Registry.BLOCK, new Identifier(MODID,"orirock_cube"), ORIROCK_CUBE);
		
		//Register materials item
		Registry.register(Registry.ITEM, new Identifier(MODID,"orirock"), ORIROCK);
		Registry.register(Registry.ITEM, new Identifier(MODID,"orirock_cube"), new BlockItem(ORIROCK_CUBE, new Item.Settings().group(ArknightsCraft.ARKNIGHTSCRAFT_ITEM_GROUP)));

		//Register weapons item
		Registry.register(Registry.ITEM, new Identifier(MODID,"surtr_sword"), SURTR_SWORD);

		//Register ores
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MODID, "orirock_cube_ores"), ORIROCK_CUBE_ORES);

		//Register effects
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MODID, "oripathy"), ORIPATHY);

		//Listen to loot table events
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
			if (BURIED_TREASURE_LOOT_TABLE_ID.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                .rolls(UniformLootTableRange.between(0.0f, 0.3f))
                .with(ItemEntry.builder(EUREKA_MEDAL));
        		table.pool(poolBuilder);
			}
		});
	}
}
