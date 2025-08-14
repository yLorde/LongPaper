package br.com.ylorde.holders;

import br.com.ylorde.utils.ConvertToColoredText;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class FurnaceGui implements InventoryHolder {
    private final Inventory inv;

    public FurnaceGui(int size, String title) {
        this.inv = Bukkit.createInventory(this, size, new ConvertToColoredText().convert(title));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
