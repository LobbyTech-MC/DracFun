package me.lidan.draconic.Other;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;

public class EnergyBreaker implements EnergyNetComponent {

    public EnergyBreaker(){ }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.NONE;
    }

    @Override
    public int getCapacity() {
        return 10;
    }

    @Override
    public String getId() {
        return null;
    }
}
