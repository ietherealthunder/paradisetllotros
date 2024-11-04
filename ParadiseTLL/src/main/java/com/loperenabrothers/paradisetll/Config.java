package com.loperenabrothers.paradisetll;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = ParadiseTLL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_LOGGING = BUILDER
            .comment("Enable logging for mod events")
            .define("enableLogging", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean enableLogging;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        enableLogging = ENABLE_LOGGING.get();
    }
}
