<html>
<head>
 <title>View Trainer</title>
</head>
<body>
 <%@ page import = "java.util.List" 
	  import = "com.ideas2it.employee.models.Trainer"
 %>
 <table border = 1 cellpadding = 5 cellspacing = 0>
    <tr>
	<th>Id</th>
	<th>Name</th>
	<th>Address</th>
	<th>Date of Birth</th>
	<th>Date of Joining</th>
	<th>Email</th>
	<th>Mobile Number</th>
	<th>Qualification</th>
	<th>Blood Group</th>
	<th>Training Experience</th>
	<th>Number of Trainees</th>
    </tr>
    <%
       List<Trainer> trainers = (List) request.getAttribute("trainers");
       for (Trainer trainer : trainers) {
    %>
    <tr>
	<td> <%= trainer.getEmployee().getId()%> </td>
	<td> <%= trainer.getEmployee().getName()%> </td>
	<td> <%= trainer.getEmployee().getAddress()%> </td>
	<td> <%= trainer.getEmployee().getDateOfBirth()%> </td>
	<td> <%= trainer.getEmployee().getDateOfJoining()%> </td>
	<td> <%= trainer.getEmployee().getEmail()%> </td>
	<td> <%= trainer.getEmployee().getMobileNumber()%> </td>
	<td> <%= trainer.getEmployee().getQualification().getDescription()%> </td>
	<td> <%= trainer.getEmployee().getBloodGroup()%> </td>
	<td> <%= trainer.getTrainingExperience()%> </td>
	<td> <%= trainer.getTrainees().size()%> </td>
    </tr>
    <%
       }
    %>
  </table>
</body>
</html>