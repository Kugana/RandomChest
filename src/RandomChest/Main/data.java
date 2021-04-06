package RandomChest.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitTask;

public class data {

	public static String color(String input) {
		if (input.contains("&")) {
			return ChatColor.translateAlternateColorCodes('&', input);
		} else
			return input;

	}

	private static final RandomChest ab = (RandomChest) Bukkit.getPluginManager().getPlugin("RandomChest");
	private static BukkitTask animate;

	public static List<Block> getNearbyBlocks(Location location, int radius) {
		List<Block> blocks = new ArrayList<Block>();
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					blocks.add(location.getWorld().getBlockAt(x, y, z));
				}
			}
		}
		return blocks;
	}

	public static List<Location> getNearbyLocation(Location location, int radius) {
		List<Location> loc = new ArrayList<Location>();
		animate = Bukkit.getServer().getScheduler().runTaskAsynchronously(ab, new Runnable() {

			@Override
			public void run() {
				for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
					for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
						for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {

							Location newloc = new Location(location.getWorld(), x, y, z);

							if (newloc.subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
								if (newloc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
									if (newloc.getBlock().getType() == Material.AIR) {
										loc.add(newloc);
										// Bukkit.broadcastMessage(""+loc.size());
									}
								}
							}
						}
					}
				}
				animate.cancel();

			}
		});
		return loc;
	}

	static Location getRandomLocation(Location origin, double radius, boolean _3D) {
		Random r = new Random();
		double randomRadius = r.nextDouble() * radius;
		double theta = Math.toRadians(r.nextDouble() * 360);
		double phi = Math.toRadians(r.nextDouble() * 180 - 90);

		double x = randomRadius * Math.cos(theta) * Math.sin(phi);
		double y = randomRadius * Math.sin(theta) * Math.cos(phi);
		double z = randomRadius * Math.cos(phi);
		Location newLoc = origin.add(x, origin.getY(), z);
		if (_3D) {
			newLoc.add(0, y, 0);
		}
		return newLoc;
	}
}
