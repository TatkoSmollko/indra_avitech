package org.assignment.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private final int id;
	private final String guid;
	private final String name;
}
