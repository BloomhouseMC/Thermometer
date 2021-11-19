package com.bloomhousemc.thermometer.internal;

import com.bloomhousemc.thermometer.internal.registry.TGameRules;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.bloomhousemc.thermometer.internal.registry.TGameRules.V;


public class Thermometer implements ModInitializer {

    public static final String MODID = "thermometer";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    /**
     * @param t floating-point value to evaluate
     * @return sin&#40pi * {@code t})
     */
    public static float sinFun(float t) {
        return MathHelper.sin(MathHelper.PI * t);
    }

    /**
     * @param t floating-point value to evaluate
     * @return |2{@code t} + 1 - 4 floor(t / 2 + 3 / 4)| - 1
     */
    public static float triangleFun(float t) {
        return MathHelper.abs(2 * t + 1 - 4 * MathHelper.floor(t / 2 + 3 / 4f)) - 1;
    }

    /**
     * Periodic function
     *
     * @param t         floating-point value to evaluate
     * @param gameRules server game rules
     * @return value of {@code sinFun(t)} if {@code V} is 1, else if {@code V} is 0 returns value of {@code triangleFun(t)}
     * @see <a href=https://en.wikipedia.org/wiki/Periodic_function>"Wikipedia page"</a>
     */
    public static float periodic(float t, GameRules gameRules) {
        if (gameRules.get(V).get() == 0)
            return sinFun(t);
        else if (gameRules.get(V).get() == 1)
            return triangleFun(t);
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public void onInitialize() {
        TGameRules.init();
    }
}
