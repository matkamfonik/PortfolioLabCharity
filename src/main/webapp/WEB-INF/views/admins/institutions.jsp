<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="/WEB-INF/views/admins/header.jsp" %>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Lista fundacji</h1>
        </div>


        <c:forEach var="institution" items="${institutions}">
            <!-- Content Row -->
            <div class="row">

                <!-- User details -->
                <div class="col-xl-10 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                        Fundacja
                                    </div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                        <p>${institution.id}</p>
                                        <p>"${institution.name}"</p>
                                        <p>${institution.description}</p>
                                        <p> Kategorie: </p> <c:forEach items="${institution.categoriesNames}"
                                                                       var="categoryName">
                                        <p>${categoryName}</p></c:forEach>
                                    </div>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/institutions/${institution.id}"><i
                                            class="fas fa-eye fa-2x text-gray-300"></i>
                                    </a>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/institutions/${institution.id}/edit"> <i
                                            class="fas fa-edit fa-2x text-gray-300"></i> </a>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/institutions/${institution.id}/delete"> <i
                                            class="fas fa-dumpster fa-2x text-gray-300"></i> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </c:forEach>


    </div>
    <!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/admins/footer.jsp" %>