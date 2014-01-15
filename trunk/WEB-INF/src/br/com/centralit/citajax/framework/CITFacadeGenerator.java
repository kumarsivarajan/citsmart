package br.com.centralit.citajax.framework;

import java.util.List;

import javax.servlet.ServletContext;

import br.com.centralit.citajax.reflexao.CitAjaxReflexao;
import br.com.centralit.citajax.util.CitAjaxUtil;
import br.com.centralit.citajax.util.Constantes;

public class CITFacadeGenerator {
	/**
	 * Processa o objeto passado como parametro e retorna uma string javascript
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public String process(String path, ServletContext ctx) throws Exception{
		String strAux = "";
		String javaScriptObject = getObjectName(path);
		if (javaScriptObject == null) return null;
		Class classe = Class.forName(Constantes.getValue("BEAN_LOCATION_PACKAGE") + "." + javaScriptObject);
		if (classe != null){
			Object objeto = classe.newInstance();
			List listaGets = CitAjaxReflexao.findGets(objeto);
			
			strAux = "function " + javaScriptObject + "(";
			String strAux2 = "";
			for(int i = 0; i < listaGets.size(); i++){
				if (((String)listaGets.get(i)).equalsIgnoreCase("class")) continue;
				
				//Class  r = Reflexao.getReturnType(objeto,(String)listaGets.get(i));
				strAux2 += "\tthis." + CitAjaxUtil.convertePrimeiraLetra((String)listaGets.get(i), "L") + " = function(){;\n";
				strAux2 += "\t\t";
				/*
				  reqEventos = getBrowserAJAX();
				  var obj = new Object();
				  obj.method = 'execute';
				  obj.parmCount = 3;  
				  obj.parm1 = elem.form.name;
				  obj.parm2 = elem.name;
				  obj.parm3 = 'change';
				  submitObject(reqEventos, obj,'../../cit/executeFacade/AjaxProcessEvent.jx', fCallBackEvent);				
				*/
				strAux2 += "\t}";
			}
			strAux2 += "\tthis.idControleCITFramework = null;\n";
			
			strAux += "){\n";
			strAux += strAux2;
			strAux += "} ";
		}
		
		return strAux;
	}
	public String getObjectName(String path){
		if (path.length() - 3 <= 0) return "";
		String strResult = "";
		for(int i = path.length() - 4; i >= 0; i--){
			if (path.charAt(i) == '/'){
				return strResult;
			}else{
				strResult = path.charAt(i) + strResult; 
			}
		}
		return strResult;
	}

}
