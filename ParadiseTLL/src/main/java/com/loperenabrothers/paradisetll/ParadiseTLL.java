package com.loperenabrothers.paradisetll;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(ParadiseTLL.MODID)
public class ParadiseTLL {
    public static final String MODID = "paradisetll";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> BACKROOM_GROUND = BLOCKS.register("backroom_ground",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL)));

    public static final RegistryObject<Block> BACKROOM_WALL = BLOCKS.register("backroom_wall",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)));

    public static final RegistryObject<Block> BACKROOM_TOP = BLOCKS.register("backroom_top",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)));

    public static final RegistryObject<Item> BACKROOM_GROUND_ITEM = ITEMS.register("backroom_ground",
            () -> new BlockItem(BACKROOM_GROUND.get(), new Item.Properties()));

    public static final RegistryObject<Item> BACKROOM_WALL_ITEM = ITEMS.register("backroom_wall",
            () -> new BlockItem(BACKROOM_WALL.get(), new Item.Properties()));

    public static final RegistryObject<Item> BACKROOM_TOP_ITEM = ITEMS.register("backroom_top",
            () -> new BlockItem(BACKROOM_TOP.get(), new Item.Properties()));

    public ParadiseTLL() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(BACKROOM_GROUND_ITEM);
            event.accept(BACKROOM_WALL_ITEM);
            event.accept(BACKROOM_TOP_ITEM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
