package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>
 * Hibernate DAO layer for Agendas
 * </p>
 * <p>
 * Generated at Fri Jan 30 09:30:05 GMT-03:00 2009
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class RoteiroHibernateDAO extends AbstractHibernateDAO<Roteiro, Integer> {

	public RoteiroHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Roteiro> findByRoteiroLiberado(Date dataAtual) {
		Query q = getSession()
				.createQuery(
						"from Roteiro where dataLiberacao <= :dataAtual and dataFinalEntrega > :dataAtual");
		q.setParameter("dataAtual", dataAtual);
		q.setCacheable(true);
		List<Roteiro> lista = q.list();
		return instanciaLista(lista);
	}

	/**
	 * Consideramos que as datas selecionadas tem seus relogios zerados. Ou
	 * seja, o data XX-YY-ZZZZ tem o relogio 00:00:00 e isso tem impacto direto
	 * na definicao de igualdade. Assim, o momento atual da data de hoje serah
	 * sempre maior do que a data de hoje que estah no banco.
	 * 
	 * @param dataAtual
	 * @param idRoteiro
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Roteiro> findByRoteiroLiberado(Date dataAtual, Integer idRoteiro) {
		Query q = getSession()
				.createQuery(
						"from Roteiro where dataLiberacao <= :dataAtual and dataFinalEntrega > :dataAtual and id = :idRoteiro");
		q.setParameter("dataAtual", dataAtual);
		q.setParameter("idRoteiro", idRoteiro);
		q.setCacheable(true);
		List<Roteiro> lista = q.list();
		return instanciaLista(lista);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Retorna os roteiros que estao fechados, ou seja, a data atual deve ser pelo menos 1 dia apos a data de entrega
	 */
	public List<Roteiro> findByRoteiroFechado(Date dataAtual) {
		Query q = getSession().createQuery(
				"from Roteiro where dataFinalEntrega <= :dataAtual");
		q.setParameter("dataAtual", dataAtual);
		q.setCacheable(true);
		List<Roteiro> lista = q.list();
		return instanciaLista(lista);
	}

	@Override
	public List<Roteiro> instanciaLista(List<Roteiro> lista) {
		try {
			for (Roteiro r : lista) {
				r = instanciaRoteiro(r);
			}
		} catch (CampoVazioException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return lista;
	}

	public static Roteiro instanciaRoteiro(Roteiro r)
			throws CampoVazioException {
		r.setPeriodo(PeriodoHibernateDAO.instanciaPeriodo(r.getPeriodo()));
		r = MyPersistenceLayer.deproxy(r, Roteiro.class);

		return r;
	}

}
