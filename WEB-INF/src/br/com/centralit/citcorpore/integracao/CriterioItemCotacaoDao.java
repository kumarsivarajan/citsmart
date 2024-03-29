package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.centralit.citcorpore.bean.CriterioItemCotacaoDTO;
import br.com.citframework.dto.IDto;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class CriterioItemCotacaoDao extends CrudDaoDefaultImpl {
	public CriterioItemCotacaoDao() {
		super(Constantes.getValue("DATABASE_ALIAS"), null);
	}
	public Collection getFields() {
		Collection listFields = new ArrayList();
		listFields.add(new Field("idCriterio" ,"idCriterio", true, false, false, false));
		listFields.add(new Field("idItemCotacao" ,"idItemCotacao", true, false, false, false));
		listFields.add(new Field("peso" ,"peso", false, false, false, false));
		return listFields;
	}
	public String getTableName() {
		return this.getOwner() + "CriterioItemCotacao";
	}
	public Collection list() throws Exception {
		return null;
	}

	public Class getBean() {
		return CriterioItemCotacaoDTO.class;
	}
	public Collection find(IDto arg0) throws Exception {
		return null;
	}
	public Collection findByIdItemCotacao(Integer parm) throws Exception {
		List condicao = new ArrayList();
		List ordenacao = new ArrayList(); 
		condicao.add(new Condition("idItemCotacao", "=", parm)); 
		ordenacao.add(new Order("idCriterio"));
		return super.findByCondition(condicao, ordenacao);
	}
	public void deleteByIdItemCotacao(Integer parm) throws Exception {
		List condicao = new ArrayList();
		condicao.add(new Condition("idItemCotacao", "=", parm));
		super.deleteByCondition(condicao);
	}
}
