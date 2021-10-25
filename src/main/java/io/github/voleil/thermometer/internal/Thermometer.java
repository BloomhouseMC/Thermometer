package io.github.voleil.thermometer.internal;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Thermometer implements ModInitializer {

    public static final String NAMESPACE = "thermometer";
    public static final Logger LOGGER = LogManager.getLogger(NAMESPACE);

    //Temperature variables
    public static final byte C_L = 25;
    public static final byte P_L = 40;
    public static final byte T_C = 5;
    public static final byte V = 1;
    @Override
    public void onInitialize() {

    }

    public static float sinFun(float t) {
        return MathHelper.sin(MathHelper.PI * t);
    }

    public static float triangleFun(float t) {
        return MathHelper.abs(2 * t + 1 - 4 * MathHelper.floor(t / 2 + 3 / 4)) - 1;
    }

    public static float periodic(float t) {
        if (V == 0)
            return sinFun(t);
        else if (V == 1)
            return triangleFun(t);
        else throw new IndexOutOfBoundsException();
    }
}
