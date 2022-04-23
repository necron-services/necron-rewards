package dev.necron.rewards;

import com.hakan.core.HCore;
import dev.necron.core.NecronPlugin;

public class NecronRewardPlugin extends NecronPlugin {

    @Override
    public void whenEnabled() {
        HCore.initialize(this);
    }

    @Override
    public void whenDisabled() {

    }
}