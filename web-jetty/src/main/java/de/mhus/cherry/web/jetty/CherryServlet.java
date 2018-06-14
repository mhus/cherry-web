package de.mhus.cherry.web.jetty;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aQute.bnd.annotation.component.Component;
import de.mhus.cherry.web.api.InternalCallContext;
import de.mhus.cherry.web.core.CherryApiImpl;
import de.mhus.lib.core.logging.MLogUtil;

@Component(provide = Servlet.class, properties="alias=/*", name="CherryServlet",servicefactory=true)
public class CherryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		InternalCallContext call = null;
		try {
			
			call = CherryApiImpl.instance().createCallContext(this, request, response);
			if (call == null) {
				sendNotFoundError(response);
				return;
			}
			
			call.getVirtualHost().doRequest(call);
			
		} catch (Throwable t) {
			MLogUtil.log().w(t);
			sendInternalError(response,t);
		}
		
	}

	private void sendNotFoundError(HttpServletResponse response) {
		if (response.isCommitted()) return; // can't send error any more
		try {
			response.sendError(404);
		} catch (IOException e) {
		}
	}

	private void sendInternalError(HttpServletResponse response, Throwable t) {
		if (response.isCommitted()) return; // can't send error any more
		try {
			response.sendError(500, t.getMessage());
		} catch (IOException e) {
		}
	}

}