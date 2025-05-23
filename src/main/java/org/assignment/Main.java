package org.assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.assignment.adapter.in.AddUserCommand;
import org.assignment.adapter.in.CommandProducerService;
import org.assignment.adapter.in.PrintAllUsersCommand;
import org.assignment.adapter.in.RemoveAllUsersCommand;
import org.assignment.adapter.in.ShutDownCommand;
import org.assignment.adapter.out.UserPersistenceAdapter;
import org.assignment.application.domain.model.User;
import org.assignment.application.domain.service.AddUserService;
import org.assignment.application.domain.service.CommandExecutorService;
import org.assignment.application.domain.service.PrintAllUsersService;
import org.assignment.application.domain.service.RemoveAllUsersService;
import org.assignment.application.port.out.UserRepository;
import org.assignment.configuration.DataSourceConfig;
import org.assignment.migration.DatabaseMigration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {
		//  dataSource initialization
		DataSource dataSource = DataSourceConfig.createH2DataSource();

		//  run database migrations using flyway
		DatabaseMigration.migrate(dataSource);

		//  needed repositories and services
		UserRepository userRepository = new UserPersistenceAdapter(dataSource);
		AddUserService addUserService = new AddUserService(userRepository);
		PrintAllUsersService printAllUsersService = new PrintAllUsersService(userRepository);
		RemoveAllUsersService removeAllUsersService = new RemoveAllUsersService(userRepository);


		//  producer and consumer initialization
		CommandProducerService commandProducerService = new CommandProducerService(new LinkedBlockingQueue<>());
		CommandExecutorService commandExecutor = new CommandExecutorService(commandProducerService.getQueue());

		try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
			executor.submit(commandExecutor);

			// enqueue commands
			commandProducerService.submit(new AddUserCommand(addUserService, new User(1, "GUID-Juraj", "Juraj")));
			commandProducerService.submit(new AddUserCommand(addUserService, new User(2, "GUID-Martin", "Martin")));
			commandProducerService.submit(new PrintAllUsersCommand(printAllUsersService));
			commandProducerService.submit(new RemoveAllUsersCommand(removeAllUsersService));
			commandProducerService.submit(new PrintAllUsersCommand(printAllUsersService));
			commandProducerService.submit(new ShutDownCommand());

			// wait until all tasks are done
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			log.info("All tasks processed successfully.");
		} catch (Exception e) {
            log.error("Executor has been interrupted due to an error: {}", e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
}