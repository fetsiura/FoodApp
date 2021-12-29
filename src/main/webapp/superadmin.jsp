<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="static/headerLogin.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <c:if test="${not empty error}">
            <script>
                window.addEventListener("load",function(){
                    alert("${error}");
                })

            </script>
        </c:if>
        <%@include file="static/leftMenu.jsp"%>
        <div class="m-4 p-3 width-medium">
            <div class="m-4 p-3 border-dashed view-height">

                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/dashboard" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-3">IMIĘ</th>
                            <th class="col-6">NAZWISKO</th>
                            <th class="col-2 center">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach items="${users}" varStatus="count" var="user">

                        <tr class="d-flex">
                            <td class="col-1">${count.count}</td>
                            <td class="col-3">${user.firstName}</td>
                            <td class="col-6">${user.lastName}</td>
                            <td class="col-2 center">
                                <c:choose>
                                    <c:when test="${user.enable=='1'}">
                                        <a href="/app/super/user/lock?id=${user.id}&enable=2" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/app/super/user/lock?id=${user.id}&enable=1" class="btn btn-success rounded-0 text-light m1">Odblokuj</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                        </c:forEach>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>


    </div>
</section>



<%@include file="static/footer.jsp"%>
