package com.bloomhousemc.thermometer.internal;

import com.bloomhousemc.thermometer.internal.registry.TGameRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.bloomhousemc.thermometer.internal.registry.TGameRules.V;


public class Thermometer implements ModInitializer {

    public static final String NAMESPACE = "thermometer";
    public static final Logger LOGGER = LogManager.getLogger(NAMESPACE);

    public static float sinFun(float t) {
        return MathHelper.sin(MathHelper.PI * t);
    }

    public static float triangleFun(float t) {
        return MathHelper.abs(2 * t + 1 - 4 * MathHelper.floor(t / 2 + 3 / 4f)) - 1;
    }

    public static float periodic(float t, GameRules gameRules) {
        if (gameRules.get(V).get() == 0)
            return sinFun(t);
        else if (gameRules.get(V).get() == 1)
            return triangleFun(t);
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public void onInitialize() {
        new TGameRules();
    }
}
