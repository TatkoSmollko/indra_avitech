package org.assignment.adapter.in;

import java.util.Set;

import org.assignment.application.domain.model.User;
import org.assignment.application.port.in.Command;
import org.assignment.application.port.in.PrintAllUsersUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintAllUsersCommand implements Command {

	private PrintAllUsersUseCase printAllUsersUseCase;

	@Override
	public void execute() {
		Set<User> allUsers = printAllUsersUseCase.findAllUsers();
		if (allUsers != null) {
			allUsers.forEach(System.out::println);
		}
	}
}
