package dev.phonis.fire;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FireUser {

    public static HashMap<UUID, FireUser> users = new HashMap<>();

    private Location buttonLocation = null;
    private Location leverLocation = null;

    public Location getButtonLocation() {
        return this.buttonLocation;
    }

    public void setButtonLocation(Location buttonLocation) {
        this.buttonLocation = buttonLocation;
    }

    public Location getLeverLocation() {
        return this.leverLocation;
    }

    public void setLeverLocation(Location leverLocation) {
        this.leverLocation = leverLocation;
    }

    public static FireUser getUserFromSender(CommandSender sender) {
        Player player = (Player) sender;

        return getUserFromPlayer(player);
    }

    public static FireUser getUserFromPlayer(Player player) {
        if (users.containsKey(player.getUniqueId())) return users.get(player.getUniqueId());

        FireUser newUser = new FireUser();

        users.put(player.getUniqueId(), newUser);

        return newUser;
    }

}