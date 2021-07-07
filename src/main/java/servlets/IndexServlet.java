package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.ResourceBundle;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request,response);	
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		System.out.println("Test im here");
		System.out.println(request.getParameter("postalCode"));
		if (request.getParameter("postalCode") == null || request.getParameter("postalCode").toString().length() == 0) {
			request.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postNoValue"));
		} else if (!request.getParameter("postalCode").toString().matches("[0-9]+")) {
			request.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postNoNumber"));
		} else if (Integer.parseInt(request.getParameter("postalCode"))<1000) {
			request.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postBelowMin"));
		} else if (Integer.parseInt(request.getParameter("postalCode"))>9990) {
			request.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postAboveMax"));
		} 
		doGet(request, response);
	}
    
    private String getPostValidationError(Locale curLoc, String key) {
    	ResourceBundle exceptions = ResourceBundle.getBundle("resources/exceptions", curLoc);
    	String postValErr = exceptions.getString(key);
    	return postValErr;
    }

}
