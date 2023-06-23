<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="loginHeader.jsp" %>
<section class="login-page">
<h2>Zaloguj się</h2>
    <c:if test="${not empty param.error}">
        <h3 style="color:red">
            Nie udało się zalogować, Spróbuj ponownie.<br /> Powód: <c:out
                value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
        </h3>
    </c:if>
<form method="post" action="/login">
    <div class="form-group">
        <input type="email" name="username" placeholder="Email" />
    </div>
    <div class="form-group">
        <input type="password" name="password" placeholder="Hasło" />
        <a href="#" class="btn btn--small btn--without-border reset-password">Resetuj hasło</a> <%-- TODO href --%>
    </div>

    <div class="form-group form-group--checkbox">
        <label>
            <input type="checkbox" name="remember-me"/>
            <span class="checkbox"></span>
            <span class="description">Zapamiętaj mnie</span>
        </label>
    </div>

    <div class="form-group form-group--buttons">
        <a href="/register" class="btn btn--without-border">Załóż konto</a>
        <button class="btn" type="submit">Zaloguj się</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
</form>
</section>
<%@include file="footer.jsp" %>

<script src="/resources/js/app.js"></script>
</body>
</html>