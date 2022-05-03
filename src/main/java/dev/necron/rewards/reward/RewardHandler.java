package dev.necron.rewards.reward;

import dev.necron.rewards.RewardPlugin;
import dev.necron.rewards.configuration.RewardConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RewardHandler {

    private static Map<String, Reward> rewards;

    public static void initialize(RewardPlugin plugin) {

        //CONFIGURATION
        RewardConfiguration.initialize(plugin);


        //REWARDS
        RewardHandler.rewards = RewardHandler.loadAll();


    }

    public static Map<String, Reward> loadAll() {
        Map<String, Reward> rewards = new HashMap<>();

        RewardConfiguration.getConfigurationsMap().forEach((name, configuration) -> {
            if (name.startsWith("rewards/")) {
                Reward reward = new Reward(configuration);
                rewards.put(name.substring(8, name.length() - 4), reward);
            }
        });

        return rewards;
    }


    public static Map<String, Reward> getContentSafe() {
        return new HashMap<>(RewardHandler.rewards);
    }

    public static Map<String, Reward> getContent() {
        return RewardHandler.rewards;
    }

    public static Collection<Reward> getValuesSafe() {
        return new ArrayList<>(RewardHandler.rewards.values());
    }

    public static Collection<Reward> getValues() {
        return RewardHandler.rewards.values();
    }

    public static Optional<Reward> findByName(String name) {
        return Optional.ofNullable(RewardHandler.rewards.get(name));
    }

    public static Reward getByName(String name) {
        return RewardHandler.findByName(name).orElseThrow(() -> new NullPointerException("Reward does not exist: " + name));
    }
}