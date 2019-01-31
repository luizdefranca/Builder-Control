package ca.programmerlife.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public interface ICrud<T>   extends Serializable{

	/**
	 * Save an object within the database
	 * @param obj
	 * @throws Exception
	 */
	void save(T obj) throws Exception;
	
	/**
	 * Persist an object within the database
	 * @param obj
	 * @throws Exception
	 */
	void persist(T obj) throws Exception;
	
	/**
	 * Save or Update an object within the database
	 * @param obj
	 * @throws Exception
	 */
	void saveOrUpdate(T obj) throws Exception;
	
	/**
	 * Update an object within the database
	 * @param obj
	 * @throws Exception
	 */
	void update(T obj) throws Exception;
	
	/**
	 * Delete an object from the database
	 * @param obj
	 * @throws Exception
	 */
	void delete(T obj) throws Exception;
	
	/**
	 * Save/Update and return an object in persistent state
	 * @param obj
	 * @return
	 */
	T merge(T obj); 
	
	/**
	 * Return a List of objects from a determined class
	 * @param obj
	 * @return List<T>
	 * @throws Exceptions
	 */
	List<T> findList(Class<T> obj) throws Exception;
	
	/**
	 * Return an object passing the class and id
	 * @param entity
	 * @param id
	 * @return T
	 * @throws Exception
	 */
	T findById(Class<T> entity, Long id) throws Exception;
	
	/**
	 * Return an object class element passing the class and id
	 * @param entity
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	Object findObjectById(Class<T> entity, Long id) throws Exception;
	
	/**
	 * Return an List of object passing a query
	 * @param query
	 * @return List<T>
	 * @throws Exception
	 */
	
	/**
	 * Return an List of object passing an HQL dynamic query
	 * @param query
	 * @return List<T>
	 * @throws Exception
	 */
	List<T> findListByDynamic(String query) throws Exception;
	
	/**
	 * Execute an update passing an HQL dynamic query
	 * @param query
	 * @throws Exception
	 */
	void executeUpdateDynamicQuery(String query) throws Exception;
	
	/**
	 * Execute an update passing an SQL dynamic query
	 * @param query
	 * @throws Exception
	 */
	void executeUpdateSQLDinamicQuery(String sqlQuery) throws Exception;
	
	/**
	 * Clear a Hibernate session
	 * @throws Exception
	 */
	void clearSession() throws Exception;
	
	/**
	 * Remove an object of Hibernate session
	 * @param obj
	 * @throws Exception
	 */
	void evict(Object obj) throws Exception;
	
	/**
	 * Return a Hibernate session 
	 * @return Session
	 * @throws Exeception
	 */
	Session getSession() throws Exception;
	
	/**
	 * Return a processable List passing a SQL Dynamic Query
	 * @param sqlQuery
	 * @return List<?>
	 * @throws Exception
	 */
	List<?> getListBySqlDynamicQuery(String sqlQuery) throws Exception;
	
	/**
	 * Return a list of object array using SQL
	 * @param sqlQuery
	 * @return List<Object[]> 
	 * @throws Exception
	 */
	List<Object[]> getListSqlDynamicArray(String sqlQuery) throws Exception;
	
	/**
	 * Return a JdbcTemplate object using the springframework.jdbc.core framework
	 * @return JdbcTemplate
	 */
	JdbcTemplate getJdbcTemplate();
	
	/**
	 * Return a JdbcTemplate object using the springframework.jdbc.core framework
	 * @return SimpleJdbcTemplate
	 */
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	/**
	 * Return a SimpleJdbcInsert object using the springframework.jdbc.core framework
	 * @return SimpleJdbcInsert
	 */
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	/**
	 * Return a SimpleJdbcCall object
	 * @return SimpleJdbcCall
	 */
	SimpleJdbcCall getSimpleJdbcCall();
	
	/**
	 * Return the number of registries in table passing the name of the table
	 * @param table
	 * @return
	 * @throws Exception
	 */
	Long numberOfRegistries(String table) throws Exception;
	
	/**
	 * Return a Query Hibernate object passing a query string
	 * @param query
	 * @return Query
	 * @throws Exception
	 */
	Query getQuery(String query) throws Exception;
	
	/**
	 * Return a List of object passing a query, the number of begin registry and the number 
	 * of elements of result
	 * @param query
	 * @param startOnRegistry
	 * @param maximumResult
	 * @return List<T>
	 */
	List<T> findListByDynamicQuery(String query, int startOnRegistry, int maximumResult);
	
	
}
