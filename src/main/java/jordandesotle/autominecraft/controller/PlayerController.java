package jordandesotle.autominecraft.controller;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerController implements Listener {

    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    static final String debug = BOLD + RED + "(DEBUG) " + RESET;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String playerName = p.getName();
        System.out.println(debug + "Player " + playerName + " has joined the server");

    }


}
