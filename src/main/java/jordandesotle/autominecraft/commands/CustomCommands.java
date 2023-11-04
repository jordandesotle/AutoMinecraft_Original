package jordandesotle.autominecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CustomCommands implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("startBot")) {

            if(args.length == 0) {
                System.out.println("startBot command excecuted");

            }

        }


        return false;
    }
}
