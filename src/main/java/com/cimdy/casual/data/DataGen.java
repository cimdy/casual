package com.cimdy.casual.data;

import com.cimdy.casual.Casual;
import com.cimdy.casual.world.WorldGenProviderRegister;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;


@Mod.EventBusSubscriber(modid = Casual.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        gen.addProvider(event.includeServer(), new WorldGenProviderRegister(
                gen.getPackOutput(),
                event.getLookupProvider(),
                Set.of(Casual.MODID)
        ));
    }
}

