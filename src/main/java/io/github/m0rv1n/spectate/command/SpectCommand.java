package io.github.m0rv1n.spectate.command;

import io.github.m0rv1n.spectate.utils.PlayerState;
import io.github.m0rv1n.spectate.utils.SpectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SpectCommand implements CommandExecutor {

    //-- Might look kinda messy, but It works...
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if(args.length >= 1) {
                //-- Exit SubCommand
                if (args[0].equalsIgnoreCase("exit") && p.hasPermission("spectate.use") && SpectUtils.getPlayerState(p) != null) {
                    p.sendMessage(SpectUtils.OKAY + "Teleporting back...");
                    SpectUtils.exitSpectate(p);
                    Objects.requireNonNull(SpectUtils.getPlayerState(p)).restorePlayer();
                    SpectUtils.pStates.remove(SpectUtils.getPlayerState(p));
                }
                //-- Add SubCommand
                else if (args[0].equalsIgnoreCase("add") && args.length == 2 && p.hasPermission("spectate.admin")) {
                    SpectUtils.saveArena(args[1], p.getLocation());
                    p.sendMessage(SpectUtils.OKAY + "Arena added successfully");
                }
                //-- Remove SubCommand
                else if (args[0].equalsIgnoreCase("remove") && args.length == 2 && p.hasPermission("spectate.admin ")) {
                    if(SpectUtils.removeArena(args[1]))
                        p.sendMessage(SpectUtils.OKAY + "Arena removed successfully");
                    else
                        p.sendMessage(SpectUtils.NOTOKAY + "Oh no.. Something went wrong..");
                }

                //-- List SubCommand
                else if (args[0].equalsIgnoreCase("list")) {
                    if (SpectUtils.getArenaNames() != null)
                        p.sendMessage(SpectUtils.OKAY + "Currently available arenas : " + SpectUtils.getArenaNames());
                    else
                        p.sendMessage(SpectUtils.NOTOKAY + "No arenas are available to join at the moment..");
                }
                //-- Teleport to arena, if it exists
                else {
                    if (SpectUtils.checkArena(args[0])) {
                        if (SpectUtils.getPlayerState(p) == null && p.hasPermission("spectate.use")) {
                            SpectUtils.pStates.add(new PlayerState(p));
                            Objects.requireNonNull(SpectUtils.getPlayerState(p)).savePlayer();
                            p.sendMessage(SpectUtils.OKAY + "Teleporting to the arena...");
                            SpectUtils.teleportToSpectate(p, args[0]);
                        } else
                            p.sendMessage(SpectUtils.NOTOKAY + "You are already spectating...");
                    } else {
                        p.sendMessage(SpectUtils.NOTOKAY + "Please Enter a valid arena name. Use /spect list to see available arenas. ");
                    }
                }
                return true;
            }
        }
        return false;
    }
}
