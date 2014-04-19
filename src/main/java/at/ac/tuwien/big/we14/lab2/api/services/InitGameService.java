package at.ac.tuwien.big.we14.lab2.api.services;

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
		
		//init a list of cateogories, provided by JSON API
		ServletContext servletContext = request.getServletContext();
		QuizFactory factory = ServletQuizFactory.init(servletContext);
		QuestionDataProvider provider = factory.createQuestionDataProvider();
		List<Category> categories = provider.loadCategoryData();
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			session = request.getSession(true);
		}
		//Question myAsk = categories.get(4).getQuestions().get(4);
		
		String game = null;
		game.startGame(categories, Players.COMPUTER, Players.USER );
		
		session.setAttribute("player1", game.getCurrentPlayer(1));
		session.setAttribute("player2", game.getCurrentPlayer(2));
		session.setAttribute("currentRound", game.getCurrentRound());
		session.setAttribute("currentGame", game);
		session.setAttribute("currentQuestion", game.getCurrentRound().getCurrentQuestion());
		
		session.setAttribute("state", "question");
		return "question";
	}

}
