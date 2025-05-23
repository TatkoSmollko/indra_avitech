package org.assignment.application.domain.service;

import java.util.Set;

import org.assignment.application.domain.model.User;
import org.assignment.application.port.in.PrintAllUsersUseCase;
import org.assignment.application.port.out.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintAllUsersService implements PrintAllUsersUseCase {
	private UserRepository userRepository;

	@Override
	public Set<User> findAllUsers() {
		return userRepository.findAllUsers();
	}
}
