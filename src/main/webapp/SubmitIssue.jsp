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
var projectId = 0;
function changeSelected() {
	  
	
	
	var projectSelect = document.getElementById("selProject");
	
	
	var f0=projectSelect.options[0].getAttribute('id');
	if (f0==0) {
		projectSelect.options[0] = null;
	}
	
	if (projectId!=0) {
		var buildSelect = document.getElementById("buildSelect" + projectId);
		buildSelect.style.display = "none";
	}
	
	
	
	 projectId=projectSelect.options[projectSelect.selectedIndex].getAttribute('id');
	
	
	
	var buildSelect = document.getElementById("buildSelect" + projectId);
	
	buildSelect.style.display = "block";
	
	
	
		
}
</script>
</head>
<body>
		
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
		<form action="AddSubmitController">
		
		
		<div align="center">
		<span style="color:green;">${sucusses}</span>
		<span style="color:red;">${errorAddIssue}</span>
		</div>
		
		<label style="float:left; clear:both;">
		<br>
		<span><b>Status</b></span>
		<br>
		<select name="status" style="float:left">
		<c:forEach items="${AllStatus}"
			var="task1" varStatus="status">
			<option> <c:out value="${task1}"/> </option>
		</c:forEach>
		
		</select>
		
		</label>
		<label style="float:left;clear:both;">
		<br>
		<span><b>Types</b></span>
		<br>
		<select name="types" >
		<c:forEach items="${AllTypes}"
			var="task1" varStatus="status">
			<option> <c:out value="${task1}"/> </option>
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
			<option> <c:out value="${task1}"/> </option>
		</c:forEach>
		</select>
		</label>
		
		<label style="float:left;clear:both;">	
		<br>
		<span><b>Project</b></span>
		<br>
		<select name="project" id="selProject" onchange="changeSelected()">
		<option id='0'> select project</option>
		<c:forEach items="${AllProject}"
			var="task1" varStatus="status">
			
			<option id="${task1.idProject}"> <c:out value="${task1.name}"/> </option>
		</c:forEach>
		
		</select>
		</label>
		
		<label style="float:left;clear:both;">	
		<br>
		<span><b>Builds</b></span>
		<br>
		<c:forEach items="${AllProject}" var="project">
			<select name="builds" id="buildSelect${project.idProject}"   style="display: none" >
			
			<c:forEach items="${project.builds}"
				var="task1" varStatus="status">
				<option id="${task1.projectId}"> <c:out value="${task1.name}"/> </option>
			</c:forEach>
		
			</select>
		</c:forEach>
		</label>
		
		<label style="float:left;clear:left;">	
		<br>
		<span><b>Users</b></span>
		<br>
		<select name="users">
		<option value="Empty" >Empty</option>
		<c:forEach items="${AllUsers}"
			var="task1" varStatus="status">
			<option> <c:out value="${task1.email}"/> </option>
		</c:forEach>	
		</select>
		</label>
		
		<div style="float:left; clear:both;">
		<br>
		<input type="submit" name="add" value="add">
	    </div>
	   <br>
	   <div align="center">
	    <label>
	    <span><b>short description</b> </span>
	    <br>
	    <input type='text' name='summary' size=40> 
	    </label>
	   </div>
	   <br>
		 <div align="center">
		 <label>
		 <span><b>huge description</b> </span>
		 <br>
		 <textarea  name='description' cols="40" rows="10" lang="UTF-8">
		 It's for typing huge description
		 </textarea> 
		 </label>
		 </div>
	</form>
		
		

</body>
</html>