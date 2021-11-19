package com.bloomhousemc.thermometer.internal.mixin.common;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ReadOnlyChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ReadOnlyChunk.class)
public abstract class ReadOnlyChunkMixin implements Chunk {

    @Unique
    private int averageTemperature;

    @Unique
    public int getAverageTemperature() { return averageTemperature; }
}
