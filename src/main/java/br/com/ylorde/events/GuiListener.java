package br.com.ylorde.events;

import br.com.ylorde.holders.FurnaceGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.jetbrains.annotations.NotNull;

public class GuiListener implements Listener {

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {
        if (!(e.getView().getTopInventory().getHolder() instanceof FurnaceGui)) return;

        if (e.getClickedInventory() != null &&
                e.getClickedInventory().equals(e.getView().getTopInventory())) {
            e.setCancelled(true);
            return;
        }

        if (e.isShiftClick()
                || e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
                || e.getClick() == ClickType.DOUBLE_CLICK
                || e.getHotbarButton() >= 0) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(@NotNull InventoryDragEvent e) {
        if (!(e.getView().getTopInventory().getHolder() instanceof FurnaceGui)) return;

        for (int raw : e.getRawSlots()) {
            if (raw < e.getView().getTopInventory().getSize()) {
                e.setCancelled(true);
                return;
            }
        }
    }
}
