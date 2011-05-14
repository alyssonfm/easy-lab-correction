package br.edu.les.easyCorrection.pojo.acesso;

import java.io.Serializable;



/**
 * <p>Pojo mapping TABLE agenda</p>
 * <p></p>
 *
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idMenu;
	
	private String nome;
	
	private String rotulo;

	public Menu() {
	}
	
	public Menu(Integer idMenu, String nome, String rotulo) {
		this.setIdMenu(idMenu);
		this.setNome(nome);
		this.setRotulo(rotulo);
	}

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

	
	


}