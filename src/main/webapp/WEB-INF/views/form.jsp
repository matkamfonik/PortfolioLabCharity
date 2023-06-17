<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="formHeader.jsp" %>
<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form method="post" modelAttribute="donation" id="donationForm">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

                <c:forEach var="category" items="${categories}">

                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categoriesIds" value="${category.id}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <form:errors path="categoriesIds"/>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <input type="number" name="quantity" step="1" min="1"/>
                    </label>
                </div>
                <form:errors path="quantity"/>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <c:forEach var="institution" items="${institutions}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="radio" name="institutionId" value="${institution.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                            <div class="title">Fundacja "${institution.name}"</div>
                            <div class="subtitle">Cel i misja: ${institution.description}</div>
                            </span>
                        </label>
                    </div>
                </c:forEach>
                <form:errors path="institutionId"/>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label for="street">Ulica</label>

                            <form:input path="street"/>
                            <form:errors path="street"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="city">Miasto</label>
                            <form:input path="city"/>
                            <form:errors path="city"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="zipCode">Kod pocztowy</label>
                            <form:input path="zipCode"/>
                            <form:errors path="zipCode"/>
                        </div>

                    </div>
                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>

                        <div class="form-group form-group--inline">
                            <label for="pickUpDate">Data</label>
                            <form:input type="date" path="pickUpDate"/>
                            <form:errors path="pickUpDate"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="pickUpTime">Godzina</label>
                            <form:input type="time" path="pickUpTime"/>
                            <form:errors path="pickUpTime"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>Uwagi dla kuriera</label>
                            <textarea form="donationForm" name="pickUpComment" rows="5">

                            </textarea>
                        </div>
                    </div>
                    <div class="form-group form-group--buttons">
                        <button type="button" class="btn prev-step">Wstecz</button>
                        <button type="button" class="btn next-step">Dalej</button>
                    </div>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag">

                                </span>
                                <span class="summary--text">
                                    <span id="quantity"></span>
                                    <span id="category"></span>
                                </span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text">
                                    Dla: <span id="institution"></span>
                                </span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul id="address">
                                <li></li>
                                <li></li>
                                <li></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul id="date">
                                <li></li>
                                <li></li>
                                <li></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>
<%@include file="footer.jsp" %>

<script src="/resources/js/app.js"></script>
</body>
</html>