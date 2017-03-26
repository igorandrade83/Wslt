package br.com.wslt.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.wslt.dao.HistoricoDAO;
import br.com.wslt.dao.UsuarioDAO;
import br.com.wslt.domain.Historico;
import br.com.wslt.util.Util;

// http://localhost:8080/Wslt/service/wslt

@Path("wslt")
public class WsltServiceHist {
	
	@GET
	public String exibirHistoricoGeral(){
		
		HistoricoDAO historicoDAO = new HistoricoDAO();
		List<Historico> historicos = historicoDAO.Listar();
		
		Gson gson = new Gson();
		return gson.toJson(historicos);
	}
	
	@GET
	@Path("{hisID}")
	public String buscarhistorico(@PathParam("hisID") int hisID){		
		HistoricoDAO historicoDAO = new HistoricoDAO();
		Gson gson = new Gson();

		return gson.toJson(historicoDAO.Buscar(hisID));
	}
	
	// http://localhost:8080/Wslt/service/wslt/data1/data2
	@GET
	@Path("{data1}/{data2}")
	public String buscarhistoricoDatas(@PathParam("data1") String data1, @PathParam("data2") String data2){		
		HistoricoDAO historicoDAO = new HistoricoDAO();

		List<Historico> historicos = historicoDAO.ListarComDatas(Util.StringToData(data1), Util.StringToData(data2));
		Gson gson = new Gson();

		return gson.toJson(historicos);
	}

	// http://localhost:8080/Wslt/service/wslt
	@POST
	public String salvar(String json){
		Gson gson = new Gson();
		HistoricoDAO historicoDAO = new HistoricoDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Historico historico = gson.fromJson(json, Historico.class);
		historico.setUsuario(usuarioDAO.Buscar(historico.getUsuario().getUsrId()));
		
		historicoDAO.salvar(historico);
		return gson.toJson(historico);
	}
	
	@PUT
	public String editar(String json){
		Gson gson = new Gson();
		HistoricoDAO historicoDAO = new HistoricoDAO();
		Historico historico = gson.fromJson(json, Historico.class);
		
		historicoDAO.editar(historico);
		return gson.toJson(historico);
	}
	
	@DELETE
	public String excluir(String json){
		Gson gson = new Gson();
		HistoricoDAO historicoDAO = new HistoricoDAO();
		Historico historico = gson.fromJson(json, Historico.class);
		
		historico = historicoDAO.Buscar(historico.getHisId());
		
		historicoDAO.excluir(historico);
		return gson.toJson(historico);
	}
	
}
