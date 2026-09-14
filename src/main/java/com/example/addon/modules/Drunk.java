package com.example.addon.modules;

import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.mixin.StatusEffectInstanceAccessor;
import meteordevelopment.meteorclient.settings.EnumSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;

public class Drunk extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    public Drunk() {
        super(AddonTemplate.CATEGORY, "Drunk", "git drunk");
    }
	
	@Override
    public void onDeactivate() {
        atumama();
    }


    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player == null) return;
        if (mc.player.hasStatusEffect(StatusEffects.NAUSEA)) {
            StatusEffectInstance instance = mc.player.getStatusEffect(StatusEffects.NAUSEA);
            if (instance != null && instance.getDuration() < 420) ((StatusEffectInstanceAccessor) instance).meteor$setDuration(420);
        } else {
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 420, 0));
        }
    }

    private void atumama() {
        if (mc.player == null) return;
        if (mc.player.hasStatusEffect(StatusEffects.NAUSEA)) {
            mc.player.removeStatusEffect(StatusEffects.NAUSEA);
        }
    }
}
