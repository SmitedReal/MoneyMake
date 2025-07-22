package smitedreal.moneyMake.Goverment;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import smitedreal.moneyMake.MoneyMake;
import smitedreal.moneyMake.Sell;

public class SendToGoverment implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        String prefix = Sell.prefix;
        String ver = Sell.ver;

        // If command sender not a player, dont do anything
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!(args.length == 1)) {
                Double govMoney = Double.parseDouble(args[0]);
                MoneyMake.economy.withdrawPlayer(player, govMoney);
                TotalMoney.AddMoney(govMoney);
            } else {
                player.sendMessage(prefix + "Usage: /stg <amount>");
            }
            return true;
        } else {
            sender.sendMessage(prefix + "This command can only be used by players.");
            return true;
        }
    }
}
