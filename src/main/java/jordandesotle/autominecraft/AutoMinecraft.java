package jordandesotle.autominecraft;

import jordandesotle.autominecraft.commands.CustomCommands;
import jordandesotle.autominecraft.controller.PlayerController;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoMinecraft extends JavaPlugin {

    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerController(), this);
        getCommand("startBot").setExecutor(new CustomCommands());
        System.out.println(BOLD + RED + "Hello World!" + RESET);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

