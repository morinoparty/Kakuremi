package com.github.morinoparty.kakuremi;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.function.Function;

import static org.bukkit.Bukkit.getPluginManager;

public final class Kakuremi extends JavaPlugin {

    private static Kakuremi instance;
    private static Scoreboard scoreboard;
    private static Team team;

    @Override
    public void onEnable() {
        instance = this;
        createScoreBoard();
        loadCommands();
        getPluginManager().registerEvents(new JoinListener(this), this);

        for (Player player : getServer().getOnlinePlayers()) {
            player.setScoreboard(this.getScoreboard());
        }
    }

    @Override
    public void onDisable() {
    }

    private void loadCommands() {
        PaperCommandManager<CommandSender> commandManager;
        try {
            commandManager = new PaperCommandManager<>(
                    this,
                    AsynchronousCommandExecutionCoordinator.<CommandSender>newBuilder().build(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        var annotationParser = new AnnotationParser<>(
                commandManager,
                CommandSender.class,
                parameters -> SimpleCommandMeta.empty()
        );

        annotationParser.parse(new KakuremiCommand(instance));
    }

    private void createScoreBoard() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        team = scoreboard.registerNewTeam("kakuremi");
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Team getTeam() {
        return team;
    }
}
