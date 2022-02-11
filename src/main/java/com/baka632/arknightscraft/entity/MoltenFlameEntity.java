//Don't worry,be happy ;)

// package com.baka632.arknightscraft.entity;

// import java.util.UUID;

// import org.jetbrains.annotations.Nullable;

// import net.minecraft.entity.Entity;
// import net.minecraft.entity.EntityType;
// import net.minecraft.entity.LivingEntity;
// import net.minecraft.nbt.NbtCompound;
// import net.minecraft.network.Packet;
// import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
// import net.minecraft.server.world.ServerWorld;
// import net.minecraft.world.World;

// public class MoltenFlameEntity extends Entity {
// 	private int warmup;
// 	private boolean startedAttack;
// 	private int ticksLeft;
// 	private boolean playingAnimation;
// 	private LivingEntity owner;
// 	private UUID ownerUuid;
    
//     public MoltenFlameEntity(EntityType<? extends Entity> type, World world) {
//         super(type, world);
//         this.ticksLeft = 22;
//     }

//     public MoltenFlameEntity(World world, double x, double y, double z, float yaw, int warmup, LivingEntity owner) {
// 		this(EntityType.EVOKER_FANGS, world);
// 		this.warmup = warmup;
// 		this.setOwner(owner);
// 		this.yaw = yaw * 57.2F;
// 		this.setPosition(x, y, z);
// 	}

// 	public void setOwner(@Nullable LivingEntity owner) {
// 		this.owner = owner;
// 		this.ownerUuid = owner == null ? null : owner.getUuid();
// 	}
    
//     @Nullable
// 	public LivingEntity getOwner() {
// 		if (this.owner == null && this.ownerUuid != null && this.world instanceof ServerWorld serverWorld) {
// 			Entity entity = serverWorld.getEntity(this.ownerUuid);
// 			if (entity instanceof LivingEntity livingEntity) {
// 				this.owner = livingEntity;
// 			}
// 		}

// 		return this.owner;
// 	}

//     @Override
//     public void tick(){
        
//     }

//     @Override
//     protected void initDataTracker() {
//       // No need to override
//     }

//     @Override
//     protected void readCustomDataFromNbt(NbtCompound nbt) {
// 		this.warmup = nbt.getInt("Warmup");
// 		if (nbt.containsUuid("Owner")) {
// 			this.ownerUuid = nbt.getUuid("Owner");
// 		}
//     }

//     @Override
//     protected void writeCustomDataToNbt(NbtCompound nbt) {
// 		nbt.putInt("Warmup", this.warmup);
// 		if (this.ownerUuid != null) {
// 			nbt.putUuid("Owner", this.ownerUuid);
// 		}
//     }

//     @Override
//     public Packet<?> createSpawnPacket() {
//         return new EntitySpawnS2CPacket(this);
//     }
    
// }
