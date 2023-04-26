package com.cimdy.casual.world;

import com.cimdy.casual.Casual;
import com.cimdy.casual.block.BlockRegister;
import com.cimdy.casual.block.custom.TherapeuticFruitBush;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;

public class FeatureRegister {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(
            Registries.FOLIAGE_PLACER_TYPE, Casual.MODID
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> THERAOEUTIC_FRUIT_BUSH = createKey("therapeutic_fruit_bush");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, THERAOEUTIC_FRUIT_BUSH, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(32, 2, 1,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegister.THERAPEUTIC_FRUIT_BUSH.get()
                                        .defaultBlockState()
                                        .setValue(TherapeuticFruitBush.AGE, 1)
                                )))));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Casual.MODID, pName));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> pContext, ResourceKey<ConfiguredFeature<?, ?>> pKey, F pFeature, FC pConfig
    ) {
        pContext.register(pKey, new ConfiguredFeature<>(pFeature, pConfig));
    }
}
