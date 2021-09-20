package com.baka632.arknightscraft.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import com.baka632.arknightscraft.ArknightsCraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
	@Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ArknightsCraft.ORIROCK_CUBE_ORES);
	}
}
