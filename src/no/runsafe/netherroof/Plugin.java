package no.runsafe.netherroof;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.configuration.IConfiguration;
import no.runsafe.framework.configuration.IConfigurationFile;
import no.runsafe.framework.event.IConfigurationChanged;
import no.runsafe.framework.event.block.IBlockPlaceEvent;
import no.runsafe.framework.server.event.block.RunsafeBlockPlaceEvent;

import java.io.InputStream;
import java.util.HashMap;

public class Plugin extends RunsafePlugin implements IBlockPlaceEvent, IConfigurationFile, IConfigurationChanged
{
	@Override
	protected void PluginSetup()
	{
	}

	@Override
	public void OnBlockPlaceEvent(RunsafeBlockPlaceEvent event)
	{
		String world = event.getPlayer().getWorld().getName();
		if(limits.containsKey(world) && limits.get(world) < event.getBlock().getLocation().getBlockY())
			event.setCancelled(true);
	}

	@Override
	public String getConfigurationPath()
	{
		return "plugins/" + getName() + "/config.yml";
	}

	@Override
	public InputStream getDefaultConfiguration()
	{
		return getResource("defaults.yml");
	}

	@Override
	public void OnConfigurationChanged()
	{
		IConfiguration config = getComponent(IConfiguration.class);
		limits.clear();
		for(String world : config.getConfigValueAsList("worlds"))
			limits.put(world, config.getConfigValueAsInt(String.format("limits.%s", world)));
	}

	private final HashMap<String, Integer> limits = new HashMap<String, Integer>();
}
