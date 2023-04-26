package com.cimdy.casual;

import com.cimdy.casual.block.BlockRegister;
import com.cimdy.casual.effect.EffectRegister;
import com.cimdy.casual.event.HealthEvent;
import com.cimdy.casual.world.FeatureRegister;
import com.cimdy.casual.world.PlacementRegister;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;



import static com.cimdy.casual.item.ItemRegister.*;

@Mod(Casual.MODID)
public class Casual
{
    public static final String MODID = "casual";

    public Casual()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onBuildContents);

        FeatureRegister.FOLIAGE_PLACER_TYPES.register(modEventBus);
        PlacementRegister.PLACED_FEATURES.register(modEventBus);
        BlockRegister.register(modEventBus);

        EffectRegister.EFFECTS.register(modEventBus);
        register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new HealthEvent());

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    void onBuildContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(THERAPEUTIC_FRUIT);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(BlockRegister.THERAPEUTIC_FRUIT_BUSH.get(), RenderType.cutout());
        }
    }
}
