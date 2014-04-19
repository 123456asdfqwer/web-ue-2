package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.Choice;

public class Answer {
	private List<Choice> choices;	
	
	public Answer() {
		choices = new ArrayList<Choice>();
	}
	public void addChoice(Choice c) {
		choices.add(c);
	}
	
	public List<Choice> getChoices() {
		return this.choices;
	}
	
	
}
