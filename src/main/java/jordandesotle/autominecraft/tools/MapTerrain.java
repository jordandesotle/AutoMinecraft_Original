package jordandesotle.autominecraft.tools;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public abstract class MapTerrain {

    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    static final String debug = BOLD + RED + "(DEBUG) " + RESET;

    public static void mapFloor(Player player) {
        Player p = player;
        String playerName = p.getName();

        Location playerLocation = p.getLocation();
        int offsetX = 0;
        int offsetY = 0;
        int offsetZ = 0;
        int playerX = (int) playerLocation.getX() - 1;
        int playerY = (int) playerLocation.getY();
        int playerZ = (int) playerLocation.getZ() - 1;

        Block blockUnderPlayer = p.getWorld().getBlockAt(playerX, playerY -1, playerZ);
        Location blockUnderPlayerLocation = blockUnderPlayer.getLocation();
        String blockUnderPlayerName = blockUnderPlayer.getBlockData().getMaterial().name();



        System.out.println(debug + "Block under player: " + GREEN + blockUnderPlayerName + " -> X: " + playerX + ", Y: " + (playerY -1) + ", Z: " + playerZ + RESET);

        if(isSurfaceBlock(blockUnderPlayer)) {
            p.getWorld().getBlockAt(blockUnderPlayerLocation).setType(Material.DIAMOND_BLOCK);
        } else {
            System.out.println("Block under player is not the highest block");
            String highestBlockName = p.getWorld().getHighestBlockAt(playerX, playerZ).getBlockData().getMaterial().name();
            System.out.println(debug + "Highest block at X: " + playerX + ", Z: " + playerZ + " is " + highestBlockName);
        }


    }

    public static boolean isSurfaceBlock(Block block) {
        int blockX = block.getX();
        int blockZ = block.getZ();
        BlockData data = block.getBlockData();
        if(data.equals(block.getWorld().getHighestBlockAt(blockX, blockZ).getBlockData())) {
            System.out.println("Blocks are the same!");
            return true;

        }
        System.out.println("Blocks are not the same :(");
        return false;
    }



}
