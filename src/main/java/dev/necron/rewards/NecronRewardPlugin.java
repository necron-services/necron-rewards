package dev.necron.rewards;

import dev.necron.NecronPlugin;

public class NecronRewardPlugin extends NecronPlugin {

    @Override
    public void whenEnabled() {

    }

    @Override
    public void whenDisabled() {

    }

    @Override
    public String getUserName() {
        return "%%__USER__%%";
    }

    @Override
    public String getProduct() {
        return "necron_rewards_plugin";
    }
}