package ca.programmerlife.framework.interfac.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ca.programmerlife.framework.hibernate.session.HibernateUtil;

@Component
@Transactional
public class CrudImplementation<T> implements ICrud<T>{

	
	private static final long serialVersionUID = 500499940536635711L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImplementation jdbcTemplateImplementation;
	
	@Autowired
	private SimpleJdbcTemplateImplementation simpleJdbcTemplateImplementation;
	
	@Autowired
	private SimpleJdbcInsertImplementation simpleJdbcInsertImplementation;
	
	@Autowired
	private SimpleJdbcCallImplementation simpleJdbcClassImplementation;
	
	@Override
	public void save(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}
	
	@Override
	public void update(T obj) throws Exception{
		validateSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}
	
	@Override
	public void delete(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
		
	}

	@Override
	public T merge(T obj) {
		validateSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> obj) throws Exception {
		validateSessionFactory();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT DISTINCT(entity) from ");
		builder.append(obj.getSimpleName());
		builder.append(" entity ");
		
		List<T> objects = sessionFactory.getCurrentSession()
				.createQuery(builder.toString()).list();
		return objects;
	}
	
	@Override
	public List<Object[]> getListSqlDynamicArray(String sqlQuery) throws Exception{
		validateSessionFactory();
		List<Object[]> list = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sqlQuery).list();
		return list;
	}
	@Override
	public T findById(Class<T> entity, Long id) throws Exception {
		validateSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public Object findObjectById(Class<T> entity, Long id) throws Exception {
		validateSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public List<T> findListByDynamic(String query) throws Exception {
		validateSessionFactory();
		List<T> objects = new ArrayList<T>();
		objects = sessionFactory.getCurrentSession().createQuery(query).list();
		return objects;
	}

	@Override
	public void executeUpdateDynamicQuery(String query) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDinamicQuery(String sqlQuery) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(sqlQuery).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().evict(obj);
	}

	@Override
	public Session getSession() throws Exception {
		validateSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListBySqlDynamicQuery(String sqlQuery) throws Exception {
		validateSessionFactory();
		List list = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery).list();
		return list;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateImplementation;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplateImplementation;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsertImplementation;
	}
	
	@Override
	public SimpleJdbcCallImplementation getSimpleJdbcCall() {
		return simpleJdbcClassImplementation;
	}

	@Override
	public Long numberOfRegistries(String table) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(1) ").append(table);
		return jdbcTemplateImplementation.queryForLong(builder.toString());
	}

	@Override
	public Query getQuery(String query) throws Exception {
		validateSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query);
		return queryReturn;
	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir 
	 * do registro passado no parâmetro (startOnRegistry) e obtendo máximo de 
	 * resultados passados em -> maximumResult
	 */
	@Override
	public List<T> findListByDynamicQuery(String query, int startOnRegistry, int maximumResult) {
		validateSessionFactory();
		List<T> list = new ArrayList();
		list = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(startOnRegistry)
				.setMaxResults(maximumResult).list();
		return list;
	}

	private void validateSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
	}
	
	@SuppressWarnings("unused")
	private void validateTransaction() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	@SuppressWarnings("unused")
	private void commitAjaxProcess() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}
	
	@SuppressWarnings("unused")
	private void rollBackAjaxProcess() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	/**
	 * Execute instantly the SQL instruction within database
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
	
}
