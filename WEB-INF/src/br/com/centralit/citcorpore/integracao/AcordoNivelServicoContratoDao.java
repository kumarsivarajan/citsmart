package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import br.com.centralit.citcorpore.bean.AcordoNivelServicoContratoDTO;
import br.com.citframework.dto.IDto;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;;

public class AcordoNivelServicoContratoDao extends CrudDaoDefaultImpl {
	public AcordoNivelServicoContratoDao() {
		super(Constantes.getValue("DATABASE_ALIAS"), null);
	}
	public Collection getFields() {
		Collection listFields = new ArrayList();
		listFields.add(new Field("idAcordoNivelServicoContrato" ,"idAcordoNivelServicoContrato", true, true, false, false));
		listFields.add(new Field("idContrato" ,"idContrato", false, false, false, false));
		listFields.add(new Field("descricaoAcordo" ,"descricaoAcordo", false, false, false, false));
		listFields.add(new Field("detalhamentoAcordo" ,"detalhamentoAcordo", false, false, false, false));
		listFields.add(new Field("valorLimite" ,"valorLimite", false, false, false, false));
		listFields.add(new Field("unidadeValorLimite" ,"unidadeValorLimite", false, false, false, false));
		listFields.add(new Field("dataInicio" ,"dataInicio", false, false, false, false));
		listFields.add(new Field("dataFim" ,"dataFim", false, false, false, false));
		listFields.add(new Field("descricaoGlosa" ,"descricaoGlosa", false, false, false, false));
		listFields.add(new Field("idFormula" ,"idFormula", false, false, false, false));
		return listFields;
	}
	public String getTableName() {
		return this.getOwner() + "AcordoNivelServicoContrato";
	}
	public Collection list() throws Exception {
		return null;
	}

	public Class getBean() {
		return AcordoNivelServicoContratoDTO.class;
	}
	public Collection find(IDto arg0) throws Exception {
		return null;
	}
	public Collection findByIdContrato(Integer parm) throws Exception {
		List condicao = new ArrayList();
		List ordenacao = new ArrayList(); 
		condicao.add(new Condition("idContrato", "=", parm)); 
		ordenacao.add(new Order("idAcordoNivelServicoContrato"));
		return super.findByCondition(condicao, ordenacao);
	}
	public void deleteByIdContrato(Integer parm) throws Exception {
		List condicao = new ArrayList();
		condicao.add(new Condition("idContrato", "=", parm));
		super.deleteByCondition(condicao);
	}
	public Collection consultaAcordoNivelServicoAtivo(Integer parm) throws Exception {
		List condicao = new ArrayList();
		List ordenacao = new ArrayList(); 
		condicao.add(new Condition("idAcordoNivelServicoContrato", "=", parm));
		ordenacao.add(new Order("idAcordoNivelServicoContrato"));
		return super.findByCondition(condicao, ordenacao);
	}
}
