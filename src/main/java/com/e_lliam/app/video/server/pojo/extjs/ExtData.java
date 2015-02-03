package com.e_lliam.app.video.server.pojo.extjs;

public  class ExtData {
	public static final ExtData EmptySuccess=new ExtData(true);
	public static final ExtData EmptyFailure=new ExtData(false);
	protected boolean success;
	protected Object errors;
	protected String msg;
	protected Object data; 

	public ExtData(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}

	public ExtData() {
		super();
	}

	public ExtData(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}



	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
