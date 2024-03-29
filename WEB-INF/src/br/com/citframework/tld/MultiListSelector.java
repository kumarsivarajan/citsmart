package br.com.citframework.tld;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class MultiListSelector extends BodyTagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 32377344027452200L;
	private String formName;
	private String name;
	private String property;
	private String collection;
	private String labelProperty;
	private String width;
	private String emptyItemLabel;
	private String lenSecondList;
	private String permitDuplicate;
	public int doStartTag() throws JspException {
		StringBuffer strBuff = new StringBuffer();
		Method metodo1 = null;
		Method metodo2 = null;
		Object[] aux = null;
		Object value = null;
		Object desc = null;
		
		String id = this.getProperty();
		String label = this.getLabelProperty();
		id = "get" + id.substring(0,1).toUpperCase() + id.substring(1);
		label = "get" + label.substring(0,1).toUpperCase() + label.substring(1);
		
		Collection col = (Collection)pageContext.getRequest().getAttribute(this.getCollection());
		if (col == null){
			col = (Collection)pageContext.getSession().getAttribute(this.getCollection());
		}
		if (col == null){
			col = new ArrayList();
		}
		Iterator it = col.iterator();
		Object obj;
		
		strBuff.append("<input type='hidden' name='valores"+getName()+"'>");
		
		strBuff.append("<table>");
		strBuff.append("	<tr>");
		strBuff.append("		<td>");
		
		strBuff.append("			<select name='" + getName() + "_select' style='width:"+getWidth()+"'>\n");
		strBuff.append("				<option value=''>"+getEmptyItemLabel()+"</option>\n");
		while(it.hasNext()){
			obj = (Object)it.next();
			try {
				metodo1 = obj.getClass().getMethod(label, null);
				metodo2 = obj.getClass().getMethod(id, null);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			try {
				desc = metodo1.invoke(obj, aux);				
				value = metodo2.invoke(obj, aux);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			strBuff.append("				<option value='"+value+"'>"+desc+"</option>\n");
		}		
		strBuff.append("			</select>");
		strBuff.append("	</td>");
		strBuff.append("	<td>");
		strBuff.append("		<input type='button' name='btnAdd' value='Adicionar' onclick='" + getName() + "_AddItem(document."+getFormName()+"." + getName() + "_select,document."+getFormName()+"." + getName() + "_multBox);'>");
		strBuff.append("	</td>");
		strBuff.append("</tr>");				   		
		strBuff.append("	<tr>");
		strBuff.append("		<td>");
		strBuff.append("   		<select name='" + getName() + "_multBox' size='"+getLenSecondList()+"' style='width:"+getWidth()+"'>");
		strBuff.append("   		</select>");
		strBuff.append("	</td>");
		strBuff.append("	<td>");
		strBuff.append("		<input type='button' name='btnRem' value='Remover' onclick='" + getName() + "_RemoveItem(document."+getFormName()+"." + getName() + "_multBox)'>");
		strBuff.append("	</td>");			   		
		strBuff.append("</tr>");
		strBuff.append("</table>");
		strBuff.append("<script>");
		strBuff.append("function " + getName() + "_AddItem(origem, destino){");
		strBuff.append("	  var selIndex = origem.selectedIndex;");
		strBuff.append("	  if(selIndex < 0)");
		strBuff.append("			return;");				
		strBuff.append("      var s1 = origem;");
		strBuff.append("      var s2 = destino;");
		strBuff.append("      if (s1.options[s1.selectedIndex].value==''){");
		strBuff.append("           alert('Selecione um item !');");
		strBuff.append("           return;");
		strBuff.append("      }");
		if (this.getPermitDuplicate().equalsIgnoreCase("N")){
			strBuff.append("      if (!VERIFICA_DUP_"+getName()+"(s1,s2)){");
			strBuff.append("           alert('Este item j� existe selecionado !');");
			strBuff.append("           return;");
			strBuff.append("      }");
		}
		strBuff.append("      for(var i = 0; i < s1.options.length; i++){");
		strBuff.append("            if(s1.options[i].selected){");
		strBuff.append("                  var o = new Option(s1.options[i].text, s1.options[i].value);");
		strBuff.append("                  s2.options[s2.options.length] = o;");
		strBuff.append("            }");
		strBuff.append("      }");
		strBuff.append("	"+ getName() + "_GeraValores(destino);");		
		strBuff.append("}");
		strBuff.append("function " + getName() + "_RemoveItem(lista){");
		strBuff.append("	var selIndex = lista.selectedIndex;");
		strBuff.append("	if(selIndex < 0)");
		strBuff.append("		return;");
		strBuff.append("	lista.options[selIndex] = null;");
		strBuff.append("	"+ getName() + "_GeraValores(lista);");
		strBuff.append("}");
		strBuff.append("function " + getName() + "_GeraValores(lista){");
		strBuff.append("      document."+getFormName()+".valores"+getName()+".value='';\n");
		strBuff.append("      var hid = document."+getFormName()+".valores"+getName()+";\n");
		strBuff.append("      for(var i = 0; i < lista.options.length; i++){");
		strBuff.append("           hid.value = hid.value + lista.options[i].value + '#'");
		strBuff.append("      }");
		strBuff.append("}");
		strBuff.append("function VERIFICA_DUP_" + getName() + "(origem, destino){");
		strBuff.append("      var s1 = origem;");
		strBuff.append("      var s2 = destino;");	
		strBuff.append("      var valSel = s1.options[s1.selectedIndex].value;");
		strBuff.append("      for(var i = 0; i < s2.options.length; i++){");
		strBuff.append("            if(s2.options[i].value == valSel){");
		strBuff.append("                  return false;");
		strBuff.append("            }");
		strBuff.append("      }");	
		strBuff.append("      return true;");
		strBuff.append("}");
		strBuff.append("</script>");
		
		try {
			pageContext.getOut().println(strBuff.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e);
		}		
		return SKIP_BODY;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getEmptyItemLabel() {
		return emptyItemLabel;
	}
	public void setEmptyItemLabel(String emptyItemLabel) {
		this.emptyItemLabel = emptyItemLabel;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getLabelProperty() {
		return labelProperty;
	}
	public void setLabelProperty(String labelProperty) {
		this.labelProperty = labelProperty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getLenSecondList() {
		return lenSecondList;
	}
	public void setLenSecondList(String lenSecondList) {
		this.lenSecondList = lenSecondList;
	}
	public String getPermitDuplicate() {
		return permitDuplicate;
	}
	public void setPermitDuplicate(String permitDuplicate) {
		this.permitDuplicate = permitDuplicate;
	}
}
