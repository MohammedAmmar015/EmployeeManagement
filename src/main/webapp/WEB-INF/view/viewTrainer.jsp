<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
                    <th colspan="2">Action</th>
                </tr>
                <%
                    for (TrainerDto trainer : trainers) {
                %>
                <tr>
                    <td> <%= trainer.getId()%></td>
                    <td> <%= trainer.getName()%></td>
                    <td><a href="viewTrainerById?id=<%= trainer.getId()%>"> <input class="update btn" type="button"
                                                                                 value="view"></a></td>
                </tr>
                <%
                }
                }
                %>
            </table>
        </div>
        </br></br>
        <c:if test = "${authority == 'ROLE_ADMIN'}">
              <a href="trainerForm"> <input class="add btn" type="button" value="Add Trainer"></a>
        </c:if>
        <a href="/"> <input class="back btn" type="button" value="Back"></a>
    </div>
</body>
</html>