package com.github.morinoparty.kakuremi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class JoinListener implements Listener {

    Kakuremi kakuremi;

    public JoinListener(Kakuremi kakuremi) {
        this.kakuremi = kakuremi;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        player.setScoreboard(kakuremi.getScoreboard());
    }
}
