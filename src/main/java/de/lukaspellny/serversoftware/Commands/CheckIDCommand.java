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

package de.lukaspellny.serversoftware.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.lukaspellny.serversoftware.generations.IDGenerator;

public class CheckIDCommand implements CommandExecutor {

    private final String permission = "serversoftware.checkid";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dieser Befehl kann nur von Spielern ausgeführt werden!");
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage("Du hast keine Berechtigung, diesen Befehl zu verwenden!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Bitte gib einen Spielernamen an!");
            return true;
        }

        String playerName = args[0];
        String playerID = IDGenerator.getPlayerID(playerName);

        if (playerID == null) {
            sender.sendMessage("Dieser Spieler hat keine ID.");
            return true;
        }

        sender.sendMessage("Die ID von " + playerName + " lautet: " + playerID);
        return true;
    }
}
