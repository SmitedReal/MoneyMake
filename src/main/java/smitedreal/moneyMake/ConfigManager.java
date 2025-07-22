package smitedreal.moneyMake;

import org.bukkit.Bukkit;
import smitedreal.smiteddata.SDAT;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
public class ConfigManager {
    public static double goldPrice = 1.0;
    public static double gapPrice = 8.0;
    public static double egapPrice = 72.0;
    public static double tax = 0.0;

    public static void ConfigWork() {
            // Create the moneymake folder if it doesnt exist
            File folder = new File("plugins/moneymake");
            if (!folder.exists()) folder.mkdirs();

            // Create the config file
            SDAT data = new SDAT("plugins/moneymake/moneymake_config.yml");
            // if the file does not exist, create it with default values
            if (!data.exists()) {
                data.writeLine("gold_price: 1.0");
                data.writeLine("gap_price: 8.0");
                data.writeLine("egap_price: 72.0");
                data.writeLine("tax: 0.0");
                Bukkit.getLogger().info("Default config file created with values:");
            } else {
                // if it exists, read the config file
                goldPrice = data.getDouble(0);
                gapPrice = data.getDouble(1);
                egapPrice = data.getDouble(2);
                tax = data.getDouble(3);
                Bukkit.getLogger().info("Config file loaded with values:");

                Bukkit.getLogger().info("Loaded gold price: " + goldPrice);
                Bukkit.getLogger().info("Loaded gap price: " + gapPrice);
                Bukkit.getLogger().info("Loaded egap price: " + egapPrice);
                Bukkit.getLogger().info("Loaded tax: " + tax);
            }
    }
}
