package io.github.m0rv1n.spectate;

import io.github.m0rv1n.spectate.utils.SpectUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Objects;

public class SpectateListeners implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        //-- Restore disconnected player if he was spectating
        if (SpectUtils.getPlayerState(e.getPlayer()) != null) {
            SpectUtils.exitSpectate(e.getPlayer());
            Objects.requireNonNull(SpectUtils.getPlayerState(e.getPlayer())).restorePlayer();
            SpectUtils.pStates.remove(SpectUtils.getPlayerState(e.getPlayer()));
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        //-- Disable "Spectator Teleport" (the hotbar thing)
        //-- Players with permission "spectator.admin" can bypass this
        if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.SPECTATE) && !e.getPlayer().hasPermission("spectate.admin")){
            if (SpectUtils.getPlayerState(e.getPlayer()) != null) {
                e.setCancelled(true);
            }
        }
    }
}
