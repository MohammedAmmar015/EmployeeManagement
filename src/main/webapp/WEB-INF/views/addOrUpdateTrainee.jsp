<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
 <head>
  <title>Trainee</title>
   <link rel="stylesheet" href="resources/css/style.css" >
 </head>
 <body>
  <div class="addContainer">
  <%@ page import="com.ideas2it.employee.models.Trainee"
	   import="com.ideas2it.employee.models.Trainer"	
  %>
  <% String operation = (String) request.getAttribute("action");
	String heading = "Add Trainee";
	if (operation.equals("updateTrainee")) {
	    heading = "Update Trainee";
	}	
  %>
  <h3> <%= heading %> </h3>
  <form:form modelAttribute="trainee" action="addOrUpdateTrainee" method="get">
    <form:hidden path="employee.id"></form:hidden>
     Name : <form:input type="text" path="employee.name" name="name" required="required" /></br></br>
     Address : <form:input type="text" path="employee.address" name="address" required="required" /></br></br>
     Mobile Number : <form:input type="number" path="employee.mobileNumber" name="mobileNumber" required="required" /></br></br>
     Email : <form:input type="email" path="employee.email" name="email" required="required" /></br></br>
     Date of Joining : <form:input type="date" path="employee.dateOfJoining" name="dateOfJoining" required="required" /></br></br>
     Date of birth : <form:input type="date" path="employee.dateOfBirth" name="dateOfBirth" required="required" /></br></br>
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
     Qualification : <form:input type="text" path="employee.qualification.description" name="qualification" required="required" /></br></br>
     <form:hidden path="traineeId" />
     Training Period : <form:input type="number" path="trainingPeriod" name="trainingPeriod" required="required" /></br></br>
     Course : <form:input type="text" path="course" name="course" required="required" /></br></br>
     Batch Number : <form:input type="number" path="batchNumber" name="batchNumber" required="required" /></br></br>
     Trainer Ids : </br>
     <form:select path="trainerIds">
           <c:forEach var="trainer" items="${trainers}" >
                 <form:option value="${trainer.employee.id}" label="${trainer.employee.id}"/>
           </c:forEach>
     </form:select>
   </br><input class = "btn" type="submit" value="<%= heading %>"/>
   <a href="/"> <input class = "btn" type="button" value="Back"></a>
  </form:form>
  </div>
 </body>
</html>