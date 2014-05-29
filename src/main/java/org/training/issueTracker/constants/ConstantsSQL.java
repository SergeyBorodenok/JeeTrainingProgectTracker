package org.training.issueTracker.constants;

public class ConstantsSQL {
	public static final String NAME_BD="IssueBD";
	public static final String QUERY_SELECT_USER="SELECT  us.ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL,us.PASSWORD, kR.ROLE from"
		      		+ "  USER_ISSUE as us,KIND_ROLE as kR where us.ROLE_ID=kR.id  and us.EMAIL=? and us.password=?" ;
	public static final String DRIVER="org.h2.Driver";
	public static final String ERROR_DB="Can't connect with data base";
	
	public static final String PATH_H2_DB="jdbc:h2:file://";
	public static final String LOGIN_DB="Sergey";
	public static final String PASSWORD_DB="proba";
	public static final String QUERY_SELECT_ISSUE="SELECT  DISTINCT ISSUE.ID,ISSUE.CREATE_DATE, ISSUE.MODIFY_DATE,"
			  +"ISSUE.SUMMARY, ISSUE.DESCRIPTION, USER_ISSUE.EMAIL  as createUser,"+ 
			  "Kind_Status.name, KIND_TYPE.name,KIND_PRIORITY.name,PROJECT.name, KIND_BUILD.name"+
			  " FROM ISSUE, KIND_BUILD, KIND_PRIORITY,"+
			      "  KIND_ROLE, KIND_STATUS,"+
			      " KIND_TYPE, PROJECT, USER_ISSUE where ISSue.Created_BY_ID=USER_ISSUE.id  "+   
			     " and ISSue.Status_ID=kind_STATUS.id and ISSUE.Type_ID= KIND_TYPE.id and ISSUE.Priority_id=KIND_PRIORITY.id"+
			    "  and ISSue.Project_id=PROJECT.id and ISSUE.Build_found_id=KIND_BUILD.id ";
	public static final String QUERY_TASK_FOR_ID="SELECT  DISTINCT ISSUE.ID,ISSUE.CREATE_DATE, ISSUE.MODIFY_DATE,"
			  +"ISSUE.SUMMARY, ISSUE.DESCRIPTION, USER_ISSUE.EMAIL  as createUser,"+ 
			  "Kind_Status.name, KIND_TYPE.name,KIND_PRIORITY.name,PROJECT.name, KIND_BUILD.name"+
			  " FROM ISSUE, KIND_BUILD, KIND_PRIORITY,"+
			      "  KIND_ROLE, KIND_STATUS,"+
			      " KIND_TYPE, PROJECT, USER_ISSUE where ISSue.Created_BY_ID=USER_ISSUE.id  "+   
			     " and ISSue.Status_ID=kind_STATUS.id and ISSUE.Type_ID= KIND_TYPE.id and ISSUE.Priority_id=KIND_PRIORITY.id"+
			    "  and ISSue.Project_id=PROJECT.id and ISSUE.Build_found_id=KIND_BUILD.id and  ISSUE.ID=?";
	public static final String QUERY_TASK_FOR_USER="SELECT  DISTINCT ISSUE.ID,ISSUE.CREATE_DATE, ISSUE.MODIFY_DATE,"
			  +"ISSUE.SUMMARY, ISSUE.DESCRIPTION, USER_ISSUE.EMAIL  as createUser,"+ 
			  "Kind_Status.name, KIND_TYPE.name,KIND_PRIORITY.name,PROJECT.name, KIND_BUILD.name"+
			  " FROM ISSUE, KIND_BUILD, KIND_PRIORITY,"+
			      "  KIND_ROLE, KIND_STATUS,"+
			      " KIND_TYPE, PROJECT, USER_ISSUE where ISSue.Created_BY_ID=USER_ISSUE.id  "+   
			     " and ISSue.Status_ID=kind_STATUS.id and ISSUE.Type_ID= KIND_TYPE.id and ISSUE.Priority_id=KIND_PRIORITY.id"+
			    "  and ISSue.Project_id=PROJECT.id and ISSUE.Build_found_id=KIND_BUILD.id and ISSue.assignee_id=? ";
	public static final String QUERY_MODIFY_USER="SELECT  USER_ISSUE.EMAIL from ISSUE,USER_ISSUE where ISSue.Modify_by_id=USER_ISSUE.id  and  ISSue.id=?";
	public static final String QUERY_RESOLUTION="SELECT  KIND_RESOLUTION.NAME from ISSUE,KIND_RESOLUTION where ISSue.RESOLUTION_id=KIND_RESOLUTION.id and  ISSue.id=? ";
	public static final String QUERY_ASSIGNEE="SELECT  USER_ISSUE.EMAIL from ISSUE,USER_ISSUE where ISSue.assignee_id=USER_ISSUE.id  and  ISSue.id=? ";
	public static final String QUERY_ALL_STATUS="SELECT  NAME from KIND_STATUS  ";
	public static final String QUERY_ALL_TYPE="SELECT  KIND_TYPE.name from KIND_TYPE  ";
	public static final String QUERY_ALL_PRIORITY="SELECT  KIND_PRIORITY.name from KIND_PRIORITY  ";
	public static final String QUERY_ALL_BUILD="SELECT   KIND_BUILD.ID,KIND_BUILD.name , KIND_BUILD.Project_ID from KIND_BUILD  ";
	public static final String QUERY_ALL_RESOLUTIONS="SELECT  KIND_RESOLUTION.NAME from KIND_RESOLUTION  ";
	public static final String QUERY_ALL_PROJECT="SELECT DISTINCT  PROJECT.id, PROJECT.name , Project.Description,Kind_build.id,Kind_build.name"
			+ " ,Kind_build.project_id, us.ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL,us.PASSWORD, kR.ROLE from"
    		+ " PROJECT,Kind_build, USER_ISSUE as us,KIND_ROLE as kR where us.ROLE_ID=kR.id  "
    		+ " and  Project.Build_ID=Kind_build.id  and  Project.MANAGER_ID=us.id  ";
	public static final String QUERY_ALL_USER="SELECT  us.ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL,us.PASSWORD, kR.ROLE from"
    		+ "  USER_ISSUE as us,KIND_ROLE as kR where us.ROLE_ID=kR.id  " ;
	public static final String QUERY_INSERT_ISSUE=" INSERT INTO ISSUE(CREATE_DATE,CREATED_BY_ID,MODIFY_DATE,MODIFY_BY_ID,SUMMARY,DESCRIPTION,STATUS_ID, TYPE_ID,PRIORITY_ID,PROJECT_ID,BUILD_FOUND_ID,ASSIGNEE_ID)  values"+
    		"(CURRENT_DATE(),?,CURRENT_DATE(),?,?,?, select ID from kind_status where "+
    		"NAME=? , select ID from kind_type where  NAME=?, select ID from kind_priority where Name=?, select ID from PROJECT "+
    		"where NAME=?, select id from kind_build where NAME=? ,select id from USER_ISSUE where EMAIL=?) ";
	public static final String QUERY_UPDATE_CLOSED_ISSUE="UPDATE ISSUE as issu set issu.MODIFY_DATE=CURRENT_DATE() , issu.MODIFY_BY_ID=?"
			+ ", issu.STATUS_ID=(select kind_status.ID from kind_status where name=?),"
			+ "issu.RESOLUTION_ID= (select KIND_RESOLUTION.id from KIND_RESOLUTION where NAME=?) where issu.ID=? ";
	public static final String QUERY_UPDATE_ISSUE=" UPDATE ISSUE as issu set issu.MODIFY_DATE=CURRENT_DATE() , issu.MODIFY_BY_ID=?,"
			+ " issu.SUMMARY=? , issu.DESCRIPTION=? ,"
			+ " issu.STATUS_ID=(select kind_status.ID from kind_status where name=?), issu.RESOLUTION_ID= (select KIND_RESOLUTION.id from KIND_RESOLUTION where NAME=?"
			+ ") ,issu.TYPE_ID= (select kind_type.ID from kind_type where  NAME=?), issu.PRIORITY_ID= (select kind_priority.ID from kind_priority where Name=? ),"
			+ "issu.PROJECT_ID= (select PROJECT.ID from PROJECT where NAME=?), issu.BUILD_FOUND_ID= (select kind_build.id from kind_build where NAME=?"
			+ " and kind_build.PROJECT_ID="	+ "(select PROJECT.ID from PROJECT where PROJECT.NAME=?) ), issu.ASSIGNEE_ID= (select USER_ISSUE.id from USER_ISSUE where EMAIL=?) where issu.ID=?";
	public static final String STATUS_CLOSED="Closed";
	public static final String DIRECTION="UP";
	public static final String ORDER_BY="ORDER BY ";
	public static final String DESC=" DESC";
	public static final String QUERY_GET_BUILD_FOR_PROJECT="SELECT   KIND_BUILD.ID,KIND_BUILD.name , KIND_BUILD.Project_ID from KIND_BUILD  where KIND_BUILD.Project_ID=?";
}
