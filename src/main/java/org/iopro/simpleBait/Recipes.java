package org.iopro.simpleBait;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class Recipes {
    public static void register() {

        /// BAIT RECIPE ///

        ItemStack fishBait = Items.fishBait();
        fishBait.setAmount(8);

        // Outline the recipe shape
        ShapelessRecipe fishBaitRecipe = new ShapelessRecipe(Keys.FISH_BAIT_RECIPE, fishBait);

        // Now just substitute the ingredients
        fishBaitRecipe.addIngredient(4, Material.ROTTEN_FLESH);

        // Don't duplicate recipe
        if (Bukkit.getRecipe(Keys.FISH_BAIT_RECIPE) != null) {
            Bukkit.removeRecipe(Keys.FISH_BAIT_RECIPE);
        }
        Bukkit.addRecipe(fishBaitRecipe);
    }
}
