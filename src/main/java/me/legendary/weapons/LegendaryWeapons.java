package me.legendary.weapons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class LegendaryWeapons extends JavaPlugin implements Listener {

    private boolean daggerCrafted = false;
    private boolean spearCrafted = false;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        createRecipes();
    }

    private void createRecipes() {

        // 🔹 Frost Dagger
        ItemStack dagger = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = dagger.getItemMeta();
        meta.setDisplayName("§bFrozen Dagger");
        dagger.setItemMeta(meta);

        NamespacedKey daggerKey = new NamespacedKey(this, "frost_dagger");
        ShapedRecipe daggerRecipe = new ShapedRecipe(daggerKey, dagger);
        daggerRecipe.shape("AAA"," B ","CCC");

        daggerRecipe.setIngredient('A', Material.DIAMOND);
        daggerRecipe.setIngredient('B', Material.DIAMOND_SWORD);
        daggerRecipe.setIngredient('C', Material.PACKED_ICE);

        Bukkit.addRecipe(daggerRecipe);

        // 🔹 Sentinel Spear
        ItemStack spear = new ItemStack(Material.TRIDENT);
        ItemMeta meta2 = spear.getItemMeta();
        meta2.setDisplayName("§6Sentinel's Spear");
        spear.setItemMeta(meta2);

        NamespacedKey spearKey = new NamespacedKey(this, "sentinel_spear");
        ShapedRecipe spearRecipe = new ShapedRecipe(spearKey, spear);
        spearRecipe.shape("AAA"," B ","CCC");

        spearRecipe.setIngredient('A', Material.DIAMOND);
        spearRecipe.setIngredient('B', Material.TRIDENT);
        spearRecipe.setIngredient('C', Material.BLAZE_ROD);

        Bukkit.addRecipe(spearRecipe);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {

        if (e.getRecipe() == null) return;

        String name = e.getCurrentItem().getItemMeta().getDisplayName();

        if (name.equals("§bFrozen Dagger")) {
            if (daggerCrafted) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage("§cThis weapon has already been crafted!");
            } else {
                daggerCrafted = true;
                Bukkit.broadcastMessage("§bThe Frozen Dagger has been crafted!");
            }
        }

        if (name.equals("§6Sentinel's Spear")) {
            if (spearCrafted) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage("§cThis weapon has already been crafted!");
            } else {
                spearCrafted = true;
                Bukkit.broadcastMessage("§6The Sentinel's Spear has been crafted!");
            }
        }
    }
}

