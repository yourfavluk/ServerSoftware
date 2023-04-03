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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class spy implements CommandExecutor {

    private final Set<UUID> spyPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            toggleSpy(sender);
        } else {
            sender.sendMessage("Nutze /spy");
        }
        return true;
    }

    private void toggleSpy(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("serversoftware.commandspy.use")) {
                UUID playerId = player.getUniqueId();
                if (spyPlayers.contains(playerId)) {
                    spyPlayers.remove(playerId);
                    player.sendMessage("§aCommandSpy deaktiviert.");
                } else {
                    spyPlayers.add(playerId);
                    player.sendMessage("§aCommandSpy aktiviert.");
                }
            } else {
                player.sendMessage("§cDu hast keine Berechtigung, um den CommandSpy zu verwenden.");
            }
        } else {
            sender.sendMessage("§cDieser Befehl kann nur von Spielern ausgeführt werden.");
        }
    }

    public void logCommand(Player player, String command) {
        if (!spyPlayers.isEmpty() && player != null && player.hasPermission("serversoftware.commandspy.use")) {
            String playerName = player.getName();
            for (UUID playerId : spyPlayers) {
                Player spyPlayer = Bukkit.getPlayer(playerId);
                if (spyPlayer != null) {
                    spyPlayer.sendMessage("§8[§7CommandSpy§8] §e" + playerName + "§8: §f" + command);
                }
            }
        }
    }

}