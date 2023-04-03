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

package de.lukaspellny.serversoftware.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class RateLimit implements Listener {

    private Plugin plugin;
    private Map<Player, Long> lastMessageTimes;

    public void RateLimit(Plugin plugin) {
        this.plugin = plugin;
        lastMessageTimes = new HashMap<>();
    }

    public void RateLimite(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("serversoftware.chat.bypass")) {
            long currentTime = System.currentTimeMillis();

            if (lastMessageTimes.containsKey(player) && currentTime - lastMessageTimes.get(player) < 3000) {
                event.setCancelled(true);
                player.sendMessage("Du kannst nur alle 3 Sekunden eine Nachricht senden!");
            }

            lastMessageTimes.put(player, currentTime);
        }
    }
}