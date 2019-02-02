package ca.programmerlife.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class utilFramework implements Serializable{

	private static final long serialVersionUID = 976287274417500488L;
	
	private static ThreadLocal<Long> thLocal = new ThreadLocal<>();
	
	/**
	 * Method used to audit the database
	 * @return ThreadLocal<Long>
	 */
	public synchronized static ThreadLocal<Long> geThreadLocal(){
		return thLocal;
	}
	
	
}
