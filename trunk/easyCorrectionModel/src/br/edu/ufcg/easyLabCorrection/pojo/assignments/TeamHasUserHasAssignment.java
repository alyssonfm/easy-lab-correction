package br.edu.ufcg.easyLabCorrection.pojo.assignments;

import br.edu.ufcg.easyLabCorrection.pojo.user.User;

public class TeamHasUserHasAssignment {
	
	private Integer id;
	private Team team;
	private Assignment assignment;
	private User user;
	
	public TeamHasUserHasAssignment() {
		super();
	}
	
	public TeamHasUserHasAssignment(Integer id, Team team,
			Assignment assignment, User user) {
		super();
		this.id = id;
		this.team = team;
		this.assignment = assignment;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
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
		result = prime * result
				+ ((assignment == null) ? 0 : assignment.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		TeamHasUserHasAssignment other = (TeamHasUserHasAssignment) obj;
		if (assignment == null) {
			if (other.assignment != null)
				return false;
		} else if (!assignment.equals(other.assignment))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeamHasUserHasAssignment [id=" + id + ", team=" + team
				+ ", assignment=" + assignment + ", user=" + user + "]";
	}
	
}
