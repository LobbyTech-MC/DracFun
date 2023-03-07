package me.lidan.draconic.Other;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.lidan.draconic.Draconic;

public class ErrorFile {
    private static File file;
    private static FileConfiguration customfile;

    public static void setup(){
        file = new File(Draconic.getInstance().getDataFolder(),"errorfile.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) { }
        }
        customfile = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get(){
        return customfile;
    }

    public static void save(){
        try {
            customfile.save(file);
        } catch (IOException e) {
            System.out.println("[Draconic] File has error and cannot be saved!");
        }
    }

    public static void reload(){
        customfile = YamlConfiguration.loadConfiguration(file);

    }

}
