package me.lidan.draconic.Events;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.lidan.draconic.Database.Database;

public class Interact implements Listener {
//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void onInteract(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        String iname = "";
//        try{
//            iname = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
//        }
//        catch (Exception ERROR){
//            iname = p.getInventory().getItemInMainHand().getType().toString();
//        }
//        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && iname.contains("Fusion")){
//            // p.sendMessage(e.getBlockFace().toString());
//            String finalIname = iname;
//            new BukkitRunnable(){
//                @Override
//                public void run() {
//                    RayTraceResult ray = p.getWorld().rayTraceBlocks(p.getEyeLocation(),
//                            p.getEyeLocation().getDirection(),
//                            20d);
//                    if (ray != null){
//                        BlockFace face = ray.getHitBlockFace();
//                        Block block = ray.getHitBlock();
//                        if (!Slimefun.getProtectionManager().hasPermission(p, block, Interaction.INTERACT_BLOCK)) return;
//                        // p.sendMessage("rayed " + face);
//                        // Entity zimon = p.getWorld().spawnEntity(block.getLocation(), EntityType.ZOMBIE);
//                        // ItemFrame i = (ItemFrame) p.getWorld().spawnEntity(block.getLocation().add(0.5,0.5,0.5),
//                                // EntityType.ITEM_FRAME);
//                        // ItemFrame i = Draconic.SpawnItemFrame(block.getLocation(),face);
//                        // i.setInvulnerable(true);
//                        // i.setFixed(true);
//                        HashMap<String,Object> blockdata = new HashMap<>();
//                        blockdata.put("type",finalIname);
//                        blockdata.put("energy",0d);
//                        blockdata.put("maxenergy",0d);
//                        blockdata.put("item",new ItemStack(Material.AIR));
//                        // Draconic.blockdata.put(block.getLocation(), blockdata);
//                        Database.setblock(block.getLocation(), blockdata);
//                        // i.setVisible(false);
//                        /*
//                        i.setFacingDirection(BlockFace.SOUTH);
//                        HangingPlaceEvent hEvent = new HangingPlaceEvent(i, p, block, face);
//                        Draconic.getInstance().getServer().getPluginManager().callEvent(hEvent);
//                         */
//                    }
//                    else p.sendMessage("ERROR?");
//                }
//            }.runTaskLater(Draconic.getInstance(), 1L);
//        }
//  }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlockPlaced();
        String iname = "";
        if (!Slimefun.getProtectionManager().hasPermission(p, block, Interaction.PLACE_BLOCK)) return;
        try{
            iname = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        }
        catch (Exception ERROR){
            iname = p.getInventory().getItemInMainHand().getType().toString();
        }
        if (iname.contains("Fusion")){
            HashMap<String,Object> blockdata = new HashMap<>();
            blockdata.put("type",iname);
            blockdata.put("energy",0d);
            blockdata.put("maxenergy",0d);
            blockdata.put("item",new ItemStack(Material.AIR));
            // Draconic.blockdata.put(block.getLocation(), blockdata);
            Database.setblock(block.getLocation(), blockdata);
        }

    }

    /*
    @EventHandler
    public void blockPlaced(BlockPlaceEvent event) {
        Block b = event.getBlock();
        b.setMetadata("PLACED", new FixedMetadataValue(Draconic.getInstance(),
                "Placed by: " + event.getPlayer().getUniqueId()));
    }
     */

}
