<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page isELIgnored ="false" %>
 <%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="JavaScript">

var projectId =-1;
function changeSelected1() {
	 var projectSelect = document.getElementById("selProject");
	if (projectId!=-1) {
		
		var buildSelect = document.getElementById("buildSelect" + projectId);
		buildSelect.style.display = "none";
	}
	 projectId=projectSelect.options[projectSelect.selectedIndex].getAttribute('id');
	var buildSelect = document.getElementById("buildSelect" + projectId);
	buildSelect.style.display = "block";
		
}
function findBuild(){
	var sel = document.getElementById("buildSelect" + projectId); 
	var txt = sel.options[sel.selectedIndex].text;
	document.editIssue.valueBuild.value=txt;
	
}
function changeSelected2(){
	var sel = document.getElementById('stat'); 
	var txt = sel.options[sel.selectedIndex].text;
	changeSelected1();
	
	if (txt=='Closed') {
		
		document.editIssue.resolution.disabled=false;
		document.editIssue.types.disabled=true;
		document.editIssue.priority.disabled=true;
		document.editIssue.project.disabled=true;
		document.editIssue.buildsAll.disabled=true;
		document.editIssue.users.disabled=true;
		
		document.editIssue.summary.disabled=true;
		document.editIssue.description.disabled=true;
	}
	if (txt=='Reopened') {
		
		
		
		document.editIssue.resolution.disabled=true;
		document.editIssue.types.disabled=false;
		document.editIssue.priority.disabled=false;
		document.editIssue.project.disabled=false;
		document.editIssue.buildsAll.disabled=false;
		document.editIssue.users.disabled=false;
	
		document.editIssue.summary.disabled=false;
		document.editIssue.description.disabled=false;
		var resolutionSelect = document.getElementById('res');
		resolutionSelect.options[0].selected=true;
		
	}
}



