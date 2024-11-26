package better_totems.viper.protogen.Patches;

import better_totems.viper.protogen.BetterTotems;
import net.minecraft.util.Identifier;

public class VersionPatch {
    public static Identifier of(String id) {
        return Identifier.of(BetterTotems.MOD_ID,id);
    };
}
