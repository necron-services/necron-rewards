package dev.necron.rewards.configuration;

import com.hakan.core.utils.HYaml;
import dev.necron.rewards.RewardPlugin;
import dev.necron.rewards.utils.RewardUtils;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"unchecked"})
public class RewardConfiguration {

    public static RewardConfiguration CONFIG;
    private final static Map<String, RewardConfiguration> configurations = new HashMap<>();

    public static void initialize(RewardPlugin plugin) {
        Collections.singletonList("config.yml")
                .forEach(path -> configurations.put(path, new RewardConfiguration(HYaml.create(plugin, path, path))));

        List<File> files = RewardUtils.getFilesByPath(plugin.getDataFolder() + "/rewards");
        if (files.size() == 0)
            HYaml.create(plugin, "rewards/example_reward.yml", "rewards/example_reward.yml");
        files.forEach(file -> configurations.put(file.getName(),
                new RewardConfiguration(HYaml.create(plugin, "rewards/" + file.getName(), "rewards/" + file.getName()))));

        CONFIG = configurations.get("config.yml");
    }

    public static Map<String, RewardConfiguration> getConfigurationsMap() {
        return configurations;
    }

    public static Collection<RewardConfiguration> getConfigurations() {
        return configurations.values();
    }

    public static Optional<RewardConfiguration> findByPath(String path) {
        return Optional.ofNullable(configurations.get(path));
    }

    public static RewardConfiguration getByPath(String path) {
        return RewardConfiguration.findByPath(path).orElseThrow(() -> new NullPointerException("reward configuration couldn't for path: " + path));
    }


    private final HYaml yaml;

    public RewardConfiguration(HYaml yaml) {
        this.yaml = yaml;
    }

    public HYaml getYaml() {
        return this.yaml;
    }

    public void save() {
        this.yaml.save();
    }

    public void reload() {
        this.yaml.reload();
    }

    public void delete() {
        this.yaml.delete();
    }

    public <T> Optional<T> find(String path) {
        return Optional.ofNullable((T) this.yaml.get(path));
    }

    public <T> Optional<T> find(String path, T tDefault) {
        Object object = this.yaml.get(path);
        return Optional.ofNullable((object != null) ? (T) object : tDefault);
    }

    public <T> Optional<T> find(String path, Class<T> clazz) {
        return Optional.ofNullable(clazz.cast(this.yaml.get(path)));
    }

    public <T> Optional<T> find(String path, T tDefault, Class<T> clazz) {
        Object object = this.yaml.get(path);
        return Optional.ofNullable((object != null) ? clazz.cast(this.yaml.get(path)) : tDefault);
    }

    public <T> T get(String path) {
        return (T) this.yaml.get(path);
    }

    public <T> T get(String path, T tDefault) {
        Object object = this.yaml.get(path);
        return (object != null) ? (T) object : tDefault;
    }

    public <T> T get(String path, Class<T> clazz) {
        return clazz.cast(this.yaml.get(path));
    }

    public <T> T get(String path, T tDefault, Class<T> clazz) {
        Object object = this.yaml.get(path);
        return (object != null) ? clazz.cast(this.yaml.get(path)) : tDefault;
    }

    public void set(String path, Object value) {
        this.yaml.set(path, value);
    }
}
