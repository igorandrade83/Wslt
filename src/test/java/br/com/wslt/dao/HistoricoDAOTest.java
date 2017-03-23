package br.com.wslt.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.wslt.domain.Historico;
import br.com.wslt.domain.Usuario;

public class HistoricoDAOTest {

	@Test
	@Ignore
	public void salvar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.Buscar(1);
		
		Historico historico = new Historico();
		
		historico.setUsuario(usuario);
		historico.setHisArtista("noono");
		historico.setHisMusica("lalala");
		historico.setHisData(new Date(System.currentTimeMillis()));
		
		HistoricoDAO historicoDAO = new HistoricoDAO();
		historicoDAO.salvar(historico);
	}
	
	@Test
	@Ignore
	public void listar(){
		HistoricoDAO historicoDAO = new HistoricoDAO();
		List<Historico> lResultado = historicoDAO.Listar();
		
		System.out.println("Total registro: " + lResultado.size());
		
		for(Historico historico: lResultado){
			System.out.println(historico.getHisId() +" - "+ historico.getUsuario().getUsrId() +" - "+ historico.getHisArtista());
		}
	}
	
	
	@Test
	@Ignore
	public void buscar(){
		int hisId = 1;
		HistoricoDAO historicoDAO = new HistoricoDAO();
		Historico historico = historicoDAO.Buscar(hisId);
				
		if(historico == null){
			System.out.println("Registro não encontrado.");
		}else{		
			System.out.println(historico.getHisId() +" - "+ historico.getUsuario().getUsrId() +" - "+ historico.getHisArtista());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		int hisId = 3;
		
		HistoricoDAO historicoDAO = new HistoricoDAO();
		Historico historico = historicoDAO.Buscar(hisId);
		
		if(historico == null){
			System.out.println("Registro não encontrado.");
		}else{	
			historicoDAO.excluir(historico);
			System.out.println("Registro excluído.");
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		int hisId = 2;

		HistoricoDAO historicoDAO = new HistoricoDAO();
		Historico historico = historicoDAO.Buscar(hisId);
		
		if(historico == null){
			System.out.println("Registro não encontrado.");
		}else{	
			historico.setHisArtista("NovoArtista");
			historicoDAO.editar(historico);
			System.out.println("Registro editado.");
		}
	}
	
}
