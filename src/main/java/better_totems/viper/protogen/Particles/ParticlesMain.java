package better_totems.viper.protogen.Particles;

import better_totems.viper.protogen.BetterTotems;
import better_totems.viper.protogen.Patches.VersionPatch;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticlesMain {
    public static final SimpleParticleType EvilParticleOfDoomAndGloom = FabricParticleTypes.simple();
    public static void initparticles_Server() {
        Registry.register(Registries.PARTICLE_TYPE, VersionPatch.of("evilparticle"), EvilParticleOfDoomAndGloom);
    }
}
