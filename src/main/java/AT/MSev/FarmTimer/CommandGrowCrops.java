package AT.MSev.FarmTimer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGrowCrops implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FarmTimer.GrowAllStated();
        ((Player)commandSender).sendMessage(ChatColor.GREEN + "Crops have been grown.");
        return true;
    }
}
