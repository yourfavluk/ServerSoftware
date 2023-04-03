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



package de.lukaspellny.serversoftware;

import de.lukaspellny.serversoftware.Admin.FlyCommand;
import de.lukaspellny.serversoftware.Admin.GamemodeCommand;
import de.lukaspellny.serversoftware.Admin.HealCommand;
import de.lukaspellny.serversoftware.Ban.BanCommand;
import de.lukaspellny.serversoftware.Ban.IPBanCommand;
import de.lukaspellny.serversoftware.Ban.TempbanCommand;
import de.lukaspellny.serversoftware.Ban.UnbanCommand;
import de.lukaspellny.serversoftware.Commands.CheckIDCommand;
import de.lukaspellny.serversoftware.Commands.IDCommand;
import de.lukaspellny.serversoftware.chat.spy;
import de.lukaspellny.serversoftware.savety.protectionA;
import de.lukaspellny.serversoftware.savety.protectionB;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Serversoftware extends JavaPlugin {

    public static String Verion = "1.0";
    @Override
    public void onEnable() {
        getLogger().info("Server Software wurde geladen.");

        // Listener:

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new protectionA(), this);
        pluginManager.registerEvents(new protectionB(), this);


        // Commands:

        getCommand("id").setExecutor(new IDCommand());
        getCommand("checkid").setExecutor(new CheckIDCommand());
        getCommand("spy").setExecutor(new spy());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("tempban").setExecutor(new TempbanCommand());
        getCommand("ipban").setExecutor(new IPBanCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("fly").setExecutor(new FlyCommand());



    }

    @Override
    public void onDisable() {
        getLogger().info("Server Software wurde entladen.");

    }
}
