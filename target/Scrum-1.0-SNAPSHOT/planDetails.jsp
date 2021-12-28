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
        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${planDetails}" var="detail">

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${detail.dayName}</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">

                        <tr class="d-flex">
                            <td class="col-2">${detail.mealName}</td>
                            <td class="col-7">${detail.recipeName}</td>
                            <td class="col-1 center">
                                <button id="buttonToDelete" name="${detail.id}" class="btn btn-danger rounded-0 text-light m-1">Usuń</button>

                            </td>
                            <td class="col-2 center">
                                <a href="/app/recipe/details?id=${detail.recipeId}" class="btn btn-info rounded-0 text-light m-1">Szczegóły przepisu</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </c:forEach>
                </div>
            </div>
        </div>



    </div>
</section>



<%@include file="static/footer.jsp"%>
