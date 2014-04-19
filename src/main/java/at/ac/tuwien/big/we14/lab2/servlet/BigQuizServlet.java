package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.ac.tuwien.big.we14.lab2.api.services.InitGameService;
import at.ac.tuwien.big.we14.lab2.api.services.QuestionCareTakerService;

public class BigQuizServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//all possible requests
		if (request.getParameter("action") != null) {
			
			//request for starting a new game
			if (request.getParameter("action").equals("newgame")) {
				String view = new InitGameService().execute(request, response);
				response.sendRedirect(view + ".jsp");
				return;
			}
			
			//request coming from question
			else if (request.getParameter("action").equals("answer")) {
				
				String view = new QuestionCareTakerService().execute(request, response);
				response.sendRedirect(view + ".jsp");
				return;
			}
			
			//round is complete, update data and start next round
			else if (request.getParameter("action").equals("nextround")) {
				System.out.println(request.getParameter("action"));

				//start next round
			}
			
			//not valid action request, send user to hell
			else {  
				System.out.println("not valid action request");
				//generate appropriate view
				//redirect user
			}
		}
	
		//not a valid request
		else {
			System.out.println("invalid request");
			//response.sendRedirect(currentGame.getState() + ".jsp");
		}
	}
	
}