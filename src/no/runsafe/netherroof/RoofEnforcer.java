package no.runsafe.netherroof;

import no.runsafe.framework.configuration.IConfiguration;
import no.runsafe.framework.event.IConfigurationChanged;
import no.runsafe.framework.event.block.IBlockPlace;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class RoofEnforcer implements IBlockPlace, IConfigurationChanged
{
	public RoofEnforcer(IOutput output)
	{
		this.console = output;
	}

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

	private final IOutput console;
	private final HashMap<String, Integer> limits = new HashMap<String, Integer>();
}
