package dev.phonis.fire.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import dev.phonis.fire.FireUser;
import org.bukkit.plugin.java.JavaPlugin;

public class ButtonLeverEvent implements Listener {

    public ButtonLeverEvent(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onButtonLeverEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clicked = event.getClickedBlock();

            if (clicked.getType() == Material.STONE_BUTTON || clicked.getType() == Material.WOOD_BUTTON) {
                Player player = event.getPlayer();
                FireUser user = FireUser.getUserFromPlayer(player);

                if (user.getButtonLocation() == null ||
                    !user.getButtonLocation().equals(clicked.getLocation())) {
                    user.setButtonLocation(clicked.getLocation());
                    player.sendMessage(
                        ChatColor.DARK_AQUA +
                            "Button set to " +
                            "X: " + user.getButtonLocation().getBlockX() + " " +
                            "Y: " + user.getButtonLocation().getBlockY() + " " +
                            "Z: " + user.getButtonLocation().getBlockZ() + " "
                    );
                }
            } else if (clicked.getType() == Material.LEVER) {
                Player player = event.getPlayer();
                FireUser user = FireUser.getUserFromPlayer(player);

                if (user.getLeverLocation() == null ||
                    !user.getLeverLocation().equals(clicked.getLocation())) {
                    user.setLeverLocation(clicked.getLocation());
                    player.sendMessage(
                        ChatColor.DARK_AQUA +
                            "Lever set to " +
                            "X: " + user.getLeverLocation().getBlockX() + " " +
                            "Y: " + user.getLeverLocation().getBlockY() + " " +
                            "Z: " + user.getLeverLocation().getBlockZ() + " "
                    );
                }
            }
        }
    }

}
