package br.com.ylorde.holders;

import br.com.ylorde.utils.ConvertToColoredText;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CRIFurnace implements InventoryHolder {
    private final Inventory inv;

    public CRIFurnace(int size, String title) {
        this.inv = Bukkit.createInventory(this, size, new ConvertToColoredText().convert(title));
    }

    @Override
    public Inventory getInventory() { return inv; }
}
