package org.assignment.adapter.in;

import org.assignment.application.port.in.Command;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class ShutDownCommand implements Command {
	@Override
	public void execute() {
		log.info("Shutdown command received to break the loop logic");
	}
}
