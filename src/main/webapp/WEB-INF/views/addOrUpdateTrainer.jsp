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
	String name = "";
	String address = "";
	String mobileNumber = "";
	String email = "";
	String dateOfJoining = "";
	String dateOfBirth = "";
	String qualification = "";
	String trainingExperience = "";
	String bloodGroup = "";
	String heading = "Add Trainer";
	if (operation.equals("updateTrainer")) {
	    trainer = (Trainer) request.getAttribute("trainer");
	    name = trainer.getEmployee().getName();
	    address = trainer.getEmployee().getAddress();
	    mobileNumber = String.valueOf(trainer.getEmployee().getMobileNumber());
	    email = trainer.getEmployee().getEmail();
	    dateOfJoining = String.valueOf(trainer.getEmployee().getDateOfJoining());
	    dateOfBirth = String.valueOf(trainer.getEmployee().getDateOfBirth());
	    bloodGroup = trainer.getEmployee().getBloodGroup();
	    qualification = trainer.getEmployee().getQualification().getDescription();
	    trainingExperience = String.valueOf(trainer.getTrainingExperience());
	    heading = "Update Trainer";
	}
	session.setAttribute("trainer", trainer);
  %>
  <h3> <%= heading %> </h3>
  <form:form modelAttribute="trainer" action="addOrUpdateTrainer" method="post">
   Name : <form:input type="text" path="employee.name" name="name" value="<%= name %>" required="required"/>
   Address : <form:input type="text" path="employee.address" name="address" value="<%= address %>" required="required"/></br></br>
   Mobile Number : <form:input type="number" path="employee.mobileNumber" name="mobileNumber" value="<%= mobileNumber %>" required="required"/></br></br>
   Email : <form:input type="email" path="employee.email" name="email" value="<%= email %>" required="required" /></br></br>
   Date of Joining : <form:input type="date" path="employee.dateOfJoining" name="dateOfJoining" value="<%= dateOfJoining %>" required="required"/></br></br>
   Date of birth : <form:input type="date" path="employee.dateOfBirth" name="dateOfBirth" value="<%= dateOfBirth %>" required="required"/></br></br>
   Blood Group : <form:radiobutton id="a+" path="employee.bloodGroup" name="bloodGroup" value="A Positive" />
		 <label for="a+">A Positive</label><br>
		 <form:radiobutton id="b+" path="employee.bloodGroup" name="bloodGroup" value="B Positive" />
		 <label for="b+">B Positive</label><br>
		 <form:radiobutton id="o+" path="employee.bloodGroup" name="bloodGroup" value="O Positive" />
		 <label for="o+">O Positive</label><br>
		 <form:radiobutton id="ab+" path="employee.bloodGroup" name="bloodGroup" value="AB Positive" />
		 <label for="ab+">AB Positive</label><br>
		 <form:radiobutton id="a-" path="employee.bloodGroup" name="bloodGroup" value="A Negative"/>
		 <label for="a-">A Negative</label><br>
		 <form:radiobutton id="b-" path="employee.bloodGroup" name="bloodGroup" value="B Negative" />
		 <label for="b-">B Negative</label><br>
		 <form:radiobutton id="o-" path="employee.bloodGroup" name="bloodGroup" value="O Negative" />
		 <label for="o-">O Negative</label><br>
		 <form:radiobutton id="ab-" path="employee.bloodGroup" name="bloodGroup" value="AB Negative" />
		 <label for="ab-">AB Negative</label><br></br>
   Qualification : <form:input type="text" path="employee.qualification.description" name="qualification" value="<%= qualification %>" required="required"/></br></br>
   trainingExperience : <form:input type="number" path="trainingExperience" name="trainingExperience" value="<%= trainingExperience %>" required="required"/></br>
   </br><input class ="btn" type="submit" value="<%= heading %>"/>
   <a href="/"> <input class ="btn" type="button" value="Back"/></a>
  </form:form>
  </div>
 </body>
</html>
