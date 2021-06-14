package fr.skah.velocity;

/*
 *  * @Created on lundi/juin/2021 - 10:58
 *  * @Project VelocityCommands
 *  * @Author Jimmy
 */

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import fr.skah.velocity.listeners.PluginMessages;

import java.util.logging.Logger;

@Plugin(id = "velocitycommands", name = "Velocity Commands", version = "0.1.0-SNAPSHOT", description = "Add channel for excute commande from spigot", authors = {"vSKAH"})
public class VelocityCommands {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public VelocityCommands(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInit(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(MinecraftChannelIdentifier.create("velocity","commands"));
        server.getEventManager().register(this, new PluginMessages(this));
        logger.info("Velocity Commands is Loaded !");
    }

    public ProxyServer getServer() {
        return server;
    }
}