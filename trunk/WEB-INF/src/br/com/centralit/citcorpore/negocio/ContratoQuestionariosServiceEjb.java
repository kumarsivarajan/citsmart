package br.com.centralit.citcorpore.negocio;

import java.sql.Date;
import java.util.Collection;

import br.com.centralit.citcorpore.bean.ContratoQuestionariosDTO;
import br.com.centralit.citcorpore.bean.ControleQuestionariosDTO;
import br.com.centralit.citcorpore.integracao.ContratoQuestionariosDao;
import br.com.centralit.citcorpore.integracao.ControleQuestionariosDao;
import br.com.centralit.citquestionario.integracao.RespostaItemQuestionarioDao;
import br.com.centralit.citquestionario.negocio.RespostaItemQuestionarioServiceBean;
import br.com.citframework.dto.IDto;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.CrudDAO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServicePojoImpl;
import br.com.citframework.util.UtilDatas;

public class ContratoQuestionariosServiceEjb extends CrudServicePojoImpl implements ContratoQuestionariosService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected CrudDAO getDao() throws ServiceException {
		
		return new ContratoQuestionariosDao();
	}

	protected void validaCreate(Object arg0) throws Exception {
		
	}

	protected void validaDelete(Object arg0) throws Exception {
		
	}

	protected void validaFind(Object arg0) throws Exception {
		
	}

	protected void validaUpdate(Object arg0) throws Exception {
		
	}
	public Collection listByIdContratoAndAba(Integer idContrato, String aba) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.listByIdContratoAndAba(idContrato, aba);
	}
	public Collection listByIdContratoAndAbaOrdemCrescente(Integer idContrato, String aba) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.listByIdContratoAndAbaOrdemCrescente(idContrato, aba);
	}
	public ContratoQuestionariosDTO getUltimoByIdContratoAndAba(Integer idContrato, String aba) throws Exception {
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.getUltimoByIdContratoAndAba(idContrato, aba);		
	}
	public ContratoQuestionariosDTO getUltimoByIdContratoAndAbaAndPeriodo(Integer idContrato, String aba, Date dataInicio, Date dataFim) throws Exception {
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.getUltimoByIdContratoAndAbaAndPeriodo(idContrato, aba, dataInicio, dataFim);		
	}
	public Collection listByIdContratoAndQuestionario(Integer idQuestionario, Integer idContrato) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.listByIdContratoAndQuestionario(idQuestionario, idContrato);
	}
	public Collection listByIdContrato(Integer idContrato) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.listByIdContrato(idContrato);
	}
	public Collection listByIdContratoOrderDecrescente(Integer idContrato) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.listByIdContratoOrderDecrescente(idContrato);
	}
	public Collection listByIdContratoOrderIdDecrescente(Integer idContrato) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.listByIdContratoOrderIdDecrescente(idContrato);
	}
	public ContratoQuestionariosDTO getQuantidadeByIdDepEstabAndAba(Integer idDepartamento, Integer idEstabelecimento, Integer idCargo, String aba) throws Exception {
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.getQuantidadeByIdDepEstabAndAba(idDepartamento, idEstabelecimento, idCargo, aba);		
	}
	public ContratoQuestionariosDTO getQuantidadeByIdDepEstabAndAbaAndPeriodo(Integer idDepartamento, Integer idEstabelecimento, 
			Integer idCargo, String aba, Date dataInicio, Date dataFim) throws Exception {
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.getQuantidadeByIdDepEstabAndAbaAndPeriodo(idDepartamento, idEstabelecimento, idCargo, aba, dataInicio, dataFim);		
	}
	public ContratoQuestionariosDTO getQuantidadeByIdDepEstabAndAbaAndPeriodoFinalizados(Integer idDepartamento, Integer idEstabelecimento, 
			Integer idCargo, String aba, Date dataInicio, Date dataFim) throws Exception {
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		return dao.getQuantidadeByIdDepEstabAndAbaAndPeriodoFinalizados(idDepartamento, idEstabelecimento, idCargo, aba, dataInicio, dataFim);		
	}

    public ContratoQuestionariosDTO getQuantidadeByIdDepEstabFuncaoAndAbaAndPeriodo(Integer idDepartamento, Integer idEstabelecimento, 
            Integer idCargo, Integer idFuncao, String aba, Date dataInicio, Date dataFim) throws Exception {
        ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
        return dao.getQuantidadeByIdDepEstabFuncaoAndAbaAndPeriodo(idDepartamento, idEstabelecimento, idCargo, idFuncao, aba, dataInicio, dataFim);     
    }

    public IDto create(IDto model) throws ServiceException, LogicException {
		//Instancia Objeto controlador de transacao
		CrudDAO crudDao = getDao();
		ControleQuestionariosDao controleQuestionariosDao = new ControleQuestionariosDao();

        TransactionControler tc = new TransactionControlerImpl(crudDao.getAliasDB());
		
		ContratoQuestionariosDTO contratoQuestionariosDTO = (ContratoQuestionariosDTO)model;
		
		RespostaItemQuestionarioServiceBean respostaItemQuestionarioServiceBean = new RespostaItemQuestionarioServiceBean();
		try{
			//Faz validacao, caso exista.
			validaCreate(model);
			
			//Seta o TransactionController para os DAOs
			crudDao.setTransactionControler(tc);
			controleQuestionariosDao.setTransactionControler(tc);
			
			//Inicia transacao
			tc.start();
			
			ControleQuestionariosDTO controleQuestionariosDto = new ControleQuestionariosDTO();
			controleQuestionariosDto = (ControleQuestionariosDTO) controleQuestionariosDao.create(controleQuestionariosDto);
			
			contratoQuestionariosDTO.setIdContratoQuestionario(controleQuestionariosDto.getIdControleQuestionario());
			
			//Executa operacoes pertinentes ao negocio.
			contratoQuestionariosDTO.setDatahoragrav(UtilDatas.getDataHoraAtual());
			model = crudDao.create(model);
			
			Integer idIdentificadorResposta = contratoQuestionariosDTO.getIdContratoQuestionario();
			
			respostaItemQuestionarioServiceBean.processCollection(tc, contratoQuestionariosDTO.getColValores(), contratoQuestionariosDTO.getColAnexos(), idIdentificadorResposta, null);
			
			//Faz commit e fecha a transacao.
			tc.commit();
			tc.close();

			return model;
		}catch(Exception e){
			this.rollbackTransaction(tc, e);
		}
		return model;
	}
	
	public void update(IDto model) throws ServiceException, LogicException {
		String PRONTUARIO_TIPO_CAPT_CERT_DIGITAL = "APPLET";
		//Instancia Objeto controlador de transacao
		CrudDAO crudDao = getDao();
		TransactionControler tc = new TransactionControlerImpl(crudDao.getAliasDB());
		
		RespostaItemQuestionarioDao respostaItemDao = new RespostaItemQuestionarioDao();
		
		ContratoQuestionariosDTO contratoQuestionariosDTO = (ContratoQuestionariosDTO)model;
		
		RespostaItemQuestionarioServiceBean respostaItemQuestionarioServiceBean = new RespostaItemQuestionarioServiceBean();
		try{
			//Faz validacao, caso exista.
			validaUpdate(model);
			
			//Seta o TransactionController para os DAOs
			crudDao.setTransactionControler(tc);
			respostaItemDao.setTransactionControler(tc);
			
			Integer idIdentificadorResposta = contratoQuestionariosDTO.getIdContratoQuestionario();
			
			//Inicia transacao
			tc.start();
			
			respostaItemDao.deleteByIdIdentificadorResposta(idIdentificadorResposta);			
			
			//Executa operacoes pertinentes ao negocio.
			contratoQuestionariosDTO.setDatahoragrav(UtilDatas.getDataHoraAtual());
			crudDao.update(model);
			
			respostaItemQuestionarioServiceBean.processCollection(tc, contratoQuestionariosDTO.getColValores(), contratoQuestionariosDTO.getColAnexos(), idIdentificadorResposta, null);
			
			//Faz commit e fecha a transacao.
			tc.commit();
			tc.close();
		}catch(Exception e){
			this.rollbackTransaction(tc, e);
		}
	}
	
	public void updateConteudoImpresso(Integer idPessQuest, String conteudoImpresso) throws Exception{
		ContratoQuestionariosDao dao = new ContratoQuestionariosDao();
		dao.updateConteudoImpresso(idPessQuest, conteudoImpresso);
	}	
}
