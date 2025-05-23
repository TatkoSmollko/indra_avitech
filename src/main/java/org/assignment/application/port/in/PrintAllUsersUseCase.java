package org.assignment.application.port.in;

import java.util.Set;

import org.assignment.application.domain.model.User;

public interface PrintAllUsersUseCase {
	Set<User> findAllUsers();
}
