package smitedreal.moneyMake;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigManager {
    public static float goldPrice; // the price from the config
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
                Bukkit.getLogger().warning("No file found, creating one, setting the price to default: 1.0");
                Files.writeString(file.toPath(), "gold_price: 1.0");
                goldPrice = 1.0f;
            } else {
                // File exists so read the gold price
                String content = Files.readString(file.toPath());
                String price = content.split(":")[1].trim();
                goldPrice = Float.parseFloat(price);
                Bukkit.getLogger().info(ChatColor.AQUA+"Loaded gold price from config: " + goldPrice);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
