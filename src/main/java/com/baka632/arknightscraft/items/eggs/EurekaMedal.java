package com.baka632.arknightscraft.items.eggs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class EurekaMedal extends Item {

    public EurekaMedal(Settings settings) {
        super(settings);
    }

    @Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2, true, false, true));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1, true, false, true));
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.getItemCooldownManager().set(this, 400);
        return TypedActionResult.pass(user.getStackInHand(hand));
	}
}