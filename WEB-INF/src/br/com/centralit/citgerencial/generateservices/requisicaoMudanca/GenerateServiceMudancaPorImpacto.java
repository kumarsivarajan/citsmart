package br.com.centralit.citgerencial.generateservices.requisicaoMudanca;

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
public class GenerateServiceMudancaPorImpacto extends GerencialGenerateService {

	private HashMap novoParametro = new HashMap();
	
	@SuppressWarnings({ "unchecked", "null", "rawtypes" })
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
		
		List colBaixa = new ArrayList();
		List colMedia = new ArrayList();
		List colAlta = new ArrayList();
		List listaRetorno = new ArrayList();
		int qtd = 1;
		GraficosDao graficosDao = new GraficosDao();
		
		try {
			colBaixa = (List) graficosDao.consultaMudancaPorImpacto(getNovoParametro(), "Baixa");
			colMedia = (List) graficosDao.consultaMudancaPorImpacto(getNovoParametro(), "Media");
			colAlta = (List) graficosDao.consultaMudancaPorImpacto(getNovoParametro(), "Alta");
			
		
			if (colBaixa != null && colBaixa.size() > 0) {
				listaRetorno.add(colBaixa.get(0));
			}
			if (colMedia != null && colMedia.size() > 0) {
				listaRetorno.add(colMedia.get(0));
			}
			if (colAlta != null && colAlta.size() > 0) {
				listaRetorno.add(colAlta.get(0));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*if(colBaixa != null && !colBaixa.isEmpty()){
			listaRetorno = (List) colBaixa;
		}else{
			listaRetorno = new ArrayList();
		}*/
		
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
