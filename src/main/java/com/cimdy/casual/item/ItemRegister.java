package com.cimdy.casual.item;

import com.cimdy.casual.Casual;
import com.cimdy.casual.effect.EffectRegister;
import com.cimdy.casual.item.custom.TherapeuticFruit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public final class ItemRegister
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Casual.MODID);


    public static final RegistryObject<Item> THERAPEUTIC_FRUIT = ITEMS.register("therapeutic_fruit",
            () -> new TherapeuticFruit(new Item.Properties().food(Foods.TherapeuticFruit)));

    private static class Foods {
        public static FoodProperties TherapeuticFruit = (
                new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).alwaysEat()
                .effect(new MobEffectInstance(EffectRegister.CURE.get(), 302, 0), 1.0F)
                .build();
    }
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
