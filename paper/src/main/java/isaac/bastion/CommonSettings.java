/**
 * Created by Aleksey on 16.07.2017.
 */

package isaac.bastion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class CommonSettings {
	private boolean cancelReinforcementModeInBastionField;
	private int listBastionTimeout;
	private long warzoneBroadcastInterval;
	private Set<Material> cancelPlacementAndDamage;

	public boolean isCancelReinforcementModeInBastionField() {
		return this.cancelReinforcementModeInBastionField;
	}

	public int getListBastionTimeout() {
		return this.listBastionTimeout;
	}

	public long getWarzoneBroadcastInterval() {
		return warzoneBroadcastInterval;
	}

	public Set<Material> getCancelPlacementAndDamage() {
		return this.cancelPlacementAndDamage;
	}

	public static CommonSettings load(ConfigurationSection config) {
		CommonSettings settings = new CommonSettings();
		settings.cancelReinforcementModeInBastionField = config.getBoolean("cancelReinforcementModeInBastionField", false);
		settings.listBastionTimeout = config.getInt("listBastionTimeout", 2000);
		settings.warzoneBroadcastInterval = config.getLong("warzoneBroadcastInterval", 1800000);
		List<String> materialNames = config.getStringList("cancelPlacementAndDamage");
		settings.cancelPlacementAndDamage = new HashSet<>();
		for (String name : materialNames) {
			Material m = Material.matchMaterial(name);
			if (m != null) {
				settings.cancelPlacementAndDamage.add(m);
			}
		}
		return settings;
	}
}
