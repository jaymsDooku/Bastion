package isaac.bastion.listeners;

import isaac.bastion.Bastion;
import isaac.bastion.BastionBlock;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WarzoneListener implements Listener {

	private Set<UUID> inWarzone = new HashSet<>();

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent pme) {
		Location to = pme.getTo();
		if (to == null) {
			return;
		}
		Location from = pme.getFrom();
		if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY()
				&& from.getBlockZ() == to.getBlockZ() && from.getWorld().equals(to.getWorld())) {
			return;
		}

		boolean warzonePresent = inWarzone(to);

		Player player = pme.getPlayer();
		if (inWarzone.contains(player.getUniqueId())) {
			if (!warzonePresent) {
				inWarzone.remove(player.getUniqueId());
				player.sendMessage(ChatColor.RED + "You have left the Warzone!");
			}
		} else {
			if (warzonePresent) {
				inWarzone.add(player.getUniqueId());
				player.sendMessage(ChatColor.RED + "You have entered the Warzone!");
			}
		}
	}

	public static boolean inWarzone(Location location) {
		Set<BastionBlock> bastionBlocks = Bastion.getBastionManager().getBlockingBastions(location);
		for (BastionBlock bastion : bastionBlocks) {
			if (bastion.isMature() && bastion.getType().isCreateWarzone()) {
				return true;
			}
		}
		return false;
	}

}
