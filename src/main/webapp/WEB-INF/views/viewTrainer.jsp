<%@page isELIgnored="false"%>
<html>
<head>
 <title>View Trainer</title>
 <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
 <%@ page import = "java.util.List" 
	  import = "com.ideas2it.employee.models.Trainer"
 %>
 <div class = "view">
 <h1> Trainer Portal </h1>
    <%
        List<Trainer> trainers = (List) request.getAttribute("trainers");
        if (trainers.size() <= 0) {
            out.println("No Data Found to Display");
	} else {
    %>
    <p > ${msg} </p>
</br><table border = 1 cellpadding = 5 cellspacing = 0>
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
	<th colspan = "2">Actions</th>
    </tr>
 <%
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
	<td> <a href="deleteTrainer?id=<%= trainer.getEmployee().getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
	<td> <a href="employeeServlet?action=updateTrainer&id=<%= trainer.getEmployee().getId()%>"> <input class ="update btn" type="button" value="Update"></a> </td>
    </tr>
    <%
        }
	}
    %>
  </table>
  </br>
  <a href="trainerForm"> <input class ="add btn" type="button" value="Add Trainer"></a>
  <a href="/"> <input class ="back btn" type="button" value="Back"></a>
  </div>
</body>
</html>