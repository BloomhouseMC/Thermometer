package com.bloomhousemc.thermometer.internal.mixin.common;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkHolder.class)
public abstract class ChunkHolderMixin implements Chunk {
    @Inject(
            method = "flushUpdates",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/server/world/ChunkHolder;pendingBlockUpdates:Z",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void flushUpdates(WorldChunk chunk, CallbackInfo ci) {
        for (ServerPlayerEntity p : PlayerLookup.tracking((ServerWorld) chunk.getWorld(), chunk.getPos())) {
            //Runs when played gets chunks
        }
    }
}
