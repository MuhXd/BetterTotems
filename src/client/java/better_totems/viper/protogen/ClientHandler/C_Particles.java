package better_totems.viper.protogen.ClientHandler;

import better_totems.viper.protogen.ParticleHanders.CustomClasses.BaseParticle;
import better_totems.viper.protogen.Particles.ParticlesMain;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class C_Particles {
    public static void initparticles_Client() {
        ParticleFactoryRegistry.getInstance().register(ParticlesMain.EvilParticleOfDoomAndGloom, BaseParticle.Factory::new);
    }
}

