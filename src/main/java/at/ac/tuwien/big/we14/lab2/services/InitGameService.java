package at.ac.tuwien.big.we14.lab2.services;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;

public class InitGameService implements Service {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		ServletContext servletContext = request.getServletContext();
		QuizFactory factory = ServletQuizFactory.init(servletContext);
		QuestionDataProvider provider = factory.createQuestionDataProvider();
		List<Category> categories = provider.loadCategoryData();
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			session = request.getSession(true);
		}
		Question myAsk = categories.get(4).getQuestions().get(4);
		
		session.setAttribute("currQuestion", myAsk);
		session.setAttribute("state", "question");
		return "question";
	}

}
