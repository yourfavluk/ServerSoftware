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

package de.lukaspellny.serversoftware.generations;

import java.util.HashMap;
import java.util.Random;


public class IDGenerator {
    private static HashMap<String, String> playerIDs = new HashMap<String, String>();

    public static String generatePlayerID(String playerName) {
        String playerID = "";
        String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            playerID += characters.charAt(rand.nextInt(characters.length()));
        }
        playerIDs.put(playerName, playerID);
        return playerID;
    }

    public static String getPlayerID(String playerName) {
        if (playerIDs.containsKey(playerName)) {
            return playerIDs.get(playerName);
        } else {
            String playerID = generatePlayerID(playerName);
            return playerID;
        }
    }
}
