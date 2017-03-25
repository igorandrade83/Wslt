package br.com.wslt.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.wslt.domain.Historico;
import br.com.wslt.util.HibernateUtil;

public class HistoricoDAO extends GenericDAO<Historico>{

	@SuppressWarnings("unchecked")
	public List<Historico> ListarComDatas(Date dataInicio, Date dataFim){
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = session.createCriteria(Historico.class);
			consulta.add(Restrictions.between("hisData", dataInicio, dataFim));
			List<Historico> historicos = consulta.list();
			
			return historicos;
		}catch (RuntimeException erro) {
			throw erro;
		}finally {
			session.close();
		}
	}
	
}
