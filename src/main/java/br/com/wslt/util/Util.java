package br.com.wslt.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Util {
	
	static public String DataToString(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		return formato.format(data);
	}
	
	@SuppressWarnings("finally")
	static public Date StringToData(String dataString){	
			Date data = null;
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			data = formato.parse(dataString.replace("-", "/"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return data;
		}
	}
}
