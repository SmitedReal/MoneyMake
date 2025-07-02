package smitedreal.moneyMake;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoneyMake extends JavaPlugin {
/*
    Hey, if you are reading this source code, this plugin just simply turns gold into in-game money using Vault
*/

    public static Economy economy = null;

    @Override
    public void onEnable() {
        new ConfigManager().ConfigWork();
        Bukkit.getPluginManager().registerEvents(new Prices(), this);
        if (!setupEconomy()) {
            getLogger().severe("Vault not found or no economy plugin found!");
        }
        getCommand("sell").setExecutor(new Sell());
        getCommand("sell").setTabCompleter(new tabcompleter());
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            getLogger().severe("ECONOMY CLASS not found");
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

