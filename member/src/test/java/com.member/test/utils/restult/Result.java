package com.member.test.utils.restult;

import java.io.Serializable;

/**
 * Created by liangming on 15/8/14.
 */
public class Result<T> implements Serializable {
	
	private static final long serialVersionUID = -8555093674142735518L;

	private boolean status;

	private int responseCode;

	private String message;

	private T entry;

	public Result(boolean status, int responseCode, String message) {
		this.status = status;
		this.responseCode = responseCode;
		this.message = message;
	}

	public Result(boolean status, int responseCode, String message, T entry) {
		this.status = status;
		this.responseCode = responseCode;
		this.message = message;
		this.entry = entry;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public T getEntry() {
		return entry;
	}

	public void setEntry(T entry) {
		this.entry = entry;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Result<?> that = (Result<?>) o;

		if (status != that.status)
			return false;
		if (responseCode != that.responseCode)
			return false;
		if (message != null ? !message.equals(that.message) : that.message != null)
			return false;
		return !(entry != null ? !entry.equals(that.entry) : that.entry != null);

	}

	@Override
	public int hashCode() {
		int result = (status ? 1 : 0);
		result = 31 * result + responseCode;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (entry != null ? entry.hashCode() : 0);
		return result;
	}

	public static Result fromResultCode(ResultCode resultCode) {
		if (resultCode == null) {
			return null;
		}
		Result result = new Result(resultCode.isSuccess(), resultCode.getCode(), resultCode.getMessage());
		return result;
	}

	public static Result<Object> fromResultCode(ResultCode resultCode, Object entry) {
		if (resultCode == null) {
			return null;
		}
		Result<Object> result = new Result<Object>(resultCode.isSuccess(), resultCode.getCode(), resultCode.getMessage(), entry);
		return result;
	}

	public static String Success(String message) {
		return message;
	}
}
