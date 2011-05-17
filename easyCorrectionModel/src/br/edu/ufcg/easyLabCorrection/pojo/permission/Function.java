package br.edu.ufcg.easyLabCorrection.pojo.permission;

import java.io.Serializable;



/**
 * <p>Pojo mapping TABLE agenda</p>
 * <p></p>
 *
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
public class Function implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer functionId;
	
	private Menu menu;
	
	private String name;
	
	private String label;

	public Function() {
		super();
	}

	public Function(Integer functionId, Menu menu, String name, String label) {
		super();
		this.functionId = functionId;
		this.menu = menu;
		this.name = name;
		this.label = label;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Function other = (Function) obj;
		if (functionId == null) {
			if (other.functionId != null)
				return false;
		} else if (!functionId.equals(other.functionId))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Function [functionId=" + functionId + ", menu=" + menu
				+ ", name=" + name + ", label=" + label + "]";
	}

}