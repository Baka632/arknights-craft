package com.baka632.arknightscraft.items.weapons;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.entity.MoltenFlameEntity;
import com.baka632.arknightscraft.init.ModItems;

import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
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
                startAttack(world,user);
                if (!user.getAbilities().creativeMode) {
                    itemStack.damage(1, null, (ServerPlayerEntity)user);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                user.swingHand(hand, true);
                user.getItemCooldownManager().set(this, 20);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
	}

    private void startAttack(World world,PlayerEntity player) {
        float yaw = player.getYaw();
        Vec3d pos = player.getPos().add(0,player.getEyeHeight(player.getPose()),0);
        Vec3d yawPos = pos.multiply(Math.cos(yaw));

        double d = Math.min(yawPos.getY(), player.getY());
        double e = Math.max(yawPos.getY(), player.getY()) + 1.0;
        float f = (float)MathHelper.atan2(yawPos.getZ() - player.getZ(), yawPos.getX() - player.getX());
        for (int i = 1; i < 8; ++i) {
            double g = 1.25 * (i + 1);
            int j = 1 * i;
            conjure(player.getX() + MathHelper.cos(f) * g, player.getZ() + MathHelper.sin(f) * g, d, e, f, j,world,player);
        }
    }

    private void conjure(double x, double z, double maxY, double y, float yaw, int warmup,World world,PlayerEntity player) {
        BlockPos blockPos = new BlockPos(x, y, z);
        boolean bl = false;
        double d = 0.0;
        do {
            VoxelShape voxelShape;
            BlockPos blockPos2;
            if (!(world.getBlockState(blockPos2 = blockPos.down())).isSideSolidFullSquare(world, blockPos2, Direction.UP))
                continue;
            if (!world.isAir(blockPos) && !(voxelShape = (world.getBlockState(blockPos)).getCollisionShape(world, blockPos)).isEmpty()) {
                d = voxelShape.getMax(Direction.Axis.Y);
            }
            bl = true;
            break;
        } while ((blockPos = blockPos.down()).getY() >= MathHelper.floor(maxY) - 1);
        if (bl) {
            world.spawnEntity(new MoltenFlameEntity(world, x, blockPos.getY() + d, z, yaw, warmup, player));
        }
    }
}
