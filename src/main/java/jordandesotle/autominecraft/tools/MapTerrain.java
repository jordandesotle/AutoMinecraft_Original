package jordandesotle.autominecraft.tools;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public abstract class MapTerrain {

    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    static final String debug = BOLD + RED + "(DEBUG) " + RESET;

    /**
     *
     * @param player    The Player who sent the command
     * @param plug      The Plugin object for this server plugin
     */
    public static void mapFloor(Player player, Plugin plug) {

        Player p = player;
        String playerName = p.getName();
        Location playerLocation = p.getLocation();

        int offsetX;
        int offsetY = 0;
        int offsetZ = 0;
        int playerX = (int) playerLocation.getX() - 1;
        int playerY = (int) playerLocation.getY() - 1;
        int playerZ = (int) playerLocation.getZ() - 1;

        Block blockUnderPlayer = p.getWorld().getBlockAt(playerX, playerY, playerZ);
        Location blockUnderPlayerLocation = blockUnderPlayer.getLocation();
        String blockUnderPlayerName = blockUnderPlayer.getBlockData().getMaterial().name();

        /** PSEUDOCODE
         * span out 5 blocks in each direction from player
         * check to see if the block is air
         * if yes: move down 1 block and repeat the check
         * if no: check to see if there is air above block
         * if yes: player can move there - replace two air blocks with glass
         * if no: move up one block and check again
         * if yes: player can move there - replace with green glass
        */
        

        for (offsetX = -5; offsetX <= 5; offsetX++) {
            for (offsetZ = -5; offsetZ <= 5; offsetZ++) {
                Location offsetLocation = new Location(p.getWorld(), playerX + offsetX, playerY, playerZ + offsetZ);
                Block blockAtOffset = p.getWorld().getBlockAt(offsetLocation);

                Location oneBlockAboveLocation = p.getWorld().getBlockAt(offsetLocation.getBlockX(), offsetLocation.getBlockY() + 1, offsetLocation.getBlockZ()).getLocation();
                Location twoBlocksAboveLocation = p.getWorld().getBlockAt(offsetLocation.getBlockX(), offsetLocation.getBlockY() + 2, offsetLocation.getBlockZ()).getLocation();

                // check to see if block is air
                if(isAirBlock(blockAtOffset)) {
                    // check if there is a block 1 block down
                    if(isAirBlock(blockAtOffset.getRelative(0, -1, 0))) {
                        // no block one block down... do not move here for now
                        // add to don't move matrix
                        blockAtOffset.getRelative(0, -1, 0).setType(Material.RED_STAINED_GLASS);
                    } else {
                        // check for 3 air blocks above to see if player can move here
                        if(isSpace(p, offsetLocation)) {
                            // add to move matrix
                            blockAtOffset.setType(Material.GREEN_STAINED_GLASS);
                        } else {
                            // add to dont move matrix
                            blockAtOffset.setType(Material.RED_STAINED_GLASS);
                        }
                    }
                } else {
                    // block at same level as player.. check for space
                    if(isSpace(p, offsetLocation)) {
                        // add to move here matrix
                        blockAtOffset.setType(Material.GLOWSTONE);

                    } else {
                        // check for block one block up
                        if(blockAtOffset.getRelative(0, 1,1).getBlockData().getMaterial() != Material.AIR) {
                            // block at this location.. check for space above
                            if(isSpace(p, blockAtOffset.getRelative(0,1,0).getLocation())) {
                                // add to move here matrix
                                blockAtOffset.getRelative(0,1,0).setType(Material.GLOWSTONE);
                            } else {
                                // add to dont move here matrix
                                blockAtOffset.getRelative(0,1,0).setType(Material.RED_STAINED_GLASS);
                            }
                        }
                    }
                }




                if (isSurfaceBlock(blockAtOffset)) {
                    p.getWorld().getBlockAt(offsetLocation).setType(Material.GLOWSTONE);
                    System.out.println("Block is the highest block");
//                    p.getWorld().getBlockAt(oneBlockAboveLocation).setType(Material.GREEN_STAINED_GLASS);

                } else {

                    if(blockAtOffset.getBlockData().getMaterial().equals(Material.AIR)) {
                        //check up and down for block

                    }



                    String highestBlockName = p.getWorld().getHighestBlockAt(offsetLocation.getBlockX(), offsetLocation.getBlockZ()).getBlockData().getMaterial().name();
                    System.out.println(debug + "Highest block at X: " + offsetLocation.getX() + ", Z: " + offsetLocation.getZ() + " is " + highestBlockName);
                    System.out.println("Block is not the highest block");
                    if (isSpace(p, offsetLocation)) {

                        System.out.println("Enough space at location");
                        p.getWorld().getBlockAt(oneBlockAboveLocation).setType(Material.GREEN_STAINED_GLASS);
                        p.getWorld().getBlockAt(twoBlocksAboveLocation).setType(Material.GREEN_STAINED_GLASS);
                        //highlightAirBlocks(plug, oneBlockAboveLocation.getBlock().getLocation(), 1, Particle.VILLAGER_HAPPY, 1);
                        //highlightAirBlocks(plug, twoBlocksAboveLocation.getBlock().getLocation(), 1, Particle.VILLAGER_HAPPY, 1);
                    } else {
                        System.out.println("Not enough space at location");
                        Block oneAboveOffset = p.getWorld().getBlockAt(offsetLocation.getBlockX(), offsetLocation.getBlockY() + 1, offsetLocation.getBlockZ());
                        Block twoAboveOffset = p.getWorld().getBlockAt(offsetLocation.getBlockX(), offsetLocation.getBlockY() + 2, offsetLocation.getBlockZ());
                        if (oneAboveOffset.getBlockData().getMaterial().equals(Material.AIR)) {
                            //oneAboveOffset.setType(Material.RED_STAINED_GLASS);
                            highlightAirBlocks(plug, oneAboveOffset.getLocation());
                        } else if (twoAboveOffset.getBlockData().getMaterial().equals(Material.AIR)) {
                            //twoAboveOffset.setType(Material.RED_STAINED_GLASS);
                            highlightAirBlocks(plug, twoAboveOffset.getLocation());
                        }
                    }
                }
            }
        }
    }


    public static boolean isSpace(Player p, Location location) {

        Location oneBlockAboveLocation = p.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() + 1, location.getBlockZ()).getLocation();
        Location twoBlocksAboveLocation = p.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() + 2, location.getBlockZ()).getLocation();
        if(p.getWorld().getBlockAt(oneBlockAboveLocation).getBlockData().getMaterial() == Material.AIR && p.getWorld().getBlockAt(twoBlocksAboveLocation).getBlockData().getMaterial() == Material.AIR) {
            return true;
        }

        return false;
    }

    public static boolean isAirBlock(Block block) {
        if(block.getBlockData().getMaterial().equals(Material.AIR)) {
            return true;
        }
        return false;
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

    private static void highlightAirBlocks(Plugin plugin, Location location) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        // Set armor stand properties
        armorStand.setVisible(false); // Set to true if you want to see the armor stand
        armorStand.setSmall(true);    // Set to false if you want a larger hitbox

        // Customize the hitbox size
        armorStand.setSmall(false); // Larger hitbox
        armorStand.setGravity(false); // Disable gravity so it doesn't fall

        // Customize the border material
        armorStand.setHelmet(new org.bukkit.inventory.ItemStack(Material.GLOWSTONE_DUST));

        // Schedule removal after a certain duration (adjust as needed)
        Bukkit.getScheduler().runTaskLater(plugin, armorStand::remove, 40L);
    }





//
//        // System.out.println(debug + "Block under player: " + GREEN + blockUnderPlayerName + " -> X: " + playerX + ", Y: " + (playerY -1) + ", Z: " + playerZ + RESET);
//
////        if(isSurfaceBlock(blockUnderPlayer)) {
////            p.getWorld().getBlockAt(blockUnderPlayerLocation).setType(Material.DIAMOND_BLOCK);
////        } else {
////            System.out.println("Block under player is not the highest block");
////            String highestBlockName = p.getWorld().getHighestBlockAt(playerX, playerZ).getBlockData().getMaterial().name();
////            System.out.println(debug + "Highest block at X: " + playerX + ", Z: " + playerZ + " is " + highestBlockName);
////        }
//
//    }

}
