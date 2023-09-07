package isaac.bastion.utils;

import isaac.bastion.BastionBlock;
import isaac.bastion.BastionType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import vg.civcraft.mc.civmodcore.utilities.TextUtil;

public class Broadcast {

	public static void warzoneBroadcast(BastionBlock bastion) {
		if (!bastion.getType().isCreateWarzone() || bastion.isMature()) {
			return;
		}

		long placed = bastion.getPlaced();
		long currentWarmedTime = System.currentTimeMillis() - placed;
		long finalWarmedTime = placed + bastion.getType().getWarmupTime();
		long warmedTimeLeft = finalWarmedTime - currentWarmedTime;
		String warmedTimeLeftStr = TextUtil.formatDuration(warmedTimeLeft);
		Location location = bastion.getLocation();
		Bukkit.getServer().broadcast(
				Component.text(ChatColor.RED + "[Bastion] Warzone creation in " + warmedTimeLeftStr
						+ " at [" + location.getBlockX() + "," + location.getBlockY() + "," +location.getBlockZ() + "]")
		);
	}

	public static void attackBastionBroadcast(BastionBlock bastion) {
		if (!bastion.getType().isDisableReinforcements() || bastion.isMature()) {
			return;
		}

		long placed = bastion.getPlaced();
		long currentWarmedTime = System.currentTimeMillis() - placed;
		long finalWarmedTime = placed + bastion.getType().getWarmupTime();
		long warmedTimeLeft = finalWarmedTime - currentWarmedTime;
		String warmedTimeLeftStr = TextUtil.formatDuration(warmedTimeLeft);
		Location location = bastion.getLocation();
		Bukkit.getServer().broadcast(
				Component.text(ChatColor.RED + "[Bastion] Attack Bastion goes active in " + warmedTimeLeftStr
						+ " at [" + location.getBlockX() + "," + location.getBlockY() + "," +location.getBlockZ() + "]")
		);
	}

}
