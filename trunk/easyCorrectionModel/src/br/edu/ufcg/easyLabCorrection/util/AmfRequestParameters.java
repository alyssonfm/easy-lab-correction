package br.edu.ufcg.easyLabCorrection.util;

public class AmfRequestParameters {
	 
    private String destination;
    private String operation;
    private Object[] parameters;

	/**
     * Creates a new {@code AmfRequestParameters} with the given fields.
     *
     * @param destination destination of the amf request
     * @param operation operation to call on the destination
     * @param parameters parameters of the operation
     */
    public AmfRequestParameters(String destination, String operation, Object[] parameters) {
        this.destination = destination;
        this.operation = operation;
        this.parameters = parameters;
    }
    
    public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
}