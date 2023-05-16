package me.lidan.draconic.Other;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import me.filoghost.holographicdisplays.api.hologram.Hologram;

public class aiflyholo extends BukkitRunnable {
    int i = 0;
    Hologram e;
    Location loc;
    long duration;
    Location startloc;
    double dx;
    double dy;
    double dz;
    long times;
    double x1;
    double y1;
    double z1;

    public aiflyholo(Hologram e, Location loc, long duration){
        this.e = e;
        this.loc = loc;
        this.duration = duration;

        this.times = duration/50;
        this.x1 = e.getLocation().getX();
        double x2 = loc.getX();

        this.y1 = e.getLocation().getY();
        double y2 = loc.getY();

        this.z1 = e.getLocation().getZ();
        double z2 = loc.getZ();

        this.dx = x2 - x1;
        this.dy = y2 - y1;
        this.dz = z2 - z1;
        // System.out.println("aiflyholo start for " + e);
    }

    @Override
    public void run() {
        try {
            double mx;
            double my;
            double mz;
            Location tploc;

            mx = x1 + dx / times * i;
            my = y1 + dy / times * i;
            mz = z1 + dz / times * i;
            tploc = new Location(e.getWorld(), mx, my, mz);
            e.teleport(tploc);
            i++;
            if (i > times) {
                // System.out.println("aiflyholo done for " + e);
                this.cancel();
            }
        } catch(Exception error){
            cancel();
        }
    }

    /*
    public static void aiflyto(Entity e, Location loc, long duration) {
        long times = duration/50;
        double x1 = e.getLocation().getX();
        double x2 = loc.getX();

        double y1 = e.getLocation().getY();
        double y2 = loc.getY();

        double z1 = e.getLocation().getZ();
        double z2 = loc.getZ();

        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;

        double mx;
        double my;
        double mz;
        Location tploc;
        for (int i = 0; i < times; i++) {
            mx = x1 + dx / times * i;
            my = y1 + dy / times * i;
            mz = z1 + dz / times * i;
            tploc = new Location(e.getWorld(), mx, my, mz);
            e.teleport(tploc);
        }
         */
    // }
}
