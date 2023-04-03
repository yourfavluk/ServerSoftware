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

package de.lukaspellny.serversoftware.points;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class SpawnManager {

    private Map<String, SpawnPoint> spawnPoints;

    public SpawnManager() {
        spawnPoints = new HashMap<>();
    }

    public void setSpawnPoint(String name, Location location) {
        spawnPoints.put(name, new SpawnPoint(location));
    }

    public SpawnPoint getSpawnPoint(String name) {
        return spawnPoints.get(name);
    }

    public void removeSpawnPoint(String name) {
        spawnPoints.remove(name);
    }

}
