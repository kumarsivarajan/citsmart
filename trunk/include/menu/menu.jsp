<%@ taglib uri="/tags/cit" prefix="cit" %>

<%@page import="br.com.citframework.util.UtilDatas"%>


						<cit:Menu/>
					


<script type="text/javascript">
var listMenu = new FSMenu('listMenu', true, 'display', 'block', 'none');
listMenu.animations[listMenu.animations.length] = FSMenu.animFade;

var arrow = null;
if (document.createElement && document.documentElement)
{
 arrow = document.createElement('img');
 arrow.src = '<%=br.com.citframework.util.Constantes.getValue("SERVER_ADDRESS")%>
 <%=br.com.citframework.util.Constantes.getValue("CONTEXTO_APLICACAO")%>/produtos/citsaude/imagens/v_arrow.gif';
 arrow.style.borderWidth = '0';
 arrow.className = 'subind';
}
addEvent(window, 'load', new Function('listMenu.activateMenu("listMenuRoot", arrow)'));


</script>