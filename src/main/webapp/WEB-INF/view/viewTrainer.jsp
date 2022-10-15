<%@page isELIgnored="false"%>
<%@ page import = "java.util.List"
         import = "com.ideas2it.employee.dto.TrainerDto"
%>
<html>
<head>
    <title>View Trainer</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="view">
    <%
        List<TrainerDto> trainers = (List) request.getAttribute("trainersDto");
        if (trainers.size() <= 0) {
            out.println("No Data Found to Display");
        } else {
    %>
    <h2> Trainer Portal </h2>
        <p> ${msg} </p>
        <div class="scrollable">
            <table>
                <tr class="tableHeading">
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
                    <th colspan="2">Actions</th>
                </tr>
                <%
                    for (TrainerDto trainer : trainers) {
                %>
                <tr>
                    <td> <%= trainer.getId()%></td>
                    <td> <%= trainer.getName()%></td>
                    <td> <%= trainer.getAddress()%></td>
                    <td> <%= trainer.getDateOfBirth()%></td>
                    <td> <%= trainer.getDateOfJoining()%></td>
                    <td> <%= trainer.getEmail()%></td>
                    <td> <%= trainer.getMobileNumber()%></td>
                    <td> <%= trainer.getQualificationDto().getDescription()%></td>
                    <td> <%= trainer.getBloodGroup()%></td>
                    <td> <%= trainer.getTrainingExperience()%></td>
                    <td> <%= trainer.getNumberOfTrainees()%></td>
                    <td><a href="deleteTrainer?id=<%= trainer.getId()%>"> <input class="delete btn" type="button"
                                                                                 value="Delete"></a></td>
                    <td><a href="updateTrainer?id=<%= trainer.getId()%>"> <input class="update btn" type="button"
                                                                                 value="Update"></a></td>
                </tr>
                <%
                }
                }
                %>
            </table>
        </div>
        </br></br>
        <a href="trainerForm"> <input class="add btn" type="button" value="Add Trainer"></a>
        <a href="/"> <input class="back btn" type="button" value="Back"></a>
    </div>
</body>
</html>