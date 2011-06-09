package br.edu.ufcg.easyLabCorrection.pojo.assignments;

import java.util.Date;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;

public class Assignment {
	
	private Integer id;
	private Stage stage;
	private String name;
	private String description;
	private Date releaseDate;
	private Date deliveryDate;
	private Date discussionDate;
	private Integer participantsMaxNumber;
	private Integer sendMaxNumber;
	private double penaltyPerDaysLate;
	private double automaticTestsPercentage;
	private Integer testTimeLimit;
	private String interfaceDirectory;
	private String testsDirectory;
	private AssignmentType assignmentType;
	
	public Assignment() {
		super();
	}

	public Assignment(Integer id, Stage stage, String name,
			String description, Date releaseDate, Date deliveryDate,
			Date discussionDate, Integer participantsMaxNumber,
			Integer sendMaxNumber, double penaltyPerDaysLate,
			double automaticTestsPercentage, Integer testTimeLimit,
			String interfaceDirectory, String testsDirectory,
			AssignmentType assignmentType) {
		super();
		this.id = id;
		this.stage = stage;
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.deliveryDate = deliveryDate;
		this.discussionDate = discussionDate;
		this.participantsMaxNumber = participantsMaxNumber;
		this.sendMaxNumber = sendMaxNumber;
		this.penaltyPerDaysLate = penaltyPerDaysLate;
		this.automaticTestsPercentage = automaticTestsPercentage;
		this.testTimeLimit = testTimeLimit;
		this.interfaceDirectory = interfaceDirectory;
		this.testsDirectory = testsDirectory;
		this.assignmentType = assignmentType;
	}

	public AssignmentType getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public Date getDiscussionDate() {
		return discussionDate;
	}
	
	public void setDiscussionDate(Date discussionDate) {
		this.discussionDate = discussionDate;
	}
	
	public Integer getParticipantsMaxNumber() {
		return participantsMaxNumber;
	}
	
	public void setParticipantsMaxNumber(Integer participantsMaxNumber) {
		this.participantsMaxNumber = participantsMaxNumber;
	}
	
	public Integer getSendMaxNumber() {
		return sendMaxNumber;
	}
	
	public void setSendMaxNumber(Integer sendMaxNumber) {
		this.sendMaxNumber = sendMaxNumber;
	}
	
	public double getPenaltyPerDaysLate() {
		return penaltyPerDaysLate;
	}
	
	public void setPenaltyPerDaysLate(double penaltyPerDaysLate) {
		this.penaltyPerDaysLate = penaltyPerDaysLate;
	}
	
	public double getAutomaticTestsPercentage() {
		return automaticTestsPercentage;
	}
	
	public void setAutomaticTestsPercentage(double automaticTestsPercentage) {
		this.automaticTestsPercentage = automaticTestsPercentage;
	}
	
	public Integer getTestTimeLimit() {
		return testTimeLimit;
	}
	
	public void setTestTimeLimit(Integer testTimeLimit) {
		this.testTimeLimit = testTimeLimit;
	}
	
	public String getInterfaceDirectory() {
		return interfaceDirectory;
	}
	
	public void setInterfaceDirectory(String interfaceDirectory) {
		this.interfaceDirectory = interfaceDirectory;
	}
	
	public String getTestsDirectory() {
		return testsDirectory;
	}
	
	public void setTestsDirectory(String testsDirectory) {
		this.testsDirectory = testsDirectory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(automaticTestsPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((discussionDate == null) ? 0 : discussionDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((interfaceDirectory == null) ? 0 : interfaceDirectory
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((participantsMaxNumber == null) ? 0 : participantsMaxNumber
						.hashCode());
		temp = Double.doubleToLongBits(penaltyPerDaysLate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result
				+ ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result
				+ ((sendMaxNumber == null) ? 0 : sendMaxNumber.hashCode());
		result = prime * result
				+ ((testTimeLimit == null) ? 0 : testTimeLimit.hashCode());
		result = prime * result
				+ ((testsDirectory == null) ? 0 : testsDirectory.hashCode());
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
		Assignment other = (Assignment) obj;
		if (Double.doubleToLongBits(automaticTestsPercentage) != Double
				.doubleToLongBits(other.automaticTestsPercentage))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discussionDate == null) {
			if (other.discussionDate != null)
				return false;
		} else if (!discussionDate.equals(other.discussionDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interfaceDirectory == null) {
			if (other.interfaceDirectory != null)
				return false;
		} else if (!interfaceDirectory.equals(other.interfaceDirectory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (participantsMaxNumber == null) {
			if (other.participantsMaxNumber != null)
				return false;
		} else if (!participantsMaxNumber.equals(other.participantsMaxNumber))
			return false;
		if (Double.doubleToLongBits(penaltyPerDaysLate) != Double
				.doubleToLongBits(other.penaltyPerDaysLate))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (sendMaxNumber == null) {
			if (other.sendMaxNumber != null)
				return false;
		} else if (!sendMaxNumber.equals(other.sendMaxNumber))
			return false;
		if (testTimeLimit == null) {
			if (other.testTimeLimit != null)
				return false;
		} else if (!testTimeLimit.equals(other.testTimeLimit))
			return false;
		if (testsDirectory == null) {
			if (other.testsDirectory != null)
				return false;
		} else if (!testsDirectory.equals(other.testsDirectory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", period=" + stage + ", name=" + name
				+ ", description=" + description + ", releaseDate="
				+ releaseDate + ", deliveryDate=" + deliveryDate
				+ ", discussionDate=" + discussionDate
				+ ", participantsMaxNumber=" + participantsMaxNumber
				+ ", sendMaxNumber=" + sendMaxNumber + ", penaltyLateDays="
				+ penaltyPerDaysLate + ", automaticTestsPercentage="
				+ automaticTestsPercentage + ", testTimeLimit=" + testTimeLimit
				+ ", interfaceDirectory=" + interfaceDirectory
				+ ", testsDirectory=" + testsDirectory + "]";
	}
	
}
