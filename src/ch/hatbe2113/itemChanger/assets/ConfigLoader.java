package ch.hatbe2113.itemChanger.assets;

import org.bukkit.configuration.file.FileConfiguration;

import ch.hatbe2113.itemChanger.main.Main;

public class ConfigLoader {

	private Main main;
	
	public ConfigLoader(Main main) {
		this.main = main;
	}
	
	public void write(String root, String value) {
		
		FileConfiguration fc = main.getConfig();
		fc.addDefault(root, value);
		fc.options().copyDefaults(true);
		main.saveConfig();
		
	}
	
	public String read(String root) {
		
		String value;
		FileConfiguration fc = main.getConfig();
		
		value = fc.getString(root);
		
		return value;
		
	}
	
	
}
