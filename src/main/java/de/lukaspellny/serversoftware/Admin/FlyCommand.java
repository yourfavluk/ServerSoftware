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

package de.lukaspellny.serversoftware.Admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDieser Befehl kann nur von einem Spieler ausgeführt werden.");
            return true;
        }

        if (!sender.hasPermission("serversoftware.admin.fly")) {
            sender.sendMessage("§cDu hast keine Berechtigung für diesen Befehl.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0 && player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage("§cDu musst im Kreativmodus sein, um andere Spieler fliegen zu lassen.");
            return true;
        }

        if (args.length < 1) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage("§aFliegen deaktiviert.");
            } else {
                player.setAllowFlight(true);
                player.sendMessage("§aFliegen aktiviert.");
            }
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cSpieler nicht gefunden.");
            return true;
        }

        if (!target.isOnline()) {
            player.sendMessage("§cDieser Spieler ist nicht online.");
            return true;
        }

        if (player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage("§cDu musst im Kreativmodus sein, um andere Spieler fliegen zu lassen.");
            return true;
        }

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
            target.sendMessage("§cFliegen von " + player.getName() + " deaktiviert.");
            player.sendMessage("§aFliegen von " + target.getName() + " deaktiviert.");
        } else {
            target.setAllowFlight(true);
            target.sendMessage("§aFliegen von " + player.getName() + " aktiviert.");
            player.sendMessage("§aFliegen von " + target.getName() + " aktiviert.");
        }

        return true;
    }
}
