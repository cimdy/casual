package com.cimdy.casual.event;

import com.cimdy.casual.Casual;
import com.cimdy.casual.effect.EffectRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Casual.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HealthEvent {

    @SubscribeEvent
    public void logIn(PlayerEvent.PlayerLoggedInEvent event) {
        GameRules rules = event.getEntity().getLevel().getGameRules();
        if(event.getEntity().getLevel().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)) {
            rules.getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getEntity().getLevel().getServer());
        }
    }

    @SubscribeEvent
    public void tickEvent(TickEvent.PlayerTickEvent event) {
        if (event.player.isSleepingLongEnough()) {
            event.player.heal(event.player.getMaxHealth() / 5);
            event.player.addEffect(new MobEffectInstance(EffectRegister.CURE.get(), 602, 0), event.player);
        }
    }


}
