/*
 * Copyright (c) 2023 Lukas Pellny (ServerSoftware)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 */


package de.lukaspellny.serversoftware.savety;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class protectionB implements Listener {

    private Plugin plugin;
    private Set<UUID> cheaters;

    public protectionB() {
        this.plugin = plugin;
        this.cheaters = new HashSet<>();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (cheaters.contains(player.getUniqueId())) {
            event.setJoinMessage(null);
            player.kickPlayer("Bitte verwende keine Clientmodifikationen ...");
            return;
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                boolean isCheater = player.getListeningPluginChannels().contains("MC|Brand")
                        && player.getListeningPluginChannels().contains("MC|BSign")
                        && player.getListeningPluginChannels().contains("MC|BOpen")
                        && player.getListeningPluginChannels().contains("MC|TrList")
                        && player.getListeningPluginChannels().contains("REGISTER")
                        && player.getListeningPluginChannels().contains("FML");
                if (isCheater) {
                    event.setJoinMessage(null);
                    player.kickPlayer("Bitte verwende keine Clientmodifikationen ...");
                    cheaters.add(player.getUniqueId());
                }
            }
        }.runTaskLater(plugin, 20L);
    }
}
