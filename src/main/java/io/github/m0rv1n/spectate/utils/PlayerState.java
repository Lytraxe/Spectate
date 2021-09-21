package io.github.m0rv1n.spectate.utils;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerState {

    private final Player p;
    private boolean flight;
    private Location location;
    private ItemStack[] inventory;
    private ItemStack[] armorItems;
    private GameMode gamemode;


    public PlayerState(Player player) {
        p = player;
    }

    public void savePlayer() {
        if(p != null) {
            flight = p.getAllowFlight();
            location = p.getLocation();
            inventory = p.getInventory().getContents();
            armorItems = p.getInventory().getArmorContents();
            gamemode = p.getGameMode();
        }
    }

    public void restorePlayer() {
        if(p != null) {
            //-- Restore Inventory
            p.getInventory().setArmorContents(armorItems);
            p.getInventory().setContents(inventory);
            //-- Restore GameMode
            p.setGameMode(gamemode);
            //-- Restore Flight
            if (flight) {
                p.setAllowFlight(true);
                p.setFlying(true);
            }
            //-- Restore Location
            p.teleport(location);
            p.updateInventory();
        }
    }

    public Player getPlayer() {
        return p;
    }
}