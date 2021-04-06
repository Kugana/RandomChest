package RandomChest.Main;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import RandomChest.Main.RandomChest;

public class RandomChest extends JavaPlugin {
	static RandomChest plugin;

	@Override
	public void onEnable() {
		plugin = this;
		Command();
	}

	@Override
	public void onDisable() {

	}

	public Plugin getInstance() {
		return plugin;

	}
	
	public void Command() {
		getCommand("rac").setExecutor(new ChestCommand());
	}

}
