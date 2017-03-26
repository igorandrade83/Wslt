package br.com.wslt.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder; 
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;

import br.com.wslt.dao.HistoricoDAO;
import br.com.wslt.dao.UsuarioDAO;
import br.com.wslt.domain.Historico;
import br.com.wslt.domain.Usuario;
import br.com.wslt.util.Util;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoWSBean implements Serializable {
	HistoricoWSBean historicoBean;

	private List<Historico> historicos;
	private Usuario usuario;
	private Historico historico;
	private Date dataInicio = null;
	private Date dataFim = null;
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	@PostConstruct
	public void listar() {
		try {
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://localhost:8080/Wslt/service/wslt");
			
			String json = caminho.request().get(String.class);
			Gson gson = new Gson();
			Historico[] vetor = gson.fromJson(json, Historico[].class);
			historicos = Arrays.asList(vetor);
			
		} catch (RuntimeException erro) {
			String msg = "Erro ao tentar Listar!";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
			erro.printStackTrace();
		}
	}

	public void listarComData() {
		try {
			if (dataInicio != null && dataFim != null) {
				
				String SdataInicio = Util.DataToString(dataInicio);
				String SdataFim = Util.DataToString(dataFim);
				
				Client cliente = ClientBuilder.newClient();
				WebTarget caminho = cliente.target("http://localhost:8080/Wslt/service/wslt/"+SdataInicio+"/"+SdataFim);
				
				String json = caminho.request().get(String.class);
				Gson gson = new Gson();
				Historico[] vetor = gson.fromJson(json, Historico[].class);
				historicos = Arrays.asList(vetor);
			} else {
				String msg = "Preencha todos os campos!";
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null, mensagem);
			}
		} catch (RuntimeException erro) {
			String msg = "Erro ao tentar Listar!";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
			erro.printStackTrace();
		}finally {
			limpaDatas();
		}
	}

	public void limpaInstanciaUsuario() {
		usuario = new Usuario();
	}
	
	public void limpaDatas() {
		this.dataInicio = null;
		this.dataFim = null;
	}
	

    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
	public void salvar() {
		try {
			String msg;
			if (!usuario.getUsrLastLg().isEmpty() && !usuario.getUsrLastSn().isEmpty() && !usuario.getUsrtwitLg().isEmpty() && !usuario.getUsrtwitSn().isEmpty()) {
				Client cliente = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
				WebTarget caminho = cliente.target("http://localhost:8080/Wslt/service/").path("user");
				Invocation.Builder invocationBuilder = caminho.request(MediaType.APPLICATION_JSON);
				Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
				
				//System.out.println(response.getStatus());
				//System.out.println(response.readEntity(String.class));
				msg = "Cadastro realizado com sucesso!";
			} else{
				msg = "Preencha todos os campos";
			}
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
		} catch (RuntimeException erro) {
			String msg = "Usuário já existente";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
			erro.printStackTrace();
		}finally {
			limpaInstanciaUsuario(); // Limpa atributo usuario;
		}
	}
	
	public void twittar() {
		try {
			String msg;
			if ((!usuario.getUsrLastLg().isEmpty() && !usuario.getUsrLastSn().isEmpty()) || (!usuario.getUsrtwitLg().isEmpty() && !usuario.getUsrtwitSn().isEmpty())) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				if (usuarioDAO.verificaLoginSenha(usuario)){
					msg = "Mensagem enviada com sucesso";
				}else{
					msg = "Usuário e senha não conferem.";
				}
			} else{
				msg = "Preencha todos os campos";
			}
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
		} catch (RuntimeException erro) {
			String msg = "Usuário já existente";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
			erro.printStackTrace();
		}finally {
			limpaInstanciaUsuario(); // Limpa atributo usuario;
		}
	}
}
