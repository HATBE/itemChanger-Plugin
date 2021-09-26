package ch.hatbe2113.itemChanger.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.hatbe2113.itemChanger.main.Main;

public class IChangeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			ItemStack is = p.getInventory().getItemInMainHand();
			
			if(args.length > 0) {
				// HELP EXEPTION
				// Check if Current "Item" is not null or AIR
				if(!args[0].equalsIgnoreCase("help")) {
					if(is == null) {
						p.sendMessage(Main.PREFIX + ChatColor.RED + "Dieser befehl ist nur für Items!");
						return false;
					}
					if(is.getType() == Material.AIR) {
						p.sendMessage(Main.PREFIX + ChatColor.RED + "Dieser befehl ist nur für Items!");
						return false;
					}
				}
				// -------------------------------------------
				// RENAME // rename item
				// -------------------------------------------
				if(args[0].equalsIgnoreCase("help")) {
					p.sendMessage("");
					p.sendMessage(ChatColor.AQUA + "ItemChanger");
					p.sendMessage(ChatColor.WHITE + "by Hatbe2113");
					p.sendMessage(ChatColor.DARK_GRAY + "/ic rename <name>" + ChatColor.GRAY + " -> Nennt Item um.");
					p.sendMessage(ChatColor.DARK_GRAY + "/ic alore <text>" + ChatColor.GRAY + " -> Fügt Lore hinzu.");
					p.sendMessage(ChatColor.DARK_GRAY + "/ic clore " + ChatColor.GRAY + " -> Löscht alle Lores.");
					p.sendMessage(ChatColor.DARK_GRAY + "/ic rlore <line>" + ChatColor.GRAY + " -> Löscht eine Lore.");
					p.sendMessage(ChatColor.DARK_GRAY + "/ic elore <line> <text>" + ChatColor.GRAY + " -> Bearbeitet eine Lore.");
					p.sendMessage("");
				} else if(args[0].equalsIgnoreCase("rename")) {
					
					if(p.hasPermission("ichange.rename")) {
						
						if(args.length >= 2) {
							
							// Fetch Message
							String msg = "";
							for (int i = 1; i < args.length; i++) {
								msg += args[i]  + " ";
							}
							
							// Create Color Codes
							for (int i = 0; i < Main.colors.length; i++) {
								msg = msg.replaceAll("&" + Main.colors[i], "§" + Main.colors[i].toLowerCase());
							}
							
							if(msg.length() > 255) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Zu viele Zeichen!");
								return false;
							}
							
							ItemMeta im = is.getItemMeta();
							im.setDisplayName(msg);
							is.setItemMeta(im);
							
							p.sendMessage(Main.PREFIX + ChatColor.GREEN + "Titel erfolgreich geändert.");
							
						} else {
							p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange rename <name>" + ChatColor.RED + "!");
						}
						
					} else {
						p.sendMessage(Main.NO_PERMS);
					}
					
					// -------------------------------------------
					// ALORE // Add Lore to item
					// -------------------------------------------
				} else if(args[0].equalsIgnoreCase("alore")) {
					
					if(p.hasPermission("ichange.lore")) {
						
						if(args.length >= 2) {
						
							List<String> itemLore = new ArrayList<String>();
							ArrayList<String> newItemLore = new ArrayList<String>();
							
							// Fetch Message
							String msg = "";
							for (int i = 1; i < args.length; i++) {
								msg += args[i]  + " ";
							}
							
							// Create Color Codes
							for (int i = 0; i < Main.colors.length; i++) {
								msg = msg.replaceAll("&" + Main.colors[i], "§" + Main.colors[i].toLowerCase());
							}
							
							if(msg.length() > 255) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Zu viele Zeichen!");
								return false;
							}
							
							ItemMeta im = is.getItemMeta();
							
							itemLore = im.getLore();
							
							if(itemLore != null) {
								if(itemLore.size() >= 32) {
									p.sendMessage(Main.PREFIX + ChatColor.RED + "Zu viele Lores!");
									return false;
								}
								
								itemLore.add(msg);
								im.setLore(itemLore);
								
							} else {
								
								newItemLore.add(msg);
								im.setLore(newItemLore);
								
							}
							
							is.setItemMeta(im);
							
							p.sendMessage(Main.PREFIX + ChatColor.GREEN + "Lore erfolgreich hinzugfügt.");
							
						} else {
							p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange alore <text>" + ChatColor.RED + "!");
						}
						
					} else {
						p.sendMessage(Main.NO_PERMS);
					}
					
					// -------------------------------------------
					// CLORE // Clear Lore from item
					// -------------------------------------------
				} else if(args[0].equalsIgnoreCase("clore")) {
					
					if(p.hasPermission("ichange.lore")) {
						
						if(args.length == 1) {
						
							List<String> itemLore;
							
							ItemMeta im = is.getItemMeta();
							
							itemLore = im.getLore();
							
							if(itemLore == null) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Dieses item hat noch keine Lore!");
								return false;
							}
							
							itemLore.clear();
							
							im.setLore(itemLore);
							is.setItemMeta(im);
							
							p.sendMessage(Main.PREFIX + ChatColor.GREEN + "Lores erfolgreich gelöscht");
							
						} else {
							p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange clore" + ChatColor.RED + "!");
						}
						
					} else {
						p.sendMessage(Main.NO_PERMS);
					}
					
					// -------------------------------------------
					// RLORE // Remove line Lore from item
					// -------------------------------------------
				} else if(args[0].equalsIgnoreCase("rlore")) {
					
					if(p.hasPermission("ichange.lore")) {
						
						if(args.length == 2) {
						
							List<String> itemLore;
							int number;
							
							ItemMeta im = is.getItemMeta();
							
							itemLore = im.getLore();
							
							if(itemLore == null) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Dieses item hat noch keine Lore!");
								return false;
							}
										
							try {
								number = Integer.parseInt(args[1]) - 1;
							} catch (Exception e) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze eine Zahl!");
								number = 1;
								return false;
							}
							
							if(number < 0 || number >= 32) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze eine Zahl zwischen " + ChatColor.DARK_RED + "1 und 32 " + ChatColor.RED + "!");
								return false;
							}
							
							if((number + 1) > itemLore.size()) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Diese Lore existiert nicht, bitte wähle eine Nummer zwischen " + ChatColor.DARK_RED + "0 und " + itemLore.size() + ChatColor.RED + "!");
								return false;
							}
							
							itemLore.remove(number);
							
							im.setLore(itemLore);
							is.setItemMeta(im);
							
							p.sendMessage(Main.PREFIX + ChatColor.GREEN + "Lore erfolgreich gelöscht");
							
						} else {
							p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange rlore <line>" + ChatColor.RED + "!");
						}
						
					} else {
						p.sendMessage(Main.NO_PERMS);
					}
					
					// -------------------------------------------
					// ELORE // Edit line Lore from item
					// -------------------------------------------
				} else if(args[0].equalsIgnoreCase("elore")) {
					
					if(p.hasPermission("ichange.lore")) {
						
						if(args.length == 3) {
							
							// Fetch Message
							String msg = "";
							for (int i = 2; i < args.length; i++) {
								msg += args[i]  + " ";
							}
							
							// Create Color Codes
							for (int i = 0; i < Main.colors.length; i++) {
								msg = msg.replaceAll("&" + Main.colors[i], "§" + Main.colors[i].toLowerCase());
							}
							
							if(msg.length() > 255) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Zu viele Zeichen!");
								return false;
							}
						
							List<String> itemLore;
							int number;
							
							ItemMeta im = is.getItemMeta();
							
							itemLore = im.getLore();
							
							if(itemLore == null) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Dieses item hat noch keine Lore!");
								return false;
							}
										
							try {
								number = Integer.parseInt(args[1]) - 1;
							} catch (Exception e) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze eine Zahl!");
								number = 1;
								return false;
							}
							
							if(number < 0 || number >= 32) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze eine Zahl zwischen " + ChatColor.DARK_RED + "1 und 32 " + ChatColor.RED + "!");
								return false;
							}
							
							if((number + 1) > itemLore.size()) {
								p.sendMessage(Main.PREFIX + ChatColor.RED + "Diese Lore existiert nicht, bitte wähle eine Nummer zwischen " + ChatColor.DARK_RED + "0 und " + itemLore.size() + ChatColor.RED + "!");
								return false;
							}
							
							itemLore.set(number, msg);
							
							im.setLore(itemLore);
							is.setItemMeta(im);
							
							p.sendMessage(Main.PREFIX + ChatColor.GREEN + "Lores erfolgreich gelöscht");
							
						} else {
							p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange elore <line> <text>" + ChatColor.RED + "!");
						}
						
					} else {
						p.sendMessage(Main.NO_PERMS);
					}
					
				} else {
					p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange <cmd> [args]" + ChatColor.RED + "!");
				}
			} else {
				p.sendMessage(Main.PREFIX + ChatColor.RED + "Bitte benutze " + ChatColor.DARK_RED + "/ichange <cmd> [args]" + ChatColor.RED + "!");
			}
			
		} else {
			sender.sendMessage(Main.NO_CONSOLE);
		}
		
		return false;
	}

}
