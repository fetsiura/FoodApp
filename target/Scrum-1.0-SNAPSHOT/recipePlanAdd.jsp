<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="static/headerLogin.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty error}">
    <script>
        window.addEventListener("load",function(){
            alert("${error}");
        })

    </script>
</c:if>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@include file="static/leftMenu.jsp"%>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <form action="/app/recipe/plan/add" method="post">

                 <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <button type="submit"  class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                    </div>
                </div>

                 <div class="schedules-content">
                        <div class="form-group row">
                            <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                Wybierz plan
                            </label>
                            <div class="col-sm-3">
                                <select class="form-control" name="planId" id="choosePlan">
                                    <c:forEach items="${plans}" var="plan">
                                        <option value="${plan.id}">${plan.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="name" class="col-sm-2 label-size col-form-label">
                                Nazwa posiłku
                            </label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="mealName" placeholder="Nazwa posiłku">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="number" class="col-sm-2 label-size col-form-label">
                                Numer posiłku
                            </label>
                            <div class="col-sm-2">
                                <input type="number" class="form-control" name="number" id="number" placeholder="Numer posiłki">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="recipe" class="col-sm-2 label-size col-form-label">
                                Przepis
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="recipe" name="recipeId">

                                    <c:forEach var="recipe" items="${recipes}">
                                        <option value="${recipe.id}">${recipe.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="day" class="col-sm-2 label-size col-form-label">
                                Dzień
                            </label>
                            <div class="col-sm-2">
                                <select class="form-control" name="dayId" id="day">
                                    <c:forEach var="day" items="${days}">
                                        <option value="${day.id}">${day.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                </div>

                </form>

            </div>
        </div>


    </div>
</section>



<%@include file="static/footer.jsp"%>
