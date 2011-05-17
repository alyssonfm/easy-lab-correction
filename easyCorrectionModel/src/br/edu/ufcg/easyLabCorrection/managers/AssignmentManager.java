package br.edu.ufcg.easyLabCorrection.managers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.CreateAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.EditingAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.ExclusionAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.ReleasesAssignmentException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class AssignmentManager extends Manager {

	/*
	 * Computacao dos estados de um assignment
	 */
	private final int STATE_INEXIST = -1;

	/*
	 * Estado prévio a criacao do assignment
	 */
	private final int ASSIGNMENT_IN_CREATION = 0;
	/*
	 * Estado posterior a criacao do assignment, mas anterior a data de liberacao
	 * deste
	 */
	private final int ASSIGNMENT_CREATED = 1;
	/*
	 * Estado posterior a data de liberacao do assignment, mas anterior a data de
	 * fechamento (entrega) deste
	 */
	private final int ASSIGNMENT_RELEASED = 2;
	/*
	 * Estado posterior a data de entrega do assignment
	 */
	private final int ASSIGNMENT_CLOSED = 3;

	/*
	 * Estado posterior a data final para correcao
	 */
	private final int ASSIGNMENT_CORRECTED = 4;

	private final double PENALTY_DAY_LATE_DEFAULT = -1;
	private final double AUTOMATIC_TESTS_PERCENTAGE_DEFAULT = -1;

	/*
	 * String a qual receberah o nome do metodo chamado: - Se foi
	 * cadastraassignment, recebe "criado" - Se foi editaassignment, recebe
	 * "atualizado"
	 * 
	 * Isso permite-nos reusar o codigo de validacao dos assignments
	 */
	private String criacaoOuAtualizacaoMsg = "NULL";

	public Assignment getAssignment(int assignmentId) {
		return DAOFactory.DEFAULT.buildAssignmentDAO().getById(assignmentId);
	}

	public List<Assignment> listAssignments() {
		return DAOFactory.DEFAULT.buildAssignmentDAO().findAll();
	}

	public List<Assignment> getReleasedAssignments() {
		Date currentDate = easyCorrectionUtil.getRealTime();
		List<Assignment> releasedAssignments = DAOFactory.DEFAULT.buildAssignmentDAO()
				.findByReleasedAssignments(currentDate);
		return releasedAssignments;
	}

	public Assignment getReleasedAssignments(Integer id) {
		Date currentDate = easyCorrectionUtil.getRealTime();
		List<Assignment> list = DAOFactory.DEFAULT.buildAssignmentDAO()
				.findByReleasedAssignments(currentDate, id);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Assignment saveAssignment(Assignment assignmentTemp)
			throws CreateAssignmentException, ReleasesAssignmentException {

		if (assignmentTemp == null) {
			throw new CreateAssignmentException("roteiro inexistente!");
		}

		this.criacaoOuAtualizacaoMsg = "criado";

		// Estes campos podem vir null, soh serao usados apos a primeira edicao
		// (quando serah adicionado o caminho no servidor)
		assignmentTemp.setTestsDirectory("");
		assignmentTemp.setInterfaceDirectory("");
		
		if (validateAssignmentInCreation(assignmentTemp)) {
			int aux = DAOFactory.DEFAULT.buildAssignmentDAO().save(assignmentTemp);
			System.out.println("O roteiro " + assignmentTemp.getName()
					+ " foi criado com sucesso!");
			return DAOFactory.DEFAULT.buildAssignmentDAO().getById(aux);
		} else {
			throw new CreateAssignmentException("roteiro não pode ser criado.");
		}
	}

	public Assignment updateAssignment(Assignment assignmentTemp)
			throws EditingAssignmentException, CreateAssignmentException,
			ReleasesAssignmentException {

		if (assignmentTemp == null) {
			throw new EditingAssignmentException("roteiro inexistente!");
		}

		this.criacaoOuAtualizacaoMsg = "atualizado";
		
		int currentAssignmentState = computesNewAssignmentState(assignmentTemp);

		switch (currentAssignmentState) {
		case (ASSIGNMENT_IN_CREATION):
			// Esse caso nunca deve ocorrer, pois nesse estado deve haver o
			// cadastro de assignments e não a edição
			validateAssignmentInCreation(assignmentTemp);
			break;

		case (ASSIGNMENT_CREATED):
			validateAssignmentCreated(assignmentTemp);
			break;

		case (ASSIGNMENT_RELEASED):
			validateAssignmentReleased(assignmentTemp);
			break;

		case (ASSIGNMENT_CLOSED):
			validateAssignmentClosed(assignmentTemp);
			break;

		case (ASSIGNMENT_CORRECTED):
			validateAssignmentCorrected(assignmentTemp);
			break;

		default:
			throw new EditingAssignmentException(MsgErros.VALORINVALIDO
					.msg("A edição do roteiro não pode ser realizada!"));
		}

		// OK, passou pelos casos de excecao, pode seguir em frente! =)
		Assignment a;

		try {
			a = getAssignment(assignmentTemp.getId());
			a = (Assignment) SwapperAtributosReflect.swapObject(a, assignmentTemp,
					Assignment.class);
			DAOFactory.DEFAULT.buildAssignmentDAO().update(a);
			System.out.println("roteiro atualizado com sucesso!");
			
		} catch (EasyCorrectionException e) {
			throw new EditingAssignmentException("roteiro inexistente!");
		}
		return a;
	}

	/*
	 * Nao estah sendo usado ainda... E nao estah em nossos planos usa-lo...
	 */
	public void deleteAssignment(Assignment assignment) throws ExclusionAssignmentException {

		if (assignment == null) {
			throw new ExclusionAssignmentException("roteiro inexistente!");
		}
		DAOFactory.DEFAULT.buildAssignmentDAO().delete(assignment);
	}

	/* ************************************************************
	 * METODOS PRIVADOS
	 * ************************************************************
	 */
	private int computesNewAssignmentState(Assignment assignmentTemp)
			throws EditingAssignmentException {

		// Antes de Computar o estado do novo assignment, vamos checar se houve
		// alguma modificacao com relacao ao antigo estado e aplicar as devidas
		// restricoes
		if (assignmentTemp == null) {
			throw new EditingAssignmentException(
					"roteiro invalido. Tente Novamente...");
		}

		this.checksDatesModification(assignmentTemp);
		return getCurrentAssignmentState(assignmentTemp);
	}

	private void checksDatesModification(Assignment newAssignment)
			throws EditingAssignmentException {

		// Tempo nesse instante
		Date currentDate = easyCorrectionUtil.getDataNow();
		Assignment oldAssignment = this.getAssignment(newAssignment.getId());

		int oldAssignmentState = (oldAssignment == null) ? ASSIGNMENT_IN_CREATION
				: getCurrentAssignmentState(oldAssignment);

		switch (oldAssignmentState) {
		case (ASSIGNMENT_IN_CREATION):
		case (ASSIGNMENT_CREATED):
			// LIBERAR, ENTREGA E DISCUSSAO PODEM SER MUDADAS
			// Mas soh para o dia atual ou depois
			if (newAssignment.getReleaseDate() == null
					|| newAssignment.getReleaseDate().before(currentDate)) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDeliveryDate() != null
					&& newAssignment.getDeliveryDate().before(currentDate)) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDiscussionDate() != null
					&& newAssignment.getDiscussionDate().before(currentDate)) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}

			break;

		case (ASSIGNMENT_RELEASED):
			// ENTREGA E DISCUSSAO PODEM SER MUDADAS
			// Mas soh para o dia atual ou depois
			// LIBERAR: se nao for modificada: mantem a antiga
			// se for modificada: soh para o dia atual ou depois

			if (newAssignment.getReleaseDate() == null
					|| (newAssignment.getReleaseDate().before(currentDate) && (!newAssignment
							.getReleaseDate().equals(
									oldAssignment.getReleaseDate())))) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDeliveryDate() != null
					&& newAssignment.getDeliveryDate().before(currentDate)) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDiscussionDate() != null
					&& newAssignment.getDiscussionDate().before(currentDate)) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}
			break;

		case (ASSIGNMENT_CLOSED):
			// DISCUSSAO PODEM SER MUDADAS
			// Mas soh para o dia atual ou depois
			// LIBERAR E ENTREGA
			// se nao for modificada: mantem a antiga
			// se for modificada: soh para o dia atual ou depois

			if (newAssignment.getReleaseDate() == null
					|| (newAssignment.getReleaseDate().before(currentDate) && (!newAssignment
							.getReleaseDate().equals(
									oldAssignment.getReleaseDate())))) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pode ser ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDeliveryDate() == null
					|| (newAssignment.getDeliveryDate().before(currentDate) && (!newAssignment
							.getDeliveryDate().equals(
									oldAssignment.getDeliveryDate())))) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDiscussionDate() != null
					&& newAssignment.getDiscussionDate().before(currentDate)) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}
			break;

		case (ASSIGNMENT_CORRECTED):
			// LIBERAR, ENTREGA E DISCUSSAO
			// se nao for modificada: mantem a antiga
			// se for modificada: soh para o dia atual ou depois

			if (newAssignment.getReleaseDate() == null
					|| (newAssignment.getReleaseDate().before(currentDate) && (!newAssignment
							.getReleaseDate().equals(
									oldAssignment.getReleaseDate())))) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pode ser ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDeliveryDate() == null
					|| (newAssignment.getDeliveryDate().before(currentDate) && (!newAssignment
							.getDeliveryDate().equals(
									oldAssignment.getDeliveryDate())))) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (newAssignment.getDiscussionDate() == null
					|| (newAssignment.getDiscussionDate().before(currentDate) && (!newAssignment
							.getDiscussionDate().equals(
									oldAssignment.getDiscussionDate())))) {
				throw new EditingAssignmentException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inv�lida. O roteiro não pode ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}

			break;

		default:
			break;
		}

		if (newAssignment.getDeliveryDate() != null
				&& newAssignment.getReleaseDate() != null
				&& (newAssignment.getDeliveryDate().before(
						newAssignment.getReleaseDate()) || newAssignment
						.getDeliveryDate().equals(
								newAssignment.getReleaseDate()))) {
			throw new EditingAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Entrega anterior/igual a Data de Liberação. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (newAssignment.getDiscussionDate() != null
				&& newAssignment.getDeliveryDate() != null
				&& (newAssignment.getDiscussionDate().before(
						newAssignment.getDeliveryDate()) || newAssignment
						.getDiscussionDate().equals(
								newAssignment.getDeliveryDate()))) {
			throw new EditingAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Discuss�o anterior/igual a Data Limite para Entrega. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		}
	}

	private int getCurrentAssignmentState(Assignment assignment) {
		// Tempo nesse instante
		Date currentTime = Calendar.getInstance().getTime();

		if (assignment.getReleaseDate() == null || assignment.getId() == 0) {
			return ASSIGNMENT_IN_CREATION;
		} else if (currentTime.before(assignment.getReleaseDate())) {
			return ASSIGNMENT_CREATED;
		} else if (currentTime.after(assignment.getReleaseDate())
				&& currentTime.before(assignment.getDeliveryDate())) {
			return ASSIGNMENT_RELEASED;
		} else if (currentTime.after(assignment.getDeliveryDate())) {
			return ASSIGNMENT_CLOSED;
		} else if (currentTime.after(assignment.getDiscussionDate())) {
			return ASSIGNMENT_CORRECTED;
		} else {
			return STATE_INEXIST;
		}
	}

	/*
	 * Metodos de Validacao de assignments para Criação e Edição
	 */
	private boolean validateAssignmentInCreation(Assignment assignment)
			throws CreateAssignmentException {

		return this.assignmentAttributesBasicValidations(assignment);

	}

	private boolean validateAssignmentCreated(Assignment assignment)
			throws CreateAssignmentException, EditingAssignmentException {

		// Os testes de JAH_CRIADO s�o somados aos de EM_CRIACAO
		validateAssignmentInCreation(assignment);

		String defaultDirTests = "/periodo" + assignment.getPeriod().toString()
				+ "/testes/" + assignment.getId() + "/";
		String interfaceDirDefault = "/periodo"
				+ assignment.getPeriod().toString() + "/interface/"
				+ assignment.getId() + "/";

		// E checa se o caminho do servidor para as pastas de testes e interface
		// estah/mantem-se correto
		if ((assignment.getTestsDirectory() != null && !assignment
				.getTestsDirectory().equals(""))
				&& !assignment.getTestsDirectory().endsWith(defaultDirTests)) {
			throw new EditingAssignmentException(
					"Hierarquia de Diretórios de Testes Automáticos diferente do default: '/periodo<periodo>/testes/<assignment_id>/'. O roteiro não pode ser atualizado!");
		} else if ((assignment.getInterfaceDirectory() != null && !assignment
				.getInterfaceDirectory().equals(""))
				&& !assignment.getInterfaceDirectory().endsWith(
						interfaceDirDefault)) {
			throw new EditingAssignmentException(
					"Hierarquia de Diretórios da Interface diferente do default: '/periodo<periodo>/interface/<assignment_id>/'. O roteiro não pode ser atualizado!");
		}

		return true;
	}

	private boolean validateAssignmentReleased(Assignment assignment)
			throws EditingAssignmentException, CreateAssignmentException,
			ReleasesAssignmentException {

		assignmentAttributesBasicValidations(assignment);

		// E checa se este possui as caracteristicas ideais para ser/manter-se
		// liberado
		if (assignment.getInterfaceDirectory() == null
				|| assignment.getParticipantsMaxNumber() <= 0
				|| assignment.getSendMaxNumber() <= 0
				|| ((assignment.getAutomaticTestsPercentage() > 0 || assignment
						.getAutomaticTestsPercentage() <= 100) && assignment
						.getTestsDirectory() == null)) {
			throw new ReleasesAssignmentException(
					"O roteiro "
							+ assignment.getName()
							+ " não pode ser liberado devido a falhas em sua especificação!");
		}
		return true;
	}

	/**
	 * Um assignment fechado n�o pode ser editado
	 * 
	 * @param assignment
	 * @return
	 * @throws EditingAssignmentException
	 * @throws CreateAssignmentException
	 */
	private boolean validateAssignmentClosed(Assignment assignment)
			throws CreateAssignmentException {

		return assignmentAttributesBasicValidations(assignment);
	}

	private boolean validateAssignmentCorrected(Assignment assignment)
			throws CreateAssignmentException {

		return assignmentAttributesBasicValidations(assignment);

	}

	private boolean assignmentAttributesBasicValidations(Assignment assignment)
			throws CreateAssignmentException {

		if (assignment.getName() == null || assignment.getName().equals("")) {
			throw new CreateAssignmentException(MsgErros.NOMEVAZIO
					.msg("Nome da atividade inválido. O roteiro não pode ser "
							+ criacaoOuAtualizacaoMsg + "!"));

		} else if (assignment.getParticipantsMaxNumber() != null
				&& assignment.getParticipantsMaxNumber() <= 0) {
			throw new CreateAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("O número máximo de integrantes deve ser sempre maior ou igual a 1 integrante. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (assignment.getSendMaxNumber() != null
				&& assignment.getSendMaxNumber() <= 0) {
			throw new CreateAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("O número máximo de envios deve ser sempre maior ou igual a 1. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (assignment.getPenaltyLateDays() != PENALTY_DAY_LATE_DEFAULT
				&& assignment.getPenaltyLateDays() < 0.0
				|| assignment.getPenaltyLateDays() > 10.0) {
			throw new CreateAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("A Penalidade por dia de atraso deve ser sempre maior ou igual a 0,0 e menor ou igual a 10,0. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (assignment.getAutomaticTestsPercentage() != AUTOMATIC_TESTS_PERCENTAGE_DEFAULT
				&& (assignment.getAutomaticTestsPercentage() < 0 || assignment
						.getAutomaticTestsPercentage() > 100)) {
			throw new CreateAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("A Porcentagem autom�tica da avaliação deve ser sempre >= 0 e <= 100. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if ((assignment.getTestTimeLimit() != null && assignment
				.getAutomaticTestsPercentage() != AUTOMATIC_TESTS_PERCENTAGE_DEFAULT)
				&& ((assignment.getAutomaticTestsPercentage() > 0 && assignment
						.getAutomaticTestsPercentage() <= 100) && assignment
						.getTestTimeLimit() <= 0)) {
			throw new CreateAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("Time-limit de execução dos testes por método inválido, deve ser sempre > 0 quando a Porcentagem Automática de Avaliação for > 0 e <= 100. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if ((assignment.getAutomaticTestsPercentage() != AUTOMATIC_TESTS_PERCENTAGE_DEFAULT && assignment
				.getTestTimeLimit() != null)
				&& (assignment.getAutomaticTestsPercentage() == 0 && assignment
						.getTestTimeLimit() != 0)) {
			throw new CreateAssignmentException(
					MsgErros.VALORINVALIDO
							.msg("Se a Porcentagem Automática da Avaliação é 0, o Time-limit dos testes por método deve ser também 0. O roteiro não pode ser "
									+ criacaoOuAtualizacaoMsg + "!"));
		} else {
			return true;
		}
	}

}