package org.training.issueTracker.modelTask;


import org.training.issueTracker.modelTask.ImpBD.TaskImpBD;

public class TaskFactory {
	public static ITaskDAO getClassFromFactory(String path) {
		 return new  TaskImpBD( path);
	}
}
