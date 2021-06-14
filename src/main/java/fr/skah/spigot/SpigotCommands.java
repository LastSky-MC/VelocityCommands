package fr.skah.spigot;

/*
 *  * @Created on lundi/juin/2021 - 11:33
 *  * @Project VelocityCommands
 *  * @Author Jimmy
 */

import fr.skah.spigot.commands.ExecuteCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SpigotCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "velocity:commands");
        Objects.requireNonNull(getServer().getPluginCommand("execute")).setExecutor(new ExecuteCommand(this));
    }
}
