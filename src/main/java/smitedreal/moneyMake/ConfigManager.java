package smitedreal.moneyMake;

import org.bukkit.Bukkit;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class ConfigManager {
    public static double goldPrice = 1.0;
    public static double gapPrice = 8.0;
    public static double egapPrice = 72.0;
    public static double tax = 0.0;

    public void ConfigWork() {
        try {
            File folder = new File("plugins/moneymake");
            if (!folder.exists()) folder.mkdirs();

            File file = new File(folder, "moneymake_config.yml");

            if (file.createNewFile()) {
                Bukkit.getLogger().info("Created new config file with default values.");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("gold_price: 1.0"); writer.newLine();
                    writer.write("gap_price: 8.0"); writer.newLine();
                    writer.write("egap_price: 72.0"); writer.newLine();
                    writer.write("tax: 0.0"); writer.newLine();
                }
            } else {
                List<String> lines = Files.readAllLines(file.toPath());
                for (String line : lines) {
                    String[] parts = line.split(":");
                    if (parts.length != 2) continue;
                    String key = parts[0].trim();
                    double value = Double.parseDouble(parts[1].trim());
                    switch (key) {
                        case "gold_price" -> goldPrice = value;
                        case "gap_price" -> gapPrice = value;
                        case "egap_price" -> egapPrice = value;
                        case "tax" -> tax = value;
                    }
                }

                Bukkit.getLogger().info("Loaded gold price: " + goldPrice);
                Bukkit.getLogger().info("Loaded gap price: " + gapPrice);
                Bukkit.getLogger().info("Loaded egap price: " + egapPrice);
                Bukkit.getLogger().info("Loaded tax: " + tax);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
