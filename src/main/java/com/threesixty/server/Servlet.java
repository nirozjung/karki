package com.threesixty.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * 
 * @author nirozjungkarki
 *
 */
public class Servlet {
	public static void main(String[] args) throws Exception {
		// Create a basic jetty server object that will listen on port 8080.
		// Note that if you set this to port 0 then a randomly available port
		// will be assigned that you can either look in the logs for the port,
		// or programmatically obtain it for use in test cases.
		Server server = new Server(8080);

		// The ServletHandler is a dead simple way to create a context handler
		// that is backed by an instance of a Servlet.
		// This handler then needs to be registered with the Server object.
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);

		// Passing in the class for the Servlet allows jetty to instantiate an
		// instance of that Servlet and mount it on a given context path.

		// IMPORTANT:
		// This is a raw Servlet, not a Servlet that has been configured
		// through a web.xml @WebServlet annotation, or anything similar.
		handler.addServletWithMapping(HelloServlet.class, "/*");

		// Start things up!
		server.start();

		// The use of server.join() the will make the current thread join and
		// wait until the server is done executing.
		// See
		// http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
		server.join();

	}

	@SuppressWarnings("serial")
	public static class HelloServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println("<h1>Hello from HelloServlet's doGet method</h1>");

		} // end doGet method

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			// test code as copied from doGet method
			resp.setContentType("text/plain;charset=utf-8");
			resp.setStatus(HttpServletResponse.SC_OK);

			// resp.getWriter().println("<h1>Hello from HelloServlet</h1>");

			PrintWriter p = resp.getWriter();
			// ServletOutputStream out = response.getOutputStream();
			// InputStream in = new FileInputStream("tmp");
			// in.
			// resp.getWriter().println(req.getParameterNames().nextElement());

			for (Enumeration<String> e = req.getParameterNames(); e.hasMoreElements();) {
				String name = e.nextElement();
				p.format("%s: %s%n", name, req.getParameter(name));

				System.out.println("Here!! from doPost" + name + " :" + req.getParameter(name));

			} // end for
		}// end method doPost
	} // end class HelloServlet
}// end class
