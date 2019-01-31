package ca.programmerlife.framework.interfac.crud;


import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
public class SimpleJdbcInsertImplementation extends SimpleJdbcInsert implements Serializable {

	private static final long serialVersionUID = -7576030521241188375L;
	
	public SimpleJdbcInsertImplementation(DataSource dataSource) {
		super(dataSource);
	}

	

}
