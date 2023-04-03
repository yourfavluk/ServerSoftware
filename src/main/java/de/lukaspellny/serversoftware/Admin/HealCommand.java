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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Dieser Befehl kann nur von Spielern verwendet werden.");
                return true;
            }
            Player player = (Player) sender;
            player.setHealth(player.getMaxHealth());
            player.sendMessage("Deine Gesundheit wurde vollständig wiederhergestellt.");
            return true;
        } else if (args.length == 1) {
            if (!sender.hasPermission("serversoftware.admin.heal")) {
                sender.sendMessage("Du hast keine Berechtigung, diesen Befehl auf andere Spieler anzuwenden.");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("Der Spieler " + args[0] + " ist nicht online.");
                return true;
            }
            target.setHealth(target.getMaxHealth());
            target.sendMessage("Deine Gesundheit wurde von " + sender.getName() + " vollständig wiederhergestellt.");
            sender.sendMessage("Du hast die Gesundheit von " + target.getName() + " vollständig wiederhergestellt.");
            return true;
        } else {
            sender.sendMessage("Falsche Verwendung! Benutze /heal oder /heal <Spielername>");
            return true;
        }
    }
}
