package br.edu.ufcg.easyLabCorrection.pojo.assignments;

public class AssignmentType {
	
	private Integer id;
	private String name;
	private Boolean compilation;
	private Boolean testExecution;
	private Boolean outputComparison;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getSendingOnly() {
		if(!getCompilation() && !getOutputComparison() && !getTestExecution()){
			return true;
		}
		return false;
	}
	
	public Boolean getCompilation() {
		return compilation;
	}
	
	public void setCompilation(Boolean compilation) {
		this.compilation = compilation;
	}
	
	public Boolean getTestExecution() {
		return testExecution;
	}
	
	public void setTestExecution(Boolean testExecution) {
		this.testExecution = testExecution;
	}
	
	public Boolean getOutputComparison() {
		return outputComparison;
	}
	
	public void setOutputComparison(Boolean outputComparison) {
		this.outputComparison = outputComparison;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((compilation == null) ? 0 : compilation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((outputComparison == null) ? 0 : outputComparison.hashCode());
		result = prime * result
				+ ((testExecution == null) ? 0 : testExecution.hashCode());
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
		AssignmentType other = (AssignmentType) obj;
		if (compilation == null) {
			if (other.compilation != null)
				return false;
		} else if (!compilation.equals(other.compilation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (outputComparison == null) {
			if (other.outputComparison != null)
				return false;
		} else if (!outputComparison.equals(other.outputComparison))
			return false;
		if (testExecution == null) {
			if (other.testExecution != null)
				return false;
		} else if (!testExecution.equals(other.testExecution))
			return false;
		return true;
	}

}
