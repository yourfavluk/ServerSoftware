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

package de.lukaspellny.serversoftware.Ban;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IPBanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("serversystem.ban.ipban")) {
            sender.sendMessage("§cDu hast keine Berechtigung für diesen Befehl.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("§cUsage: /ipban <Spieler>");
            return true;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage("§cDieser Spieler ist nicht online.");
            return true;
        }

        String targetAddress = target.getAddress().getHostString();
        Bukkit.getBanList(BanList.Type.IP).addBan(targetAddress, "Du wurdest von diesem Server gebannt.", null, sender.getName());
        target.kickPlayer("§cDu wurdest von diesem Server gebannt.");

        Bukkit.broadcastMessage("§a" + targetName + " §7und seine Adresse §a" + targetAddress + " §7wurden von §a" + sender.getName() + " §7gebannt.");
        return true;
    }
}
