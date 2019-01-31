package ca.programmerlife.framework.interfac.crud;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
public class SimpleJdbcCallImplementation extends SimpleJdbcCall implements Serializable{

	private static final long serialVersionUID = -5188867677340928923L;
	
	public SimpleJdbcCallImplementation(DataSource dataSource) {
		super(dataSource);
	}

	
	

}
