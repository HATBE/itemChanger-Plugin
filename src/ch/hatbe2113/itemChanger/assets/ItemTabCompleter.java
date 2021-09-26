package ch.hatbe2113.itemChanger.assets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ItemTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			List<String> keys = new ArrayList<>();
			if(cmd.getName().equalsIgnoreCase("ichange")) {
				if(args.length == 1) {
					keys.add("alore");
					keys.add("clore");
					keys.add("elore");
					keys.add("rlore");
					keys.add("rename");
					keys.add("help");
				} 
			}
			
			if(keys.size() > 0) {
				return keys;
			}
		
		}
		
		return null;
	}

}
