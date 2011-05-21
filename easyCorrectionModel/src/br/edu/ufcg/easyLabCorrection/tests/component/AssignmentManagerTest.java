package br.edu.ufcg.easyLabCorrection.tests.component;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ufcg.easyLabCorrection.exceptions.AssignmentException;
import br.edu.ufcg.easyLabCorrection.managers.AssignmentManager;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;
import br.edu.ufcg.easyLabCorrection.system.Facade;

/**
 * This test class is going to test the AssignmentManager, that has the
 * following roles: Assignment CRUD
 * 
 */
public class AssignmentManagerTest {

	private Facade facade;
	private AssignmentManager assignment;
	private static final Calendar testCalendar = Calendar.getInstance();

	private int idOK;
	private Stage stageOK;
	private String nameOK;
	private String descriptionOK;
	private Date releaseDateOK;
	private Date deliveryDateOK;
	private Date discussionDateOK;
	private Integer participantsMaxNumberOK;
	private Integer sendMaxNumberOK;
	private double automaticTestsPercentageOK;
	private double penaltyPerDaysLateOK;
	private Integer testTimeLimitOK;
	private String interfaceDirectoryOK;
	private String testsDirectoryOK;

	public AssignmentManagerTest() {
		facade = new Facade();

		this.idOK = 0;
		this.stageOK = new Stage(1, "2011.1");
		this.nameOK = "ROTEIRO OK";
		this.descriptionOK = "DESCRIPTION OK";
		testCalendar.add(Calendar.DAY_OF_YEAR, 7);
		this.releaseDateOK = testCalendar.getTime();
		testCalendar.add(Calendar.DAY_OF_YEAR, 7);
		this.deliveryDateOK = testCalendar.getTime();
		testCalendar.add(Calendar.DAY_OF_YEAR, 7);
		this.discussionDateOK = testCalendar.getTime();
		this.participantsMaxNumberOK = 1;
		this.sendMaxNumberOK = 1;
		this.automaticTestsPercentageOK = 0.5;
		this.penaltyPerDaysLateOK = 0.5;
		this.testTimeLimitOK = 1;
		this.interfaceDirectoryOK = "Interface Dir OK";
		this.testsDirectoryOK = "Tests Dir OK";

	}

	@BeforeClass
	public void reinicializaBD() {
		facade.reinicializaBancoDeDados();
	}

