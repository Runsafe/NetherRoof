package no.runsafe.netherroof;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.features.Events;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void PluginSetup()
	{
		addComponent(Events.class);
		addComponent(RoofEnforcer.class);
	}
}
