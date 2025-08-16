package br.com.ylorde.holders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomItem extends ItemStack {
    private final ItemStack item;

    public CustomItem(Material material, int size) {
        this.item = new ItemStack(material, size);
    }

    public ItemStack getITem() { return  item; }
}
