package br.com.centralit.citgerencial.generateservices.incidentes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.centralit.citcorpore.integracao.GraficosDao;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citgerencial.bean.GerencialGenerateService;
import br.com.citframework.util.SQLConfig;

/**
 * @author rodrigo.oliveira
 * @since 14/08/2012
 */
public class GenerateServiceIncidentesPorServico extends GerencialGenerateService {

	private HashMap novoParametro = new HashMap();
	
	public List execute(HashMap parametersValues, Collection paramtersDefinition) throws ParseException {
		
	    Set set = parametersValues.entrySet();
	    Iterator i = set.iterator();

	    while(i.hasNext()){
	      Map.Entry entrada = (Map.Entry)i.next();
	      getNovoParametro().put(entrada.getKey(), entrada.getValue());
	    }
	    
		String datainicial = (String) getNovoParametro().get("PARAM.dataInicial");
		String datafinal = (String) getNovoParametro().get("PARAM.dataFinal");
		
		Date datafim = new Date();
		Date datainicio = new Date();
		SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
		try {
			datainicio = new SimpleDateFormat("dd/MM/yyyy").parse(datainicial);
			datafim = new SimpleDateFormat("dd/MM/yyyy").parse(datafinal); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(datafim);  
		calendar.add(GregorianCalendar.DATE, 1);  
		
		if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase(SQLConfig.MYSQL)){
		    getNovoParametro().put("PARAM.dataInicial", formatoBanco.format(datainicio));
		    getNovoParametro().put("PARAM.dataFinal", formatoBanco.format(calendar.getTime()));
		}else{
		    getNovoParametro().put("PARAM.dataInicial", new java.sql.Date(datainicio.getTime()));
    		    getNovoParametro().put("PARAM.dataFinal", new java.sql.Date(calendar.getTime().getTime()));			    
		}
		
		Collection col = null;
		List listaRetorno = null;
		GraficosDao graficosDao = new GraficosDao();
		
		try {
			col = graficosDao.consultaIncidentesPorServico(getNovoParametro(), "I");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(col != null && !col.isEmpty()){
			listaRetorno = (List) col;
		}else{
			listaRetorno = new ArrayList();
		}
		
		//resetando par�metro
		setNovoParametro(null);
		
		return listaRetorno;
	}
	
	public HashMap getNovoParametro() {
		return novoParametro;
	}

	public void setNovoParametro(HashMap novoParametro) {
		this.novoParametro = novoParametro;
	}

}
