package org.assignment.adapter.in;

import org.assignment.application.domain.model.User;
import org.assignment.application.port.in.AddUserUseCase;
import org.assignment.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserCommand implements Command {

	private AddUserUseCase addUserUseCase;
	private User user;

	@Override
	public void execute() {
		if(user != null) {
			addUserUseCase.addUser(user);
		}else{
			throw new NullPointerException("User cannot be null");
		}

	}
}
