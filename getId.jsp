<html>
 <head>
  <title>Get Id</title>
 </head>
 <body>
  <%
    String operation = request.getParameter("action");
    String heading = null;
    String button = null;
    switch (operation) {
	case "removeTrainer":
	    heading = "Enter Id to Remove Trainer";
	    button = "Remove Trainer";
	    break;

	case "updateTrainer":
	    heading = "Enter Id to Update Trainer";
	    button = "Update Trainer";
	    break;

	case "removeTrainee":
	    heading = "Enter Id to Remove Trainee";
	    button = "Remove Trainee";
	    break;

	case "updateTrainee":
	    heading = "Enter Id to Update Trainee";
	    button = "Update Trainee";
	    break;
    }
  %>
  <form action="employeeServlet?action=<%=operation%>" method ="post">
   <h1> <%= heading %> </h1>
   Employee Id:<input type="number" name="id"/>
   <input type="submit" value="<%= button %>"/>
  </form>
 </body>
</html>