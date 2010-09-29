package br.edu.les.easyCorrection.pojo.sistema;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChaveCompostaInteger implements Serializable{

	private Integer key1;
	private Integer key2;

	public ChaveCompostaInteger() {
		super();
	}

	/**
	 * @param key1
	 * @param key2
	 */
	public ChaveCompostaInteger(Integer key1, Integer key2) {
		super();
		this.key1 = key1;
		this.key2 = key2;
	}

	/**
	 * @return the key1
	 */
	public Integer getKey1() {
		return key1;
	}

	/**
	 * @param key1 the key1 to set
	 */
	public void setKey1(Integer key1) {
		this.key1 = key1;
	}

	/**
	 * @return the key2
	 */
	public Integer getKey2() {
		return key2;
	}

	/**
	 * @param key2 the key2 to set
	 */
	public void setKey2(Integer key2) {
		this.key2 = key2;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key1 == null) ? 0 : key1.hashCode());
		result = prime * result + ((key2 == null) ? 0 : key2.hashCode());
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
		ChaveCompostaInteger other = (ChaveCompostaInteger) obj;
		if (key1 == null) {
			if (other.key1 != null)
				return false;
		} else if (!key1.equals(other.key1))
			return false;
		if (key2 == null) {
			if (other.key2 != null)
				return false;
		} else if (!key2.equals(other.key2))
			return false;
		return true;
	}

	
}
