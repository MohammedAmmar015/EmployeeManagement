<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <p class="trainerName">${trainer.name}</p>
                        <p class="trainerEmail">${trainer.email}</p>
                    </li>
                    <li class="floatRight">
                        <p>Number of Trainee</p>
                        <p class="numberOfTrainee">${trainer.numberOfTrainees}</p>
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
        <c:forEach var = "trainee" items="${employee}">
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