	@Test
	public void AssignmentCRUDBadParametersTest() {

		Assert.assertNull(assignment.getAssignment(1)); // NULL
		Assert.assertEquals(assignment.getAssignments().size(), 0); // EMPTY
		Assert.assertNull(assignment.getReleasedAssignment(1)); // NULL
		Assert.assertEquals(assignment.getReleasedAssignments().size(), 0); // EMPTY
		Assert.assertEquals(assignment.getClosedAssignments().size(), 0); // EMPTY

		/*
		 * CREATE
		 */
		Assignment aNULL = null;
		Assignment a1 = new Assignment(-1, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a2 = new Assignment(idOK, new Stage(-1, "2011.1"), nameOK,
				descriptionOK, releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a3 = new Assignment(idOK, new Stage(idOK, "xxxx.xxxxxxx"),
				nameOK, descriptionOK, releaseDateOK, deliveryDateOK,
				discussionDateOK, participantsMaxNumberOK, sendMaxNumberOK,
				penaltyPerDaysLateOK, automaticTestsPercentageOK,
				testTimeLimitOK, interfaceDirectoryOK, testsDirectoryOK);
		Assignment a4 = new Assignment(idOK, stageOK, null, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a5 = new Assignment(idOK, stageOK, "", descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		// WE DECIDED NOT TO TEST THE DATES, BECAUSE OF A POSSIBLE CHANGE ON
		// THE REQUIREMENTS
		Assignment a6 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK, -1,
				sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a7 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK, 0,
				sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a8 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, -1, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a9 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, 0, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a10 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, -1,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a11 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, 1.1,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		Assignment a12 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				-1, testTimeLimitOK, interfaceDirectoryOK, testsDirectoryOK);
		Assignment a13 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				1.1, testTimeLimitOK, interfaceDirectoryOK, testsDirectoryOK);
		Assignment a14 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, -1, interfaceDirectoryOK,
				testsDirectoryOK);
		Assignment a15 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, 0, interfaceDirectoryOK,
				testsDirectoryOK);
		Assignment a16 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK, null,
				testsDirectoryOK);
		Assignment a17 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK, "",
				testsDirectoryOK);
		Assignment a18 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, null);
		Assignment a19 = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, "");

		Assignment aOK = new Assignment(idOK, stageOK, nameOK, descriptionOK,
				releaseDateOK, deliveryDateOK, discussionDateOK,
				participantsMaxNumberOK, sendMaxNumberOK, penaltyPerDaysLateOK,
				automaticTestsPercentageOK, testTimeLimitOK,
				interfaceDirectoryOK, testsDirectoryOK);
		
		saveBadAssignment(aNULL);
		saveBadAssignment(a1);
		saveBadAssignment(a2);
		saveBadAssignment(a3);
		saveBadAssignment(a4);
		saveBadAssignment(a5);
		saveBadAssignment(a6);
		saveBadAssignment(a7);
		saveBadAssignment(a8);
		saveBadAssignment(a9);
		saveBadAssignment(a10);
		saveBadAssignment(a11);
		saveBadAssignment(a12);
		saveBadAssignment(a13);
		saveBadAssignment(a14);
		saveBadAssignment(a15);
		saveBadAssignment(a16);
		saveBadAssignment(a17);
		saveBadAssignment(a18);
		saveBadAssignment(a19);

		try {
			assignment.saveAssignment(aOK);
		} catch (AssignmentException e) {
			Assert.assertTrue(false);
		}

		/*
		 * RETRIEVE
		 */

		Assert.assertNull(assignment.getAssignment(-1)); // NULL
		Assert.assertNull(assignment.getAssignment(0)); // NULL
		Assert.assertNotSame(assignment.getAssignments().size(), 0); // NOT_EMPTY
		Assert.assertNull(assignment.getReleasedAssignment(-1)); // NULL
		Assert.assertNull(assignment.getReleasedAssignment(0)); // NULL
		Assert.assertEquals(assignment.getReleasedAssignments().size(), 0); // EMPTY
		Assert.assertEquals(assignment.getClosedAssignments().size(), 0); // EMPTY

		/*
		 * UPDATE (call save for assignment objects with the same id as aOK, but
		 * with bad parameters)
		 */

		saveBadAssignment(aNULL);
		saveBadAssignment(a2);
		saveBadAssignment(a3);
		saveBadAssignment(a4);
		saveBadAssignment(a5);
		saveBadAssignment(a6);
		saveBadAssignment(a7);
		saveBadAssignment(a8);
		saveBadAssignment(a9);
		saveBadAssignment(a10);
		saveBadAssignment(a11);
		saveBadAssignment(a12);
		saveBadAssignment(a13);
		saveBadAssignment(a14);
		saveBadAssignment(a15);
		saveBadAssignment(a16);
		saveBadAssignment(a17);
		saveBadAssignment(a18);
		saveBadAssignment(a19);

		/*
		 * DELETE
		 */

		try {
			assignment.deleteAssignment(null);
			Assert.assertTrue(false);
		} catch (AssignmentException e) {
		}
		try {
			assignment.deleteAssignment(a1);
			Assert.assertTrue(false);
		} catch (AssignmentException e) {
		}

	}

	private void saveBadAssignment(Assignment a) {
		try {
			assignment.saveAssignment(a);
			Assert.assertTrue(false);
		} catch (AssignmentException e) {
		}
	}

}
