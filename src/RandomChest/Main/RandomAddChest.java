package RandomChest.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RandomAddChest extends data {

	private static final RandomChest ab = (RandomChest) Bukkit.getPluginManager().getPlugin("RandomChest");
	private static int animate;

	@SuppressWarnings("unlikely-arg-type")
	public static void addchest(Player p, int radiu, int interval, int amount) {
		List<Location> XYZ = new ArrayList<>();
		Location ploc = p.getLocation();
		List<Location> radiuloc = data.getNearbyLocation(ploc, radiu);
		File file = new File("plugins/FOLDoomDay/FOLDoomDay.yml");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(file);
		List<String> chestname = y.getStringList("ChestName.Name");

		animate = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ab, () -> {
			if (XYZ.size() != amount) {
				Random random = new Random();
				if (radiuloc.size() > 0) {
					int chestlocradom = random.nextInt(radiuloc.size());

					Location chestloc = radiuloc.get(chestlocradom);
					if (chestloc.getBlock().getType() == Material.AIR
							&& chestloc.subtract(0, 1, 0).getBlock().getType() != Material.AIR
							&& chestloc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
						if (chestloc.distance(ploc) <= radiu) {

							if (!getNearbyBlocks(chestloc, radiu).contains(Material.CHEST)) {
								XYZ.add(chestloc);
								chestloc.getBlock().setType(Material.CHEST);

								Chest chest = (Chest) chestloc.getBlock().getState();
								chest.getInventory().clear();
								String str = "Loot: ";
								String str1 = null;
								int lv;
								int n5 = random.nextInt(100);
								if (n5 < 50) {
									str1 = "&acommon ";
									lv = 1;
								} else if (n5 < 85) {
									str1 = "&egeneral";
										
									lv = 2;
								} else if (n5 < 97) {
									str1 = "&9rare";
									lv = 3;
								} else {
									str1 = "&5legend";
									lv = 4;
								}

								chest.setCustomName(data.color(str + str1));
								chest.update();
								String name = chestloc.getWorld().getName() + "&" + chestloc.getBlockX() + "&"
										+ chestloc.getBlockY() + "&" + chestloc.getBlockZ();
								y.set("Chest." + name + ".LV", lv);
								y.set("Chest." + name + ".World", chestloc.getWorld().getName());
								y.set("Chest." + name + ".X", chestloc.getBlockX());
								y.set("Chest." + name + ".Y", chestloc.getBlockY());
								y.set("Chest." + name + ".Z", chestloc.getBlockZ());
								y.set("ChestName.Name", chestname);
								chestname.add(name);
								try {
									y.save(file);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
					}
				}
			} else {

				p.sendMessage(data.color("&asuccessful add " + amount + " chest"));
				Bukkit.getScheduler().cancelTask(animate);
			}
		}, 0, 20);

	}

}
