package br.com.centralit.citcorpore.negocio;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.integracao.ControleContratoModuloSistemaDao;
import br.com.centralit.citcorpore.integracao.ModuloSistemaDao;
import br.com.centralit.citcorpore.integracao.SistemaOperacionalDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.CrudDAO;
import br.com.citframework.service.CrudServicePojoImpl;

/**
 * @author Pedro
 *
 */
@SuppressWarnings({"serial","rawtypes"})
public class ControleContratoModuloSistemaServiceEjb extends CrudServicePojoImpl implements
	ControleContratoModuloSistemaService {

    protected CrudDAO getDao() throws ServiceException {
	return new ControleContratoModuloSistemaDao();
    }

    protected void validaCreate(Object arg0) throws Exception {
    }

    protected void validaDelete(Object arg0) throws Exception {
    }

    protected void validaUpdate(Object arg0) throws Exception {
    }

    protected void validaFind(Object obj) throws Exception {
    }

	public Collection list(List ordenacao) throws LogicException, RemoteException, ServiceException {
	return null;
    }

    public Collection list(String ordenacao) throws LogicException, RemoteException,
	    ServiceException {
	return null;
    }

	@Override
	public void deleteByIdControleContrato(ControleContratoDTO controleContrato)
			throws Exception {
		ControleContratoModuloSistemaDao dao = new ControleContratoModuloSistemaDao();
		dao.deleteByIdControleContrato(controleContrato);
		
	}

}
