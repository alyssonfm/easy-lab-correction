package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.UsuarioHibernateDAO;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class AvaliacaoHibernateDAO extends
		AbstractHibernateDAO<Avaliacao, Integer>  {

	public AvaliacaoHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Avaliacao> instanciaLista(List<Avaliacao> lista) {
		try {
			for (Avaliacao a : lista) {
				a = instanciaAvaliacao(a);
			}	
		} catch (CampoVazioException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Avaliacao instanciaAvaliacao(Avaliacao a) throws CampoVazioException{
		a.setSubmissao(SubmissaoHibernateDAO.instanciaSubmissao(a.getSubmissao()));
		a.setUsuario(UsuarioHibernateDAO.instanciaUsuario(a.getUsuario()));
		a = MyPersistenceLayer.deproxy(a, Avaliacao.class);
		
		return a;
	}

}
