package jordandesotle.autominecraft.commands;

import jordandesotle.autominecraft.tools.FindObject;
import jordandesotle.autominecraft.tools.MapTerrain;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class CustomCommands implements CommandExecutor {

    Plugin myPlugin;

    public CustomCommands(Plugin p) {
        myPlugin = p;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("startBot")) {

            if(args.length == 1) {
                String name = args[0];
                System.out.println("Creating bot: " + name);
//                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
//                npc.spawn(sender.getServer().getPlayer(sender.getName()).getLocation());
                System.out.println("startBot command excecuted");

            } else {
                sender.sendMessage("/startBot <botName>");
            }

        }

        if(command.getName().equals("moveNPCToPlayer")) {
            if(args.length == 1) {

            }
        }

        if(command.getName().equals("findBlock")) {

            if(args.length == 1) {
                System.out.println("startBot command excecuted");
                FindObject.findBlock(sender.getServer().getPlayer(sender.getName()), args[0]);

            } else {
                System.out.println("Incorrect usage of findBlock");
            }

        }

        if(command.getName().equals("mapFloor")) {

            if(args.length == 0) {
                MapTerrain.mapFloor(sender.getServer().getPlayer(sender.getName()), myPlugin);
            } else {
                System.out.println("Incorrect usage of mapFloor");
            }

        }


        return false;
    }
}
