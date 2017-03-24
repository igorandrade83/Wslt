package br.com.wslt.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.wslt.dao.UsuarioDAO;
import br.com.wslt.domain.Usuario;

// http://localhost:8080/Wslt/service/user

@Path("user")
public class WsltServiceUser {
	
	@GET
	public String exibirUsuarioGeral(){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.Listar();
		
		Gson gson = new Gson();
		return gson.toJson(usuarios);
	}
	
	@GET
	@Path("{usrID}")
	public String buscarUsuario(@PathParam("usrID") int usrID){		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Gson gson = new Gson();

		return gson.toJson(usuarioDAO.Buscar(usrID));
	}

	// http://localhost:8080/Wslt/service/user
	@POST
	public String salvar(String json){
		Gson gson = new Gson();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		usuarioDAO.salvar(usuario);
		return gson.toJson(usuario);
	}

	@PUT
	public String editar(String json){
		Gson gson = new Gson();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		usuarioDAO.editar(usuario);
		return gson.toJson(usuario);
	}
	
	@DELETE
	public String excluir(String json){
		Gson gson = new Gson();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		usuario = usuarioDAO.Buscar(usuario.getUsrId());
		
		usuarioDAO.excluir(usuario);
		return gson.toJson(usuario);
	}

}
