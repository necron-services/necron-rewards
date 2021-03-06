package dev.necron.rewards.gui.adapter;

import com.hakan.core.ui.inventory.HInventory;
import dev.necron.rewards.configuration.RewardConfiguration;
import dev.necron.rewards.gui.adapter.item.MenuItem;
import dev.necron.rewards.utils.RewardUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked"})
public abstract class InventoryGUI extends HInventory {

    protected RewardConfiguration configuration;

    public InventoryGUI(@Nonnull String id, @Nonnull String title, int size, @Nonnull InventoryType type, @Nonnull Set<Option> options) {
        super(id, title, size, type, options);
    }

    public InventoryGUI(@Nonnull String id, @Nonnull String title, int size, @Nonnull InventoryType type) {
        super(id, title, size, type);
    }

    public InventoryGUI(@Nonnull RewardConfiguration configuration) {
        super("hclaims_" + configuration.getYaml().getFile().getName(),
                RewardUtils.colored(configuration.get("title")),
                configuration.get("size"),
                InventoryType.valueOf(configuration.get("type")));

        this.configuration = configuration;
        this.loadOtherItems();
    }

    public void setItem(MenuItem menuItem, @Nullable Consumer<InventoryClickEvent> consumer) {
        super.setItem(menuItem.getSlot(), menuItem.build(), consumer);
    }

    public void setItem(MenuItem menuItem) {
        super.setItem(menuItem.getSlot(), menuItem.build());
    }

    public void loadOtherItems() {
        this.configuration.find("other-items", MemorySection.class)
                .ifPresent(section -> section.getKeys(false).forEach(itemKey -> {
                    MenuItem menuItem = MenuItem.fromConfiguration(this.configuration, "other-items." + itemKey);

                    List<Integer> slots = new ArrayList<>();
                    if (menuItem.getSlot() != -1) slots.add(menuItem.getSlot());
                    else slots = this.configuration.get("other-items." + itemKey + ".slots");

                    slots.forEach(slot -> this.setItem(slot, menuItem.build(), event -> {
                        List<String> console_commands = this.configuration.get("other-items." + itemKey + ".console-commands", new ArrayList<>(), List.class);
                        List<String> player_commands = this.configuration.get("other-items." + itemKey + ".player-commands", new ArrayList<>(), List.class);
                        for (String command : console_commands) {
                            String parsedCommand = command.replace("%player%", event.getWhoClicked().getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), parsedCommand);
                        }
                        for (String command : player_commands) {
                            String parsedCommand = command.replace("%player%", event.getWhoClicked().getName());
                            Bukkit.getServer().dispatchCommand(event.getWhoClicked(), parsedCommand);
                        }
                    }));
                }));
    }
}