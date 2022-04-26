package dev.necron.rewards.configuration;

import com.hakan.core.utils.HYaml;
import dev.necron.rewards.NecronRewardPlugin;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Iterator;

public class RewardConfiguration {
    public static HYaml CONFIG;

    public static void initialize(NecronRewardPlugin plugin) {
        CONFIG = HYaml.create(plugin, "config.yml", "config.yml");

        Iterator<File> it = FileUtils.iterateFiles(new File("plugins/NecronRewards/rewards"), null, false);
        while (it.hasNext()) {
            File rewardYaml = it.next();

        }
    }
}
