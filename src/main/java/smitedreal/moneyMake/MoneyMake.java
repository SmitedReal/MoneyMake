package smitedreal.moneyMake;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import smitedreal.moneyMake.Goverment.SendToGoverment;
import smitedreal.moneyMake.Goverment.TotalMoney;

public final class MoneyMake extends JavaPlugin {

    public static Economy economy = null;

    @Override
    public void onEnable() {
        // Register commands and setup config
        ConfigManager.ConfigWork();
        TotalMoney.SetupTM();
        Bukkit.getPluginManager().registerEvents(new Prices(), this);
        //check if economy is set up
        if (!setupEconomy()) {
            getLogger().severe("Vault not found or no economy plugin found!");
            getLogger().severe("Check the dependencies of the MoneyMake plugin, or add another economy plugin!");
            throw new NullPointerException();
        }
        getCommand("sell").setExecutor(new Sell());
        getCommand("sell").setTabCompleter(new tabcompleter());
        getCommand("sendtogov").setExecutor(new SendToGoverment());
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
    public void onDisable() {}
}