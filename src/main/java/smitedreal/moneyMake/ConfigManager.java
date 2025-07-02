package smitedreal.moneyMake;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigManager {
    // prices for other classes
    public static double goldPrice = 1.0;
    public static double gapPrice = 8.0;
    public static double egapPrice = 72.0;
    public void ConfigWork() {
        try {
            // Create the folder if it doesnt exist
            File folder = new File("plugins/moneymake");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Create the config file inside the folder
            File file = new File(folder, "moneymake_config.yml");

            if (file.createNewFile()) {
                // File didnt exist so write default config
                Bukkit.getLogger().info("Created new config file with default gold price: " + goldPrice);
                // make the gold price
                Files.writeString(file.toPath(), "gold_price: 1.0");
                goldPrice = 1.0f;
                // gold apple and enchanted golden apple prices
                Files.writeString(file.toPath(), "gap_price: 8.5");
                Files.writeString(file.toPath(), "egap_price: 1.0");
            } else {
                // File exists so read the gold price
                String content = Files.readString(file.toPath());
                String price = content.split(":")[1].trim();
                goldPrice = Double.parseDouble(price);
                Bukkit.getLogger().info("Loaded gold price from config: " + goldPrice);
                // Read other prices
                String gapPriceStr = content.split(":")[2].trim();
                String egapPriceStr = content.split(":")[3].trim();
                gapPrice = Double.parseDouble(gapPriceStr);
                egapPrice = Double.parseDouble(egapPriceStr);
                Bukkit.getLogger().info("Loaded gap price from config: " + gapPrice);
                Bukkit.getLogger().info("Loaded egap price from config: " + egapPrice);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
