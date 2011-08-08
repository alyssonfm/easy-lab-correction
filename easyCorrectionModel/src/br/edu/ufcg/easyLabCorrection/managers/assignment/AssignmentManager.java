package br.edu.ufcg.easyLabCorrection.managers.assignment;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.AssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.Manager;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.AssignmentType;
import br.edu.ufcg.easyLabCorrection.util.ErrorMsgs;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing assignments in the system Easy Lab Correction.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class AssignmentManager extends Manager {

	/*
	 * Computation of the states of an assignment.
	 */
	private final int STATE_INEXIST = -1;

	/*
	 * State prior to the creation assignment.
	 */
	private final int ASSIGNMENT_IN_CREATION = 0;
	/*
	 * State after the creation of the assignment, but before the date of this
	 * release.
	 */
	private final int ASSIGNMENT_CREATED = 1;
	/*
	 * State after the release date of the assignment, but before the closing
	 * date (delivery) of this.
	 */
	private final int ASSIGNMENT_RELEASED = 2;
	/*
	 * State after the date of delivery of the assignment.
	 */
	private final int ASSIGNMENT_CLOSED = 3;

	/*
	 * State after the final date for correction.
	 */
	private final int ASSIGNMENT_CORRECTED = 4;

	private final double PENALTY_DAY_LATE_DEFAULT = -1;
	private final double AUTOMATIC_TESTS_PERCENTAGE_DEFAULT = -1;

	private String assignmentNextState = "NULL";

	/**
	 * Function used to retrieve an assignment by an identifier of assignment
	 * passed as parameter.<br>
	 * 
	 * @param assignmentId
	 *            The identifier of assignment used in the recovery.<br>
	 * @return The assignment whose identifier corresponds at the identifier
	 *         passed as parameter.<br>
	 */
	public Assignment getAssignment(int assignmentId) {
		return DAOFactory.DEFAULT.buildAssignmentDAO().getById(assignmentId);
	}

	/**
	 * Function used to retrieve all assignments of system.<br>
	 * 
	 * @return A list of all assignments of the system.<br>
	 */
	public List<Assignment> getAssignments() {
		return DAOFactory.DEFAULT.buildAssignmentDAO().findAllByStage();
	}

	/**
	 * Function used to retrieve all assignments of system.<br>
	 * 
	 * @return A list of all assignments of the system.<br>
	 */
	public List<Assignment> getAssignmentsByAFM() {
		List<Assignment> list = getInCreationAssignments();
		list.addAll(getReleasedAssignments());
		list.addAll(getInCorrectionAssignments());
		list.addAll(getClosedAssignments());
		return list;
	}

	/**
	 * Function used to retrieve an assignment released in the system, receives
	 * as parameter an assignment identifier.<br>
	 * 
	 * @param id
	 *            The assignment identifier used to retrieve a released
	 *            assignment.<br>
	 * @return The assignment released whose identifier corresponds at the
	 *         identifier passed as parameter.<br>
	 */
	public Assignment getReleasedAssignment(Integer id) {
		Date currentDate = easyCorrectionUtil.getRealTime();
		List<Assignment> list = DAOFactory.DEFAULT.buildAssignmentDAO()
				.findByReleasedAssignments(currentDate, id);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Function used to retrieve all assignments in creation of system.<br>
	 * 
	 * @return A list of all assignments in creation of the system.<br>
	 */
	public List<Assignment> getInCreationAssignments() {
		Date currentDate = easyCorrectionUtil.getRealTime();
		List<Assignment> inCreationAssignments = DAOFactory.DEFAULT
				.buildAssignmentDAO().findByInCreationAssignments(currentDate);
		return inCreationAssignments;
	}

	/**
	 * Function used to retrieve all assignments released of system.<br>
	 * 
	 * @return A list of all assignments released of the system.<br>
	 */
	public List<Assignment> getReleasedAssignments() {
		Date currentDate = easyCorrectionUtil.getRealTime();
		List<Assignment> releasedAssignments = DAOFactory.DEFAULT
				.buildAssignmentDAO().findByReleasedAssignments(currentDate);
		return releasedAssignments;
	}

	/**
	 * Function used to retrieve all assignments in correction of system.<br>
	 * 
	 * @return A list of all assignments in correction of the system.<br>
	 */
	public List<Assignment> getInCorrectionAssignments() {
		Date currentDate = easyCorrectionUtil.getRealTime();
		List<Assignment> inCorrectionAssignments = DAOFactory.DEFAULT
				.buildAssignmentDAO()
				.findByInCorrectionAssignments(currentDate);
		return inCorrectionAssignments;
	}

	/**
	 * Function used to retrieve all assignments closed of system.<br>
	 * 
	 * @return A list of all assignments closed of the system.<br>
	 */
	public List<Assignment> getClosedAssignments() {
		Date currentDate = easyCorrectionUtil.getRealTime();
		return DAOFactory.DEFAULT.buildAssignmentDAO().findByClosedAssignments(
				currentDate);
	}

	/**
	 * Function used to save a new assignment in database of system.<br>
	 * 
	 * @param assignmentTemp
	 *            The assignment to be saved in the system.<br>
	 * @return The assignment save in the system.<br>
	 * @throws AssignmentException
	 *             The exception that can be launched in an attempt to save the
	 *             assignment system.<br>
	 */
	public Assignment saveAssignment(Assignment assignmentTemp)
			throws AssignmentException {

		if (assignmentTemp == null) {
			throw new AssignmentException(ErrorMsgs.NULL_OBJECT
					.msg("Assignment"));
		}

		this.assignmentNextState = "created";

		assignmentTemp.setTestsDirectory("");
		assignmentTemp.setInterfaceDirectory("");

		validateAssignmentInCreation(assignmentTemp);

		int aux = DAOFactory.DEFAULT.buildAssignmentDAO().save(assignmentTemp);

		return DAOFactory.DEFAULT.buildAssignmentDAO().getById(aux);
	}

	/**
	 * Function used to update an assignment in the system.<br>
	 * 
	 * @param assignmentTemp
	 *            The assignment to be updated.<br>
	 * @return The assignment updated.<br>
	 * @throws AssignmentException
	 *             The exception that can be launched in an attempt to update
	 *             the assignment system.<br>
	 */
	public Assignment updateAssignment(Assignment assignmentTemp)
			throws AssignmentException {

		if (assignmentTemp == null) {
			throw new AssignmentException(ErrorMsgs.NULL_OBJECT
					.msg("Assignment"));
		}

		this.assignmentNextState = "updated";

		int currentAssignmentState = computesNewAssignmentState(assignmentTemp);

		switch (currentAssignmentState) {
		case (ASSIGNMENT_IN_CREATION):
			// This case should never occur, since this state should be the 
			// registration of assignments rather than editing.
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
			throw new AssignmentException(
					ErrorMsgs.UNKNOWN_ASSIGNMENT_STATE.msg());
		}

		// OK, passed by the cases of exception, can move on! =)
		Assignment a;

		try {
			a = getAssignment(assignmentTemp.getId());
			a = (Assignment) SwapperAtributosReflect.swapObject(a,
					assignmentTemp, Assignment.class);
			DAOFactory.DEFAULT.buildAssignmentDAO().update(a);

		} catch (EasyCorrectionException e) {
			throw new AssignmentException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("Assignment"));
		}
		return a;
	}

	/*
	 * Not being used yet ... And is not in our plans to use it ...
	 */
	/**
	 * Procedure used to delete an assignment of system.<br>
	 * 
	 * @param assignment
	 *            The assignment to be deleted.<br>
	 * @throws AssignmentException
	 *             The exception that can be launched in an attempt to delete
	 *             the assignment system.<br>
	 */
	public void deleteAssignment(Assignment assignment)
			throws AssignmentException {

		if (assignment == null) {
			throw new AssignmentException(ErrorMsgs.NULL_OBJECT
					.msg("Assignment"));
		}
		DAOFactory.DEFAULT.buildAssignmentDAO().delete(assignment);
	}
	
	public void deleteAssignmentByStage(Integer stageId) {
		
		DAOFactory.DEFAULT.buildAssignmentDAO().deleteByStage(stageId);
		
	}

	/* ************************************************************
	 * PRIVATE METHODS
	 * ************************************************************
	 */
	private int computesNewAssignmentState(Assignment assignmentTemp)
			throws AssignmentException {

		/*
		 * Compute the state before the new assignment, 
		 * we check if there is any change with respect to 
		 * the former state and apply the appropriate restrictions.
		 */
		if (assignmentTemp == null) {
			throw new AssignmentException(ErrorMsgs.NULL_OBJECT
					.msg("Assignment"));
		}

		this.checksDatesModification(assignmentTemp);
		return getCurrentAssignmentState(assignmentTemp);
	}

	private void checksDatesModification(Assignment newAssignment)
			throws AssignmentException {

		// Time in the instant
		Date currentDate = easyCorrectionUtil.getDataNow();
		Assignment oldAssignment = this.getAssignment(newAssignment.getId());

		int oldAssignmentState = (oldAssignment == null) ? ASSIGNMENT_IN_CREATION
				: getCurrentAssignmentState(oldAssignment);

		switch (oldAssignmentState) {
		case (ASSIGNMENT_IN_CREATION):
		case (ASSIGNMENT_CREATED):
			// LIBERAR, ENTREGA E DISCUSSAO PODEM SER MUDADAS
			// But only for the current day or after.
			if (newAssignment.getReleaseDate() == null
					|| newAssignment.getReleaseDate().before(currentDate)) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_RELEASE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDeliveryDate() != null
					&& newAssignment.getDeliveryDate().before(currentDate)) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DEADLINE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDiscussionDate() != null
					&& newAssignment.getDiscussionDate().before(currentDate)) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DISCUSSION_DATE
								.msg(assignmentNextState));

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
				throw new AssignmentException(
						ErrorMsgs.INVALID_RELEASE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDeliveryDate() != null
					&& newAssignment.getDeliveryDate().before(currentDate)) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DEADLINE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDiscussionDate() != null
					&& newAssignment.getDiscussionDate().before(currentDate)) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DISCUSSION_DATE
								.msg(assignmentNextState));

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
				throw new AssignmentException(
						ErrorMsgs.INVALID_RELEASE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDeliveryDate() == null
					|| (newAssignment.getDeliveryDate().before(currentDate) && (!newAssignment
							.getDeliveryDate().equals(
									oldAssignment.getDeliveryDate())))) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DEADLINE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDiscussionDate() != null
					&& newAssignment.getDiscussionDate().before(currentDate)) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DISCUSSION_DATE
								.msg(assignmentNextState));

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
				throw new AssignmentException(
						ErrorMsgs.INVALID_RELEASE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDeliveryDate() == null
					|| (newAssignment.getDeliveryDate().before(currentDate) && (!newAssignment
							.getDeliveryDate().equals(
									oldAssignment.getDeliveryDate())))) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DEADLINE_DATE
								.msg(assignmentNextState));

			} else if (newAssignment.getDiscussionDate() == null
					|| (newAssignment.getDiscussionDate().before(currentDate) && (!newAssignment
							.getDiscussionDate().equals(
									oldAssignment.getDiscussionDate())))) {
				throw new AssignmentException(
						ErrorMsgs.INVALID_DISCUSSION_DATE
								.msg(assignmentNextState));

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
			throw new AssignmentException(
					"Deadline for delivery before/same release date. The assignment can not be "
							+ assignmentNextState + "!");

		} else if (newAssignment.getDiscussionDate() != null
				&& newAssignment.getDeliveryDate() != null
				&& (newAssignment.getDiscussionDate().before(
						newAssignment.getDeliveryDate()) || newAssignment
						.getDiscussionDate().equals(
								newAssignment.getDeliveryDate()))) {
			throw new AssignmentException(
					"Deadline for discussion previous/equal to deadline for submission. The assignment can not be "
							+ assignmentNextState + "!");

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
	 * Metodos de Validacao de assignments para Criacao e Edicao
	 */
	private void validateAssignmentInCreation(Assignment assignment)
			throws AssignmentException {

		this.assignmentAttributesBasicValidations(assignment);

	}

	private void validateAssignmentCreated(Assignment assignment)
			throws AssignmentException {

		// Os testes de JAH_CRIADO sao somados aos de EM_CRIACAO
		validateAssignmentInCreation(assignment);

		String testsDirDefault = "/periodo" + assignment.getStage().toString()
				+ "/testes/" + assignment.getId() + "/";
		String environmentDirDefault = "/periodo"
				+ assignment.getStage().toString() + "/environment/"
				+ assignment.getId() + "/";

		System.out.println(assignment.getTestsDirectory());
		System.out.println(assignment.getInterfaceDirectory());

		// E checa se o caminho do servidor para as pastas de testes e interface
		// estah/mantem-se correto
		if ((assignment.getTestsDirectory() != null && !assignment
				.getTestsDirectory().equals(""))
				&& !assignment.getTestsDirectory().endsWith(testsDirDefault)) {
			throw new AssignmentException(
					ErrorMsgs.WRONG_SERVER_TEST_DIR_HIERARCHY
							.msg("updated"));
		} else if ((assignment.getInterfaceDirectory() != null && !assignment
				.getInterfaceDirectory().equals(""))
				&& !assignment.getInterfaceDirectory().endsWith(
						environmentDirDefault)) {
			throw new AssignmentException(
					ErrorMsgs.WRONG_SERVER_INTERFACE_DIR_HIERARCHY
							.msg("updated"));
		}
	}

	private void validateAssignmentReleased(Assignment assignment)
			throws AssignmentException {

		assignmentAttributesBasicValidations(assignment);

		// E checa se este possui as caracteristicas ideais para ser/manter-se
		// liberado
		if (assignment.getInterfaceDirectory() == null
				|| assignment.getParticipantsMaxNumber() <= 0
				|| assignment.getSendMaxNumber() <= 0
				|| ((assignment.getAutomaticTestsPercentage() > 0 || assignment
						.getAutomaticTestsPercentage() <= 100) && assignment
						.getTestsDirectory() == null)) {
			throw new AssignmentException(
					ErrorMsgs.RELEASED_ASSIGNMENT_FIELDS_CHANGED
							.msg(assignment.getName()));
		}
	}

	private void validateAssignmentClosed(Assignment assignment)
			throws AssignmentException {

		assignmentAttributesBasicValidations(assignment);
	}

	private void validateAssignmentCorrected(Assignment assignment)
			throws AssignmentException {

		assignmentAttributesBasicValidations(assignment);

	}

	public List<AssignmentType> listAssignmentType() {

		return DAOFactory.DEFAULT.buildAssignmentTypeDAO().findAll();
	}
	
	public List<Assignment> getAssignmentByCourse(String course) {

		return DAOFactory.DEFAULT.buildAssignmentDAO().findByCourse(course);
	}

	private boolean assignmentAttributesBasicValidations(Assignment assignment)
			throws AssignmentException {

		if (assignment.getName() == null || assignment.getName().equals("")) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_ASSIGNMENT_NAME
							.msg(assignmentNextState));

		} else if (assignment.getParticipantsMaxNumber() != null
				&& assignment.getParticipantsMaxNumber() <= 0) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_PARTICIPANTS_MAX_NUMBER
							.msg(assignmentNextState));

		} else if (assignment.getSendMaxNumber() != null
				&& assignment.getSendMaxNumber() <= 0) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_SUBMISSION_MAX_NUMBER
							.msg(assignmentNextState));

		} else if (assignment.getPenaltyPerDaysLate() != PENALTY_DAY_LATE_DEFAULT
				&& assignment.getPenaltyPerDaysLate() < 0.0
				|| assignment.getPenaltyPerDaysLate() > 10.0) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_PENALTY_PER_DAY_LATE
							.msg(assignmentNextState));

		} else if (assignment.getAutomaticTestsPercentage() != AUTOMATIC_TESTS_PERCENTAGE_DEFAULT
				&& (assignment.getAutomaticTestsPercentage() < 0 || assignment
						.getAutomaticTestsPercentage() > 100)) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_AUTOMATIC_ASSESSMENT_PERCENTAGE
							.msg(assignmentNextState));

		} else if ((assignment.getTestTimeLimit() != null && assignment
				.getAutomaticTestsPercentage() != AUTOMATIC_TESTS_PERCENTAGE_DEFAULT)
				&& ((assignment.getAutomaticTestsPercentage() > 0 && assignment
						.getAutomaticTestsPercentage() <= 100) && assignment
						.getTestTimeLimit() <= 0)) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_EXECUTION_TIME_LIMIT_NOT_ZERO
							.msg(assignmentNextState));

		} else if ((assignment.getAutomaticTestsPercentage() != AUTOMATIC_TESTS_PERCENTAGE_DEFAULT && assignment
				.getTestTimeLimit() != null)
				&& (assignment.getAutomaticTestsPercentage() == 0 && assignment
						.getTestTimeLimit() != 0)) {
			throw new AssignmentException(
					ErrorMsgs.INVALID_EXECUTION_TIME_LIMIT_ZERO
							.msg(assignmentNextState));
		} else {
			return true;
		}
	}
	
}