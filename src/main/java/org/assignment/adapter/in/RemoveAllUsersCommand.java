package org.assignment.adapter.in;

import org.assignment.application.port.in.Command;
import org.assignment.application.port.in.RemoveAllUsersUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveAllUsersCommand implements Command {

	private RemoveAllUsersUseCase removeAllUsersUseCase;

	@Override
	public void execute() {
		removeAllUsersUseCase.removeAllUsers();
	}
}
