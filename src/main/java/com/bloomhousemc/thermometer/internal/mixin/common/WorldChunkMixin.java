package com.bloomhousemc.thermometer.internal.mixin.common;

import com.bloomhousemc.thermometer.internal.Thermometer;
import com.bloomhousemc.thermometer.internal.mixin.util.TemperatureAccessor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static com.bloomhousemc.thermometer.internal.Thermometer.periodic;
import static com.bloomhousemc.thermometer.internal.registry.TGameRules.*;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin implements Chunk, TemperatureAccessor {

    @Unique
    private int averageTemperature;
    
    @Unique
    public int getAverageTemperature() {
        return averageTemperature;
    }

    @Shadow
    public abstract ChunkPos getPos();

    /**
     * Assigns a value to {@code averageTemperature}.
     * @param serverWorld retrieves temperature modifying variables from the server game rules
     * @param protoChunk unused variable
     * @param consumer unused variable
     * @param ci unused variable
     */
    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/ProtoChunk;Ljava/util/function/Consumer;)V", at = @At("RETURN"))
    private void copyFromProtoChunk(ServerWorld serverWorld,
                                    ProtoChunk protoChunk,
                                    @Nullable Consumer<WorldChunk> consumer,
                                    CallbackInfo ci) {
        var gameRules = serverWorld.getGameRules();
        float z = getPos().z;
        averageTemperature = (int) (gameRules.getInt(C_L) + periodic(z / gameRules.getInt(P_L) + gameRules.getInt(T_C), gameRules));
        Thermometer.LOGGER.debug("Generating chunk with " + averageTemperature + " degrees celsius average temperature.");
    }
}
