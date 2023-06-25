<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="/WEB-INF/views/users/header.jsp" %>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800">Dary</h1>

        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Fundacja</th>
                            <th>Status</th>
                            <th>Data utworzenia wpisu</th>
                            <th>Data odebrania daru</th>
                            <th>Data przekazania daru</th>
                            <th>Planowana data odebrania daru</th>
                            <th>Adres przekazania daru</th>
                            <th>Komentarz do odbioru</th>
                            <th>Przekazane rzeczy</th>
                            <th>Liczba worków</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Fundacja</th>
                            <th>Status</th>
                            <th>Data utworzenia wpisu</th>
                            <th>Data odebrania daru</th>
                            <th>Data przekazania daru</th>
                            <th>Planowana data odebrania daru</th>
                            <th>Adres przekazania daru</th>
                            <th>Komentarz do odbioru</th>
                            <th>Przekazane rzeczy</th>
                            <th>Liczba worków</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${donations}" var="donation">
                            <tr>
                                <td>${donation.institutionName}</td>
                                <td>${donation.statusName}</td>
                                <td>${donation.created}</td>
                                <td>${donation.received}</td>
                                <td>${donation.transferred}</td>
                                <td>${donation.pickUpDate}</td>
                                <td>ul. ${donation.street}, ${donation.zipCode} ${donation.city}</td>
                                <td>${donation.pickUpComment}</td>
                                <td>
                                    <c:forEach items="${donation.categoriesNames}" var="category">
                                        ${category}<br>
                                    </c:forEach>
                                </td>
                                <td>${donation.quantity}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>


</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/users/footer.jsp" %>