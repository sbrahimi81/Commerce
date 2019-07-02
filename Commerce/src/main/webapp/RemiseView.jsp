<%-- 
    Document   : RemiseView
    Created on : 14 juin 2019, 11:51:18
    Author     : edeux
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionnaire des remises</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/ressources/css/main.css" />">
    </head>
    <body>
        <div>

            <h1>Edition des taux de remise</h1>

            <c:if test="${not empty error}">
                <div>
                    <p>${error}</p>
                </div>
            </c:if >

            <c:if test="${not empty message}">
                <div>
                    <p>${message}</p>
                </div>
            </c:if >

            <form method="POST">
                <input type="hidden" name="action" value="ADD">

                <input type="text" name="add-code" placeholder="Code">
                <input type="text" name="add-taux" placeholder="Taux">
                <div>
                    <button type="submit">Ajouter</button>
                </div>
            </form>

            <table>

                <thead>
                    <tr>
                        <th>Code</th>
                        <th>Taux</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="row" items="${rows}">
                        <tr>
                            <td>${row.code}</td>
                            <td>${row.taux}</td>
                            <td>

                                <form method="post" style="margin-bottom: 0;">
                                    <input type="hidden" name="action" value="DELETE">
                                    <input type="hidden" name="code" value="${row.code}">

                                    <button>Modifier</button>
                                    <button type="submit">Supprimer</button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </div>

        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="<c:url value="/ressources/js/main.js" />"></script>
    </body>
</html>
