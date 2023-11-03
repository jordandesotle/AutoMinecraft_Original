package jordandesotle.autominecraft;

import jordandesotle.autominecraft.controller.PlayerController;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoMinecraft extends JavaPlugin {

    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerController(), this);
        System.out.println("Hello World");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
