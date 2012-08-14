package no.runsafe.netherroof;

import no.runsafe.framework.configuration.IConfiguration;
import no.runsafe.framework.event.IConfigurationChanged;
import no.runsafe.framework.event.block.IBlockPlaceEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.block.RunsafeBlockPlaceEvent;

import java.util.HashMap;

public class RoofEnforcer implements IBlockPlaceEvent, IConfigurationChanged
{
	public RoofEnforcer(IConfiguration configuration, IOutput output)
	{
		this.config = configuration;
		this.console = output;
	}

	@Override
	public void OnBlockPlaceEvent(RunsafeBlockPlaceEvent event)
	{
		String world = event.getPlayer().getWorld().getName();
		if (limits.containsKey(world) && limits.get(world) < event.getBlockPlaced().getLocation().getBlockY())
			event.setCancelled(true);
	}

	@Override
	public void OnConfigurationChanged()
	{
		limits.clear();
		for (String world : config.getConfigValueAsList("worlds"))
			limits.put(world, config.getConfigValueAsInt(String.format("limits.%s", world)));
	}

	private final IConfiguration config;
	private final IOutput console;
	private final HashMap<String, Integer> limits = new HashMap<String, Integer>();
}
