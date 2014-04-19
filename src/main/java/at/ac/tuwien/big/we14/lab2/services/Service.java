package at.ac.tuwien.big.we14.lab2.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.ac.tuwien.big.we14.lab2.api.impl.Answer;

public interface Service {
	public String execute(HttpServletRequest request, HttpServletResponse response);
}
