package better_totems.viper.protogen.Items;

import net.minecraft.item.Item;
import better_totems.viper.protogen.Items.ModItems;
import net.minecraft.util.Hand;

import java.util.Arrays;
import java.util.List;


public class TotemPacketsIds {
    private static List<Item> Items = Arrays.asList(ModItems.EvilTotem,ModItems.LevitationTotem);
    public static int Offset = 100;
    public static int getObject(Item Item) {
        int startid = -1;
        for (Item Targ : Items) {
            startid+=1;
            if (Item == Targ) {
                return Offset+startid;
            }
        }
        return -1;
    };
    public static Item GetRealObject(int id) {
        int startid = TotemPacketsIds.Offset-1;
        for (Item Targ : Items) {
            startid+=1;
            if (startid == id) {
                return Targ;
            }
        }
        return null;
    };
}
