package integration;

import org.assignment.adapter.out.UserPersistenceAdapter;
import org.assignment.application.domain.model.User;
import org.assignment.application.port.out.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceAdapterIntegrationTest extends BaseIntegrationTest {

	private UserRepository userRepository;

	@BeforeEach
	void setupRepository() {
		userRepository = new UserPersistenceAdapter(dataSource);
	}

	@Test
	void testAddUserAndFindAllUsers() {
		User user = new User(1, "guid-user", "USER");
		userRepository.addUser(user);

		Set<User> users = userRepository.findAllUsers();
		assertEquals(1, users.size());
		assertTrue(users.contains(user));
	}

	@Test
	void testRemoveAllUsers() {
		userRepository.addUser(new User(1, "guid-a", "User A"));
		userRepository.addUser(new User(2, "guid-b", "User B"));

		assertEquals(2, userRepository.findAllUsers().size());

		userRepository.deleteAllUsers();
		assertEquals(0, userRepository.findAllUsers().size());
	}

	@Test
	void testFindAllUsers_whenNoneExist() {
		Set<User> users = userRepository.findAllUsers();
		assertTrue(users.isEmpty());
	}
}
