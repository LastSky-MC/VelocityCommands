package fr.skah.spigot.commands;

/*
 *  * @Created on lundi/juin/2021 - 11:36
 *  * @Project VelocityCommands
 *  * @Author Jimmy
 */

import fr.skah.spigot.SpigotCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ExecuteCommand implements CommandExecutor {


    private final SpigotCommands instance;

    public ExecuteCommand(SpigotCommands instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seulement les joueurs peuvent utiliser cette commande !");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("/execute <Playername:Console> <Command>");
            return true;
        }

        if (args[0].equalsIgnoreCase("console") && !sender.hasPermission("velocity.commands.console")) return false;

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : args) stringBuilder.append(string).append(" ");
        forwardString(stringBuilder.toString(), (Player) sender);
        return true;
    }


    private void forwardString(String string, Player player) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF(string);
            player.sendPluginMessage(instance, "velocity:commands", b.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
