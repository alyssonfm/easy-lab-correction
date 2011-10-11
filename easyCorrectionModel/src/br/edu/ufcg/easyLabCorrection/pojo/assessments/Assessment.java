package br.edu.ufcg.easyLabCorrection.pojo.assessments;

import java.util.Date;

import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;

public class Assessment {
	
	private Integer id;
	private Submission submission;
	private double automaticGrade;
	private double correctionGrade;
	private String testsExecutionResult;
	private double penalty;
	private Date assessmentDate;
	private User corrector;
	private boolean corrected;
	private String comments;

	public Assessment(Integer id, 
			Submission submission, 
			double automaticGrade,
			double correctionGrade, 
			String testsExecutionResult,
			double penalty,
			Date assessmentDate,
			User corrector) {
		
		
		this.id = id;
		this.submission = submission;
		this.automaticGrade = automaticGrade;
		this.correctionGrade = correctionGrade;
		this.testsExecutionResult = testsExecutionResult;
		this.penalty = penalty;
		this.assessmentDate = assessmentDate;
		this.corrector = corrector;
		this.comments = "";
	}

	public Assessment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Submission getSubmission() {
		return submission;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}

	public double getAutomaticGrade() {
		return automaticGrade;
	}

	public void setAutomaticGrade(double automaticGrade) {
		this.automaticGrade = automaticGrade;
	}

	public double getCorrectionGrade() {
		return correctionGrade;
	}

	public void setCorrectionGrade(double correctionGrade) {
		this.correctionGrade = correctionGrade;
	}

	public String getTestsExecutionResult() {
		return testsExecutionResult;
	}

	public void setTestsExecutionResult(String testsExecutionResult) {
		this.testsExecutionResult = testsExecutionResult;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public User getCorrector() {
		return corrector;
	}

	public void setCorrector(User corrector) {
		this.corrector = corrector;
	}

	public boolean isCorrected() {
		return corrected;
	}

	public void setCorrected(boolean corrected) {
		this.corrected = corrected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assessmentDate == null) ? 0 : assessmentDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(automaticGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (corrected ? 1231 : 1237);
		temp = Double.doubleToLongBits(correctionGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((corrector == null) ? 0 : corrector.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(penalty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((submission == null) ? 0 : submission.hashCode());
		result = prime
				* result
				+ ((testsExecutionResult == null) ? 0 : testsExecutionResult
						.hashCode());
		return result;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessment other = (Assessment) obj;
		if (assessmentDate == null) {
			if (other.assessmentDate != null)
				return false;
		} else if (!assessmentDate.equals(other.assessmentDate))
			return false;
		if (Double.doubleToLongBits(automaticGrade) != Double
				.doubleToLongBits(other.automaticGrade))
			return false;
		if (corrected != other.corrected)
			return false;
		if (Double.doubleToLongBits(correctionGrade) != Double
				.doubleToLongBits(other.correctionGrade))
			return false;
		if (corrector == null) {
			if (other.corrector != null)
				return false;
		} else if (!corrector.equals(other.corrector))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(penalty) != Double
				.doubleToLongBits(other.penalty))
			return false;
		if (submission == null) {
			if (other.submission != null)
				return false;
		} else if (!submission.equals(other.submission))
			return false;
		if (testsExecutionResult == null) {
			if (other.testsExecutionResult != null)
				return false;
		} else if (!testsExecutionResult.equals(other.testsExecutionResult))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assessment [id=" + id + ", submission=" + submission
				+ ", automaticGrade=" + automaticGrade + ", correctionGrade="
				+ correctionGrade + ", testsExecutionResult="
				+ testsExecutionResult + ", penalty=" + penalty
				+ ", assessmentDate=" + assessmentDate + ", corrector="
				+ corrector + ", corrected=" + corrected + "]";
	}
	
}
