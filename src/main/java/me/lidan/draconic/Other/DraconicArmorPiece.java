package me.lidan.draconic.Other;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;

public class DraconicArmorPiece extends SlimefunItem implements Rechargeable {

    private final float capacity;
    public final double shield;

    public DraconicArmorPiece(ItemGroup category, SlimefunItemStack item, ItemStack[] recipe, float capacity,
                              double shield) {
        super(category, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
        this.shield = shield;
        this.capacity = capacity;
    }
    public DraconicArmorPiece(ItemGroup category, SlimefunItemStack item, ItemStack[] recipe, float capacity,
                              double shield,RecipeType type) {
        super(category, item, type, recipe);
        this.shield = shield;
        this.capacity = capacity;
    }

    /*
    @Override
    public void preRegister() {
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);
    }

    private void onBlockRightClick(PlayerRightClickEvent e){
        e.cancel();
        e.getPlayer().sendMessage("Cool Click!");
    }
     */

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return capacity;
    }

    public double getShield(ItemStack item) {
        return shield;
    }

}
