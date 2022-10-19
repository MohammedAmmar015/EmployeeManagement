<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Trainer</title>
  <link rel="stylesheet" href="/css/viewStyle.css" >
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
        <li><img class="icon" src="images\phone-solid.svg"/>${employee.mobileNumber}</li>
        <li><img class="icon" src="images\calendar-days-solid.svg"/>${employee.dateOfBirth}</li>
        <li><img class="icon" src="images\user-graduate-solid.svg"/>${employee.qualificationDto.description}</li>
        <li><img class="icon" src="images\envelope-solid.svg"/>${employee.email}</li>
        <li><img class="icon" src="images\location-dot-solid.svg"/>${employee.address}</li>
        <li class="update"><a class="btn" href = "update${employee.roleDto.description}?id=${employee.id}">Update</a></li>
        <li class="delete"><a class="btn" href = "delete${employee.roleDto.description}?id=${employee.id}">Delete</a></li>
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
          <td><img class="icon location" src="images\building-solid.svg"/>Apple Inc</td>
          <td><img class="icon location" src="images\location-dot-solid.svg"/>Los Angeles</td>
          <td class="float"><img class="icon location" src="images\calendar-days-solid.svg"/>12 April 2022</td>
        </tr>
        <tr>
          <td class="row1 roleHead" colspan="2">Junior Front End Developer</td>
          <td class="row1 float"><p class="mode">Full Time</p></td>
        </tr>
        <tr>
          <td><img class="icon location" src="images\building-solid.svg"/>Figma</td>
          <td><img class="icon location" src="images\location-dot-solid.svg"/>San Francisco</td>
          <td class="float"><img class="icon location" src="images\calendar-days-solid.svg"/>12 April 2020 - 10 May 2021</td>
        </tr>
        <tr>
          <td class="row1 roleHead" colspan="2">Intern Developer</td>
          <td class="row1 float"><p class="mode">Full Time</p></td>
        </tr>
        <tr>
          <td><img class="icon location" src="images\building-solid.svg"/>Microsoft</td>
          <td><img class="icon location" src="images\location-dot-solid.svg"/>New York City</td>
          <td class="float"><img class="icon location" src="images\calendar-days-solid.svg"/>17 April 2017 - 19 July 2018</td>
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
          <td><img class="icon location" src="images\graduation-cap-solid.svg"/>Manchester Institute of Technology</td>
          <td class="float"><img class="icon location" src="images\calendar-days-solid.svg"/>12 April 2020 - 10 May 2021</td>
        </tr>
        <tr>
          <td class="row1 roleHead" colspan="2">Bachelor in Software Engineering</td>
        </tr>
        <tr>
          <td><img class="icon location" src="images\graduation-cap-solid.svg"/>Manchester Institute of Technology</td>
          <td class="float"><img class="icon location" src="images\calendar-days-solid.svg"/>12 April 2020 - 10 May 2021</td>
        </tr>
      </table></br>
    </div>

  </div>
</div>
</body>
</html>