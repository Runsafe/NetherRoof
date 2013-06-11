package no.runsafe.netherroof;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.block.IBlockPlace;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.block.RunsafeBlock;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.HashMap;

public class RoofEnforcer implements IBlockPlace, IConfigurationChanged
{
	@Override
	public boolean OnBlockPlace(RunsafePlayer player, RunsafeBlock block)
	{
		String world = player.getWorld().getName();
		return !(limits.containsKey(world) && limits.get(world) < block.getLocation().getBlockY());
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		limits.clear();
		for (String world : configuration.getConfigValueAsList("worlds"))
			limits.put(world, configuration.getConfigValueAsInt(String.format("limits.%s", world)));
	}

	private final HashMap<String, Integer> limits = new HashMap<String, Integer>();
}
