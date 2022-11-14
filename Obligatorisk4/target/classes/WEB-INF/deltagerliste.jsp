<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./simple.css">
<title>Deltagerliste</title>
</head>
<body>


    <c:set var="innlogget" value='<%=session.getAttribute("mobil")%>' />
    <p>
        Innlogget som:
        <c:out value="${innlogget}">
        </c:out>
    </p>
    <h2>Deltagerliste</h2>
    <table>
        <tbody>
            <tr>
                <th>Kjonn</th>
                <th align="left">Fornavn</th>
                <th align="left">Etternavn</th>
                <th align="left">Mobil</th>
            </tr>

            <c:forEach var="deltakere" items="${deltakere}">
                <c:choose>
                    <c:when test="${innlogget == deltakere.mobil}">
                        <tr
                            style="background-color: #aaffaa; color: black;">
                            <td>${deltakere.kjonn}</td>
                            <td>${deltakere.fornavn}</td>
                            <td>${deltakere.etternavn}</td>
                            <td>${deltakere.mobil}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>${deltakere.kjonn}</td>
                            <td>${deltakere.fornavn}</td>
                            <td>${deltakere.etternavn}</td>
                            <td>${deltakere.mobil}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

        </tbody>
    </table>
    <br>
    <form action="logout" method="post">
        <button type="submit">Logg ut</button>
    </form>

</body>
</html>