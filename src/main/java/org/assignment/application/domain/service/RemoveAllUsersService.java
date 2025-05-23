package org.assignment.application.domain.service;

import org.assignment.application.port.in.RemoveAllUsersUseCase;
import org.assignment.application.port.out.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveAllUsersService implements RemoveAllUsersUseCase {

	private UserRepository userRepository;

	@Override
	public void removeAllUsers() {
		userRepository.deleteAllUsers();
	}
}
