<html>
<head>
 <title>View Trainee</title>
 <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
 <%@ page import = "java.util.List" 
	  import = "java.util.ArrayList" 
	  import = "com.ideas2it.employee.models.Trainee"
	  import = "com.ideas2it.employee.models.Trainer"
 %>
 <div class ="view">
 <h1> Trainee Portal </h1>
 <p > ${msg} </p>
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
	<th>Training Period</th>
	<th>Course</th>
	<th>Batch Number</th>
	<th>Trainers Id</th>
	<th colspan = "2">Actions</th>
    </tr>
    <%
       List<Trainee> trainees = (List) request.getAttribute("trainees");
       for (Trainee trainee : trainees) {
    %>
    <tr>
	<td> <%= trainee.getEmployee().getId()%> </td>
	<td> <%= trainee.getEmployee().getName()%> </td>
	<td> <%= trainee.getEmployee().getAddress()%> </td>
	<td> <%= trainee.getEmployee().getDateOfBirth()%> </td>
	<td> <%= trainee.getEmployee().getDateOfJoining()%> </td>
	<td> <%= trainee.getEmployee().getEmail()%> </td>
	<td> <%= trainee.getEmployee().getMobileNumber()%> </td>
	<td> <%= trainee.getEmployee().getQualification().getDescription()%> </td>
	<td> <%= trainee.getEmployee().getBloodGroup()%> </td>
	<td> <%= trainee.getTrainingPeriod()%> </td>
	<td> <%= trainee.getCourse()%> </td>
	<td> <%= trainee.getBatchNumber()%> </td>
	<%
	    List<Integer> trainerIds = new ArrayList<>();
	    for (Trainer trainer : trainee.getTrainers()) {
		trainerIds.add(trainer.getEmployee().getId());
	    }
	%>
	<td> <%= trainerIds %> </td>
	<td> <a href="deleteTrainee?id=<%= trainee.getEmployee().getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
	<td> <a href="employeeServlet?action=updateTrainee&id=<%= trainee.getEmployee().getId()%>"> <input class ="delete btn" type="button" value="Update"></a> </td>
    </tr>
    <%
       }
    %>
  </table></br>
  <a href="addOrUpdateTrainee.jsp?action=addTrainee"> <input class ="add btn" type="button" value="Add Trainee"></a>
  <a href="/"> <input class ="back btn" type="button" value="Back"></a>
  </div>
</body>
</html>