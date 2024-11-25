package better_totems.viper.protogen.VerifyParticles;

import better_totems.viper.protogen.BetterTotems;
import better_totems.viper.protogen.Status.TemplateStatus;
import net.minecraft.entity.EntityAttachments;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModdedPotionEffects {
    public static final RegistryEntry<StatusEffect> NoFall = registerStatusEffect("nofall", new TemplateStatus(StatusEffectCategory.BENEFICIAL,0x14fce5));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect StatusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(BetterTotems.MOD_ID, name), StatusEffect);
    }


    public static void registerEffects() {}
}
