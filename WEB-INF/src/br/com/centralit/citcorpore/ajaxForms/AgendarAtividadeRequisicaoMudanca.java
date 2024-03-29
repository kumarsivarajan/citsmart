package br.com.centralit.citcorpore.ajaxForms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citajax.util.Constantes;
import br.com.centralit.citcorpore.bean.AtividadePeriodicaDTO;
import br.com.centralit.citcorpore.bean.ExecucaoAtividadePeriodicaDTO;
import br.com.centralit.citcorpore.bean.GrupoAtvPeriodicaDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ProgramacaoAtividadeDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.AtividadePeriodicaService;
import br.com.centralit.citcorpore.negocio.ExecucaoAtividadePeriodicaService;
import br.com.centralit.citcorpore.negocio.GrupoAtvPeriodicaService;
import br.com.centralit.citcorpore.negocio.OcorrenciaSolicitacaoService;
import br.com.centralit.citcorpore.negocio.ProgramacaoAtividadeService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaService;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.UtilI18N;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilStrings;
@SuppressWarnings({"rawtypes","unchecked"})
public class AgendarAtividadeRequisicaoMudanca  extends AjaxFormAction{


	@Override
	public void load(DocumentHTML document, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsuarioDTO usuario = WebUtil.getUsuario(request);
		if (usuario == null){
			document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
			document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
			return;
		}
		AtividadePeriodicaDTO atividadePeriodicaDTO = (AtividadePeriodicaDTO)document.getBean();
		HTMLForm form = document.getForm("form");
		form.clear();	
		HTMLSelect idGrupoAtvPeriodica = (HTMLSelect) document.getSelectById("idGrupoAtvPeriodica");
		GrupoAtvPeriodicaService grupoAtvPeriodicaService = (GrupoAtvPeriodicaService) ServiceLocator.getInstance().getService(GrupoAtvPeriodicaService.class, null);
		Collection colGrupos = grupoAtvPeriodicaService.listGrupoAtividadePeriodicaAtiva();
		idGrupoAtvPeriodica.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
		idGrupoAtvPeriodica.addOptions(colGrupos, "idGrupoAtvPeriodica", "nomeGrupoAtvPeriodica", null);	
		
		form.setValues(atividadePeriodicaDTO);
		
		if (atividadePeriodicaDTO.getIdRequisicaoMudanca()== null){
			document.alert(UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.naoidentificarsolicitacao"));
		    document.executeScript("fechar();");
		    return;
		}
		
		AtividadePeriodicaService atividadePeriodicaService = (AtividadePeriodicaService) ServiceLocator.getInstance().getService(AtividadePeriodicaService.class, null);
		ProgramacaoAtividadeService programacaoAtividadeService = (ProgramacaoAtividadeService) ServiceLocator.getInstance().getService(ProgramacaoAtividadeService.class, null);
		ExecucaoAtividadePeriodicaService execucaoAtividadePeriodicaService = (ExecucaoAtividadePeriodicaService) ServiceLocator.getInstance().getService(ExecucaoAtividadePeriodicaService.class, null);
		Collection colAgendamentos = atividadePeriodicaService.findByIdRequisicaoMudanca(atividadePeriodicaDTO.getIdRequisicaoMudanca());
		StringBuffer strBufferAgend = new StringBuffer();
		String buffer = "";
		strBufferAgend.append("<table width='100%'>");
		strBufferAgend.append("<tr>");
		strBufferAgend.append("<td style='border:1px solid black'>");
		strBufferAgend.append("<b>&nbsp;</b>");
		strBufferAgend.append("</td>");
		strBufferAgend.append("<td style='border:1px solid black'>");
		strBufferAgend.append("<b>"+ UtilI18N.internacionaliza(request, "citcorpore.comum.criacao")+"</b>");
		strBufferAgend.append("</td>");	
		strBufferAgend.append("<td style='border:1px solid black'>");
		strBufferAgend.append("<b>"+ UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.datahoraAgendamento")+"</b>");
		strBufferAgend.append("</td>");
		strBufferAgend.append("<td style='border:1px solid black'>");
		strBufferAgend.append("<b>"+ UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.grupo")+"</b>");
		strBufferAgend.append("</td>");
		strBufferAgend.append("<td style='border:1px solid black'>");
		strBufferAgend.append("<b>"+ UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.orientacao")+"</b>");
		strBufferAgend.append("</td>");
		strBufferAgend.append("<td style='border:1px solid black'>");
		strBufferAgend.append("<b>"+ UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.execucao")+"</b>");
		strBufferAgend.append("</td>");
		strBufferAgend.append("</tr>");
		if (colAgendamentos != null){
		    for (Iterator it = colAgendamentos.iterator(); it.hasNext();){
			AtividadePeriodicaDTO atividadePeriodicaAux = (AtividadePeriodicaDTO)it.next();
			Collection colProgs = programacaoAtividadeService.findByIdAtividadePeriodicaOrderDataHora(atividadePeriodicaAux.getIdAtividadePeriodica());
			if (colProgs != null){
			    for (Iterator itProg = colProgs.iterator(); itProg.hasNext();){
				ProgramacaoAtividadeDTO programacaoAtividadeDTO = (ProgramacaoAtividadeDTO)itProg.next();
				strBufferAgend.append("<tr>");		
				strBufferAgend.append("<td style='border:1px solid black'>");
				strBufferAgend.append("#CONTROLE#");
				strBufferAgend.append("</td>");
				strBufferAgend.append("<td style='border:1px solid black'>");
					strBufferAgend.append(UtilI18N.internacionaliza(request, "citcorpore.comum.data")+": " + UtilDatas.dateToSTR(atividadePeriodicaAux.getDataCriacao()));
					strBufferAgend.append("<br>" + UtilI18N.internacionaliza(request, "login.usuario")+ ": " + atividadePeriodicaAux.getCriadoPor());
					strBufferAgend.append("<br><br>" + UtilI18N.internacionaliza(request, "gerenciaservico.codagendamento ") + ": " + atividadePeriodicaAux.getIdAtividadePeriodica());
				strBufferAgend.append("</td>");			
				strBufferAgend.append("<td style='border:1px solid black'>");
					strBufferAgend.append("<b><u>" + UtilDatas.dateToSTR(programacaoAtividadeDTO.getDataInicio()) + " " + programacaoAtividadeDTO.getHoraInicio() + "</u></b>");
					strBufferAgend.append("<br><br>" + UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.duracaoestimada") + ": " + programacaoAtividadeDTO.getDuracaoEstimada() + "min");
				strBufferAgend.append("</td>");
				GrupoAtvPeriodicaDTO grupoAtvPeriodicaDTO = new GrupoAtvPeriodicaDTO();
				grupoAtvPeriodicaDTO.setIdGrupoAtvPeriodica(atividadePeriodicaAux.getIdGrupoAtvPeriodica());
				grupoAtvPeriodicaDTO = (GrupoAtvPeriodicaDTO) grupoAtvPeriodicaService.restore(grupoAtvPeriodicaDTO);
				String grupoAtv = "--";
				if (grupoAtvPeriodicaDTO != null){
				    grupoAtv = grupoAtvPeriodicaDTO.getNomeGrupoAtvPeriodica();
				}
				strBufferAgend.append("<td style='border:1px solid black'>");
					strBufferAgend.append(grupoAtv);
				strBufferAgend.append("</td>");
				strBufferAgend.append("<td style='border:1px solid black'>");
				String strOr = atividadePeriodicaAux.getOrientacaoTecnica();
				if (strOr != null){
				    strOr = strOr.replaceAll("\n", "<br>");
				}else{
				    strOr = "&nbsp;";
				}
					strBufferAgend.append(strOr);
				strBufferAgend.append("</td>");
				strBufferAgend.append("<td style='border:1px solid black'>");
				StringBuffer strExecucao = new StringBuffer();
				String finalizado = "";
					Collection colExecucoes = execucaoAtividadePeriodicaService.findByIdAtividadePeriodica(atividadePeriodicaAux.getIdAtividadePeriodica());
					if (colExecucoes != null){
					    for (Iterator itExc = colExecucoes.iterator(); itExc.hasNext();){
						ExecucaoAtividadePeriodicaDTO execucaoAtividadePeriodicaDTO = (ExecucaoAtividadePeriodicaDTO) itExc.next();
						strExecucao.append(UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.dataexecucao")+ " " + UtilDatas.dateToSTR(execucaoAtividadePeriodicaDTO.getDataExecucao()) + " " + execucaoAtividadePeriodicaDTO.getHoraExecucao());
						strExecucao.append("<br>"+ UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.dataregistro")+ " " + UtilDatas.dateToSTR(execucaoAtividadePeriodicaDTO.getDataRegistro()) + " " + execucaoAtividadePeriodicaDTO.getHoraRegistro());
						strExecucao.append("<br>"+ UtilI18N.internacionaliza(request, "colaborador.situacao") +":<b><u>" + execucaoAtividadePeriodicaDTO.getSituacaoDescr() + "</u></b>");
						strExecucao.append("<br>"+UtilI18N.internacionaliza(request, "gerenciaservico.detalhamentoexecucao") +":<br> " + UtilStrings.nullToVazio(execucaoAtividadePeriodicaDTO.getDetalhamento()));
						if (!execucaoAtividadePeriodicaDTO.getSituacao().equalsIgnoreCase("F")){
						    finalizado = "N";
						}
						if (finalizado.equalsIgnoreCase("") && execucaoAtividadePeriodicaDTO.getSituacao().equalsIgnoreCase("F")){
						    finalizado = "S";
						}
					  }
					}
				if (strExecucao.toString().equalsIgnoreCase("")){
				    strBufferAgend.append(UtilI18N.internacionaliza(request, "gerenciaservico.semregistro"));
				}else{
				    strBufferAgend.append(strExecucao);
				}
				strBufferAgend.append("</td>");
				
				strBufferAgend.append("</tr>");
				
				buffer = strBufferAgend.toString();
				
				if (finalizado.equalsIgnoreCase("S")){
					buffer = strBufferAgend.toString();
					buffer = buffer.replaceAll("\\#CONTROLE\\#", "<img src='" + Constantes.getValue("SERVER_ADDRESS") +
	    				Constantes.getValue("CONTEXTO_APLICACAO") + "/imagens/tick.png' border='0'/>");
				}else{
					buffer = buffer.replaceAll("\\#CONTROLE\\#", "<img src='" + Constantes.getValue("SERVER_ADDRESS") +
		    				Constantes.getValue("CONTEXTO_APLICACAO") + "/imagens/relogio.png' border='0'/>");			    
				}
			    }
			}
		    }
		}
		strBufferAgend.append("</table>");
		document.getElementById("divAgendamentos").setInnerHTML(buffer);
		
	}
	
	public void save(DocumentHTML document, HttpServletRequest request, HttpServletResponse response) throws Exception {
	UsuarioDTO usuario = WebUtil.getUsuario(request);
	if (usuario == null){
		document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
		document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
		return;
	}
	AtividadePeriodicaDTO atividadePeriodicaDTO = (AtividadePeriodicaDTO)document.getBean();
	if (atividadePeriodicaDTO.getDuracaoEstimada() == null || atividadePeriodicaDTO.getDuracaoEstimada().intValue() == 0){
	    document.alert(UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.valida.duracao"));
	    return;
	}
	RequisicaoMudancaService requisicaoMudancaService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(RequisicaoMudancaService.class, WebUtil.getUsuarioSistema(request));
	GrupoAtvPeriodicaService grupoAtvPeriodicaService = (GrupoAtvPeriodicaService) ServiceLocator.getInstance().getService(GrupoAtvPeriodicaService.class, null);
	RequisicaoMudancaDTO requisicaoMudancaDto = requisicaoMudancaService.restoreAll(atividadePeriodicaDTO.getIdRequisicaoMudanca());
	String orient = "";
	String ocorr = "";
	if (!ocorr.equalsIgnoreCase("")) ocorr += "\n";
	ocorr += UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.dataagendamento") +" " + UtilDatas.dateToSTR(atividadePeriodicaDTO.getDataInicio());
	if (!ocorr.equalsIgnoreCase("")) ocorr += "\n";
	ocorr += UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.horaagendamento") + " " + atividadePeriodicaDTO.getHoraInicio();
	if (!ocorr.equalsIgnoreCase("")) ocorr += "\n";
	ocorr += UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.duracaoestimada") +" " + atividadePeriodicaDTO.getDuracaoEstimada();
	GrupoAtvPeriodicaDTO grupoAtvPeriodicaDTO = new GrupoAtvPeriodicaDTO();
	grupoAtvPeriodicaDTO.setIdGrupoAtvPeriodica(atividadePeriodicaDTO.getIdGrupoAtvPeriodica());
	grupoAtvPeriodicaDTO = (GrupoAtvPeriodicaDTO) grupoAtvPeriodicaService.restore(grupoAtvPeriodicaDTO);
	if (grupoAtvPeriodicaDTO != null){
	    ocorr += "\n"+UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.grupo") + ": " + grupoAtvPeriodicaDTO.getNomeGrupoAtvPeriodica();
	}
	if (atividadePeriodicaDTO.getOrientacaoTecnica() != null){
	    orient = atividadePeriodicaDTO.getOrientacaoTecnica();
	    ocorr += "\n"+ UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.orientacaotecnica") +": \n" + atividadePeriodicaDTO.getOrientacaoTecnica();
	}
	orient += "\n\n"+ UtilI18N.internacionaliza(request, "requisicaoMudanca.requisicaoMudanca") +": \n" + requisicaoMudancaDto.getDescricao();
	
	atividadePeriodicaDTO.setTituloAtividade(UtilI18N.internacionaliza(request, "gerenciaservico.agendaratividade.requisicaoMudanca") +" " + atividadePeriodicaDTO.getIdRequisicaoMudanca());
	atividadePeriodicaDTO.setDescricao(requisicaoMudancaDto.getDescricao());
	atividadePeriodicaDTO.setDataCriacao(UtilDatas.getDataAtual());
	atividadePeriodicaDTO.setCriadoPor(usuario.getNomeUsuario());
	atividadePeriodicaDTO.setIdContrato(requisicaoMudancaDto.getIdContrato());
	atividadePeriodicaDTO.setOrientacaoTecnica(orient);
	
	Collection colItens = new ArrayList();
	ProgramacaoAtividadeDTO programacaoAtividadeDTO = new ProgramacaoAtividadeDTO();
	programacaoAtividadeDTO.setTipoAgendamento("U");
	programacaoAtividadeDTO.setDataInicio(atividadePeriodicaDTO.getDataInicio());
	programacaoAtividadeDTO.setHoraInicio(atividadePeriodicaDTO.getHoraInicio());
	programacaoAtividadeDTO.setHoraFim("00:00");
	programacaoAtividadeDTO.setDuracaoEstimada(atividadePeriodicaDTO.getDuracaoEstimada());
	programacaoAtividadeDTO.setRepeticao("N");
	colItens.add(programacaoAtividadeDTO);
	
	AtividadePeriodicaService atividadePeriodicaService = (AtividadePeriodicaService) ServiceLocator.getInstance().getService(AtividadePeriodicaService.class, null);
	atividadePeriodicaDTO.setColItens(colItens);
	atividadePeriodicaService.create(atividadePeriodicaDTO);
	
	document.alert(UtilI18N.internacionaliza(request, "MSG05"));
	
	OcorrenciaSolicitacaoService ocorrenciaSolicitacaoService = (OcorrenciaSolicitacaoService) ServiceLocator.getInstance().getService(OcorrenciaSolicitacaoService.class, null);
	OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO = new OcorrenciaSolicitacaoDTO();
	ocorrenciaSolicitacaoDTO.setIdSolicitacaoServico(atividadePeriodicaDTO.getIdSolicitacaoServico());
	ocorrenciaSolicitacaoDTO.setDataregistro(UtilDatas.getDataAtual());
	ocorrenciaSolicitacaoDTO.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
	ocorrenciaSolicitacaoDTO.setTempoGasto(0);
	ocorrenciaSolicitacaoDTO.setDescricao(Enumerados.CategoriaOcorrencia.Agendamento.getDescricao());
	ocorrenciaSolicitacaoDTO.setDataInicio(UtilDatas.getDataAtual());
	ocorrenciaSolicitacaoDTO.setDataFim(UtilDatas.getDataAtual());
	ocorrenciaSolicitacaoDTO.setInformacoesContato(UtilI18N.internacionaliza(request, "MSG013"));
	ocorrenciaSolicitacaoDTO.setRegistradopor(usuario.getNomeUsuario());
	ocorrenciaSolicitacaoDTO.setOcorrencia(ocorr);
	ocorrenciaSolicitacaoDTO.setOrigem(Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
	ocorrenciaSolicitacaoDTO.setCategoria(Enumerados.CategoriaOcorrencia.Agendamento.getSigla());
	ocorrenciaSolicitacaoDTO.setIdItemTrabalho(requisicaoMudancaDto.getIdTarefa());
	ocorrenciaSolicitacaoService.create(ocorrenciaSolicitacaoDTO);
	
	document.executeScript("fechar();");
    }

	@Override
	public Class getBeanClass() {
		return AtividadePeriodicaDTO.class;
	}

}
