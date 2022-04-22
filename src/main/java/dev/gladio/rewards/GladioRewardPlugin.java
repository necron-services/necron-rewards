package dev.gladio.rewards;

import dev.gladio.GladioPlugin;

public class GladioRewardPlugin extends GladioPlugin {

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
        return "gladio_rewards_plugin";
    }
}