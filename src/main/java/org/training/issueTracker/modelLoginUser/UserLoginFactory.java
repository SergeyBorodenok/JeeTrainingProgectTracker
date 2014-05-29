package org.training.issueTracker.modelLoginUser;

import org.training.issueTracker.modelLoginUser.ImplBD.UserLoginImpBD;

public class UserLoginFactory {
	public static IUserLoginDAO getClassFromFactory(String path) {
		 return new  UserLoginImpBD(path);
	}
}
