package br.edu.ufcg.easyLabCorrection.pojo.user;

import br.edu.ufcg.easyLabCorrection.pojo.system.SystemStage;

public class UserHasSystemStage {
	
	private Integer id;
	private User user;
	private SystemStage systemStage;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SystemStage getSystemStage() {
		return systemStage;
	}
	public void setSystemStage(SystemStage systemStage) {
		this.systemStage = systemStage;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((systemStage == null) ? 0 : systemStage.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserHasSystemStage other = (UserHasSystemStage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (systemStage == null) {
			if (other.systemStage != null)
				return false;
		} else if (!systemStage.equals(other.systemStage))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
}
