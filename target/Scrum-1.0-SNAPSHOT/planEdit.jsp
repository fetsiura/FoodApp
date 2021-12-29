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
                    <form action="/app/plan/edit" method="post">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Edycja planu</h3></div>
                            <div class="col d-flex justify-content-end mb-2"><button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button></div>
                        </div>

                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Nazwa planu</th>
                                <td class="col-7">
                                    <input name="id" type="hidden" value="${plan.id}">
                                    <input name="adminId" type="hidden" value="${plan.adminId}">
                                    <input name="created" type="hidden" value="${plan.created}">
                                    <input class="w-100 p-1" name="name" value="${plan.name}">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Opis planu</th>
                                <td class="col-7"> <textarea class="w-100 p-1" rows="5" name="description" >${plan.description}</textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>


    </div>
</section>



<%@include file="static/footer.jsp"%>
