package fr.skah.velocity.listeners;

/*
 *  * @Created on lundi/juin/2021 - 10:58
 *  * @Project VelocityCommands
 *  * @Author Jimmy
 */

import com.google.common.io.ByteArrayDataInput;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import fr.skah.velocity.VelocityCommands;

public class PluginMessages {

    private final VelocityCommands velocityCommands;

    public PluginMessages(VelocityCommands velocityCommands) {
        this.velocityCommands = velocityCommands;
    }

    @Subscribe
    public void onReceivePluginMessage(PluginMessageEvent event) {
        if (event.getIdentifier().getId().equals("velocity:commands")) {
            ByteArrayDataInput dataInput = event.dataAsDataStream();
            String receveidString = dataInput.readUTF();
            String[] args = receveidString.split(" ");
            String withoutSender = receveidString.replace(args[0], "");

            if (args[0].equalsIgnoreCase("console")) {
                velocityCommands.getServer().getCommandManager().executeAsync(velocityCommands.getServer().getConsoleCommandSource(), withoutSender);
            } else {
                velocityCommands.getServer().getPlayer(args[0]).ifPresent(player -> velocityCommands.getServer().getCommandManager().executeAsync(player, withoutSender));
            }
        }
    }

}
