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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TempbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("serversystem.ban.tempban")) {
            sender.sendMessage("§cDu hast keine Berechtigung für diesen Befehl.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /tempban <Spieler> <Dauer in Minuten>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cSpieler nicht gefunden.");
            return true;
        }

        int duration = Integer.parseInt(args[1]);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, duration);

        target.kickPlayer("§cDu wurdest temporär gebannt.");
        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "§cDu wurdest temporär gebannt.", calendar.getTime(), sender.getName());
        Bukkit.broadcastMessage("§a" + target.getName() + " §7wurde von §a" + sender.getName() + " §7für §c" + duration + " Minuten §7temporär gebannt.");
        return true;
    }
}
