package com.cimdy.casual.effect;

import com.cimdy.casual.Casual;
import com.cimdy.casual.effect.custom.Cure;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EffectRegister
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Casual.MODID);

    public static final RegistryObject<MobEffect> CURE = EFFECTS.register("cure", Cure::new);
}
