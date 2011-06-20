package br.edu.ufcg.easyLabCorrection.pojo.assignments;

import java.util.Date;

import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;

public class Submission {

	private Integer id;
	private Date submissionDate;
	private TeamHasUserHasAssignment teamHasUserHasAssignment;
	private String url;
	private String status;
	
	public Submission(Integer id, 
			Date submissionDate,
			TeamHasUserHasAssignment teamHasUserHasAssignment, 
			String url,
			String status) {
		super();
		this.id = id;
		this.submissionDate = submissionDate;
		this.teamHasUserHasAssignment = teamHasUserHasAssignment;
		this.url = url;
		this.status = status;
	}
	
	public Submission() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public TeamHasUserHasAssignment getTeamHasUserHasAssignment() {
		return teamHasUserHasAssignment;
	}

	public void setTeamHasUserHasAssignment(
			TeamHasUserHasAssignment teamHasUserHasAssignment) {
		this.teamHasUserHasAssignment = teamHasUserHasAssignment;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((submissionDate == null) ? 0 : submissionDate.hashCode());
		result = prime
				* result
				+ ((teamHasUserHasAssignment == null) ? 0
						: teamHasUserHasAssignment.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Submission other = (Submission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submissionDate == null) {
			if (other.submissionDate != null)
				return false;
		} else if (!submissionDate.equals(other.submissionDate))
			return false;
		if (teamHasUserHasAssignment == null) {
			if (other.teamHasUserHasAssignment != null)
				return false;
		} else if (!teamHasUserHasAssignment
				.equals(other.teamHasUserHasAssignment))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Submission [id=" + id + ", submissionDate=" + submissionDate
				+ ", teamHasUserHasAssignment=" + teamHasUserHasAssignment
				+ ", url=" + url + ", status=" + status + "]";
	}
	
	
	
}
