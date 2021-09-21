package io.github.m0rv1n.spectate.utils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SpectUtils {
    //-- Chat prefixes
    public static final String OKAY = ChatColor.translateAlternateColorCodes('&', "&7[&e!!&7] &3Spectate &7[&e!!&7] &b ");
    public static final String NOTOKAY = ChatColor.translateAlternateColorCodes('&', "&7[&e!!&7] &3Spectate &7[&e!!&7] &c ");


    static Plugin _plugin;

    public static List<String> arenaNames;
    static Arena[] arenas;

    public static List<PlayerState> pStates = new ArrayList<>();

    public static boolean checkArena(String arena) {
        //-- Check if the requested arena exists or not
        return (arenaNames.contains(arena));
    }

    //-- Teleport to the saved arena's location
    public static void teleportToSpectate(Player player, String name) {
        Arena arena = getArena(name);
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(arena.getLocation());
    }

    public static void exitSpectate(Player player) {
        //-- Why does this thing exist ?
    }

    //-- Get the PlayerState from the List
    public static PlayerState getPlayerState(Player p) {
        if (pStates.size() != 0) {
            for (PlayerState pState : pStates) {
                if (pState.getPlayer().getName().equalsIgnoreCase(p.getName()))
                    return pState;
            }
        }
        return null;
    }

    //-- Get the arena from the List
    static Arena getArena(String name) {
        for (int i = 0; i <= arenas.length; i++) {
            if (arenas[i].getName().equalsIgnoreCase(name))
                return arenas[i];
        }
        return null;
    }

    //-- Return available arena names
    public static String getArenaNames () {
        if (arenaNames.size() != 0) {
            StringBuilder str = new StringBuilder(" ");
            for (String arenaName : arenaNames) {
                str.append(arenaName).append(", ");
            }
            return str.toString();
        }
        return null;
    }

    //-- Load Arenas from config.yml file
    public static void loadArenas(Plugin plugin) {
        _plugin = plugin;
        arenaNames = null;
        arenaNames = new ArrayList<String>();
        arenaNames.addAll(plugin.getConfig().getKeys(false));
        arenas = null;
        arenas = new Arena[arenaNames.size()];
        if(arenaNames.size() > 0) {
            for (int i = 0; i < arenaNames.size(); i++) {
                arenas[i] = new Arena(arenaNames.get(i), plugin.getConfig().getLocation(arenaNames.get(i)));
            }
        }
    }

    //-- Save arena to config.yml file. Arena name is the key and the Location as the value
    public static void saveArena(String name, Location location) {
        _plugin.getConfig().set(name, location);
        _plugin.saveConfig();
        loadArenas(_plugin);
    }

    //-- Delete the requested arena from config.yml
    public static boolean removeArena(String name) {
        if(checkArena(name)) {
            _plugin.getConfig().set(name, null);
            _plugin.saveConfig();
            loadArenas(_plugin);
            return true;
        }
        return false;
    }
}

//-- Arena Class
class Arena {
    String _name;
    Location _location;

    public Arena(String name, Location location){
        this._name = name;
        this._location = location;
    }

    public String getName() {
        return _name;
    }

    public Location getLocation() {
        return _location;
    }
}
