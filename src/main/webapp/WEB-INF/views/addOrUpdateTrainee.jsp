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
  <%
    String action = (String) request.getAttribute("action");
	String heading = "Add Trainee";
	if (action.equals("updateTrainee")) {
	    heading = "Update Trainee";
	}	
  %>
  <h3> <%= heading %> </h3>
  <form:form modelAttribute="trainee" action="addOrUpdateTrainee?action=${action}" method="post">
    <form:hidden path="employee.id"/>
    <form:hidden path="traineeId" />
     <table>
             <tr>
                 <td>
                     Name :
                 </td>
                 <td>
                     <form:input type="text" path="employee.name" name="name" required="required"/>
                 </td>
             </tr>
             <tr>
                 <td>
                     Address :
                 </td>
                 <td>
                     <form:input type="text" path="employee.address" name="address" required="required"/>
                 </td>
             </tr>
             <tr>
                 <td>
                     Mobile Number :
                 </td>
                 <td>
                     <form:input type="number" path="employee.mobileNumber" name="mobileNumber" required="required"/>
                 </td>
             </tr>
              <tr>
                  <td>
                      Email :
                  </td>
                  <td>
                      <form:input type="email" path="employee.email" name="email" required="required" />
                  </td>
              </tr>
             <tr>
                 <td>
                     Date of Joining :
                 </td>
                 <td>
                     <form:input type="date" path="employee.dateOfJoining" name="dateOfJoining" required="required"/>
                 </td>
             </tr>
             <tr>
                 <td>
                     Date of birth :
                 </td>
                 <td>
                     <form:input type="date" path="employee.dateOfBirth" name="dateOfBirth" required="required"/>
                 </td>
             </tr>
             <tr>
                 <td>
                     Blood Group :
                 </td>
                 <td>
                     <form:select path="employee.bloodGroup">
                        <form:option value="A Positive" label="A Positive"/>
                        <form:option value="B Positive" label="B Positive"/>
                        <form:option value="O Positive" label="O Positive"/>
                        <form:option value="AB Positive" label="AB Positive"/>
                        <form:option value="A Negative" label="A Negative"/>
                        <form:option value="B Negative" label="B Negative"/>
                        <form:option value="O Negative" label="O Negative"/>
                        <form:option value="AB Negative" label="AB Negative"/>
                     </form:select>
                 </td>
             </tr>
             <tr>
                 <td>
                     Qualification :
                 </td>
                 <td>
                     <form:input type="text" path="employee.qualification.description" name="qualification" required="required"/>
                 </td>
             </tr>
             <tr>
                         <td>
                             Training Period :
                         </td>
                         <td>
                             <form:input type="number" path="trainingPeriod" name="trainingPeriod" required="required" />
                         </td>
                     </tr>
             <tr>
                         <td>
                             Course :
                         </td>
                         <td>
                             <form:input type="text" path="course" name="course" required="required" />
                         </td>
                     </tr>
                    <tr>
                         <td>
                             Batch Number :
                         </td>
                         <td>
                             <form:input type="number" path="batchNumber" name="batchNumber" required="required" />
                         </td>
                     </tr>
                    <tr>
                         <td>
                             Trainer Ids :
                         </td>
                         <td>
                             <form:select path="trainerIds">
                                        <c:forEach var="trainer" items="${trainers}" >
                                              <form:option value="${trainer.employee.id}" label="${trainer}"/>
                                        </c:forEach>
                             </form:select>
                         </td>
                     </tr>
                     <tr>
                         <td>
                             <input class ="btn" type="submit" value="<%= heading %>"/>
                         </td>
                         <td>
                             <a href="/"> <input class ="btn" type="button" value="Back"/></a>
                         </td>
                     </tr>
                 </table>
  </form:form>
  </div>
 </body>
</html>