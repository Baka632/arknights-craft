package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.entity.AngelinaStaffEntity;
import com.baka632.arknightscraft.entity.MoltenFlameEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<MoltenFlameEntity> MOLTEN_FLAME = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier(ArknightsCraft.MODID, "molten_flame"),
        FabricEntityTypeBuilder.<MoltenFlameEntity>create(SpawnGroup.MISC, MoltenFlameEntity::new)
                .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                .trackRangeBlocks(4).trackedUpdateRate(10)
                .build());
    
    public static final EntityType<AngelinaStaffEntity> ANGELINA_STAFF = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier(ArknightsCraft.MODID, "angelina_staff_entity"),
        FabricEntityTypeBuilder.<AngelinaStaffEntity>create(SpawnGroup.MISC, AngelinaStaffEntity::new)
                .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                .trackRangeBlocks(4).trackedUpdateRate(10)
                .build());

    public static void init(){
        FabricDefaultAttributeRegistry.register(ANGELINA_STAFF, MobEntity.createMobAttributes());
    }

    private ModEntities() {
    }
}
