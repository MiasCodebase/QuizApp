package common;

import java.util.List;

public class OperationResult<T> {
	
	private boolean success;
	private T data;
	private List<String> errors;
	private String message;

	private OperationResult(boolean success,T data, List<String> errors, String message) {
		this.success = success;
		this.data = data;
		this.errors = errors != null ? errors : List.of();
		this.message = message;
	}
	
	public static <T> OperationResult<T> success(T data) {
		return new OperationResult<T>(true, data, null, null);
	}
	
	public static <T> OperationResult<T> success(T data, String message) {
		return new OperationResult<T>(true, data, null, message);
	}
	
	public static <T> OperationResult<T> success() {
		return new OperationResult<T>(true, null, null, null);
	}
	
	public static <T> OperationResult<T> success(String message) {
		return new OperationResult<T>(true, null, null, message);
	}

	public static <T> OperationResult<T> failure(List<String> errors) {
		return new OperationResult<T>(false, null, errors, null);
	}

	public boolean isSuccess() {
		return success;
	}

	public T getData() {
		return data;
	}

	public List<String> getErrors() {
		return errors;
	}
	
	public String getMessage() {
		return message;
	}
	
}
