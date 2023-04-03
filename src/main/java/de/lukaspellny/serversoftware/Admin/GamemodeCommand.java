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

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDieser Befehl kann nur von einem Spieler ausgeführt werden.");
            return true;
        }

        if (!sender.hasPermission("serversoftware.admin.gamemode")) {
            sender.sendMessage("§cDu hast keine Berechtigung für diesen Befehl.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§cVerwendung: /gamemode <Modus> [Spieler]");
            return true;
        }

        GameMode gameMode;
        String modeName = args[0].toLowerCase();

        switch (modeName) {
            case "0":
            case "survival":
            case "s":
                gameMode = GameMode.SURVIVAL;
                break;
            case "1":
            case "creative":
            case "c":
                gameMode = GameMode.CREATIVE;
                break;
            case "2":
            case "adventure":
            case "a":
                gameMode = GameMode.ADVENTURE;
                break;
            case "3":
            case "spectator":
            case "sp":
                gameMode = GameMode.SPECTATOR;
                break;
            default:
                player.sendMessage("§cUngültiger Spielmodus.");
                return true;
        }

        if (args.length > 1) {
            if (!player.hasPermission("serversoftware.admin.gamemode.others")) {
                player.sendMessage("§cDu hast keine Berechtigung, den Spielmodus anderer Spieler zu ändern.");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage("§cSpieler nicht gefunden.");
                return true;
            }

            if (!target.isOnline()) {
                player.sendMessage("§cDieser Spieler ist nicht online.");
                return true;
            }

            target.setGameMode(gameMode);
            target.sendMessage("§aDein Spielmodus wurde von " + player.getName() + " auf " + gameMode.toString().toLowerCase() + " geändert.");
            player.sendMessage("§aDer Spielmodus von " + target.getName() + " wurde auf " + gameMode.toString().toLowerCase() + " geändert.");

        } else {
            player.setGameMode(gameMode);
            player.sendMessage("§aSpielmodus geändert auf " + gameMode.toString().toLowerCase() + ".");
        }

        return true;
    }
}
