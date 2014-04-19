package at.ac.tuwien.big.we14.lab2.api.services;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.impl.Answer;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleChoice;


public class QuestionCareTakerService implements Service {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		//create answer object and populate it with choices commited by the user
		Answer answer = new Answer();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String currentParam = params.nextElement();
			if(currentParam.substring(0, 6).equals("option")) {
				int currentId = Integer.parseInt(currentParam.substring(6));
				
				Choice choice = new SimpleChoice();
				
				choice.setText(request.getParameter(currentParam));
				choice.setId(currentId);
		
				answer.addChoice(choice);
			}
		}
		
		//System.out.println(answer.getChoices());
		//update current state
		HttpSession session = request.getSession();
		session.setAttribute("state", "finish");
		
		
		//generate view according to currenta game state
		return "finish";
	}
	


}
