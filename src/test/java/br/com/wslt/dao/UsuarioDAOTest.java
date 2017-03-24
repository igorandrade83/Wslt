package br.com.wslt.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import com.mysql.jdbc.EscapeTokenizer;
import br.com.wslt.domain.Usuario;

public class UsuarioDAOTest<T> {

	@Test
	@Ignore
	public void salvar(){
		Usuario usuario = new Usuario();
		usuario.setUsrLastLg("LastFM2");
		usuario.setUsrLastSn("SENHAL2");
		usuario.setUsrtwitLg("@twitter2");
		usuario.setUsrtwitSn("SENHA4562");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	@Ignore
	public void listar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lResultado = usuarioDAO.Listar();
		
		System.out.println("Total registro: " + lResultado.size());
		
		for(Usuario usuario: lResultado){
			System.out.println(usuario.getUsrId() +" - "+ usuario.getUsrLastLg() +" - "+ usuario.getUsrLastSn());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		int usrId = 1;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.Buscar(usrId);
		
		if(usuario == null){
			System.out.println("Registro não encontrado.");
		}else{		
			System.out.println(usuario.getUsrId() +" - "+ usuario.getUsrLastLg() +" - "+ usuario.getUsrLastSn());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		int usrId = 2;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.Buscar(usrId);
		
		if(usuario == null){
			System.out.println("Registro não encontrado.");
		}else{	
			usuarioDAO.excluir(usuario);
			System.out.println("Registro excluído.");
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		int usrId = 3;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.Buscar(usrId);
		
		if(usuario == null){
			System.out.println("Registro não encontrado.");
		}else{	
			usuario.setUsrLastLg("N");
			usuarioDAO.editar(usuario);
			System.out.println("Registro editado.");
		}
	}

}
