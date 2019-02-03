package ca.programmerlife.utils.all;

import java.io.Serializable;

public class Constant implements Serializable{

	private static final long serialVersionUID = -6061180137230827324L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static final String SUCCESS = "Success";
	public static final String SUCCESSFUL_OPERATION = "Success Operation Done";
	public static final String WRONG_OPERATION = "Could not perform operation";
	

}
