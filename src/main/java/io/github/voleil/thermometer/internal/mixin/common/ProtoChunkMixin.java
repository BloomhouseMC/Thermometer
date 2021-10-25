package io.github.voleil.thermometer.internal.mixin.common;

import io.github.voleil.thermometer.internal.TemperatureAccesor;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static io.github.voleil.thermometer.internal.Thermometer.*;
@Mixin(ProtoChunk.class)
public abstract class ProtoChunkMixin implements Chunk, TemperatureAccesor {
    @Shadow
    public abstract ChunkPos getPos();

    @Unique
    public byte averageTemperature;

    @Unique
    public byte getAverageTemperature() {
        return averageTemperature;
    }
    @Inject(method = "<init>(Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/world/chunk/UpgradeData;[Lnet/minecraft/world/chunk/ChunkSection;Lnet/minecraft/world/ChunkTickScheduler;Lnet/minecraft/world/ChunkTickScheduler;Lnet/minecraft/world/HeightLimitView;)V", at = @At("RETURN"))
    private void initComponents(CallbackInfo ci) {
        byte z = (byte) this.getPos().z;
        averageTemperature = (byte) (C_L + periodic(z / P_L + T_C));
    }
}
