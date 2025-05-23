import org.assignment.adapter.in.AddUserCommand;
import org.assignment.adapter.in.PrintAllUsersCommand;
import org.assignment.application.domain.model.User;
import org.assignment.application.port.in.AddUserUseCase;
import org.assignment.application.port.in.PrintAllUsersUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

class UserCommandTests {

	@Test
	void addCommand_execute_shouldCallAddUserUseCaseWithUser() {
		User mockUser = new User(1, "GUIDxx", "MockedName");
		AddUserUseCase addUserUseCase = mock(AddUserUseCase.class);
		AddUserCommand command = new AddUserCommand(addUserUseCase, mockUser);

		command.execute();

		verify(addUserUseCase, times(1)).addUser(mockUser);
	}

	@Test
	void execute_shouldThrowIllegalArgumentExceptionWhenUserIsNull() {
		AddUserUseCase addUserUseCase = mock(AddUserUseCase.class);
		AddUserCommand nullUserCommand = new AddUserCommand(addUserUseCase, null);

		assertThrows(NullPointerException.class, nullUserCommand::execute);
	}

	@Test
	void printAllCommand_execute_shouldCallAddUserUseCaseWithUser() {
		User mockUser = new User(1, "GUIDxx", "MockedName");
		AddUserUseCase addUserUseCase = mock(AddUserUseCase.class);
		AddUserCommand command = new AddUserCommand(addUserUseCase, mockUser);

		command.execute();

		verify(addUserUseCase, times(1)).addUser(mockUser);
	}

	@Test
	void execute_shouldCallFindAllUsersAndPrintThem() {
		PrintAllUsersUseCase mockUseCase = mock(PrintAllUsersUseCase.class);
		Set<User> mockUsers = Set.of(new User(1, "guid-a", "User A"), new User(2, "guid-b", "User B"));
		when(mockUseCase.findAllUsers()).thenReturn(mockUsers);

		PrintAllUsersCommand cmd = new PrintAllUsersCommand(mockUseCase);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		cmd.execute();

		String output = out.toString();
		assertTrue(output.contains("User A"));
		assertTrue(output.contains("User B"));
		verify(mockUseCase).findAllUsers();
	}

	@Test
	void execute_shouldHandleNullUserSet() {
		PrintAllUsersUseCase mockUseCase = mock(PrintAllUsersUseCase.class);
		when(mockUseCase.findAllUsers()).thenReturn(null);

		PrintAllUsersCommand cmd = new PrintAllUsersCommand(mockUseCase);

		assertDoesNotThrow(cmd::execute);
		verify(mockUseCase).findAllUsers();
	}

}

