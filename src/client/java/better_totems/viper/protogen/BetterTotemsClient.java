package better_totems.viper.protogen;

import better_totems.viper.protogen.ClientHandler.C_Particles;
import better_totems.viper.protogen.Particles.ParticlesMain;
import net.fabricmc.api.ClientModInitializer;
// crazy
public class BetterTotemsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		C_Particles.initparticles_Client();
	}
}