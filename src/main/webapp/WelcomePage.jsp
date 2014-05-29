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
function setFormActionUpload(url,number) {
	document.editIssue.numberIssue.value=number;
	document.editIssue.action=url;
	document.editIssue.submit();
}
function setFormSorted(url,colum,kind){
	
	document.editIssue.action=url;
	document.editIssue.columSort.value=colum;
	document.editIssue.kindSort.value=kind;
	document.editIssue.submit();
}
</script>
</head>
<body>
	
	 <c:if test="${ empty user}">
 		
	
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
	
	<c:if test="${not empty problemAuthentification}">
		<br>
		<p> <span style='color: red'><c:out value="${problemAuthentification}"/></span>	</p>
		
		
	</c:if>
	<c:if test="${empty listAllTask }">
		<p> We don't have problem in all our application</p>
	</c:if>
	<c:if test="${not empty listAllTask }">
		<form name='editIssue' action="">
	<INPUT TYPE="hidden" name="numberIssue" value="no">
	<INPUT TYPE="hidden" name="columSort" value="no">
	<INPUT TYPE="hidden" name="kindSort" value="no">
		<table border="1"  align="center" cellspacing="0" width="90%">
			<c:forEach  items="${listAllTask}"
			var="task1" varStatus="status">
			<c:if test="${status.first}">
		<tr>	
		
		<th>ID <a  href="JavaScript:setFormSorted('SortedController','ISSUE.ID','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('SortedController','ISSUE.ID','DOWN')" >&darr;</a></th>
		<th>Priority  <a  href="JavaScript:setFormSorted('SortedController','KIND_PRIORITY.name','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('SortedController','KIND_PRIORITY.name','DOWN')" >&darr;</a></th>
		<th >Assignee</th>
		<th>Type <a  href="JavaScript:setFormSorted('SortedController','KIND_TYPE.name','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('SortedController','KIND_TYPE.name','DOWN')" >&darr;</a></th>
		<th>Status <a  href="JavaScript:setFormSorted('SortedController','Kind_Status.name','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('SortedController','Kind_Status.name','DOWN')" >&darr;</a></th>
		<th>Summary <a  href="JavaScript:setFormSorted('SortedController','ISSUE.SUMMARY','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('SortedController','ISSUE.SUMMARY','DOWN')" >&darr;</a></th> </tr>	
		</c:if>
		<tr> 
			<td> <a href="JavaScript:setFormActionUpload('EditIssueController',${task1.id })"> <c:out value="${task1.id }"/> </a> </td>
			<td> <c:out value="${task1.priority }"></c:out> </td>
			<td> <c:out value="${task1.assignee }"></c:out> </td>
			<td> <c:out value="${task1.type }"></c:out> </td>
			<td> <c:out value="${task1.status }"></c:out> </td>
			<td> <c:out value="${task1.summary }"></c:out> </td>
		 </tr>
			</c:forEach>
		</table>
		</form>
	</c:if>
	</c:if>
	<form name="search">
	<hr>
	<div style="float:left">	&nbsp;&nbsp;<INPUT TYPE='SUBMIT'  name='search' value='search'> <br>
	</div>
	</form>
	<c:if test="${not empty user}">
		
		<c:if test="${user.role eq 'USER' }">
		<form name='logout' action='LogoutController'>
			<div style="float:right;">
			<INPUT TYPE='SUBMIT' name='Logout' value='Logout' >
		    </div>
		</form>
		<div style="clear:both; float:left">
		<br>
		<a href='#'>Edit your data</a>
		<br>
		</div>
		<div>
			<form name='submitIssue' action="SubmitIssueController">
				<INPUT TYPE='SUBMIT'  name='Submit Issue' value='Submit Issue'>
			</form>
			
		</div>
		
		
		</c:if>
		<c:if test="${user.role eq 'ADMIN' }">
		
		<form name='form' action='LogoutController'>
		  <div style="float:right;">
		     <INPUT TYPE='SUBMIT'    name='Logout' value='Logout' >
		  </div>
		</form>
		<div style="float:left">
			<form name='submitIssue' action="SubmitIssueController">
				<INPUT TYPE='SUBMIT'  name='Submit Issue' value='Submit Issue'>
			</form>
			
		</div>
	   <div style="float:right;"><a href='#'> Edit your data</a>&nbsp;&nbsp;&nbsp;</div>
		<br><hr>
		<div align="center" style="color:green;">
		<h2>SUBMENU ADMIN </h2>
		</div>
		<div style="float:left;">
		 <h3 align="center" style="color:green;"> Main menu </h3>
		
		<ul> 	
		  <li><a href='#'>Projects</a></li>
		  <li><a href='#'>Statuses</a></li>
		  <li> <a href='#'>Resolutions</a></li> 
		  <li><a href='#'>Priorities</a></li>
		  <li><a href='#'>Types</a></li>
		</ul>
		
		</div>
		<div style="float:left">
		<h3 align="center" style="color:green;">Add menu</h3>
		<ul>
		<li><a href='#'>Project</a></li>
		<li><a href='#'>Resolution</a></li>
		<li><a href='#'>Priority</a></li>
		<li><a href='#'>Type</a></li>
		<li><a href='#'>Search User</a></li>
		<li><a href='#'>Add User</a></li>
		</ul>
		</div>
		</c:if>
 		<c:if test="${empty listUserTask }">
		<p> You don't have task</p>
		</c:if>
	<c:if test="${not empty listUserTask }">
	<form name='editIssue' action="">
	<INPUT TYPE="hidden" name="numberIssue" value="no">
	<INPUT TYPE="hidden" name="columSort" value="no">
	<INPUT TYPE="hidden" name="kindSort" value="no">
		<table border="1"  align="center" cellspacing="0" width="90%">
			<c:forEach  items="${listUserTask}"
			var="task1" varStatus="status">
			<c:if test="${status.first}">
		<tr>	
		<th>ID <a  href="JavaScript:setFormSorted('UserSortedTaskController','ISSUE.ID','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('UserSortedTaskController','ISSUE.ID','DOWN')" >&darr;</a></th>
		<th>Priority  <a  href="JavaScript:setFormSorted('UserSortedTaskController','KIND_PRIORITY.name','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('UserSortedTaskController','KIND_PRIORITY.name','DOWN')" >&darr;</a></th>
		<th >Assignee</th>
		<th>Type <a  href="JavaScript:setFormSorted('UserSortedTaskController','KIND_TYPE.name','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('UserSortedTaskController','KIND_TYPE.name','DOWN')" >&darr;</a></th>
		<th>Status <a  href="JavaScript:setFormSorted('UserSortedTaskController','Kind_Status.name','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('UserSortedTaskController','Kind_Status.name','DOWN')" >&darr;</a></th>
		<th>Summary <a  href="JavaScript:setFormSorted('UserSortedTaskController','ISSUE.SUMMARY','UP')">&uarr;</a>  <a href="JavaScript:setFormSorted('UserSortedTaskController','ISSUE.SUMMARY','DOWN')" >&darr;</a></th> </tr>	
		</c:if>
		<tr> 
			<td> <a href="JavaScript:setFormActionUpload('EditIssueController',${task1.id })"> <c:out value="${task1.id }"/> </a> </td>
			<td> <c:out value="${task1.priority }"></c:out> </td>
			<td> <c:out value="${task1.assignee }"></c:out> </td>
			<td> <c:out value="${task1.type }"></c:out> </td>
			<td> <c:out value="${task1.status }"></c:out> </td>
			<td> <c:out value="${task1.summary }"></c:out> </td>
		 </tr>
			</c:forEach>
		</table>
		</form>
	</c:if>
 		
 		
 	</c:if>
</body>
</html>