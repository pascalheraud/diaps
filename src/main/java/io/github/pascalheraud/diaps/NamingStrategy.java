package io.github.pascalheraud.diaps;

import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.stereotype.Component;

@Component
public class NamingStrategy implements org.springframework.data.relational.core.mapping.NamingStrategy {

	@Override
	public String getColumnName(RelationalPersistentProperty property) {
		return property.getName().toLowerCase();
	}
}
