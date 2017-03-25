package br.com.wslt.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.wslt.dao.HistoricoDAO;
import br.com.wslt.dao.UsuarioDAO;
import br.com.wslt.domain.Historico;
import br.com.wslt.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {
	HistoricoBean historicoBean;

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
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicos = historicoDAO.Listar();

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
				HistoricoDAO historicoDAO = new HistoricoDAO();
				historicos = historicoDAO.ListarComDatas(dataInicio, dataFim);
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
		}
	}

	public void instancia() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);

			// Limpa atributo usuario;
			instancia();
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicos = historicoDAO.Listar();

			String msg = "Mensagem enviada com sucesso!";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
		} catch (RuntimeException erro) {
			String msg = "Erro ao tentar salvar!";
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, mensagem);
			erro.printStackTrace();
		}
	}
}
