package br.com.wslt.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

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
	
	static public void ConvertUTC(){
		final Date currentTime = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("UTC time: " + sdf.format(currentTime));
		
	}
}
