package org.assignment.application.domain.service;

import org.assignment.application.domain.model.User;
import org.assignment.application.port.in.AddUserUseCase;
import org.assignment.application.port.out.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddUserService implements AddUserUseCase {

	private final UserRepository userRepository;
	@Override
	public void addUser(User user) {
		 userRepository.addUser(user);
	}
}
