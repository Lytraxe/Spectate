package io.github.m0rv1n.spectate;

import io.github.m0rv1n.spectate.utils.SpectUtils;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.m0rv1n.spectate.command.SpectCommand;

import java.util.Objects;


public class Spectate extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        SpectUtils.loadArenas(this);
        Objects.requireNonNull(this.getCommand("spect")).setExecutor(new SpectCommand());
        getServer().getPluginManager().registerEvents(new SpectateListeners(), this);
    }

    @Override
    public void onDisable() {}
}
