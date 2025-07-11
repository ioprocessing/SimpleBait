package org.iopro.simpleBait;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class SafetyListeners implements Listener {

    @EventHandler
    public void onPlaceBait(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if(e.getItem() != null) {
            ItemMeta handItem = e.getItem().getItemMeta();
                // Check if the item being clicked with has the 'FISH_BAIT' key
                if (handItem.getPersistentDataContainer().has(Keys.FISH_BAIT)) {
                    e.setCancelled(true);
                }
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        // Check if either the first or the second item in the anvil has the 'FISH_BAIT' key
        if ((event.getInventory().getFirstItem() != null && event.getInventory().getFirstItem().getItemMeta().getPersistentDataContainer().has(Keys.FISH_BAIT, PersistentDataType.BOOLEAN))
                || (event.getInventory().getSecondItem() != null && event.getInventory().getSecondItem().getItemMeta().getPersistentDataContainer().has(Keys.FISH_BAIT, PersistentDataType.BOOLEAN))) {
            event.setResult(null);
        }
    }


}
