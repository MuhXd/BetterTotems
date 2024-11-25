package better_totems.viper.protogen.Items;

import better_totems.viper.protogen.BetterTotems;

public class MainItemPacketHandler {
    public static void main() {
        BetterTotems.LOGGER.info("Starting");
        ModItems.register_moditem();
        ModItemGroups.registerItemGroups();
    }
}
