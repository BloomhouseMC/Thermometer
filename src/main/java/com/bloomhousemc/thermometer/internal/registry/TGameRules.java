package com.bloomhousemc.thermometer.internal.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.world.GameRules;

public class TGameRules {
    //TODO: Check if a rule has already been registered by another mod before registering
    public static final GameRules.Key<GameRules.IntRule> C_L = GameRuleRegistry.register("cL", GameRules.Category.MISC, GameRuleFactory.createIntRule(25, -10, 25));
    public static final GameRules.Key<GameRules.IntRule> P_L = GameRuleRegistry.register("pL", GameRules.Category.MISC, GameRuleFactory.createIntRule(40, 40, 40));
    public static final GameRules.Key<GameRules.IntRule> T_C = GameRuleRegistry.register("tC", GameRules.Category.MISC, GameRuleFactory.createIntRule(5, -10, 10));
    public static final GameRules.Key<DoubleRule> V = GameRuleRegistry.register("v", GameRules.Category.MISC, GameRuleFactory.createDoubleRule(1, 0, 1));
}
