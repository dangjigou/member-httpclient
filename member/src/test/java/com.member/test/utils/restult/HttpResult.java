package com.member.test.utils.restult;

/**
 * Created by luzhenhui on 15/8/14.
 */
public class HttpResult<T> {

    private boolean status;

    private int responseCode;

    private String message;

    private T entry;

	public HttpResult(boolean status, int responseCode, String message) {
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
    }

    public HttpResult(boolean status, int responseCode, String message, T entry) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpResult<?> that = (HttpResult<?>) o;

        if (status != that.status) return false;
        if (responseCode != that.responseCode) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
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

    @Override
    public String toString() {
        return "HttpResult{" +
                "status=" + status +
                ", responseCode=" + responseCode +
                ", message='" + message + '\'' +
                ", entry=" + entry +
                '}';
    }

    public static HttpResult fromDomainResultCode(ResultCode resultCode) {
        if (resultCode == null) {
            return null;
        }
        HttpResult httpResult = new HttpResult(
                resultCode.isSuccess(), resultCode.getCode(), resultCode.getMessage());

        return httpResult;
    }

    public static HttpResult fromHttpResultCode(HttpResultCode resultCode) {
        if (resultCode == null) {
            return null;
        }
        HttpResult httpResult = new HttpResult(
                resultCode.isSuccess(), resultCode.getCode(), resultCode.getMessage());

        return httpResult;
    }
    
	public static HttpResult fromHttpResultCode(HttpResultCode resultCode, Object entry) {
		if (resultCode == null) {
			return null;
		}
		HttpResult httpResult = new HttpResult(resultCode.isSuccess(), resultCode.getCode(), resultCode.getMessage(), entry);
		return httpResult;
	}
	
	public static HttpResult fromResult(Result result) {
		if (result == null) {
			return null;
		}
		HttpResult httpResult = new HttpResult(result.isStatus(), result.getResponseCode(), result.getMessage(), result.getEntry());
		return httpResult;
	}
}
