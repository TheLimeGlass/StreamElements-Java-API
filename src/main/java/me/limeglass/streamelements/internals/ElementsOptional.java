package me.limeglass.streamelements.internals;

import java.io.IOException;
import java.util.Optional;

public class ElementsOptional<T> {

	private final Optional<T> optional;
	private IOException exception;
	
	public ElementsOptional() {
		this.optional = Optional.empty();
	}
	
	public ElementsOptional(Optional<T> optional) {
		this.optional = optional;
	}
	
	public ElementsOptional(IOException exception) {
		this.optional = Optional.empty();
		this.exception = exception;
	}

	public void setException(IOException exception) {
		this.exception = exception;
	}
	
	public IOException getException() {
		return exception;
	}
	
	public Optional<T> getOptional() {
		return optional;
	}
	
	public boolean isPresent() {
		return optional.isPresent();
	}
	
	public boolean hasError() {
		return exception != null;
	}
	
	public T get() {
		return optional.get();
	}

}
