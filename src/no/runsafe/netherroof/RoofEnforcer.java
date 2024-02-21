package no.runsafe.netherroof;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.event.block.IBlockPlace;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.player.IPlayer;

import java.util.HashMap;

public class RoofEnforcer implements IBlockPlace, IConfigurationChanged
{
	@Override
	public boolean OnBlockPlace(IPlayer player, IBlock block)
	{
		String world = player.getWorldName();
		return !(limits.containsKey(world) && limits.get(world) < block.getLocation().getBlockY());
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		limits.clear();
		for (String world : configuration.getConfigValueAsList("worlds"))
			limits.put(world, configuration.getConfigValueAsInt(String.format("limits.%s", world)));
	}

	private final HashMap<String, Integer> limits = new HashMap<>();
}
