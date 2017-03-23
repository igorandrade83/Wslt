package br.com.wslt.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.wslt.util.HibernateUtil;

public class GenericDAO<T> {
	private Class<T> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(){
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(T t){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
		}catch (RuntimeException erro) {
			if (transaction != null){
				transaction.rollback();
			}
			throw erro;
		}finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> Listar(){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = session.createCriteria(classe);
			List<T> lResultado = consulta.list();
			return lResultado;
		}catch (RuntimeException erro) {
			throw erro;
		}finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T Buscar(int usrId){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = session.createCriteria(classe);
			consulta.add(Restrictions.idEq(usrId));
			T t = (T) consulta.uniqueResult();
			return t;
		}catch (RuntimeException erro) {
			throw erro;
		}finally {
			session.close();
		}
	}
	
	public void excluir(T t){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		}catch (RuntimeException erro) {
			if (transaction != null){
				transaction.rollback();
			}
			throw erro;
		}finally {
			session.close();
		}
	}
	
	
	public void editar(T t){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.update(t);
			transaction.commit();
		}catch (RuntimeException erro) {
			if (transaction != null){
				transaction.rollback();
			}
			throw erro;
		}finally {
			session.close();
		}
	}
}
