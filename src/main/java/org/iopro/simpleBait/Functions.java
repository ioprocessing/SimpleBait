package org.iopro.simpleBait;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Functions {
    public static ItemStack getJunkItem() {
        List<Material> junk = List.of(Material.ROTTEN_FLESH, Material.STICK,
                Material.KELP, Material.INK_SAC,
                Material.FLINT, Material.CLAY_BALL,
                Material.LILY_PAD);

        return new ItemStack(junk.get((int)(Math.random() * (7))));
    }
}
