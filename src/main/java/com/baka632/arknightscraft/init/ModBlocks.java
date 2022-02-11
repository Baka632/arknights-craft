package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block ORIROCK_CUBE = new Block
    (FabricBlockSettings.of(Material.STONE).breakByHand(false).requiresTool().strength(0.6f));

    public static void init(){
        Registry.register(Registry.BLOCK, new Identifier(ArknightsCraft.MODID,"orirock_cube"), ORIROCK_CUBE);
    }

    private ModBlocks(){
    }
}
