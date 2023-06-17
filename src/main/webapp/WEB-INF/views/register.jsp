<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@include file="loginHeader.jsp" %>
<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <input type="text" name="firstName" placeholder="Imię" />
            <input:errors path="firstName"/>
        </div>

        <div class="form-group">
            <input type="text" name="lastName" placeholder="Nazwisko" />
            <input:errors path="lastName"/>
        </div>

        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
            <input:errors path="email"/>
        </div>

        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" />
            <input:errors path="password"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@include file="footer.jsp" %>

<script src="/resources/js/app.js"></script>
</body>
</html>