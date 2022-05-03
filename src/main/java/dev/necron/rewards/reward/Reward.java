package dev.necron.rewards.reward;

import com.hakan.core.particle.HParticle;
import dev.necron.rewards.configuration.RewardConfiguration;
import dev.necron.rewards.gui.adapter.item.MenuItem;
import dev.necron.rewards.utils.RewardUtils;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

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

    public Reward(MenuItem readyMenuItem,
                  MenuItem cooldownMenuItem,
                  String permission,
                  int cooldown,
                  Sound sound,
                  HParticle particle,
                  List<String> commands,
                  List<ItemStack> items) {
        this.readyMenuItem = readyMenuItem;
        this.cooldownMenuItem = cooldownMenuItem;
        this.permission = permission;
        this.cooldown = cooldown;
        this.sound = sound;
        this.particle = particle;
        this.commands = commands;
        this.items = items;
    }

    public Reward(RewardConfiguration configuration) {
        this.readyMenuItem = MenuItem.fromConfiguration(configuration, "gui-item-ready");
        this.cooldownMenuItem = MenuItem.fromConfiguration(configuration, "gui-item-cooldown");
        this.permission = configuration.get("settings.usage.permission");
        this.cooldown = configuration.get("settings.usage.cooldown");
        this.sound = Sound.valueOf(configuration.get("settings.effects.sound"));
        this.particle = new HParticle(configuration.get("settings.effects.particle"));
        this.commands = configuration.get("rewards.commands");
        this.items = RewardUtils.getStacksFromYaml(configuration);
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
