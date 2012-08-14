package no.runsafe.netherroof;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.configuration.IConfigurationFile;

import java.io.InputStream;

public class Plugin extends RunsafePlugin implements IConfigurationFile
{
	@Override
	protected void PluginSetup()
	{
		addComponent(RoofEnforcer.class);
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
}
