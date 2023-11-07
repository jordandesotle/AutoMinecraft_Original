package jordandesotle.autominecraft.tools;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class FindObject {

    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    static final String debug = BOLD + RED + "(DEBUG) " + RESET;

    public static Block findBlock(Player player, String blockName) {

        return null;
    }

}
