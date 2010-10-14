package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class SubmissaoHibernateDAO extends
		AbstractHibernateDAO<Submissao, Integer>  {

	public SubmissaoHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Submissao> instanciaLista(List<Submissao> lista) {
		try {
			for (Submissao s : lista) {
				s = instanciaSubmissao(s);
			}	
		} catch (CampoVazioException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Submissao instanciaSubmissao(Submissao s) throws CampoVazioException{
		s.setEquipeHasUsuarioHasRoteiro(EquipeHasUsuarioHasRoteiroHibernateDAO.instanciaEquipeHasUsuarioHasRoteiro(s.getEquipeHasUsuarioHasRoteiro()));
		s = MyPersistenceLayer.deproxy(s, Submissao.class);
		return s;
	}

}
