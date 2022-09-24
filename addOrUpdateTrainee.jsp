<html>
 <head>
  <title>Trainee</title>
 </head>
 <body>
  <%@ page import="com.ideas2it.employee.models.Trainee"
	   import="com.ideas2it.employee.models.Trainer"	
 %>
  <% String operation = request.getParameter("action");
	Trainee trainee = null;
	String name = "";
	String address = "";
	String mobileNumber = "";
	String email = "";
	String dateOfJoining = "";
	String dateOfBirth = "";
	String qualification = "";
	String trainingPeriod = "";
	String course = "";
	String batchNumber = "";
	String trainerIds = "";
	String bloodGroup = "";
	String heading = "Add Trainee";
	if (operation.equals("updateTrainee")) {
	    trainee = (Trainee) request.getAttribute("trainee");
	    name = trainee.getEmployee().getName();
	    address = trainee.getEmployee().getAddress();
	    mobileNumber = String.valueOf(trainee.getEmployee().getMobileNumber());
	    email = trainee.getEmployee().getEmail();
	    dateOfJoining = String.valueOf(trainee.getEmployee().getDateOfJoining());
	    dateOfBirth = String.valueOf(trainee.getEmployee().getDateOfBirth());
	    bloodGroup = trainee.getEmployee().getBloodGroup();
	    qualification = trainee.getEmployee().getQualification().getDescription();
	    trainingPeriod = String.valueOf(trainee.getTrainingPeriod());
	    course = trainee.getCourse();
	    batchNumber = String.valueOf(trainee.getBatchNumber());
	    for (Trainer trainer : trainee.getTrainers()) {
	    	trainerIds = String.join(",", String.valueOf(trainer.getEmployee().getId()), trainerIds);
	    }
	    heading = "Update Trainee";
	}	
	session.setAttribute("trainee", trainee);
  %>
  <h3> <%= heading %> </h3>
  <form action="employeeServlet?action=addOrUpdateTrainee&operation=<%=operation%>" method="post">
   Name : </br><input type="text" name="name" value="<%= name %>" required/></br>
   Address : </br><input type="text" name="address" value="<%= address %>" required/></br>
   Mobile Number : </br><input type="number" name="mobileNumber" value="<%= mobileNumber %>" required/></br>
   Email : </br><input type="email" name="email" value="<%= email %>" required/></br>
   Date of Joining : </br><input type="date" name="dateOfJoining" value="<%= dateOfJoining %>" required/></br>
   Date of birth : </br><input type="date" name="dateOfBirth" value="<%= dateOfBirth %>" required/></br>
   Blood Group : </br><input type="radio" id="a+" name="bloodGroup" value="A Positive" <%=(bloodGroup.equals("A Positive") ? "checked = checked" : "")%>>
		 <label for="a+">A Positive</label><br>
		 <input type="radio" id="b+" name="bloodGroup" value="B Positive" <%=(bloodGroup.equals("B Positive") ? "checked = checked" : "")%>>
		 <label for="b+">B Positive</label><br>
		 <input type="radio" id="o+" name="bloodGroup" value="O Positive" <%=(bloodGroup.equals("O Positive") ? "checked = checked" : "")%>>
		 <label for="o+">O Positive</label><br>
		 <input type="radio" id="ab+" name="bloodGroup" value="AB Positive" <%=(bloodGroup.equals("AB Positive") ? "checked = checked" : "")%>>
		 <label for="ab+">AB Positive</label><br>
		 <input type="radio" id="a-" name="bloodGroup" value="A Negative" <%=(bloodGroup.equals("A Negative") ? "checked = checked" : "")%>>
		 <label for="a-">A Negative</label><br>
		 <input type="radio" id="b-" name="bloodGroup" value="B Negative" <%=(bloodGroup.equals("B Negative") ? "checked = checked" : "")%>>
		 <label for="b-">B Negative</label><br>
		 <input type="radio" id="o-" name="bloodGroup" value="O Negative" <%=(bloodGroup.equals("O Negative") ? "checked = checked" : "")%>>
		 <label for="o-">O Negative</label><br>
		 <input type="radio" id="ab-" name="bloodGroup" value="AB Negative" <%=(bloodGroup.equals("AB Negative") ? "checked = checked" : "")%>>
		 <label for="ab-">AB Negative</label><br>
   Qualification : </br><input type="text" name="qualification" value="<%= qualification %>" required/></br>
   Training Period : </br><input type="number" name="trainingPeriod" value="<%= trainingPeriod %>" required/></br>
   Course : </br><input type="text" name="course" value="<%= course %>" required/></br>
   Batch Number : </br><input type="number" name="batchNumber" value="<%= batchNumber %>" required/></br>
   Trainer Ids : </br><input type="text" name="trainerIds" value="<%= trainerIds %>" required/></br>
   </br><input type="submit" value="<%= heading %>"/>
   <a href="employeeServlet?action=viewTrainer"> <input type="button" value="Back"></a>
  </form>
 </body>
</html>