<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="/WEB-INF/views/admins/header.jsp" %>


<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">${institution.name}</h1>
    </div>

    <!-- Content Row -->
    <div class="row">

        <!-- User details -->
        <div class="col-xl-10 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <form:form method="post" modelAttribute="institution">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    Nazwa
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800"><form:input path="name"/></div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-signature fa-2x text-gray-300"></i>
                            </div>
                        </div>

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    Opis
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800"><form:input
                                        path="description"/></div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-signature fa-2x text-gray-300"></i>
                            </div>
                        </div>

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    Kategorie:
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800"><form:select multiple="true"
                                                                                                 path="categoriesIds"><form:options
                                        items="${categories}" itemLabel="name" itemValue="id"/> </form:select></div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-signature fa-2x text-gray-300"></i>
                            </div>
                        </div>

                        <button type="submit" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-recycle fa-sm text-white-50"></i> Zapisz
                        </button>

                    </form:form>
                </div>
            </div>
        </div>

    </div>


</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/admins/footer.jsp" %>