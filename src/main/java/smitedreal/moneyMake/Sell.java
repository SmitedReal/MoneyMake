package smitedreal.moneyMake;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // If command sender not a player, dont do anything
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        } else {
            //define player
            Player player = (Player) sender;
            //if no args, send usage
            if (args.length == 0) {
                sender.sendMessage("Usage: /sell <hand|all>");
            } else if (args.length == 1) {
                // if args 0 is hand of all
                if (args[0].equalsIgnoreCase("hand")) {
                    // item in main hand
                    ItemStack item = player.getInventory().getItemInMainHand();
                    // check if player is holding an item
                    if (item.getType() == Material.GOLD_INGOT) {
                        item.setType(Material.AIR); // remove gold ingot
                        for (int i = 0; i < item.getAmount(); i++) {
                            MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice); // deposit 1 money to player per gold
                        }
                    } else {
                        player.sendMessage("You are not holding any gold.");
                    }
                    player.sendMessage("You sold the gold in your hand.");
                } else if (args[0].equalsIgnoreCase("all")) {
                    // if args 0 is all, sell all items in inventory
                    for (int i = 0; i < player.getInventory().getSize(); i++) {
                        // check if item in slot is gold
                        if (player.getInventory().getItem(i).getType().equals(Material.GOLD_INGOT)) {
                            player.getInventory().getItem(i).setType(Material.AIR); // remove gold ingot
                            for (int y = 0; i < player.getInventory().getItem(i).getAmount(); i++) {
                                MoneyMake.economy.depositPlayer(player, ConfigManager.goldPrice); // deposit 1 money to player per gold
                            }
                        }
                    }
                    player.sendMessage("You sold all the gold in your inventory.");
                } else {
                    // if its not anything else than hand of all, its wrong
                    sender.sendMessage("Invalid argument. Use 'hand' or 'all'.");
                }
            }
            return true;
        }
    }
}
