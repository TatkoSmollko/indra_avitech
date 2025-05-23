package org.assignment.application.domain.service;

import java.util.concurrent.BlockingQueue;

import org.assignment.adapter.in.ShutDownCommand;
import org.assignment.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
public class CommandExecutorService implements Runnable {
	private final BlockingQueue<Command> queue;

	@Override
	public void run() {
		while (true) {
			try {
				Command cmd = queue.take();
				if(cmd instanceof ShutDownCommand){
					cmd.execute();
					break;
				}
				cmd.execute();
			} catch (InterruptedException e) {
				log.error("Interrupted while executing command", e);
				break;
			}
		}
	}
}
