package com.bloomhousemc.thermometer.internal.mixin.common;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EmptyChunk.class)
public abstract class EmptyChunkMixin implements Chunk {

    @Unique
    private int averageTemperature;

    @Unique
    public int getAverageTemperature() { return averageTemperature; }
}
