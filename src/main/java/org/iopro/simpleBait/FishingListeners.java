package org.iopro.simpleBait;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishingListeners implements Listener {
    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            // Iterate through inventory; at first instance of fish bait, subtract count by 1 and end
            for(ItemStack item : p.getInventory().getContents()) {
                if(item != null && item.getItemMeta().getPersistentDataContainer().has(Keys.FISH_BAIT)) {
                    item.setAmount(item.getAmount() - 1);
                    return;
                }
            }
            Item item = (Item) e.getCaught();
            item.setItemStack(Functions.getJunkItem());
            e.setExpToDrop(0);
        }
    }

    @EventHandler
    public void onFishyBusiness(PlayerAdvancementCriterionGrantEvent e) {
        if(e.getAdvancement().getKey().equals(NamespacedKey.fromString("husbandry/fishy_business"))) {
            if(e.getPlayer().getFishHook().getHookedEntity() == null)
                e.setCancelled(true);
        }
    }
}
