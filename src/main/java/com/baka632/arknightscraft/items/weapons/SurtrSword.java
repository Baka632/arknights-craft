package com.baka632.arknightscraft.items.weapons;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.init.ModItems;

import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
        super(ToolMaterials.NETHERITE, 5, 1.0F, (new Item.Settings()).group(ModItems.ARKNIGHTSCRAFT_ITEM_GROUP));
    }

    @Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        ItemStack itemStack = user.getStackInHand(hand);
        if (world instanceof ServerWorld) {
            BlockPos blockPos = user.getBlockPos();
            if (blockPos != null) {
                for (int i = 1; i < 8; i++) {
                    EvokerFangsEntity evokerFangsEntityX = new EvokerFangsEntity(world, user.getX() + i, user.getY(), user.getZ(), 0, 1, user);
                    world.spawnEntity(evokerFangsEntityX);
                }
                if (!user.getAbilities().creativeMode) {
                    itemStack.damage(1, null, (ServerPlayerEntity)user);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                user.swingHand(hand, true);
                user.getItemCooldownManager().set(this, 17);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
	}
}
