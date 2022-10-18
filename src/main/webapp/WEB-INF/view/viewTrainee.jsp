<%@ page import = "java.util.List"
         import = "java.util.ArrayList"
         import = "com.ideas2it.employee.dto.TraineeDto"
         import = "com.ideas2it.employee.dto.TrainerDto"
%>
<html>
<head>
    <title>View Trainee</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="view">
    <%
        List<TraineeDto> trainees = (List) request.getAttribute("traineesDto");
        if (trainees.size() <= 0) {
            out.println("No Data Found to Display");
        } else {
     %>
    <h2> Trainee Portal </h2>
    <p> ${msg} </p>
    <div class="scrollable">
            <table>
                <tr class="tableHeading">
                    <th>Id</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
                <%
                for (TraineeDto trainee : trainees) {
                %>
                <tr>
                    <td> <%= trainee.getId()%></td>
                    <td> <%= trainee.getName()%></td>
                    <td><a href="getTraineeById?id=<%= trainee.getId()%>"> <input class="update btn" type="button"
                                                                                 value="view"></a></td>
                </tr>
                <%
                }
                }
                %>
            </table>
    </div>
    </br></br>
    <a href="\traineeForm"> <input class="add btn" type="button" value="Add Trainee"></a>
    <a href="/"> <input class="back btn" type="button" value="Back"></a>
</div>
</body>
</html>