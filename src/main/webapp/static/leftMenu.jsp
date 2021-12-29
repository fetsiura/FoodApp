<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="nav flex-column long-bg">
    <li class="nav-item">
        <a class="nav-link" href="/app/dashboard">
            <span>Pulpit</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/app/recipe/list">
            <span>Przepisy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/app/plan/list">
            <span>Plany</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/app/user/edit">
            <span>Edytuj dane</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled" href="/app/user/edit/password">
            <span>Zmień hasło</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <c:choose>
        <c:when test="${admin.superadmin=='1'}">
            <li class="nav-item">
                <a class="nav-link disabled" href="/app/super/user/list">
                    <span>Użytkownicy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

</ul>