window.onload = changeSelected2;
</script>
</head>
<body>
	<c:if test="${not empty user}">
	<div style="float:left">
		<form name='back' action='UserTaskController'>
			<INPUT TYPE='SUBMIT' name='Back' value='Back' >
		</form>
	</div>
	
	<div style="float:right">
		<form name='logout' action='LogoutController'>
			<INPUT TYPE='SUBMIT' name='Logout' value='Logout' >
		</form>
	</div>
		
		<br>
		<br>
	<form name='editIssue' action="UpdateEditIssueController">
	<input type="hidden" name="valueBuild" value="no">
	<div align="center">
		<span style="color:green;">${sucusses}</span>
		<span style="color:red;">${problemEditIssue}</span>
	</div>
		<label> Issue id  </label> &nbsp;&nbsp;&nbsp; 
		
		<input type="text" name='issue_id' readonly="readonly" value="${issue.id}" > <br>
		<label> Date create </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.createDate}"> <br>
		<label> Date modify </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.modifyDate}"> <br>
		<label> created by User </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.createdUser}"> <br>
		<label> modified by User </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.modifyUser}"> <br>
		
		<label style="float:left; clear:both;">
		<br>
		<span><b>Status</b></span>
		<br>
		<select id='stat'  name="status" style="float:left"  onchange="changeSelected2()">
		<c:forEach items="${AllStatus}"
			var="task1" varStatus="status">
			<c:if test="${ task1 ne 'New'}">
			<c:if test="${issue.status  eq task1}">
				<option selected="selected"> <c:out value="${task1}"/> </option>
			</c:if>
			<c:if test="${ issue.status ne task1 }">
				<option> <c:out value="${task1}"/> </option>
			</c:if>
			</c:if>
		</c:forEach>
		
		</select>
		
		</label>
		<label style="float:left;clear:both;">
		<br>
		<span><b>Types</b></span>
		<br>
		<select id='typ1' name="types" >
		<c:forEach items="${AllTypes}"
			var="task1" varStatus="status">
			<c:if test="${issue.type  eq task1}">
				<option selected="selected"> <c:out value="${task1}"/> </option>
			</c:if>
			<c:if test="${ issue.type ne task1 }">
				<option> <c:out value="${task1}"/> </option>
			</c:if>
		</c:forEach>
		</select>
		</label>
		
		
		<label style="float:left;clear:both;">
		<br>
		<span><b>Priority</b></span>
		<br>
		<select name="priority">
		<c:forEach items="${AllPriority}"
			var="task1" varStatus="status">
			<c:if test="${issue.priority  eq task1}">
				<option selected="selected"> <c:out value="${task1}"/> </option>
			</c:if>
			<c:if test="${ issue.priority ne task1 }">
				<option> <c:out value="${task1}"/> </option>
			</c:if>
		</c:forEach>
		</select>
		</label>
		
		<label style="float:left;clear:both;">	
		<br>
		<span><b>Project</b></span>
		<br>
		<select name="project" id="selProject" onchange="changeSelected1()">
		<c:forEach items="${AllProject}"
			var="task1" varStatus="status">
			
			<c:if test="${issue.project  eq task1.name}">
				<option selected="selected" id="${task1.idProject}"> <c:out value="${task1.name}"/> </option>
			</c:if>
			<c:if test="${ issue.project ne task1.name }">
				<option id="${task1.idProject}"> <c:out value="${task1.name}"/> </option>
			</c:if>
			
			
			
		</c:forEach>
		
		</select>
		</label>
		
		<label style="float:left;clear:both;">	
		<br>
		<span><b>Builds</b></span>
		<br>
		
		<c:forEach items="${AllProject}" var="project" varStatus="status">
			
			<select name="buildsAll" id="buildSelect${project.idProject}" style="display: none" >
			
			<c:forEach items="${project.builds}"
			
			var="task1" varStatus="status">
			
			<c:if test="${issue.buildFound  eq task1.name}">
				<option selected="selected" id="${task1.projectId}"> <c:out value="${task1.name}"/> </option>
			</c:if>
			<c:if test="${ issue.buildFound ne task1.name }">
				<option id="${task1.projectId}"> <c:out value="${task1.name}"/> </option>
			</c:if>
			
			
			
				
			</c:forEach>
		
			</select>
		</c:forEach>
		
		
		
		</label>
		
		
		
		<label style="float:left;clear:both;">	
		<br>
		<span><b>Users</b></span>
		<br>
		
		<select name="users">
		
		<c:forEach items="${AllUsers}"
			var="task1" varStatus="status">
			<c:if test="${issue.assignee  eq task1.email}">
				
				<option selected="selected"> <c:out value="${task1.email}"/> </option>
			</c:if>
			<c:if test="${ issue.assignee ne task1.email }">
				<option> <c:out value="${task1.email}"/> </option>
			</c:if>
		</c:forEach>	
		</select>
		</label>
		
		
		<label style="float:left;clear:both;" >
		<br>
		<span><b>resolution Issue</b></span>
		<br> 
		<select id='res' name="resolution" disabled="disabled" >
		<option value="Empty" >Empty</option>
		<c:forEach items="${AllResolutions}"
			var="task1" varStatus="status">
			
			<c:if test="${issue.resolution eq task1}">
				<option selected="selected"> <c:out value="${task1}"/> </option>
			</c:if>
			<c:if test="${ issue.resolution ne task1 }">
				<option> <c:out value="${task1}"/> </option>
			</c:if>
			
		</c:forEach>
		</select>
		</label>
		
		
		<div style="float:left; clear:both;">
		<br>
		<input type="submit" name="Update" value="Update" onclick="findBuild()">
	    </div>
	   
	   <div align="right">
	    <label>
	    <span><b>short description</b></span>
	    <br>
	   <input type='text' name='summary' value="${issue.summary}" size=40> 
	    </label>
	   </div>
	  <br>
	  	
		 <div align="right">
		 <label>
			
		 <span><b>huge description</b> </span>
		 <br>
		 <textarea  name='description' cols="40 "  rows="10" lang="UTF-8">
		"${issue.description}" 
		 </textarea> 
		 </label>
		 </div>
				
	</form>  
	</c:if>
	<c:if test="${empty user }">
		<form action="AuthentificationServlet">
		<div align="center" style="color:green">
			<b><em>Please be identified</em></b>
		</div>
		<br>
	
		<div>
			<div>
			<label> 
			<span style="color:green">please enter your name</span>
			<br>
			<input type='text' size='20' name='login'>
			</label>
			</div>
			<div>
			<label> 
			<br>
			<span style="color:green">please enter your password</span>
			<br>
			<input type='password' size='20' name='password'>
			</label>
			</div>
			<div>
			<br>
			<INPUT TYPE='SUBMIT'  name='registrate' value='registrate'>
			</div>
      		<br>
		</div>
	</form>
	
		<label> Issue id  </label> &nbsp;&nbsp;&nbsp; 
		
		<input type="text" name='issue_id' readonly="readonly" value="${issue.id}" > <br>
		<label> Date create </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.createDate}"> <br>
		<label> Date modify </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.modifyDate}"> <br>
		<label> created by User </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.createdUser}"> <br>
		<label> modified by User </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.modifyUser}"> <br>
		<label> Status </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.status}"> <br>
		<label>Types </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.type}"> <br>	
		<label>Priority </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.priority}"> <br>	
		<label>Project </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.project}"> <br>	
		<label>buildFound </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.buildFound}"> <br>
		<label>Assignee </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.assignee}"> <br>
		<label>Resolution </label> &nbsp;&nbsp;&nbsp;
		<input type="text" disabled="disabled"  value="${issue.resolution}"> <br>
		
	</c:if>
	
</body>
</html>