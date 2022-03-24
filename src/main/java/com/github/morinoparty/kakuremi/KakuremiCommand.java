package com.github.morinoparty.kakuremi;

import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandMethod("kakuremi|hide")
public class KakuremiCommand {

    static Kakuremi plugin;

    public KakuremiCommand(Kakuremi plugin) {
        this.plugin = plugin;
    }

    @CommandMethod("")
    @CommandPermission("kakuremi.hide")
    private void hide(Player player) {

        var team = plugin.getTeam();
        var mm = MiniMessage.miniMessage();

        if (!team.hasPlayer(player)) {
            team.addPlayer(player);
            var parsed = mm.deserialize("<gray>自分のネームタグを非表示にしました");
            player.sendMessage(parsed);
        } else {
            team.removePlayer(player);
            var parsed = mm.deserialize("<gray>自分のネームタグを表示しています");
            player.sendMessage(parsed);
        }
    }

    @CommandMethod("hideall")
    @CommandPermission("kakuremi.hide.all")
    private void hideAll(CommandSender sender) {

        var team = plugin.getTeam();

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            team.addPlayer(player);
        }

        var mm = MiniMessage.miniMessage();
        var parsed = mm.deserialize("<gray>すべてのプレイヤーのネームタグを非表示にしました");

        sender.sendMessage(parsed);
    }

    @CommandMethod("showall")
    @CommandPermission("kakuremi.hide.all")
    private void showAll(CommandSender sender) {

        var team = plugin.getTeam();

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            team.removePlayer(player);
        }

        var mm = MiniMessage.miniMessage();
        var parsed = mm.deserialize("<gray>すべてのプレイヤーのネームタグを表示しています");

        sender.sendMessage(parsed);
    }
}
