package com.cimdy.casual.world;

import com.cimdy.casual.Casual;
import com.cimdy.casual.block.BlockRegister;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class PlacementRegister {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(
            Registries.PLACED_FEATURE, Casual.MODID
    );

    public static final ResourceKey<PlacedFeature> THERAOEUTIC_FRUIT_BUSH_CHECKED = createKey("therapeutic_fruit_bush_checked");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> THERAOEUTIC_FRUIT_BUSH = getter.getOrThrow(FeatureRegister.THERAOEUTIC_FRUIT_BUSH);

        register(context, THERAOEUTIC_FRUIT_BUSH_CHECKED, THERAOEUTIC_FRUIT_BUSH, List.of(
                PlacementUtils.HEIGHTMAP,
                PlacementUtils.filteredByBlockSurvival(BlockRegister.THERAPEUTIC_FRUIT_BUSH.get()),
                RarityFilter.onAverageOnceEvery(80)
        ));
    }

    public static ResourceKey<PlacedFeature> createKey(String pName) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Casual.MODID, pName));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(feature, modifiers));
    }
}
