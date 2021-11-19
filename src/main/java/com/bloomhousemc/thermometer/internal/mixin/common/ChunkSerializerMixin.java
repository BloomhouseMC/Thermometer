package com.bloomhousemc.thermometer.internal.mixin.common;

import com.bloomhousemc.thermometer.internal.util.TemperatureAccessor;
import com.bloomhousemc.thermometer.internal.Thermometer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkSerializer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.poi.PointOfInterestStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSerializer.class)
public abstract class ChunkSerializerMixin {
    @Inject(method = "deserialize", at = @At("RETURN"))
    private static void deserialize(ServerWorld world,
                                    StructureManager structureManager,
                                    PointOfInterestStorage poiStorage,
                                    ChunkPos pos,
                                    NbtCompound tag,
                                    CallbackInfoReturnable<ProtoChunk> cir) {

        System.out.println("Sup");
        NbtCompound levelData = tag.getCompound("Level");
        levelData.getInt("temperature");
        Thermometer.LOGGER.debug("Retrieving chunk from file with " + levelData.getInt("temperature") + " degrees celsius average temperature.");
    }

    @Inject(method = "serialize", at = @At("RETURN"))
    private static void serialize(ServerWorld world, Chunk chunk, CallbackInfoReturnable<NbtCompound> cir) {
        NbtCompound ret = cir.getReturnValue();
        NbtCompound levelData = ret.getCompound("Level");
        levelData.putInt("temperature", ((TemperatureAccessor) chunk).getAverageTemperature());
    }
}