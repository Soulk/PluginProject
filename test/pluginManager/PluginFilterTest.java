package pluginManager;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import plugins.Plugin;

public class PluginFilterTest {

	PluginLoader pluginLoader;
	File dir;
	PluginFilter pluginFilter;
	String falseFileName, trueFileName, noExtensionFileName, extensionFileName,
			wrongPackageFilename, noEmptyConstructor;

	@Before
	public void setUp() throws Exception {
		pluginLoader = new PluginLoader();
		dir = new File("bin/plugins");
		pluginFilter = new PluginFilter();
		falseFileName = "falseFileName.class";
		trueFileName = "ToUpperCase.class";
		noExtensionFileName = "noExtensionFileName";
		extensionFileName = "ADotClassName.class";
		wrongPackageFilename = "TestPluginPackage.class";
	}

	@Test
	public void getPluginClassWithExistingFileTest()
			throws ClassNotFoundException {
		Class<?> classLoad = pluginLoader.loadClass("plugins.ToUpperCase");
		assertEquals(classLoad, pluginFilter.getClassFromFilename(trueFileName));
	}

	@Test
	public void checkEmptyConstructorTest() throws ClassNotFoundException {
		Class<Plugin> plugin = pluginFilter.getClassFromFilename(trueFileName);
		assertTrue(pluginFilter.checkConstructor(plugin));
	}

	@Test
	public void checkInterfaceTest() throws ClassNotFoundException {
		Class<Plugin> plugin = pluginFilter.getClassFromFilename(trueFileName);
		assertTrue(pluginFilter.checkInterface(plugin));
	}

	@Test
	public void checkNoInterfaceTest() {
		Class<Plugin> plugin = pluginFilter
				.getClassFromFilename(wrongPackageFilename);
		assertFalse(pluginFilter.checkInterface(plugin));
	}

	@Test
	public void acceptWithAnExistingPluginTest() {
		assertTrue(pluginFilter.accept(dir, trueFileName));
	}

	@Test
	public void acceptWithANonDotClassFileNameTest() {
		assertFalse(pluginFilter.accept(dir, noExtensionFileName));
	}

	@Test
	public void acceptWithAPluginWrongPackageTest() {
		assertFalse(pluginFilter.accept(dir, wrongPackageFilename));
	}

	@Test
	public void acceptAFalseFile() {
		assertFalse(pluginFilter.accept(dir, falseFileName));
	}
}
