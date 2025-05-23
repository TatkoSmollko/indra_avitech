package org.assignment.adapter.in;

import java.util.concurrent.BlockingQueue;

import org.assignment.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandProducerService {
	private final BlockingQueue<Command> queue;

	public void submit(Command command) {
		if (queue != null) {
			queue.add(command);
		}else{
			throw new NullPointerException("queue cannot be null");
		}

	}
}

