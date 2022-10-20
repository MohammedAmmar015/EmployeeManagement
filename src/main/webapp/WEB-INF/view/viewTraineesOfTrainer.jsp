<%@page isELIgnored="false"%>
<%@ page import = "java.util.Set"
         import = "com.ideas2it.employee.dto.TraineeDto"
         import = "com.ideas2it.employee.dto.TrainerDto"
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Trainer List</title>
    <link rel="stylesheet" href="/css/viewTraineeStyle.css">
</head>
<body>
<div class="container">
    <table class="traineeTable">
        <tr>
            <td class="tableHead" colspan="6">
                <ul>
                    <li>
                        <img class="trainerImage" src="\images\no-photo.jpg">
                    </li>
                    <li class="trainerDetails">
                        <p class="trainerName">${trainerDto.name}</p>
                        <p class="trainerEmail">${trainerDto.email}</p>
                    </li>
                    <li class="floatRight">
                        <p>Number of Trainee</p>
                        <p class="numberOfTrainee">${trainerDto.numberOfTrainees}</p>
                    </li>
                </ul>
            </td>
        </tr>
        <tr class="tableHeading">
            <th></th>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Batch Number</th>
        </tr>
        <c:forEach var = "trainee" items="${traineesDto}">
        <tr>
            <td><img class="traineeImage" src="\images\no-photo.jpg"></td>
            <td>${trainee.id}</td>
            <td class"traineeName">${trainee.name}</td>
            <td>${trainee.email}</td>
            <td>${trainee.course}</td>
            <td>${trainee.batchNumber}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>