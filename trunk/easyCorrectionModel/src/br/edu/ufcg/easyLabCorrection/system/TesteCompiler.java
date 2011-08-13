package br.edu.ufcg.easyLabCorrection.system;

import java.io.File;

import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;

public class TesteCompiler {
	
	public static void main(String[] args) throws Throwable {
		
		System sys = new System();
		Submission sub = sys.getSubmission(282);
		
		String testsDirectory = ServletUpload.local2
				+ sub.getTeamHasUserHasAssignment().getAssignment()
						.getTestsDirectory().replace("/", File.separator);
		String sourceDirectory = ServletUpload.local2
				+ sub.getUrl().replace("/", File.separator);
		String libDirectory = (ServletUpload.local2 + "/").replace("/",
				File.separator);
		
		sys.compilationUnit(sourceDirectory, testsDirectory, libDirectory);
	}
	
}
