package com.bloomhousemc.thermometer.internal.mixin.common;

import com.bloomhousemc.thermometer.internal.util.TemperatureAccessor;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ProtoChunk.class)
public abstract class ProtoChunkMixin implements Chunk, TemperatureAccessor {
    @Unique
    private int averageTemperature;

    @Unique
    public int getAverageTemperature() {
        return averageTemperature;
    }
}
