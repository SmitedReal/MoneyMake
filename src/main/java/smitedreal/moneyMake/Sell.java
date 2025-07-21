package smitedreal.moneyMake;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String prefix = ChatColor.YELLOW+""+ChatColor.BOLD+"[Money Make]: "+ChatColor.RESET; // prefix for messages
        String ver = "Running Version: 1.2"; // version of the plugin
        int z = 0;
        // If command sender not a player, dont do anything
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix+ChatColor.DARK_RED+"This command can only be used by players.");
            return true;
        } else {
            //define player
            Player player = (Player) sender;
            //if no args, send usage
            if (args.length == 0) {
                sender.sendMessage(prefix+ChatColor.RED+"Usage: /sell <hand|all>");
            } else if (args.length == 1) {
                // if args 0 is hand of all
                if (args[0].equalsIgnoreCase("hand")) {
                    // item in main hand
                    ItemStack item = player.getInventory().getItemInMainHand();
                    // check if player is holding gold
                    if (item.getType() == Material.GOLD_INGOT) {
                        for (int i = 0; i < item.getAmount(); i++) {
                            MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice - ConfigManager.tax); // deposit 1 money to player per gold
                        }
                        item.setAmount(-1);
                        player.sendMessage(prefix+"You sold the gold in your hand.");
                    } else if (item.getType().equals(Material.GOLD_NUGGET)) {
                        for (int i = 0; i < item.getAmount(); i++) {
                            MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice / 9 - ConfigManager.tax); // deposit 1 money to player per gold nugget
                        }
                        item.setAmount(-1);
                        player.sendMessage(prefix+"You sold the gold nugget in your hand.");
                    } else if (item.getType().equals(Material.GOLD_BLOCK)) {
                        for (int i = 0; i < item.getAmount(); i++) {
                            MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice * 9 - ConfigManager.tax); // deposit 9 money to player per gold block
                        }
                        item.setAmount(-1);
                        player.sendMessage(prefix+"You sold the gold block in your hand.");
                    } else if (item.getType().equals(Material.GOLDEN_APPLE)) {
                        for (int i = 0; i < item.getAmount(); i++) {
                            MoneyMake.economy.depositPlayer(player, ConfigManager.gapPrice - ConfigManager.tax); // deposit 8 money to player per golden apple
                        }
                        item.setAmount(-1);
                        player.sendMessage(prefix+"You sold the golden apple in your hand.");
                    } else if (item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
                        for (int i = 0; i < item.getAmount(); i++) {
                            MoneyMake.economy.depositPlayer(player, ConfigManager.egapPrice - ConfigManager.tax); // deposit 72 money to player per enchanted golden apple
                        }
                        item.setAmount(-1);
                        player.sendMessage(prefix+"You sold the enchanted golden apple in your hand.");

                    }
                    else {
                        player.sendMessage(prefix+ChatColor.RED+"You are not holding any gold.");
                    }
                } else if (args[0].equalsIgnoreCase("all")) {
                    // if args 0 is all, sell all items in inventory
                    for (int i = 0; i < player.getInventory().getSize(); i++) {
                        ItemStack item = player.getInventory().getItem(i);
                        // check if item in slot is gold
                        if (item == null || item.getType() == Material.AIR) {
                            continue; // skip empty slots
                        }
                        if (item.getType().equals(Material.GOLD_INGOT)) {
                            for (int y = 0; y < item.getAmount(); y++) {
                                z++;
                                MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice - ConfigManager.tax); // deposit 1 money to player per gold
                            }
                            item.setAmount(-1);

                        } else if (item.getType().equals(Material.GOLD_NUGGET)) {
                            for (int y = 0; y < item.getAmount(); y++) {
                                z++;
                                MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice / 9 - ConfigManager.tax); // deposit 1 money to player per gold nugget
                            }
                            item.setAmount(-1);
                        } else if (item.getType().equals(Material.GOLD_BLOCK)) {
                            for (int y = 0; y < item.getAmount(); y++) {
                                z += 9;
                                MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice * 9 - ConfigManager.tax); // deposit 9 money to player per gold block
                            }
                            item.setAmount(-1);
                        }
                        else if (item.getType().equals(Material.GOLDEN_APPLE)) {
                            for (int y = 0; y < item.getAmount(); y++) {
                                z += 8;
                                MoneyMake.economy.depositPlayer(player, ConfigManager.gapPrice - ConfigManager.tax); // deposit 8 money to player per golden apple
                            }
                            item.setAmount(-1);
                        } else if (item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
                            for (int y = 0; y < item.getAmount(); y++) {
                                z += 72;
                                MoneyMake.economy.depositPlayer(player, ConfigManager.egapPrice - ConfigManager.tax); // deposit 72 money to player per enchanted golden apple
                            }
                            item.setAmount(-1);
                        }
                        else {
                            // if item is not gold, do nothing
                            continue;
                        }

                    }
                    if (z == 0) {
                        player.sendMessage(prefix+ChatColor.RED + "You have no gold in your inventory.");
                    } else {
                        player.sendMessage(prefix+"You sold " + z + " gold from your inventory.");
                    }

                } else {
                    // if its not anything else than hand of all, its wrong
                    sender.sendMessage(prefix+ChatColor.RED+"Invalid argument. Use 'hand' or 'all'.");
                }
            } else if (args[0].equalsIgnoreCase("ver")) {
                // if args 0 is ver, send version
                sender.sendMessage(prefix+ChatColor.GREEN+ver);
            } else {
                // if args 0 is not hand or all or ver, send usage
                sender.sendMessage(prefix+ChatColor.RED+"Usage: /sell <hand|all|ver>");
            }
            return true;
        }
    }
}
