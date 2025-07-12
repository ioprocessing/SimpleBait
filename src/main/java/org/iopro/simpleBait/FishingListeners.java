package org.iopro.simpleBait;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class FishingListeners implements Listener {
    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            // Iterate through inventory; at first instance of fish bait, subtract count by 1 and end
            for(ItemStack item : p.getInventory().getContents()) {
                if(item != null && item.getItemMeta().getPersistentDataContainer().has(Keys.FISH_BAIT)) {
                    Item fish = (Item) e.getCaught();
                    // If they originally caught a fish and don't have fishy business, grant them the advancement
                    if (List.of(Material.COD, Material.SALMON, Material.PUFFERFISH, Material.TROPICAL_FISH).contains(fish.getItemStack().getType())
                    && !p.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.fromString("husbandry/fishy_business"))).isDone())
                        p.setMetadata("CaughtFish", new FixedMetadataValue(SimpleBait.getInstance(), "Caught Fish"));
                    item.setAmount(item.getAmount() - 1);
                    return;
                }
            }
            e.setExpToDrop(0);
            Item item = (Item) e.getCaught();
            item.setItemStack(Functions.getJunkItem());
        }
    }

    @EventHandler
    public void onFishyBusiness(PlayerAdvancementCriterionGrantEvent e) {
        if(e.getAdvancement().getKey().equals(NamespacedKey.fromString("husbandry/fishy_business")))
            // If the player has just caught a fish, allow them to get the advancement. Otherwise, cancel it
            if(e.getPlayer().hasMetadata("CaughtFish")) {
                e.getPlayer().removeMetadata("CaughtFish", SimpleBait.getInstance());
                return;
            }
        e.setCancelled(true);
    }
}
