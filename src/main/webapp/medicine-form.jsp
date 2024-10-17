<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Medicine Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand"> Medicine Management Application </a>
        </div>

        <ul class="navbar-nav">
            <li class="nav-item"><a href="<%=request.getContextPath()%>/list" class="nav-link">Medicine List</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-6">
    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${medi ne null}">
                    <form action="update" method="post">
                </c:when>
                <c:otherwise>
                    <form action="insert" method="post">
                </c:otherwise>
            </c:choose>

            <h2>
                <c:choose>
                    <c:when test="${medi ne null}">
                        Edit Medicine
                    </c:when>
                    <c:otherwise>
                        Add New Medicine
                    </c:otherwise>
                </c:choose>
            </h2>

            <c:if test="${medi ne null}">
                <input type="hidden" name="id" value="<c:out value='${medi.id}' />" />
            </c:if>

            <fieldset class="form-group">
                <label>MediName</label>
                <input type="text" value="<c:out value='${medi.MediName}' />" class="form-control" name="MediName" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>MediDetails</label>
                <input type="text" value="<c:out value='${medi.MediDetails}' />" class="form-control" name="MediDetails">
            </fieldset>

            <fieldset class="form-group">
                <label>ForDisease</label>
                <input type="text" value="<c:out value='${medi.ForDisease}' />" class="form-control" name="ForDisease">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
