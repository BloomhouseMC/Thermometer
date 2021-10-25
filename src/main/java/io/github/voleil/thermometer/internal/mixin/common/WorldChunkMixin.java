package io.github.voleil.thermometer.internal.mixin.common;

import io.github.voleil.thermometer.internal.TemperatureAccesor;
import io.github.voleil.thermometer.internal.Thermometer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin implements Chunk {

    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/ProtoChunk;Ljava/util/function/Consumer;)V", at = @At("RETURN"))
    private void copyFromProtoChunk(ServerWorld serverWorld, ProtoChunk protoChunk, @Nullable Consumer<WorldChunk> consumer, CallbackInfo ci) {
        Thermometer.LOGGER.debug(((TemperatureAccesor) protoChunk).getAverageTemperature());
    }
}
