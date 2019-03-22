package atj;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(description = "Servlet of temperature converter", urlPatterns = { "/MyServlet" })
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private double temperature;
	private String action;
	private String result;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		temperature = Double.parseDouble(request.getParameter("temperature"));
		action = request.getParameter("action");
		Convert();
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("page.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void Convert() {
		if (action.equals("celsius")) {
			double temp = (1.8 * temperature) + 32.0;
			result = String.format("%.2f", temp) + " F";
		} else if (action.equals("fahrenheit")) {
			double temp = ((temperature - 32.0) * 5.0) / 9.0;
			result = String.format("%.2f", temp) + " C";
		}
	}

}