package better_totems.viper.protogen.Items;


import better_totems.viper.protogen.BetterTotems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static ItemGroup AnotherGroup = Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(BetterTotems.MOD_ID, "item_group"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemGroup."+BetterTotems.MOD_ID+".fungroup"))
                        .icon(() -> new ItemStack(ModItems.EvilTotem))
                        .entries(((displayContext, entries) -> {
                            entries.add(ModItems.EvilTotem);
                            entries.add(ModItems.LevitationTotem);
                        })).build()
        );
    public static void registerItemGroups() {
        BetterTotems.LOGGER.info("Registering item groups");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((entries -> {
            entries.add(ModItems.EvilTotem);
            entries.add(ModItems.LevitationTotem);
        }));

    }
}