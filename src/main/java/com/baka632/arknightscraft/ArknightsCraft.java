package com.baka632.arknightscraft;

import java.util.Calendar;

import com.baka632.arknightscraft.init.ModBlocks;
import com.baka632.arknightscraft.init.ModEffects;
import com.baka632.arknightscraft.init.ModEntities;
import com.baka632.arknightscraft.init.ModFeatures;
import com.baka632.arknightscraft.init.ModItems;
import com.baka632.arknightscraft.init.ModLootTable;
import com.baka632.arknightscraft.items.eggs.EurekaMedal;
import com.baka632.arknightscraft.items.weapons.SurtrSword;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArknightsCraft implements ModInitializer {
	public static final String MODID = "arknightscraft";
	public static final Logger LOGGER = LogManager.getLogger("ArknightsCraft");
	
	//Effects
	//public static final StatusEffect ORIPATHY = new Oripathy();

	@Override
	public void onInitialize() {
		LOGGER.info("[ArknightsCraft]欢迎游玩ArknightsCraft!\t作者:Baka632");
		Calendar todayCalender = Calendar.getInstance();
		if ((todayCalender.get(Calendar.MONTH) == Calendar.MAY && todayCalender.get(Calendar.DATE) == 1) || (todayCalender.get(Calendar.MONTH) == Calendar.APRIL && todayCalender.get(Calendar.DATE) == 30)) {
			LOGGER.info("[ArknightsCraft]Happy birthday,Arknights!");
		}
		else if(todayCalender.get(Calendar.MONTH) == Calendar.MARCH && todayCalender.get(Calendar.DATE) == 14){
			LOGGER.info("[ArknightsCraft]I miss you,AcademyCraft...");
		}

		//Register effects
		//Registry.register(Registry.STATUS_EFFECT, new Identifier(MODID, "oripathy"), ORIPATHY);

		//Register entities

		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		ModFeatures.init();
		ModEffects.init();
		ModLootTable.init();
	}
}
