<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false"%>
<html>
 <head>
  <title>Update Trainer</title>
   <link rel="stylesheet" href="resources/css/style.css">
 </head>
 <body>
  <div class="addContainer">
  <%@ page import="com.ideas2it.employee.models.Trainer" %>
  <%
    String operation = (String) request.getAttribute("action");
	Trainer trainer = (Trainer) request.getAttribute("trainer");
	String heading = "Add Trainer";
	if (operation.equals("updateTrainer")) {
	    heading = "Update Trainer";
	}
  %>
  <h3> <%= heading %> </h3>
  <form:form modelAttribute="trainer" action="addOrUpdateTrainer" method="get">
  <form:hidden path="employee.id" />
   Name : <form:input type="text" path="employee.name" name="name" required="required"/>
   Address : <form:input type="text" path="employee.address" name="address" required="required"/></br></br>
   Mobile Number : <form:input type="number" path="employee.mobileNumber" name="mobileNumber" required="required"/></br></br>
   Email : <form:input type="email" path="employee.email" name="email" required="required" /></br></br>
   Date of Joining : <form:input type="date" path="employee.dateOfJoining" name="dateOfJoining" required="required"/></br></br>
   Date of birth : <form:input type="date" path="employee.dateOfBirth" name="dateOfBirth" required="required"/></br></br>
   Blood Group : <form:select path="employee.bloodGroup">
   <form:option value="A Positive" label="A Positive"/>
   <form:option value="B Positive" label="B Positive"/>
   <form:option value="O Positive" label="O Positive"/>
   <form:option value="AB Positive" label="AB Positive"/>
   <form:option value="A Negative" label="A Negative"/>
   <form:option value="B Negative" label="B Negative"/>
   <form:option value="O Negative" label="O Negative"/>
   <form:option value="AB Negative" label="AB Negative"/>
   </form:select>
   Qualification : <form:input type="text" path="employee.qualification.description" name="qualification" required="required"/></br></br>
   <form:hidden path="trainerId" />
   trainingExperience : <form:input type="number" path="trainingExperience" name="trainingExperience" required="required"/></br>
   </br><input class ="btn" type="submit" value="<%= heading %>"/>
   <a href="/"> <input class ="btn" type="button" value="Back"/></a>
  </form:form>
  </div>
 </body>
</html>
