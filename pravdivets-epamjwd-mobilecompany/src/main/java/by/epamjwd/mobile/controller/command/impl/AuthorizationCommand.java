package by.epamjwd.mobile.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;

public class AuthorizationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		//CHANGE to char array - security

	}

	
	
}