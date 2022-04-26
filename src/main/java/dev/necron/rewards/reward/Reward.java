package dev.necron.rewards.reward;
import com.hakan.core.particle.HParticle;
import com.hakan.core.utils.HYaml;
import dev.necron.rewards.gui.MenuItem;
import dev.necron.rewards.utils.RewardUtils;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class Reward {

        private final MenuItem readyMenuItem;
        private final MenuItem cooldownMenuItem;

        private final String permission;
        private final int cooldown;

        private final Sound sound;
        private final HParticle particle;

        private final List<String> commands;
        private final List<ItemStack> items;

    public Reward(MenuItem readyMenuItem, MenuItem cooldownMenuItem, String permission, int cooldown, Sound sound, HParticle particle, List<String> commands, List<ItemStack> items) {
        this.readyMenuItem = readyMenuItem;
        this.cooldownMenuItem = cooldownMenuItem;
        this.permission = permission;
        this.cooldown = cooldown;
        this.sound = sound;
        this.particle = particle;
        this.commands = commands;
        this.items = items;
    }

    public Reward(HYaml yaml) {
        this.readyMenuItem = new MenuItem(yaml, "gui-item-ready");
        this.cooldownMenuItem = new MenuItem(yaml, "gui-item-cooldown");
        this.permission = yaml.getString("settings.usage.permission");
        this.cooldown = yaml.getInt("settings.usage.cooldown");
        this.sound = Sound.valueOf(yaml.getString("settings.effects.sound"));
        this.particle = new HParticle(yaml.getString("settings.effects.particle"));
        this.commands =yaml.getStringList("rewards.commands");
        this.items = RewardUtils.getItemStacksFromYaml(yaml);
    }

    public MenuItem getReadyMenuItem() {
        return this.readyMenuItem;
    }

    public MenuItem getCooldownMenuItem() {
        return this.cooldownMenuItem;
    }

    public String getPermission() {
        return this.permission;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public Sound getSound() {
        return this.sound;
    }

    public HParticle getParticle() {
        return this.particle;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public List<ItemStack> getItems() {
        return this.items;
    }
}
