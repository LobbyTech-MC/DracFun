package me.lidan.draconic.Commands;

import static me.lidan.draconic.Database.Database.lastselectall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.lidan.draconic.Draconic;
import me.lidan.draconic.Database.Database;
import me.lidan.draconic.Fusion.FusionCrafting;
import me.lidan.draconic.Other.ErrorFile;

public class DraconicCmd implements CommandExecutor {
	  
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (sender == null){
            return false;
        }
        if(!p.hasPermission("draconic.admin")){
            return false;
        }
        if (args.length > 0 ) {
            if (args.length > 2 && (args[0].equalsIgnoreCase("map") || args[0].equalsIgnoreCase("set"))) {
                Draconic.vars.put("set::" + p.getName(), 1d);
                Draconic.vars.put(args[1] + "::" + p.getName(), Double.parseDouble(args[2]));
                sender.sendMessage("§6[Draconic] set " + args[1] + " to " + args[2]);
            } else if (args[0].equalsIgnoreCase("shield")) {
                if (args[1].equalsIgnoreCase("set") && args[2] != null) {
                    p.sendMessage("§6[Draconic] set shield to " + args[2]);
                    sender.sendMessage("§6[Draconic] set shield to " + args[2]);
                    Draconic.vars.put("shield::" + p.getName(), Double.parseDouble(args[2]));
                }
            } else if (args[0].equalsIgnoreCase("maxshield")) {
                if (args[1].equalsIgnoreCase("set") && args[2] != null) {
                    p.sendMessage("§6[Draconic] set shield to " + args[2]);
                    sender.sendMessage("§6[Draconic] set shield to " + args[2]);
                    Draconic.vars.put("shield::" + p.getName(), Double.parseDouble(args[2]));
                }
            }
            else if(args[0].equalsIgnoreCase("printmap")){
                final Player fp = p;
                Draconic.vars.forEach((k,v) -> fp.sendMessage("key: " + k + " value: " + v));
            }
            else if(args[0].equalsIgnoreCase("reload")){
                p.performCommand("plugman reload Draconic");
            }
            else if(args[0].equalsIgnoreCase("serialize")){
                String msg = Draconic.DracSerializer.serialize(p.getInventory().getItemInMainHand());
                p.sendMessage(msg);
                // System.out.println("Serialized ItemStack = " + msg);
            }
            else if(args.length > 1 && args[0].equalsIgnoreCase("deserialize")){
                Object fixed = Draconic.DracSerializer.deserialize(args[1]);
                try {
                    ItemStack item = (ItemStack) fixed;
                    p.getInventory().addItem(item);
                } catch(ClassCastException error){
                    p.sendMessage("Failed! " + error.getMessage());
                    p.sendMessage("Got Object! " + fixed);
                }
            }
            else if(args.length > 1 && args[0].equalsIgnoreCase("errorize")){
                Object fixed = Draconic.DracSerializer.errorize(p.getInventory().getItemInMainHand());
                try {
                    ItemStack item = (ItemStack) fixed;
                    p.getInventory().addItem(item);
                } catch(ClassCastException error){
                    p.sendMessage("Failed! " + error.getMessage());
                    p.sendMessage("Got Object! " + fixed);
                }
            }
            else if(args[0].equalsIgnoreCase("supererrorize")){
                Object fixed = Draconic.DracSerializer.supererrorize(p.getInventory().getItemInMainHand());
                try {
                    ItemStack item = (ItemStack) fixed;
                    p.getInventory().addItem(item);
                } catch(ClassCastException error){
                    p.sendMessage("Failed! " + error.getMessage());
                    p.sendMessage("Got Object! " + fixed);
                }
            } else if(args[0].equalsIgnoreCase("savelast")) {
                Draconic.DracSerializer.savelast();
            }
            else if(args.length > 1 && args[0].equalsIgnoreCase("deserialize-file")){
                Object fixed = Draconic.DracSerializer.deserialize(ErrorFile.get().getString(args[1]));
                try {
                    ItemStack item = (ItemStack) fixed;
                    p.getInventory().addItem(item);
                } catch(ClassCastException error){
                    p.sendMessage("Failed! " + error.getMessage());
                    p.sendMessage("Got Object! " + fixed);
                }
            }
            else if(args.length > 2 && args[0].equalsIgnoreCase("sound")){
                p.playSound(p.getLocation(), Sound.valueOf(args[1]),1F,Float.parseFloat(args[2]));
            }
            else if(args[0].equalsIgnoreCase("selectAll")) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        Database.selectAll();
                        while(lastselectall == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.runTaskAsynchronously(Draconic.getInstance());
            }
            else if(args[0].equalsIgnoreCase("selectAllAndDelete")) {
                Database.selectAllAndDelete();
            }
            else if(args[0].equalsIgnoreCase("select")) {
                Database.select(Draconic.getTargetBlock(p).getLocation());
            }
            else if(args[0].equalsIgnoreCase("delete")) {
                Database.delete(Draconic.getTargetBlock(p).getLocation());
            }
            else if(args.length > 1 && args[0].equalsIgnoreCase("viewrecipe")) {
                if (!FusionCrafting.viewRecipe(p,args[1]))
                    p.sendMessage("This recipe doesn't exists");
            }
            else if(args[0].equalsIgnoreCase("getcolor")) {
                LeatherArmorMeta lch = (LeatherArmorMeta)p.getInventory().getItemInMainHand().getItemMeta();
                p.sendMessage("" + lch.getColor());
            }
            else if(args[0].equalsIgnoreCase("hideentity")) {
                Double.parseDouble(args[1]);
                Predicate<Entity> filter = i -> (i != p);
                RayTraceResult ray = p.getWorld().rayTraceEntities(p.getEyeLocation(),
                        p.getEyeLocation().getDirection(),
                        20d,1,filter);
                if (ray == null){
                    p.sendMessage("Yeany did an error!");
                    return true;
                }
                if(ray.getHitEntity() == null){
                    p.sendMessage("Maxi did an error!");
                    return true;
                }
                Entity e = ray.getHitEntity();
                Draconic.packetHideEntity(e, p);
            }
            else if(args[0].equalsIgnoreCase("showblocks")){
                for (Location loc : lastselectall.keySet()) {
                    p.sendBlockChange(loc, Material.ORANGE_TERRACOTTA.createBlockData());
                }
            }
            else if(args[0].equalsIgnoreCase("showblockswithholo")){
                ArrayList<Hologram> holos = new ArrayList<>();
                
                //DecentHolograms impl = DecentHologramsAPI.get();
                //DHAPI hologramapi = impl.getHologramManager().registerHologram(null);
                
                for (Location loc : lastselectall.keySet()) {
                    p.sendBlockChange(loc, Material.ORANGE_TERRACOTTA.createBlockData());
                    Hologram holo = DHAPI.createHologram("Draconic_" + loc.hashCode(), loc, false);
                    holos.add(holo);
                    HashMap<String, Object> text = lastselectall.get(loc);
                    if (text == null) {
                    	DHAPI.addHologramLine(holo, "null");
                    }
                    else {
                    	DHAPI.addHologramLine(holo, text.toString());
                    }

                }
            }
        }

        return true;
    }
}