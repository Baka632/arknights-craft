package com.baka632.arknightscraft.entity;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Collections;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.init.ModEntities;
import com.baka632.arknightscraft.init.ModItems;

public class AngelinaStaffEntity extends LivingEntity {
    private boolean keyForward;
    private boolean keyBack;
    private boolean keyLeft;
    private boolean keyRight;

    public AngelinaStaffEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);

        if (!world.isClient) {
            ServerPlayNetworking.registerGlobalReceiver(ArknightsCraft.SEND_KEY_STATE_PACK_ID, (server,player,handler,packet,sender)->{
                int[] values = packet.readIntArray();
                boolean left = values[0] == 1;
                boolean right = values[1] == 1;
                boolean forward = values[2] == 1;
                boolean back = values[3] == 1;
    
                this.keyLeft = left;
                this.keyRight = right;
                this.keyForward = forward;
                this.keyBack = back;
            });
        }
    }

    public AngelinaStaffEntity(World world) {
        super(ModEntities.ANGELINA_STAFF, world);
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        // 让实体不坠落
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return true;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof PlayerEntity player) {
            return player.isPlayer();
        }
        else {
            return entity == null;
        }
    }


    @Override
    public void travel(Vec3d movementInput) {
        Entity entity = getFirstPassenger();
        if (entity instanceof PlayerEntity player && this.hasPlayerRider()) {
            // 防止摔死
            player.fallDistance = 0;
            this.fallDistance = 0;

            this.forwardSpeed = 0.5f;
            this.setYaw(player.getYaw());
            this.setPitch(player.getPitch());
            float yaw = this.getYaw();
            this.prevYaw = yaw;
            this.serverYaw = yaw;
            this.headYaw = yaw;
            this.setRotation(yaw, this.getPitch());

            float horizontalSpeed = keyRight ? -0.5f : 0;
            float x = keyLeft ? 0.5f : horizontalSpeed;

            float y = keyForward ? -(player.getPitch() - 10) / 45 : 0;

            float forwardSpeed = keyBack ? -0.5f : 0;
            float z = keyForward ? 3 : forwardSpeed;

            Vec3d movement = new Vec3d(x,y,z);
            this.applyMovementInput(movement, 0.02f);
        } else if (entity == null && !this.hasPassengers() && !this.onGround) {
            super.travel(new Vec3d(0, -0.3f, 0));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!player.isSneaking() && !this.world.isClient && !this.hasPlayerRider() && !this.hasPassengers()) {
            player.startRiding(this);
            return ActionResult.SUCCESS;
        }
        return super.interact(player, hand);
    }

    @Override
    protected boolean isImmobile() {
        return false;
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        if (attacker instanceof PlayerEntity player && player.isSneaking()) {
            this.removeAllPassengers();
            this.kill();
            ItemStack stack = new ItemStack(ModItems.ANGELINA_STAFF);
            this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), stack.setCustomName(this.getCustomName())));
        }
        return true;
    }

    @Override
    public ItemEntity dropStack(ItemStack stack) {
        return new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), stack.setCustomName(this.getCustomName()));
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot var1) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot var1, ItemStack var2) {
        // 不使用该方法        
    }

    @Override
    public Arm getMainArm() {
        return Arm.LEFT;
    }

    @Override
    protected void knockback(LivingEntity target) {
        // 不允许被击退效果影响
    }
    
    @Override
    public boolean isAttackable() {
        return true;
    }
    
    @Override
    public boolean isPushable() {
        return false;
    }
}
