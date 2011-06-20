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
public class Permission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer permissionId;
	
	private Group group;
	
	private Function function;
	
	public Permission() {
		super();
	}

	public Permission(Integer permissionId, Group group, Function function) {
		super();
		this.permissionId = permissionId;
		this.group = group;
		this.function = function;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result
				+ ((permissionId == null) ? 0 : permissionId.hashCode());
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
		Permission other = (Permission) obj;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (permissionId == null) {
			if (other.permissionId != null)
				return false;
		} else if (!permissionId.equals(other.permissionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", group=" + group
				+ ", function=" + function + "]";
	}
	
}