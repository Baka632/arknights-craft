package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.GenerationStep;

public class ModFeatures {
    private static final String ORIROCK_CUBE_ORE_STRING = "orirock_cube_ore";

    private static final ConfiguredFeature<?, ?> ORIROCK_CUBE_ORE_CONFIGURED_FEATURE = Feature.ORE
      .configure(new OreFeatureConfig(
          OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
          ModBlocks.ORIROCK_CUBE.getDefaultState(),
          9)); // vein size
 
    public static final PlacedFeature ORIROCK_CUBE_ORE_PLACED_FEATURE = ORIROCK_CUBE_ORE_CONFIGURED_FEATURE.withPlacement(
      CountPlacementModifier.of(20), // number of veins per chunk
      SquarePlacementModifier.of(), // spreading horizontally
      HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

    public static void init(){
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
            new Identifier(ArknightsCraft.MODID, ORIROCK_CUBE_ORE_STRING), ORIROCK_CUBE_ORE_CONFIGURED_FEATURE);
            Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ArknightsCraft.MODID, ORIROCK_CUBE_ORE_STRING),
            ORIROCK_CUBE_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
            RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier(ArknightsCraft.MODID, ORIROCK_CUBE_ORE_STRING)));
    }

    private ModFeatures(){

    }
}
