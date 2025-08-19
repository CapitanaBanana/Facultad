package ar.edu.unlp.info.oo2.biblioteca;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONSimpleAdapter extends VoorheesExporter{
    @Override
    public String exportar(List<Socio> socios){
        if (socios.isEmpty()) {
			return "[]";
		}
		JSONArray lista= new JSONArray();
		socios.forEach(socio -> {
			JSONObject s= new JSONObject();
			s.put("nombre", socio.getNombre());
			s.put("email", socio.getEmail());
			s.put("legajo", socio.getLegajo());
			lista.add(s);
		});
		return lista.toJSONString();
    }
}
