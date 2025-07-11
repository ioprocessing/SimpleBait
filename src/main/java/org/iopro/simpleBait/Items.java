package org.iopro.simpleBait;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class Items {
    public static ItemStack fishBait() {
        ItemStack fishBait = new ItemStack(Material.BEETROOT_SEEDS);
        ItemMeta meta = fishBait.getItemMeta();

        TextComponent bait_name = Component.text("Fish Bait", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false);

        meta.lore(Arrays.asList(
                Component.text("Fish while holding this in ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("your inventory to catch fish.").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));

        // Set custom model
        CustomModelDataComponent model = meta.getCustomModelDataComponent();
        model.setStrings(List.of("fish_bait"));
        meta.setCustomModelDataComponent(model);

        // Set custom name
        meta.customName(bait_name);

        // Add custom tag that we'll check for later
        meta.getPersistentDataContainer().set(Keys.FISH_BAIT, PersistentDataType.BOOLEAN, true);

        // Finalize the changes we made to the meta
        fishBait.setItemMeta(meta);

        return fishBait;
    }
}
