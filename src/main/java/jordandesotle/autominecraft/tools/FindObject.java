package jordandesotle.autominecraft.tools;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class FindObject {

    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    static final String debug = BOLD + RED + "(DEBUG) " + RESET;

    public static Block findBlock(Player player, String blockName) {
        Player p = player;
        String playerName = p.getName();
        System.out.println(debug + "Player (" + playerName + ") wants to find a " + blockName + " block");
        return null;
    }

}
