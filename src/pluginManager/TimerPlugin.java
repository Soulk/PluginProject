package pluginManager;

import javax.swing.Timer;

/**
 * 
 * @author David Fitoussi & Simon Decottignies
 * 
 *         Timer count second
 *
 */
public class TimerPlugin {
	PluginFinder plug;

	public TimerPlugin(PluginFinder plug) {
		// 1000 = delay
		new Timer(1000, plug).start();

	}
}
