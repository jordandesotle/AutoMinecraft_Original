package jordandesotle.autominecraft;

import jordandesotle.autominecraft.commands.CustomCommands;
import jordandesotle.autominecraft.controller.PlayerController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoMinecraft extends JavaPlugin {

    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    Plugin autoMinecraftPlugin;

    @Override
    public void onEnable() {

        autoMinecraftPlugin = this;

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerController(), autoMinecraftPlugin);
        getCommand("startBot").setExecutor(new CustomCommands(autoMinecraftPlugin));
        getCommand("findBlock").setExecutor(new CustomCommands(autoMinecraftPlugin));
        getCommand("mapFloor").setExecutor(new CustomCommands(autoMinecraftPlugin));
        System.out.println(BOLD + RED + "Hello World!" + RESET);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

