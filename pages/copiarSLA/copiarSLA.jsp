
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/cit" prefix="cit" %>
<%@page import="br.com.centralit.citcorpore.util.WebUtil"%>
<%@page import="br.com.centralit.citcorpore.bean.UsuarioDTO"%>
<%@page import="br.com.centralit.citcorpore.util.CitCorporeConstantes"%>
<%@ taglib uri="/tags/i18n" prefix="i18n"%>
<%
	response.setCharacterEncoding("ISO-8859-1");
	response.setHeader( "Cache-Control", "no-cache");
	response.setHeader( "Pragma", "no-cache");
	response.setDateHeader ( "Expires", -1);
%>
 <!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@include file="/include/noCache/noCache.jsp" %>


	<%@include file="/include/titleComum/titleComum.jsp" %>
	
<%@include file="/include/header.jsp" %>
<%@include file="/include/javaScriptsComuns/javaScriptsComuns.jsp"%>
	
	<script type="text/javascript" src="../../cit/objects/DemandaDTO.js"></script> 
	
	<script>
	var objTab = null;
	
	addEvent(window, "load", load, false);
	function load(){
		document.form.afterRestore = function () {
		}
	}
</script>

<!-- Area de JavaScripts -->
<script>
	function gravarForm(){
		JANELA_AGUARDE_MENU.show();
		document.form.save();
	}
	
	var checkedFlag = false;
	
	function marcaTodas(){
		
		var arrayElements = document.getElementsByName('idServicoCopiarPara');
		
	 	if(checkedFlag == false){
			for(var i=0;i<arrayElements.length;i++){
				if(arrayElements[i].type='checkbox'){
					arrayElements[i].checked = true;
				}
			}
			checkedFlag = true;
			return;
	 	}else{
	 		for(var i=0;i<arrayElements.length;i++){
				if(arrayElements[i].type='checkbox'){
					arrayElements[i].checked = false;
				}
			}
	 		checkedFlag = false;
	 		return;
	 	}
	 	
	}
	
</script>
	
</head>

<!-- Definicoes Comuns -->
<cit:janelaAguarde id="JANELA_AGUARDE_MENU" title="<i18n:message key='citcorpore.comum.aguardeProcessando'/>" style="display:none;top:100px;width:300px;left:200px;height:50px;position:absolute;">
</cit:janelaAguarde>

<body>

<div id="paginaTotal" >
	<div id="areautil">
		<div id="formularioIndex">
       		<div id=conteudo>
				<table width="100%">
					<tr>
						<td width="100%">
								<h2><b><i18n:message key="citcorpore.comum.copiarSLA"/></b></h2>
								<!-- ## AREA DA APLICACAO ## -->
										 	<form name='form' action='<%=CitCorporeConstantes.CAMINHO_SERVIDOR%><%=request.getContextPath()%>/pages/copiarSLA/copiarSLA'>
										 		<input type='hidden' name='idServicoContrato'/>
										 		<input type='hidden' name='idContrato'/>
										 		<input type='hidden' name='idAcordoNivelServico'/>
											  	<table id="tabFormulario" cellpadding="0" cellspacing="0">
											  		<tr>
											  			<td>
											  				<b><i18n:message key="citcorpore.comum.copiarAcordo"/>:</b>
											  			</td>
											  			<td>
											  				<b><i18n:message key="citcorpore.comum.paraServicosDesteContrato"/>:</b>											  				
											  			</td>
											  		</tr>											  	
											  		<tr>
											  			<td>
											  				<div id='slaCopiar' style='width: 300px; height: 300px; overflow: auto; border: 1px solid black'>
											  				</div>
											  			</td>
											  			<td>
											  				<div id='copiarPara' style='width: 600px; height: 300px; overflow: auto; border: 1px solid black'>
											  				</div>											  				
											  			</td>
											  		</tr>
										         <tr>
										         	<td>
										         		<button type='button' id="btnGravar" name='btnGravar' style="margin-top: 5px; margin-left: 3px" class="light img_icon has_text" onclick="gravarForm()">
															<img src="<%=br.com.citframework.util.Constantes.getValue("CONTEXTO_APLICACAO")%>/template_new/images/icons/small/grey/pencil.png">
															<span><i18n:message key="dinamicview.gravardados"/></span>
														</button>
														<button type='button' id="btnFechar" name='btnFechar' style="margin-top: 5px; margin-left: 3px" class="light img_icon has_text" onclick="parent.limparAreaInformacao();parent.fecharVisao()">
															<img src="<%=br.com.citframework.util.Constantes.getValue("CONTEXTO_APLICACAO")%>/template_new/images/icons/small/grey/trashcan.png">
															<span><i18n:message key="citcorpore.comum.fechar"/></span>
														</button>
									         		</td>
										         	<td>
										         		<div style="margin-top: 5px;">
											         		<input type='checkbox' name='selecionarTodas' id='selecionarTodas' onclick='marcaTodas();' style='float:left;' />
											         		<label style='float:left;'><i18n:message key="citcorpore.comum.selecionarTodas"/></label>
										         		</div>
									         		</td>									         		
									         	</tr>
										</table>
									</form>
						</td>
					</tr>
				</table>
			</div>	
		</div>
	</div>
</div>

</body>
</html>
							