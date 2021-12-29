<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="static/headerLogin.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="RecipeDelete.js"></script>



<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@include file="static/leftMenu.jsp"%>
        <c:if test="${not empty error}">
            <script>
                window.addEventListener("load",function(){
                    alert("${error}");
                })

            </script>
        </c:if>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding"><h3 class="color-header text-uppercase">Lista Przepisów</h3></div>
                    <div class="col noPadding d-flex justify-content-end mb-2"><a href="/app/recipe/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj przepis</a></div>
                </div>
                <table class="table border-bottom schedules-content">
                    <thead>
                    <tr class="d-flex text-color-darker">
                        <th scope="col" class="col-1">ID</th>
                        <th scope="col" class="col-2">NAZWA</th>
                        <th scope="col" class="col-7">OPIS</th>
                        <th scope="col" class="col-2 center">AKCJE</th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${recipes}" var="recipe" varStatus="count">

                    <tr class="d-flex">
                        <th scope="row" class="col-1">${count.count}</th>
                        <td class="col-2">
                            ${recipe.name}
                        </td>
                        <td class="col-7">${recipe.description}</td>
                        <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                            <button id="buttonToDelete" name="${recipe.id}" class="btn btn-danger rounded-0 text-light m-1">Usuń</button>
                            <a href="/app/recipe/details?id=${recipe.id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            <a href="/app/recipe/edit?id=${recipe.id}" class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                        </td>
                    </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>



<%@include file="static/footer.jsp"%>
