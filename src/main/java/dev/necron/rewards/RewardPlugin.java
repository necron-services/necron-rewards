package dev.necron.rewards;

import com.hakan.core.HCore;
import dev.necron.core.NecronPlugin;
import dev.necron.rewards.reward.RewardHandler;
import dev.necron.rewards.user.RewardUserHandler;

public class RewardPlugin extends NecronPlugin {

    @Override
    public void whenEnabled() {
        HCore.initialize(this);
        RewardHandler.initialize(this);
        RewardUserHandler.initialize(this);
    }

    @Override
    public void whenDisabled() {

    }
}