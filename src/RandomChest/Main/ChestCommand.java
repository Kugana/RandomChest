package RandomChest.Main;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class ChestCommand implements TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 3) {
				// radiu interval amount
				if (args[0] != null) {
					if (args[1] != null) {
						if (args[2] != null) {
							RandomAddChest.addchest(p, Integer.valueOf(args[0]), Integer.valueOf(args[1]),
									Integer.valueOf(args[2]));
							return true;
						}
					}
				}
			}
		} else {
			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return null;
	}

}
