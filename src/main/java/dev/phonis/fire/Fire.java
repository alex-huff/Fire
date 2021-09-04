package dev.phonis.fire;

import dev.phonis.fire.event.ButtonLeverEvent;
import net.minecraft.server.v1_8_R3.BlockButtonAbstract;
import net.minecraft.server.v1_8_R3.BlockLever;
import net.minecraft.server.v1_8_R3.BlockPosition;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.plugin.java.JavaPlugin;

public class Fire extends JavaPlugin implements CommandExecutor {

    public void onEnable() {
        this.getCommand("fire").setExecutor(this);
        this.getCommand("lever").setExecutor(this);
        new ButtonLeverEvent(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FireUser user = FireUser.getUserFromSender(sender);

        if (cmd.getName().compareToIgnoreCase("fire") == 0) {
            if (user.getButtonLocation() == null) {
                sender.sendMessage(
                    ChatColor.DARK_AQUA +
                        "No button set."
                );

                return true;
            }

            user.getButtonLocation().getChunk().load();

            Block block = user.getButtonLocation().getBlock();

            if ((block.getType().equals(Material.STONE_BUTTON) || block.getType().equals(Material.WOOD_BUTTON))) {
                net.minecraft.server.v1_8_R3.World nmsWorld = ((CraftWorld) block.getWorld()).getHandle();
                BlockPosition blockPos = new BlockPosition(block.getX(), block.getY(), block.getZ());
                BlockButtonAbstract nmsBlock = (BlockButtonAbstract) nmsWorld.getType(blockPos).getBlock();

                nmsBlock.interact(nmsWorld, blockPos, nmsWorld.getType(blockPos), null, null, 0f, 0f, 0f);
                sender.sendMessage(
                    ChatColor.DARK_AQUA +
                        "Button pressed."
                );
            } else {
                sender.sendMessage(
                    ChatColor.DARK_AQUA +
                        "Unable to press button."
                );

                return true;
            }

            return true;
        }

        if (cmd.getName().compareToIgnoreCase("lever") == 0) {
            if (user.getLeverLocation() == null) {
                sender.sendMessage(
                    ChatColor.DARK_AQUA +
                        "No lever set."
                );

                return true;
            }

            Block block = user.getLeverLocation().getBlock();

            if (block.getType() == Material.LEVER) {
                net.minecraft.server.v1_8_R3.World nmsWorld = ((CraftWorld) block.getWorld()).getHandle();
                BlockPosition blockPos = new BlockPosition(block.getX(), block.getY(), block.getZ());
                BlockLever nmsBlock = (BlockLever) nmsWorld.getType(blockPos).getBlock();

                nmsBlock.interact(nmsWorld, blockPos, nmsWorld.getType(blockPos), null, null, 0f, 0f, 0f);
                sender.sendMessage(
                    ChatColor.DARK_AQUA +
                        "Lever flicked."
                );
            } else {
                sender.sendMessage(
                    ChatColor.DARK_AQUA +
                        "Unable to flick lever."
                );

                return true;
            }

            return true;
        }

        return false;
    }

}