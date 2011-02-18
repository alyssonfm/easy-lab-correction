package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.UsuarioHibernateDAO;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

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
		try{
			a.setCorretor(UsuarioHibernateDAO.instanciaUsuario(a.getCorretor()));
		}
		catch(Exception e){
			a.setCorretor(null);
		}
		a = MyPersistenceLayer.deproxy(a, Avaliacao.class);
		
		return a;
	}

	@SuppressWarnings("unchecked")
	public List<Avaliacao> findByRoteiroSemCorretor(int idRoteiro) {
		Query q = getSession().createQuery("from Avaliacao where corretor.idUsuario is null and submissao.equipeHasUsuarioHasRoteiro.roteiro.id = :idRoteiro");
		q.setParameter("idRoteiro", idRoteiro);
		q.setCacheable(true);
		List <Avaliacao> lista = q.list();
		instanciaLista(lista);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> findByRoteiroEquipe(int idRoteiro, int idEquipe) {
		Query q = getSession().createQuery("from Avaliacao where submissao.equipeHasUsuarioHasRoteiro.roteiro.id = :idRoteiro " +
				" and submissao.equipeHasUsuarioHasRoteiro.equipe.id =:idEquipe");
		q.setParameter("idEquipe", idEquipe);
		q.setParameter("idRoteiro", idRoteiro);
		q.setCacheable(true);
		List <Avaliacao> lista = q.list();
		instanciaLista(lista);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> findBySubmissao(int idSubmissao) {
		Query q = getSession().createQuery("from Avaliacao where submissao.id = :idSubmissao");
		q.setParameter("idSubmissao", idSubmissao);
		q.setCacheable(true);
		List <Avaliacao> lista = q.list();
		instanciaLista(lista);
		return lista;
	}


	@SuppressWarnings("unchecked")
	public List<Avaliacao> findByRoteiroComCorretor(int idRoteiro,
			int idCorretor) {
		Query q = getSession().createQuery("from Avaliacao where corretor.idUsuario = :idCorretor and submissao.equipeHasUsuarioHasRoteiro.roteiro.id = :idRoteiro");
		q.setParameter("idRoteiro", idRoteiro);
		q.setParameter("idCorretor", idCorretor);
		q.setCacheable(true);
		List <Avaliacao> lista = q.list();
		instanciaLista(lista);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> findByEquipeERoteiroUnicos(Integer idRoteiro, Integer idCorretor) {
		Query q = getSession().createQuery("from Avaliacao where submissao.equipeHasUsuarioHasRoteiro.roteiro.id = :idRoteiro " +
				"and corretor.idUsuario = :idCorretor " +
				"GROUP BY submissao.equipeHasUsuarioHasRoteiro.equipe.id");
		q.setParameter("idRoteiro",idRoteiro);
		q.setParameter("idCorretor",idCorretor);
		q.setCacheable(true);
		List <Avaliacao> lista = q.list();
		return instanciaLista(lista);
	}

}
