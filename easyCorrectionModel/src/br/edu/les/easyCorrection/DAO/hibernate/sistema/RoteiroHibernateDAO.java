package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class RoteiroHibernateDAO extends
		AbstractHibernateDAO<Roteiro, Integer>  {

	public RoteiroHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Roteiro> instanciaLista(List<Roteiro> lista) {
		try {
			for (Roteiro r : lista) {
				r = instanciaRoteiro(r);
			}	
		} catch (CampoVazioException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Roteiro instanciaRoteiro(Roteiro r) throws CampoVazioException{
		r.setPeriodo(PeriodoHibernateDAO.instanciaPeriodo(r.getPeriodo()));
		r = MyPersistenceLayer.deproxy(r, Roteiro.class);
		
		return r;
	}

}
