package better_totems.viper.protogen.Items;

import better_totems.viper.protogen.BetterTotems;
import better_totems.viper.protogen.Items.ItemBase.BaseItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class ModItems {

    public static final Item EvilTotem = register("eviltotem", 1,Rarity.RARE);
    public static final Item LevitationTotem = register("levitationtotem", 1,Rarity.EPIC);

    public static Item register(String id,int stack,Rarity SetRarity) {
        Identifier Identid = Identifier.of(BetterTotems.MOD_ID, id);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identid);
        Item.Settings settings = new Item.Settings()
                // If your item is based on a block
               // .useBlockPrefixedTranslationKey()
                .maxCount(stack)
                .rarity(SetRarity)
                .registryKey(key);
        Item registeredItem = Registry.register(Registries.ITEM, key, new BaseItem(settings));
        return registeredItem;
    }

    public static void register_moditem() {
        BetterTotems.LOGGER.info("Registering mod items");
    }
}
