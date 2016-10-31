package de.mhus.cherry.renderer.jsp;

import java.io.File;

public class JspDefaultServletContext extends DefaultServletContext {
	
	// http://www.e-pde.gr/docs/jasper-howto.html
	public JspDefaultServletContext(File root ) {
		super(root);
//		File tmp = new File( host.getTmpRoot(), "jsp");
//		tmp.mkdirs();
		param.put("scratchdir", "/tmp" );
		param.put("keepgenerated", "true");
		param.put("enablePooling", "false");
	}
	
}
