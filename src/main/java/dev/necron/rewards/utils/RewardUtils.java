package dev.necron.rewards.utils;

import com.hakan.core.item.HItemBuilder;
import com.hakan.core.utils.HYaml;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RewardUtils {

    public static List<ItemStack> getItemStacksFromYaml(HYaml yaml) {
        List<ItemStack> itemStackList = new ArrayList<>();
        yaml.getConfigurationSection("rewards.items").getKeys(false).forEach(itemID -> {
            HItemBuilder itemBuilder = new HItemBuilder(Material.valueOf(yaml.getString("rewards.items." + itemID + ".material")))
                    .name(yaml.getString("rewards.items." + itemID + ".name"))
                    .nbt(yaml.getString("rewards.items." + itemID + ".nbt"))
                    .amount(yaml.getInt("rewards.items." + itemID + ".amount"))
                    .lores(true, yaml.getStringList("rewards.items." + itemID + ".lore"));
            yaml.getConfigurationSection("rewards.items." + itemID + ".enchants").getKeys(false)
                    .forEach(key -> itemBuilder.addEnchant(Enchantment.getByName(key), yaml.getInt("rewards.items." + itemID + ".enchants." + key)));
            itemStackList.add(itemBuilder.build());
        });
    return itemStackList;
    }



    public static ItemStack getItemStacksFromYaml(HYaml yaml, String path) {
            HItemBuilder itemBuilder = new HItemBuilder(Material.valueOf(yaml.getString("rewards.items." + path + ".material")))
                    .name(yaml.getString("rewards.items." + path + ".name"))
                    .nbt(yaml.getString("rewards.items." + path + ".nbt"))
                    .amount(yaml.getInt("rewards.items." + path + ".amount"))
                    .lores(true, yaml.getStringList("rewards.items." + path + ".lore"));
            yaml.getConfigurationSection("rewards.items." + path + ".enchants").getKeys(false)
                    .forEach(key -> itemBuilder.addEnchant(Enchantment.getByName(key), yaml.getInt("rewards.items." + path + ".enchants." + key)));
    return itemBuilder.build();
    }



}

