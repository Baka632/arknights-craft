package com.baka632.arknightscraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
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

	//Materials item
	public static final Item ORIROCK = new Item(new FabricItemSettings().group(ArknightsCraft.ARKNIGHTSCRAFT_ITEM_GROUP));

	//Materials block
	public static final Block ORIROCK_CUBE = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,0).requiresTool().strength(0.6f));

	//Ore generation
	public static final ConfiguredFeature<?,?> ORIROCK_CUBE_ORES = Feature.ORE
		.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			ArknightsCraft.ORIROCK_CUBE.getDefaultState(),
			9))
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			0, 0, 64)))
		.spreadHorizontally()
		.repeat(16);

	@Override
	public void onInitialize() {
		//Register blocks
		Registry.register(Registry.BLOCK, new Identifier(MODID,"orirock_cube"), ORIROCK_CUBE);
		
		//Register materials item
		Registry.register(Registry.ITEM, new Identifier(MODID,"orirock"), ORIROCK);
		Registry.register(Registry.ITEM, new Identifier(MODID,"orirock_cube"), new BlockItem(ORIROCK_CUBE, new Item.Settings().group(ArknightsCraft.ARKNIGHTSCRAFT_ITEM_GROUP)));

		//Register ores
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MODID, "orirock_cube_ores"), ORIROCK_CUBE_ORES);
	}
}
