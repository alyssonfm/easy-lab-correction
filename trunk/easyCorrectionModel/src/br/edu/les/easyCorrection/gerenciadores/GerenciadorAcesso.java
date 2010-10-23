package br.edu.les.easyCorrection.gerenciadores;

import java.util.LinkedList;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.AutenticacaoException;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ObjetoNaoEncontradoException;
import br.edu.les.easyCorrection.exceptions.ValorDuplicadoException;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.GeraMd5;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorAcesso {

	private GerenciadorSubmissoes gerenciadorSubmissoes;

	public GerenciadorAcesso() {
		super();
		gerenciadorSubmissoes = new GerenciadorSubmissoes();
	}

	public Menu getMenu(Integer id) {
		List<Menu> menu = DAOFactory.DEFAULT.buildMenuDAO().findById(id);
		if (menu.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("menu"));
		}
		return menu.get(0);
	}

	public Menu consultarMenuPorRotuloENome(String rotulo, String nome) {
		List<Menu> lista = DAOFactory.DEFAULT.buildMenuDAO().findByNomeERotulo(
				nome, rotulo);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	public Menu cadastrarMenu(Menu menu) throws EasyCorrectionException {
		Menu m = new Menu();
		Menu men = new Menu();
		if (!easyCorrectionUtil.isNull(menu)) {
			if (easyCorrectionUtil.isNull(menu.getIdMenu())
					|| menu.getIdMenu().equals(new Integer(0))) {
				// Verifica se o rotulo ou o nome ja existe
				m = consultarMenuPorRotuloENome(menu.getNome(), menu
						.getRotulo());
				// Se o rótulo/nome não existe e e o id é null
				if (easyCorrectionUtil.isNull(m)) {
					Integer id = DAOFactory.DEFAULT.buildMenuDAO().save(menu);
					menu.setIdMenu(id);
					// Se o rótulo existe
				} else if (!easyCorrectionUtil.isNull(m)) {
					throw new ValorDuplicadoException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
				// Se o id eh diferente de null
			} else {
				men = consultarMenuPorRotuloENome(menu.getNome(), menu
						.getRotulo());
				if (easyCorrectionUtil.isNull(men)) {
					men = getMenu(menu.getIdMenu());
					men = (Menu) SwapperAtributosReflect.swapObject(men, menu,
							Menu.class);
					DAOFactory.DEFAULT.buildMenuDAO().update(men);
				} else if (menu.getIdMenu().equals(men.getIdMenu())) {
					men = (Menu) SwapperAtributosReflect.swapObject(men, menu,
							Menu.class);
					DAOFactory.DEFAULT.buildMenuDAO().update(men);
				} else {
					throw new ValorDuplicadoException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
			}
		}
		return menu;
	}

	public void excluirMenu(Menu menu) throws EasyCorrectionException {
		Menu m = getMenu(menu.getIdMenu());
		m = (Menu) SwapperAtributosReflect.swapObject(m, menu, Menu.class);
		DAOFactory.DEFAULT.buildMenuDAO().delete(m);
	}

	public List<Menu> listarMenusOrdenados() {
		return DAOFactory.DEFAULT.buildMenuDAO().findAllOrdenado();
	}

	public List<Menu> listarMenu() {
		return DAOFactory.DEFAULT.buildMenuDAO().findAll();
	}

	public Funcao cadastrarFuncao(Funcao funcao) throws EasyCorrectionException {
		Funcao f = new Funcao();
		Funcao fun = new Funcao();
		if (!easyCorrectionUtil.isNull(funcao)) {
			if (easyCorrectionUtil.isNull(funcao.getIdFuncao())
					|| funcao.getIdFuncao().equals(new Integer(0))) {
				// Verifica se o rótulo ou o nome já existe
				f = consultarFuncaoNomeERotulo(funcao.getNome(), funcao
						.getRotulo());
				// Se o rótulo/nome não existe e e o id é null
				if (easyCorrectionUtil.isNull(f)) {
					Integer id = DAOFactory.DEFAULT.buildFuncaoDAO().save(
							funcao);
					funcao.setIdFuncao(id);
					// Se o rótulo existe
				} else if (!easyCorrectionUtil.isNull(f)) {
					throw new ValorDuplicadoException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
				// Se o id é diferente de null
			} else {
				fun = consultarFuncaoNomeERotulo(funcao.getNome(), funcao
						.getRotulo());
				if (easyCorrectionUtil.isNull(fun)) {
					fun = getFuncao(funcao.getIdFuncao());
					fun = (Funcao) SwapperAtributosReflect.swapObject(fun,
							funcao, Funcao.class);
					DAOFactory.DEFAULT.buildFuncaoDAO().update(fun);
				} else if (funcao.getIdFuncao().equals(fun.getIdFuncao())) {
					fun = (Funcao) SwapperAtributosReflect.swapObject(fun,
							funcao, Funcao.class);
					DAOFactory.DEFAULT.buildFuncaoDAO().update(fun);
				} else {
					throw new ValorDuplicadoException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
			}
		}
		return funcao;
	}

	public Funcao getFuncao(Integer id) {
		List<Funcao> funcao = DAOFactory.DEFAULT.buildFuncaoDAO().findById(id);
		if (funcao.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("funcao"));
		}
		return funcao.get(0);
	}

	public Funcao consultarFuncaoNomeERotulo(String nome, String rotulo) {
		List<Funcao> lista = DAOFactory.DEFAULT.buildFuncaoDAO()
				.findByNomeERotulo(nome, rotulo);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	private boolean containsFuncao(List<Permissao> lista, Funcao funcao) {
		for (Permissao p : lista) {
			if (p.getFuncao().getIdFuncao().equals(funcao.getIdFuncao())) {
				return true;
			}
		}
		return false;
	}

	public List<Funcao> listarFuncao() {
		return DAOFactory.DEFAULT.buildFuncaoDAO().findAll();
	}

	public void excluirFuncao(Funcao funcao) throws EasyCorrectionException {
		Funcao f = getFuncao(funcao.getIdFuncao());
		f = (Funcao) SwapperAtributosReflect
				.swapObject(f, funcao, Funcao.class);
		DAOFactory.DEFAULT.buildFuncaoDAO().delete(f);
	}

	public List<Funcao> consultarFuncaoPorMenu(Integer idMenu) {
		return DAOFactory.DEFAULT.buildFuncaoDAO().findByMenu(idMenu);
	}

	public Grupo cadastrarGrupo(Grupo grupo) throws EasyCorrectionException {
		Grupo g = new Grupo();
		Grupo gr = new Grupo();
		if (!easyCorrectionUtil.isNull(grupo)) {
			if (easyCorrectionUtil.isNull(grupo.getIdGrupo())
					|| grupo.getIdGrupo().equals(new Integer(0))) {
				// Verifica se o nome já existe
				g = consultarGrupoPorNome(grupo.getNome());
				// Se o nome não existe e e o id é null
				if (easyCorrectionUtil.isNull(g)) {
					Integer id = DAOFactory.DEFAULT.buildGrupoDAO().save(grupo);
					grupo.setIdGrupo(id);
					// Se o rótulo existe
				} else if (!easyCorrectionUtil.isNull(g)) {
					throw new ValorDuplicadoException(MsgErros.VALOR_DUPLICADO
							.msg("nome"));
				}
				// Se o id é diferente de null
			} else {
				gr = consultarGrupoPorNome(grupo.getNome());
				if (easyCorrectionUtil.isNull(gr)) {
					gr = getGrupo(grupo.getIdGrupo());
					gr = (Grupo) SwapperAtributosReflect.swapObject(gr, grupo,
							Grupo.class);
					DAOFactory.DEFAULT.buildGrupoDAO().update(gr);
				} else if (grupo.getIdGrupo().equals(gr.getIdGrupo())) {
					gr = (Grupo) SwapperAtributosReflect.swapObject(gr, grupo,
							Grupo.class);
					DAOFactory.DEFAULT.buildGrupoDAO().update(gr);
				} else {
					throw new ValorDuplicadoException(MsgErros.VALOR_DUPLICADO
							.msg("nome"));
				}
			}
		}
		return grupo;
	}

	public Grupo getGrupo(Integer id) {
		List<Grupo> grupo = DAOFactory.DEFAULT.buildGrupoDAO().findById(id);
		if (grupo.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo"));
		}
		return grupo.get(0);
	}

	public List<Grupo> listarGrupo() {
		return DAOFactory.DEFAULT.buildGrupoDAO().findAll();
	}

	public void excluirGrupo(Grupo grupo) throws EasyCorrectionException {
		Grupo g = getGrupo(grupo.getIdGrupo());
		g = (Grupo) SwapperAtributosReflect.swapObject(g, grupo, Grupo.class);
		DAOFactory.DEFAULT.buildGrupoDAO().delete(g);
	}

	public Grupo consultarGrupoPorNome(String nome) {

		List<Grupo> lista = DAOFactory.DEFAULT.buildGrupoDAO().findByNome(nome);
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	public Grupo getGrupoPorNome(String nome) {
		List<Grupo> lista = DAOFactory.DEFAULT.buildGrupoDAO().findByNome(nome);
		if (lista.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo"));
		}
		return lista.get(0);
	}

	public List<Permissao> cadastrarPermissao(List<Permissao> permissoes)
			throws EasyCorrectionException {
		Permissao p = new Permissao();
		List<Permissao> lista = new LinkedList<Permissao>();
		for (Permissao permissao : permissoes) {
			if (!easyCorrectionUtil.isNull(permissao)
					&& easyCorrectionUtil.isNull(permissao.getIdPermissao())) {
				Integer id = DAOFactory.DEFAULT.buildPermissaoDAO().save(
						permissao);
				permissao.setIdPermissao(id);
				lista.add(permissao);
			} else {
				try {
					p = getPermissao(permissao.getIdPermissao());
					p = (Permissao) SwapperAtributosReflect.swapObject(p,
							permissao, Permissao.class);
					DAOFactory.DEFAULT.buildPermissaoDAO().update(p);
					lista.add(p);

				} catch (ObjetoNaoEncontradoException e) {
					Integer id = DAOFactory.DEFAULT.buildPermissaoDAO().save(
							permissao);
					permissao.setIdPermissao(id);
					lista.add(permissao);
				}
			}
		}
		return lista;
	}

	public List<Permissao> cadastraPermissaoGrupo(Grupo g, List<Funcao> lista) {
		List<Permissao> permissaoDoGrupoBanco = DAOFactory.DEFAULT
				.buildPermissaoDAO().findByIdGrupo(g.getIdGrupo());
		List<Permissao> novaLista = new LinkedList<Permissao>();
		// criando a lista de Permissao
		for (Funcao f : lista) {
			Permissao p = new Permissao();
			p.setFuncao(f);
			p.setGrupo(g);
			novaLista.add(p);
		}
		// gravando as novas permissoes
		for (Permissao addPermissao : novaLista) {
			if (!containsFuncao(permissaoDoGrupoBanco, addPermissao.getFuncao())) {
				Integer id = DAOFactory.DEFAULT.buildPermissaoDAO().save(
						addPermissao);
				addPermissao.setIdPermissao(id);
			} else {
				List<Permissao> outraP = DAOFactory.DEFAULT.buildPermissaoDAO()
						.findByGrupoAndFuncao(g.getIdGrupo(),
								addPermissao.getFuncao().getIdFuncao());
				addPermissao = outraP.get(0); // eh garantido que a lista não
				// é vazia
			}
		}
		// removendo as permissoes não passada na lista
		for (Permissao delPermissao : permissaoDoGrupoBanco) {
			if (!containsFuncao(novaLista, delPermissao.getFuncao())) {
				DAOFactory.DEFAULT.buildPermissaoDAO().delete(delPermissao);
			}
		}

		return novaLista;
	}

	public Permissao getPermissao(Integer id) {
		List<Permissao> permissao = DAOFactory.DEFAULT.buildPermissaoDAO()
				.findById(id);
		if (permissao.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("permissao"));
		}
		return permissao.get(0);
	}

	public List<Funcao> verificaPermissoes(Integer idUsuario) {
		List<Funcao> listaFuncao = new LinkedList<Funcao>();
		List<GrupoUsuario> listaGU = DAOFactory.DEFAULT.buildGrupoUsuarioDAO()
				.findByUsuarioId(idUsuario);
		if (!listaGU.isEmpty()) {
			for (GrupoUsuario gU : listaGU) {
				List<Permissao> listaP = DAOFactory.DEFAULT.buildPermissaoDAO()
						.findByIdGrupo(gU.getGrupo().getIdGrupo());
				if (!listaP.isEmpty()) {
					for (Permissao p : listaP) {
						if (!verificaRepetido(listaFuncao, p.getFuncao())) {
							listaFuncao.add(p.getFuncao());
						}
					}
				}
			}
		}
		return listaFuncao;
	}

	public List<Permissao> consultarPermissoesPorGrupo(Integer idGrupo) {
		return DAOFactory.DEFAULT.buildPermissaoDAO().findByIdGrupo(idGrupo);
	}

	public GrupoUsuario getGrupoUsuario(Integer id) {
		List<GrupoUsuario> grupoUsuario = DAOFactory.DEFAULT
				.buildGrupoUsuarioDAO().findById(id);
		if (grupoUsuario.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario grupo"));
		}
		return grupoUsuario.get(0);
	}

	public GrupoUsuario cadastrarGrupoUsuario(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		GrupoUsuario gUs = new GrupoUsuario();
		if (!easyCorrectionUtil.isNull(grupoUsuario)) {
			try {
				gUs = getGrupoUsuarioPorGrupoEUsuario(grupoUsuario.getGrupo()
						.getIdGrupo(), grupoUsuario.getUsuario().getIdUsuario());
				gUs = (GrupoUsuario) SwapperAtributosReflect.swapObject(gUs,
						grupoUsuario, GrupoUsuario.class);
				DAOFactory.DEFAULT.buildGrupoUsuarioDAO().update(gUs);
			} catch (ObjetoNaoEncontradoException e) {
				Integer id = DAOFactory.DEFAULT.buildGrupoUsuarioDAO().save(
						grupoUsuario);
				grupoUsuario.setIdGrupoUsuario(id);
			}
		}
		return grupoUsuario;
	}

	public List<GrupoUsuario> listarGrupoUsuario() {
		return DAOFactory.DEFAULT.buildGrupoUsuarioDAO().findAllGrupoUsuario();
	}

	public Usuario getUsuario(Integer id) {
		List<Usuario> usuario = DAOFactory.DEFAULT.buildUsuarioDAO().findById(
				id);
		if (usuario.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return usuario.get(0);
	}

	public Usuario getUsuarioPorLogin(String login) {
		List<Usuario> usuario = DAOFactory.DEFAULT.buildUsuarioDAO()
				.findByLogin(login);
		if (usuario.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return usuario.get(0);
	}

	public GrupoUsuario getGrupoUsuarioPorGrupoEUsuario(Integer idGrupo,
			Integer idUsuario) {
		List<GrupoUsuario> lista = DAOFactory.DEFAULT.buildGrupoUsuarioDAO()
				.findByUsuarioEGrupo(idGrupo, idUsuario);
		if (lista.isEmpty()) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo usuario"));
		}
		return lista.get(0);
	}

	public List<GrupoUsuario> getGrupoUsuarioPorUsuario(Integer idUsuario) {
		return DAOFactory.DEFAULT.buildGrupoUsuarioDAO().findByUsuarioId(
				idUsuario);
	}

	public Usuario consultarUsuarioPorLogin(String login) {
		List<Usuario> lista = DAOFactory.DEFAULT.buildUsuarioDAO().findByLogin(
				login);
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	public Usuario getUsuarioPorEmail(String email) {
		List<Usuario> lista = DAOFactory.DEFAULT.buildUsuarioDAO().findByEmail(
				email);
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	public void verificaSeNumeroUsuarioMaiorEquipe(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		Equipe equipe = new Equipe();
		if (grupoUsuario.getGrupo().getNome().equalsIgnoreCase("Aluno")) {
			List<GrupoUsuario> gu = DAOFactory.DEFAULT.buildGrupoUsuarioDAO()
					.findByGrupo("Aluno");
			List<Equipe> equipes = gerenciadorSubmissoes.getEquipes();
			int numeroUsuario = gu.size() + 1;
			if (numeroUsuario > equipes.size()) {
				for (int i = 0; i < 5; i++) {
					equipes = gerenciadorSubmissoes.getEquipes();
					if (equipes.isEmpty()) {
						equipe.setNome("Equipe 1");
					} else {
						int index = equipes.get(equipes.size() - 1).getId() + 1;
						equipe.setNome("Equipe " + index);
					}
					gerenciadorSubmissoes.cadastraEquipe(equipe);
				}
			}
		}
	}

	public GrupoUsuario cadastrarUsuario(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		Usuario u = new Usuario();
		Usuario us = new Usuario();
		Usuario usu = new Usuario();

		if (easyCorrectionUtil.isNull(grupoUsuario)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O GrupoUsuario"));
		}
		if (easyCorrectionUtil.isNull(grupoUsuario.getUsuario())) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O Usuario"));
		}
		if (easyCorrectionUtil.isNull(grupoUsuario.getGrupo())) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O Grupo"));
		}

		if (!easyCorrectionUtil.isNull(grupoUsuario.getUsuario())) {
			if (!grupoUsuario.getUsuario().getIdUsuario()
					.equals(new Integer(0))) {
				u = getUsuario(grupoUsuario.getUsuario().getIdUsuario());
				if (!u.getSenha().equals(grupoUsuario.getUsuario().getSenha())) {
					// Gera o md5 da senha
					String senha = GeraMd5.md5(grupoUsuario.getUsuario()
							.getSenha());
					grupoUsuario.getUsuario().setSenha(senha);
				}
			} else {
				// Gera o md5 da senha
				String senha = "";
				if (grupoUsuario.getUsuario().getSenha().equals("")) {
					senha = GeraMd5.md5(grupoUsuario.getUsuario().getLogin());
				} else {
					senha = GeraMd5.md5(grupoUsuario.getUsuario().getSenha());
				}
				grupoUsuario.getUsuario().setSenha(senha);
			}
			// Cadastrar o usuário
			u = consultarUsuarioPorLogin(grupoUsuario.getUsuario().getLogin());
			usu = getUsuarioPorEmail(grupoUsuario.getUsuario().getEmail());
			// Se o login não existe e e o id é null
			if (easyCorrectionUtil.isNull(u) && easyCorrectionUtil.isNull(usu)) {
				try {
					us = getUsuario(grupoUsuario.getUsuario().getIdUsuario());
					us = (Usuario) SwapperAtributosReflect.swapObject(us,
							grupoUsuario.getUsuario(), Usuario.class);
					DAOFactory.DEFAULT.buildUsuarioDAO().update(us);
					grupoUsuario.getUsuario().setIdUsuario(us.getIdUsuario());
				} catch (ObjetoNaoEncontradoException e) {
					Integer id = DAOFactory.DEFAULT.buildUsuarioDAO().save(
							grupoUsuario.getUsuario());
					grupoUsuario.getUsuario().setIdUsuario(id);
					verificaSeNumeroUsuarioMaiorEquipe(grupoUsuario);
				}
			} else {
				try {
					us = getUsuario(grupoUsuario.getUsuario().getIdUsuario());
					if (!easyCorrectionUtil.isNull(u)) {
						if (!grupoUsuario.getUsuario().getIdUsuario().equals(
								u.getIdUsuario())) {
							throw new ObjetoNaoEncontradoException(
									MsgErros.LOGIN.msg(""));
						}
					}
					if (!easyCorrectionUtil.isNull(usu)) {
						if (!grupoUsuario.getUsuario().getIdUsuario().equals(
								usu.getIdUsuario())) {
							throw new ObjetoNaoEncontradoException(
									MsgErros.EMAIL.msg(""));
						}
					}
					us = (Usuario) SwapperAtributosReflect.swapObject(us,
							grupoUsuario.getUsuario(), Usuario.class);
					DAOFactory.DEFAULT.buildUsuarioDAO().update(us);
					grupoUsuario.getUsuario().setIdUsuario(us.getIdUsuario());
				} catch (ObjetoNaoEncontradoException e) {
					if (!easyCorrectionUtil.isNull(u)) {
						throw new ObjetoNaoEncontradoException(MsgErros.LOGIN
								.msg(""));
					}
					if (!easyCorrectionUtil.isNull(usu)) {
						throw new ObjetoNaoEncontradoException(MsgErros.EMAIL
								.msg(""));
					}
				}
			}

		}
		// Cadastra o grupoUsu�rio do usu�rio
		cadastrarGrupoUsuario(grupoUsuario);
		return grupoUsuario;
	}

	public Usuario atualizarUsuario(Usuario usuario)
			throws EasyCorrectionException {

		if (easyCorrectionUtil.isNull(usuario)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O Usuario"));
		}
		// Gera o md5 da senha
		String senha = GeraMd5.md5(usuario.getSenha());
		usuario.setSenha(senha);

		Usuario us = getUsuario(usuario.getIdUsuario());
		us = (Usuario) SwapperAtributosReflect.swapObject(us, usuario,
				Usuario.class);
		DAOFactory.DEFAULT.buildUsuarioDAO().update(us);
		return us;
	}

	public void excluirUsuario(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		
		if (easyCorrectionUtil.isNull(grupoUsuario)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O GrupoUsuario"));
		}
		
		// Exclui grupoUsuario
		GrupoUsuario gU = getGrupoUsuario(grupoUsuario.getIdGrupoUsuario());
		gU = (GrupoUsuario) SwapperAtributosReflect.swapObject(gU,
				grupoUsuario, GrupoUsuario.class);
		DAOFactory.DEFAULT.buildGrupoUsuarioDAO().delete(gU);

		// Exclui usuário
		Usuario u = getUsuario(grupoUsuario.getUsuario().getIdUsuario());
		u = (Usuario) SwapperAtributosReflect.swapObject(u, grupoUsuario
				.getUsuario(), Usuario.class);
		DAOFactory.DEFAULT.buildUsuarioDAO().delete(u);
	}

	public List<Usuario> listarUsuarios() {
		return DAOFactory.DEFAULT.buildUsuarioDAO().findAll();
	}

	public List<Funcao> validaUsuario(Usuario usuario) {
		List<Funcao> funcoes = new LinkedList<Funcao>();

		// Gera o md5 da senha
		String senha = GeraMd5.md5(usuario.getSenha());
		usuario.setSenha(senha);

		// Verifica se o login e senha são válidas
		Usuario u = verificaLoginESenha(usuario);
		if (!easyCorrectionUtil.isNull(u)) {
			// Verifica as permissões do usuário e retorna um conjunto de
			// funções
			funcoes = verificaPermissoes(u.getIdUsuario());
		}
		return funcoes;
	}

	public Usuario verificaLoginESenha(Usuario usuario) {
		List<Usuario> lista = DAOFactory.DEFAULT.buildUsuarioDAO()
				.findByLoginESenha(usuario.getLogin(), usuario.getSenha());
		if (lista.isEmpty()) {
			throw new AutenticacaoException(MsgErros.AUTENTICACAO.msg());
		}
		return lista.get(0);

	}

	public boolean verificaRepetido(List<Funcao> lista, Funcao funcao) {
		for (Funcao f : lista) {
			if (f.equals(funcao)) {
				return true;
			}
		}
		return false;
	}

	public List<GrupoUsuario> consultarUsuarioPorGrupo(Integer idGrupo) {
		return DAOFactory.DEFAULT.buildGrupoUsuarioDAO().findByGrupo(idGrupo);
	}

	public Usuario alterarSenha(Usuario usuario, String novaSenha) {
		// Gera o md5 da senha
		String senha = GeraMd5.md5(novaSenha);
		Usuario usuarioBanco = getUsuarioPorLogin(usuario.getLogin());

		if (!easyCorrectionUtil.isNull(usuarioBanco)) {
			usuarioBanco.setSenha(senha);
			DAOFactory.DEFAULT.buildUsuarioDAO().update(usuarioBanco);
		} else {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return usuarioBanco;
	}

	public Periodo getPeriodo(int i) {
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(i);
	}
}
