package com.loperenabrothers.paradisetll;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ParadiseTLL.MODID);

    // Define your custom blocks here
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", 
        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
}
