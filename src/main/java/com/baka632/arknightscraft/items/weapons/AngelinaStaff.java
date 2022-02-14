package com.baka632.arknightscraft.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AngelinaStaff extends Item {
    private final TargetPredicate convertibleLivingEntityPredicate;
    
    public AngelinaStaff(Settings settings) {
        super(settings);
        this.convertibleLivingEntityPredicate = 
        TargetPredicate.createNonAttackable()
        .setBaseMaxDistance(16.0)
        .setPredicate(livingEntity -> {
            return livingEntity instanceof HostileEntity || livingEntity instanceof Angerable;
        });
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            List<LivingEntity> list = 
                world.getTargets(LivingEntity.class, this.convertibleLivingEntityPredicate, user, user.getBoundingBox().expand(16.0, 4.0, 16.0));
            if (list.isEmpty()) {
                return TypedActionResult.pass(itemStack);
            }
            else{
                user.getItemCooldownManager().set(this, 300);
            }
            
            for (Entity entity : list) {
                if (entity instanceof LivingEntity livingEntity) {
                    if (isNotAttackTarget(livingEntity, user)) {
                        continue;
                    }
                    
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 140, 15, false, false, true),user);
                }
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    private boolean isNotAttackTarget(LivingEntity entity,PlayerEntity user){
        boolean isNotTarget = false;
        if (entity instanceof PiglinEntity piglin && !piglin.isAngryAt(user) 
            || entity instanceof TameableEntity tameable && tameable.isOwner(user)
            || entity instanceof Angerable angerable && !angerable.hasAngerTime())
        {
            isNotTarget = true;
        }
        return isNotTarget;
    }
}
