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
            <h1 class="h3 mb-0 text-gray-800">Lista adminów</h1>
        </div>


        <c:forEach var="admin" items="${admins}">
            <!-- Content Row -->
            <div class="row">

                <!-- User details -->
                <div class="col-xl-10 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                        Admin
                                    </div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            ${admin.id} ${admin.firstName} ${admin.lastName} ${admin.email} <c:if
                                            test="${admin.enabled > 0}">Aktywny</c:if> <c:if
                                            test="${admin.enabled < 1}">Nieaktywny</c:if>
                                    </div>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/${admin.id}"><i class="fas fa-eye fa-2x text-gray-300"></i>
                                    </a>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/${admin.id}/edit"> <i
                                            class="fas fa-edit fa-2x text-gray-300"></i> </a>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/${admin.id}/disable"> <i
                                            class="fas fa-ban fa-2x text-gray-300"></i> </a>
                                </div>
                                <div class="col-auto">
                                    <a href="/admins/${admin.id}/delete"> <i
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