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
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="mt-4 ml-4 mr-4">
                    <!-- fix action, method -->
                    <!-- add name attribute for all inputs -->
                    <form action="/app/recipe/edit" method="post">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Edycja przepisu</h3></div>
                            <div class="col d-flex justify-content-end mb-2"><button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button></div>
                        </div>

                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Nazwa Przepisu</th>
                                <td class="col-7">
                                    <input name="id" type="hidden" value="${recipe.id}">
                                    <input name="created" type="hidden" value="${recipe.created}">
                                    <input name="adminId" type="hidden" value="${recipe.adminId}">
                                    <input class="w-100 p-1" name="name" value="${recipe.name}">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Opis przepisu</th>
                                <td class="col-7"> <textarea class="w-100 p-1" rows="5" name="description" >${recipe.description}</textarea></td>

                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                <td class="col-3">
                                    <input class="p-1" type="number" name="preparationTime" value="${recipe.preparationTime}">

                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="row d-flex">
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                            <div class="col-2"></div>
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                        </div>
                        <div class="row d-flex">
                            <div class="col-5 p-4">
                                <textarea class="w-100 p-1" name="preparation" rows="10">${recipe.preparation}</textarea>

                            </div>
                            <div class="col-2"></div>

                            <div class="col-5 p-4">
                                <textarea class="w-100 p-1" name="ingredients" rows="10"> ${recipe.ingredients}</textarea>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>

    </div>
</section>



<%@include file="static/footer.jsp"%>
