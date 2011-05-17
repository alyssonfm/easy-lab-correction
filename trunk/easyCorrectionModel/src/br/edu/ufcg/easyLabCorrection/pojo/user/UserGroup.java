package br.edu.ufcg.easyLabCorrection.pojo.user;

import java.io.Serializable;

import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;

/**
 * <p>Pojo mapping TABLE agenda</p>
 * <p></p>
 *
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
public class UserGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userGroupId;
	
	private Group group;
	
	private User user;
	
	public UserGroup() {
		super();
	}

	public UserGroup(Integer userGroupId, Group group, User user) {
		super();
		this.userGroupId = userGroupId;
		this.group = group;
		this.user = user;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((userGroupId == null) ? 0 : userGroupId.hashCode());
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
		UserGroup other = (UserGroup) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userGroupId == null) {
			if (other.userGroupId != null)
				return false;
		} else if (!userGroupId.equals(other.userGroupId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGroup [userGroupId=" + userGroupId + ", group=" + group
				+ ", user=" + user + "]";
	}

}