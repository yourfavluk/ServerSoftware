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

import de.lukaspellny.serversoftware.generations.IDGenerator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerID = IDGenerator.getPlayerID(player.getName());
            if (sender instanceof Player){

                if (player.hasPermission("system.permission.id"));
                    player.sendMessage("Dein ID ist: " + playerID + " (Generiert)");

            } else {
                player.sendMessage("Dazu hast du keine Rechte :P");
            }




        return false;
    }
}
