package dev.necron.rewards.utils;

import com.hakan.core.item.HItemBuilder;
import dev.necron.rewards.configuration.RewardConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.MemorySection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RewardUtils {

    public static String colored(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<ItemStack> getStacksFromYaml(RewardConfiguration configuration) {
        List<ItemStack> itemStackList = new ArrayList<>();
        configuration.get("rewards.items", MemorySection.class).getKeys(false)
                .forEach(key -> itemStackList.add(getStackFromYaml(configuration, "rewards.items." + key)));
        return itemStackList;
    }

    public static ItemStack getStackFromYaml(RewardConfiguration configuration, String path) {
        HItemBuilder itemBuilder = new HItemBuilder(Material.valueOf(configuration.get(path + ".type")))
                .name(configuration.get(path + ".name"))
                .nbt(configuration.get(path + ".nbt"))
                .amount(configuration.get(path + ".amount"))
                .glow(configuration.get(path + ".glow"))
                .lores(true, configuration.get(path + ".lore"));
        configuration.get(path + ".enchants", MemorySection.class).getKeys(false)
                .forEach(key -> itemBuilder.addEnchant(Enchantment.getByName(key), configuration.get(path + ".enchants." + key)));
        return itemBuilder.build();
    }

    public static List<File> getFilesByPath(String path) {
        List<File> files = new ArrayList<>();
        File[] listOfFiles = new File(path).listFiles();

        if (listOfFiles == null)
            return files;

        for (File file : listOfFiles)
            if (file.getName().endsWith(".yml"))
                files.add(file);

        return files;
    }
}