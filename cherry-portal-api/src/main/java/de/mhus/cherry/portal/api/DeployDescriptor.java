package de.mhus.cherry.portal.api;

import java.io.File;

import de.mhus.lib.core.MProperties;

public interface DeployDescriptor {

	public enum SPACE {PUBLIC,PRIVATE}

	String getName();
	File getPath(SPACE space);
	MProperties getConfiguration(SPACE space);
	
}