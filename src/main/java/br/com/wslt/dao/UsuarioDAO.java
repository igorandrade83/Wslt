package br.com.wslt.dao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.wslt.domain.Usuario;
import br.com.wslt.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	@SuppressWarnings("unchecked")
	public boolean verificaLoginSenha(Usuario usuario){
		try{
			if (LoginSenhaLast(usuario) || LoginSenhaTwit(usuario)){
				return true;
			}else{
				return false;
			}
		}catch (RuntimeException erro) {
			throw erro;
		}
	}
	
	
	public boolean LoginSenhaLast(Usuario usuario){
		try{
			Session sessionL = HibernateUtil.getFabricaDeSessoes().openSession();
			Criteria consultaLast = sessionL.createCriteria(Usuario.class);
			consultaLast.add(Restrictions.like("usrLastLg", usuario.getUsrLastLg()));
			consultaLast.add(Restrictions.like("usrLastSn", usuario.getUsrLastSn()));
			List<Usuario> usuariosLast = consultaLast.list();
			sessionL.close();
			if (usuariosLast.size() > 0){
				return true;
			}else{
				return false;
			}
		}catch (RuntimeException erro) {
			throw erro;
		}
	}
	
	public boolean LoginSenhaTwit(Usuario usuario){
		try{
			Session sessionT = HibernateUtil.getFabricaDeSessoes().openSession();
			Criteria consultaTwit = sessionT.createCriteria(Usuario.class);
			consultaTwit.add(Restrictions.like("usrtwitLg", usuario.getUsrtwitLg()));
			consultaTwit.add(Restrictions.like("usrtwitSn", usuario.getUsrtwitSn()));
			List<Usuario> usuariosTwit = consultaTwit.list();
			sessionT.close();
			if (usuariosTwit.size() > 0){
				return true;
			}else{
				return false;
			}
		}catch (RuntimeException erro) {
			throw erro;
		}
	}
	
	public Usuario BuscarUsuarioLastFm(String usrLastLg){
		try{
			Session sessionL = HibernateUtil.getFabricaDeSessoes().openSession();
			Criteria consultaLast = sessionL.createCriteria(Usuario.class);
			consultaLast.add(Restrictions.like("usrLastLg", usrLastLg));
			List<Usuario> usuariosLast = consultaLast.list();
			sessionL.close();
			if (usuariosLast.size() > 0){
				return usuariosLast.get(0);
			}else {
				return null;
			}
		}catch (RuntimeException erro) {
			throw erro;
		}
	}
}