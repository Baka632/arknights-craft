package com.baka632.arknightscraft.items.weapons;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SurtrSword extends SwordItem {
    public SurtrSword() {
        super(ToolMaterials.NETHERITE, 10, -10.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
    }

    @Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        ItemStack itemStack = user.getStackInHand(hand);
        if (world instanceof ServerWorld) {
            BlockPos blockPos = user.getBlockPos();
            if (blockPos != null) {
                EvokerFangsEntity evokerFangsEntity = new EvokerFangsEntity(world, user.getX(), user.getY(), user.getZ(), 5f, 1, user);
                world.spawnEntity(evokerFangsEntity);
                if (!user.abilities.creativeMode) {
                    itemStack.damage(1, null, (ServerPlayerEntity)user);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                user.swingHand(hand, true);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
	}
}
