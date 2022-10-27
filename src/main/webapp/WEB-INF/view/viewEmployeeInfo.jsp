<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Trainer</title>
  <link rel="stylesheet" href="/css/viewStyle.css" >
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">
  <div class="left">
    <div class="profile">
      <img class="photo" src ="images/no-photo.jpg" alt="Profile Photo"/>
      <p class ="fname"> ${employee.name}</p>
      <p class ="department"> ${employee.roleDto.description} </p>
    </div>
    <div class="details">
      <ul>
        <li><i class="fa fa-phone"></i>${employee.mobileNumber}</li>
        <li><i class="fa fa-calendar" ></i></i>${employee.dateOfBirth}</li>
        <li><i class="fa fa-graduation-cap" ></i>${employee.qualificationDto.description}</li>
        <li><i class="fa fa-envelope"></i>${employee.email}</li>
        <li><i class="fa fa-map-marker" ></i>${employee.address}</li>
        <c:if test = "${authority == 'ROLE_ADMIN'}">
             <li class="update"><a class="btn" href = "update${employee.roleDto.description}?id=${employee.id}">Update</a></li>
             <li class="delete"><a class="btn" href = "delete${employee.roleDto.description}?id=${employee.id}">Delete</a></li>
        </c:if>
         <c:if test = "${employee.roleDto.description == 'Trainer'}">
              <li class="viewTrainee">
                <a class="btn" href="viewTraineesOfTrainer?id=${employee.id}">Trainees</a>
              </li>
        </c:if>
        <li class="back"><a class="btn" href = "view${employee.roleDto.description}">Back</a></li>
      </ul>
    </div>
  </div>
  <div class="right">
    <div class="desc summary">
      <p class="topics">Professional Summary</p>
      <p class="text">Lorem ipsum dolor sit amet. Aut obcaecati numquam sed totam deserunt qui voluptatem nemo sed quas sapiente. Nam adipisci ipsa id rerum repellat aut voluptas voluptas qui perferendis galisum ex voluptatem voluptas. Est facere consequatur rem earum molestiae At natus ipsam et repudiandae consequatur.
        Non numquam nihil et neque vitae a dolores aliquid ex repellendus sapiente aut sint aliquam id accusamus quia. Ut consequuntur velit eos perferendis laborum eos voluptatem repellendus ab sunt voluptates.</p>
    </div>
    <div class="desc experience">
      <p class="topics">Work Experience</p></br>
      <table>
        <tr>
          <td class="row1 roleHead" colspan="2">Senior Front End Developer</td>
          <td class="row1 float"><p class="mode">Full Time</p></td>
        </tr>
        <tr>
          <td><i class="fa fa-building"></i>Apple Inc</td>
          <td><i class="fa fa-map-marker" >Los Angeles</td>
          <td class="float"><i class="fa fa-calendar" ></i>12 April 2022</td>
        </tr>
        <tr>
          <td class="row1 roleHead" colspan="2">Junior Front End Developer</td>
          <td class="row1 float"><p class="mode">Full Time</p></td>
        </tr>
        <tr>
          <td><i class="fa fa-building"></i>Figma</td>
          <td><i class="fa fa-map-marker" >San Francisco</td>
          <td class="float"><i class="fa fa-calendar" ></i>12 April 2020 - 10 May 2021</td>
        </tr>
        <tr>
          <td class="row1 roleHead" colspan="2">Intern Developer</td>
          <td class="row1 float"><p class="mode">Full Time</p></td>
        </tr>
        <tr>
          <td><i class="fa fa-building"></i>Microsoft</td>
          <td><i class="fa fa-map-marker" >   New York City</td>
          <td class="float"><i class="fa fa-calendar" ></i>17 April 2017 - 19 July 2018</td>
        </tr>
      </table></br>
    </div>
    <div class="desc education">
      <p class="topics">Education</p></br>
      <table>
        <tr>
          <td class="row1 roleHead" colspan="2">Masters in Software Engineering</td>
        </tr>
        <tr>
          <td><i class="fa fa-graduation-cap"></i>Manchester Institute of Technology</td>
          <td class="float"><i class="fa fa-calendar" ></i>12 April 2020 - 10 May 2021</td>
        </tr>
        <tr>
          <td class="row1 roleHead" colspan="2">Bachelor in Software Engineering</td>
        </tr>
        <tr>
          <td><i class="fa fa-graduation-cap"></i>Manchester Institute of Technology</td>
          <td class="float"><i class="fa fa-calendar" ></i></i>12 April 2020 - 10 May 2021</td>
        </tr>
      </table></br>
    </div>

  </div>
</div>
</body>
</html>