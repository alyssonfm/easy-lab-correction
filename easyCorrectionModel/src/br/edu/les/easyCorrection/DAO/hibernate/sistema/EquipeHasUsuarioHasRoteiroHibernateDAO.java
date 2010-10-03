package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.UsuarioHibernateDAO;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class EquipeHasUsuarioHasRoteiroHibernateDAO extends
		AbstractHibernateDAO<EquipeHasUsuarioHasRoteiro, Integer>  {

	public EquipeHasUsuarioHasRoteiroHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EquipeHasUsuarioHasRoteiro> instanciaLista(List<EquipeHasUsuarioHasRoteiro> lista) {
		try {
			for (EquipeHasUsuarioHasRoteiro e : lista) {
				e = instanciaEquipeHasUsuarioHasRoteiro(e);
			}	
		} catch (CampoVazioException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static EquipeHasUsuarioHasRoteiro instanciaEquipeHasUsuarioHasRoteiro(EquipeHasUsuarioHasRoteiro e) throws CampoVazioException{
		e.setEquipe(EquipeHibernateDAO.instanciaEquipe(e.getEquipe()));
		e.setRoteiro(RoteiroHibernateDAO.instanciaRoteiro(e.getRoteiro()));
		e.setUsuario(UsuarioHibernateDAO.instanciaUsuario(e.getUsuario()));
		e = MyPersistenceLayer.deproxy(e, EquipeHasUsuarioHasRoteiro.class);
		
		return e;
	}

}