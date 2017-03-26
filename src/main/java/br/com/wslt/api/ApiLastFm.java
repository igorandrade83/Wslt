package br.com.wslt.api;

import java.net.Proxy;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.google.gson.Gson;

import br.com.wslt.dao.HistoricoDAO;
import br.com.wslt.domain.Historico;
import br.com.wslt.domain.Usuario;


public class ApiLastFm {
	private String key = "c71049718b5efcd7a2578bab40b8c668";
	private List<Historico> historicos = new ArrayList<Historico>();
	private Historico historico = new Historico();
	
	public void RecebeListaApiLast(String usrLastLg) throws JSONException{
		Client cliente = ClientBuilder.newClient();
		
		//WebService do LastFm
		String url = "http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user="+usrLastLg+"&api_key="+key+"&format=json";
		WebTarget caminhoLastFm = cliente.target(url);
		
		//WebService NOsso
		WebTarget caminhoWslt = cliente.target("http://localhost:8080/Wslt/service/user/last/"+usrLastLg);
		String jsonWslt = caminhoWslt.request().get(String.class);
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(jsonWslt, Usuario.class);
	
		//Coleta informações do Json que vem do LastFm
		String jsonLastFm = caminhoLastFm.request().get(String.class);		
        JSONObject jo = new JSONObject(jsonLastFm);
        JSONObject j = jo.getJSONObject("recenttracks");
        JSONArray ja = j.getJSONArray("track");

        //int count = ja.length(); -> Limitando em 10;
        for (int i = 0; i < 10; i++) {
        	
        	//Retira sujeira do nome do artista (a identificação do album as vezes acompanha.
            JSONObject jsonObject = ja.getJSONObject(i);
            String artistaRecortado = (String) jsonObject.getString("artist").subSequence(10, jsonObject.getString("artist").length()-12);
            int indiceNumeracaoDisco = artistaRecortado.indexOf("\"");
            if (indiceNumeracaoDisco > 0){
            	artistaRecortado = artistaRecortado.substring(0, indiceNumeracaoDisco);
            }
            
            historico.setHisArtista(artistaRecortado);
            historico.setHisMusica(jsonObject.getString("name"));
            historico.setUsuario(usuario);
            historico.setHisData(new Date());
            
            historicos.add(historico);
            historico = new Historico();
        }
        SalvaListaHistorico(historicos);
	}
	
	public void SalvaListaHistorico(List<Historico> historicos) throws JSONException{
		 HistoricoDAO historicoDAO = new HistoricoDAO();
		 
		 for (int i = 0; i < historicos.size(); i++) {
			 historicoDAO.salvar(historicos.get(i));
		 }
	}
}
