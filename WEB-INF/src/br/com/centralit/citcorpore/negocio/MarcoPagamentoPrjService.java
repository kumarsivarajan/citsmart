package br.com.centralit.citcorpore.negocio;
import java.util.Collection;

import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudServicePojo;
public interface MarcoPagamentoPrjService extends CrudServicePojo {
	public Collection findByIdProjeto(Integer parm) throws Exception;
	public void deleteByIdProjeto(Integer parm) throws Exception;
	public void saveFromCollection(Collection colItens, Integer idProjetoParm) throws ServiceException, LogicException;
}
