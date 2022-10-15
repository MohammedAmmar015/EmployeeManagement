<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.ideas2it.employee.models.Trainer" %>
<%@page isELIgnored="false"%>
<%
    String action = (String) request.getAttribute("action");
    Trainer trainer = trainer = (Trainer) request.getAttribute("trainer");
    String heading = "Add Trainer";
    if (action.equals("updateTrainer")) {
        heading = "Update Trainer";
    }
%>
<html>
<head>
    <title><%=heading%></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="addContainer">

    <h2> <%= heading %> </h2>
    <form:form modelAttribute="trainerDto" action="addOrUpdateTrainer?action=${action}" method="post">
        <form:hidden path="id"/>
        <form:select hidden="hidden" path="roleDto.description">
            <form:option selected="selected" value="Trainer">Trainer</form:option>
        </form:select>
        <table class="form">
            <tr>
                <td>
                    Name :
                </td>
                <td>
                    <form:input type="text" path="name" name="name" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    Address :
                </td>
                <td>
                    <form:input type="text" path="address" name="address" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    Mobile Number :
                </td>
                <td>
                    <form:input type="number" path="mobileNumber" name="mobileNumber" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    Email :
                </td>
                <td>
                    <form:input type="email" path="email" name="email" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    Date of Joining :
                </td>
                <td>
                    <form:input type="date" path="dateOfJoining" name="dateOfJoining" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    Date of birth :
                </td>
                <td>
                    <form:input type="date" path="dateOfBirth" name="dateOfBirth" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    Blood Group :
                </td>
                <td>
                    <form:select path="bloodGroup" required="required">
                        <form:option value="" label="none" selected="selected" disabled="disabled"/>
                        <form:option value="A Positive" label="A Positive"/>
                        <form:option value="B Positive" label="B Positive"/>
                        <form:option value="O Positive" label="O Positive"/>
                        <form:option value="AB Positive" label="AB Positive"/>
                        <form:option value="A Negative" label="A Negative"/>
                        <form:option value="B Negative" label="B Negative"/>
                        <form:option value="O Negative" label="O Negative"/>
                        <form:option value="AB Negative" label="AB Negative"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    Qualification :
                </td>
                <td>
                    <form:input type="text" path="qualificationDto.description" name="qualification" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    trainingExperience :
                </td>
                <td>
                    <form:input type="number" path="trainingExperience" name="trainingExperience" required="required"/>
                </td>
            </tr>
            <tr class="formButton">
                <td>
                    <input class="add btn" type="submit" value="<%= heading %>"/>
                </td>
                <td>
                    <a href="/viewTrainer"> <input class="back btn" type="button" value="Back"/></a>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
