<html>
<head>
 <title>View Trainee</title>
 <link rel="stylesheet" href="/css/style.css">
</head>
<body>
 <%@ page import = "java.util.List" 
	  import = "java.util.ArrayList" 
	  import = "com.ideas2it.employee.models.Trainee"
	  import = "com.ideas2it.employee.models.Trainer"
 %>
 <div class ="view">
 <h2> Trainee Portal </h2>
 <p > ${msg} </p>
 <div class = "scrollable">
 <%
     List<Trainee> trainees = (List) request.getAttribute("trainees");
     if (trainees.size() <= 0) {
         out.println("No Data Found to Display");
     } else {
 %>
 <table>
    <tr class = "tableHeading">
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
        for (Trainee trainee : trainees) {
    %>
    <tr>
	<td> <%= trainee.getId()%> </td>
	<td> <%= trainee.getName()%> </td>
	<td> <%= trainee.getAddress()%> </td>
	<td> <%= trainee.getDateOfBirth()%> </td>
	<td> <%= trainee.getDateOfJoining()%> </td>
	<td> <%= trainee.getEmail()%> </td>
	<td> <%= trainee.getMobileNumber()%> </td>
	<td> <%= trainee.getQualification().getDescription()%> </td>
	<td> <%= trainee.getBloodGroup()%> </td>
	<td> <%= trainee.getTrainingPeriod()%> </td>
	<td> <%= trainee.getCourse()%> </td>
	<td> <%= trainee.getBatchNumber()%> </td>
	<%
	    String trainerIds = "";
	    for (Trainer trainer : trainee.getTrainers()) {
		    trainerIds = String.join(",", String.valueOf(trainer.getId()),trainerIds);
	    }
	%>
	<td> <%= trainerIds %> </td>
	<td> <a href="deleteTrainee?id=<%= trainee.getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
	<td> <a href="updateTrainee?id=<%= trainee.getId()%>"> <input class ="update btn" type="button" value="Update"></a> </td>
    </tr>
    <%
       }
       }
    %>
  </table>
  </div>
  </br></br>
  <a href="\traineeForm"> <input class ="add btn" type="button" value="Add Trainee"></a>
  <a href="/"> <input class ="back btn" type="button" value="Back"></a>
  </div>
</body>
</html>