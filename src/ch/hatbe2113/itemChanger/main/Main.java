package ch.hatbe2113.itemChanger.main;


import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import ch.hatbe2113.itemChanger.assets.ConfigLoader;
import ch.hatbe2113.itemChanger.assets.ItemTabCompleter;
import ch.hatbe2113.itemChanger.commands.IChangeCommand;

public class Main extends JavaPlugin {
	
	// Version vom 15.10.2020
	
	private ConfigLoader configLoader = new ConfigLoader(this);
	
	public static String PREFIX;
	public static String NO_PERMS;
	public static String NO_CONSOLE;
	public static String[] ench;
	
	public static String colors[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "r", "R"};
	
	public void onEnable() {
		
		init();
		
	}
	
	public void onDisable() {
		
		shutdownMessage();
		
	}
	
	public void init() {
		
		createConfig();	
		readConfig();
		startupMessage();
		addCommands();
		addListeners();
		createEnchArray();
		
	}
	
	private void startupMessage() {
		
		System.out.println("");
		System.out.println("---------------------------------");
		System.out.println("");
		System.out.println(PREFIX + ChatColor.WHITE + "Startup"); 
		System.out.println("");
		System.out.println("---------------------------------");
		System.out.println("");
		
	}
	
	private void shutdownMessage() {
		
		System.out.println("");
		System.out.println("---------------------------------");
		System.out.println("");
		System.out.println(PREFIX + ChatColor.WHITE + "Shutdown"); 
		System.out.println("");
		System.out.println("---------------------------------");
		System.out.println("");
		
	}
	
	private void addCommands() {
		
		getCommand("ichange").setExecutor(new IChangeCommand());
		getCommand("ichange").setTabCompleter(new ItemTabCompleter());
		
	}
	
	private void addListeners() {
		
		// No Listeners
		
	}
	
	private void createConfig() {
		
		configLoader.write("prefix", "§8[§9itemChanger§8] §r"); // PREFIX
		configLoader.write("noPerms", "Du hast keine Rechte diesen Command auszuführen!"); // NO_PERMS
		configLoader.write("noConsole", "Nur Spieler können diesen Command ausführen!"); // NO_PERMS
		
	}

	private void readConfig() {
		
		Main.PREFIX = configLoader.read("prefix");
		Main.NO_PERMS = PREFIX + configLoader.read("noPerms");
		Main.NO_CONSOLE = PREFIX + ChatColor.RED + configLoader.read("noConsole");
		
	}
	
	private void createEnchArray() {
		String[] array = null;
		
		
		Main.ench = array;
		
	}
	
}
