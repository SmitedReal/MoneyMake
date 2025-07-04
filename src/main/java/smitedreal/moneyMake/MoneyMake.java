package smitedreal.moneyMake;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoneyMake extends JavaPlugin {

    public static Economy economy = null;

    @Override
    public void onEnable() {
        // Register commands and setup config
        new ConfigManager().ConfigWork();
        Bukkit.getPluginManager().registerEvents(new Prices(), this);
        //check if economy is set up
        if (!setupEconomy()) {
            getLogger().severe("Vault not found or no economy plugin found!");
        }
        getCommand("sell").setExecutor(new Sell());
        getCommand("sell").setTabCompleter(new tabcompleter());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    }

