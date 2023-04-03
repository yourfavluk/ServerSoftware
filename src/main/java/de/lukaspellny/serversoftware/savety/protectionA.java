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
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class protectionA implements Listener {

    public final Map<String, String> playerIPs = new HashMap<>();

    // Spieler Hinzufügen zur Hashmap
    public final void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        java.lang.String playerName = player.getName();
        java.lang.String playerIP = player.getAddress().getAddress().getHostAddress();

        if (playerIPs.containsValue(playerIP)) {
            event.setJoinMessage("");
            player.kickPlayer("§cDu darfst nur mit einem Account online sein.");


        } else {
            playerIPs.put(playerName, playerIP);

        }


    }

    // Entferne den Spieler von der Hashmap

    public final void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String playerName = player.getName();
        playerIPs.remove(playerName);

    }
}

