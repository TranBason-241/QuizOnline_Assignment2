

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Management Question</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >

                <button type="button" id="sidebarCollapse" class="btn btn-link d-block d-md-none">
                    <i class="bx bx-menu icon-single"></i>
                </button>

                <a class="navbar-brand" href="#">
                    <h4 class="font-weight-bold">QuizOnline</h4>
                </a>



                <div class="collapse navbar-collapse">
                    <form action="AdminController" method="post" class="form-inline my-2 my-lg-0 mx-auto">
                        <c:set var="optionSEARCHED" value="${requestScope.optionSEARCHED == null ? '' : requestScope.optionSEARCHED }"></c:set>
                            <ul style="display: flex; list-style: none" >
                                <li style="margin-right: 30px">
                                    <h4 style="color: white; text-align: center">Subject</h4>
                                    <span>
                                    <c:if test="${optionSEARCHED.equals('txtSubjectSEARCH')}">
                                        <input checked="checked" required="required" type="radio" name="optionSEARCH" value="txtSubjectSEARCH" >
                                    </c:if>
                                    <c:if test="${!optionSEARCHED.equals('txtSubjectSEARCH')}">
                                        <input required="required" type="radio" name="optionSEARCH" value="txtSubjectSEARCH" >
                                    </c:if>

                                    <select class="form-control mr-2" name="txtSubjectSEARCH" >

                                        <option value="Subject">Subject</option>
                                        <c:forEach var="subject1" items="${sessionScope.LIST_SUBJECT}" >
                                            <c:set var="subjectSEARCHED" value="${requestScope.subjectSEARCHED == null ? '' : requestScope.subjectSEARCHED}" ></c:set>

                                            <c:if test="${requestScope.subjectSEARCHED == null}">
                                                <c:set value="ssd" var="ss"></c:set>
                                            </c:if>
                                            <c:if test="${!subject1.subjectID.equals(subjectSEARCHED)}" >
                                                <option   value="${subject1.subjectID}" >${subject1.getSubjectName()}</option> 
                                            </c:if>
                                            <c:if test="${subject1.subjectID.equals(subjectSEARCHED)}" >
                                                <option  selected="selected"  value="${subject1.getSubjectID()}" >${subject1.getSubjectName()}</option>
                                            </c:if>

                                        </c:forEach>
                                    </select>
                                </span>
                            </li>
                            <li style="margin-right: 30px" >
                                <h4 style="color: white; text-align: center">Status</h4>
                                <span>

                                    <c:if test="${optionSEARCHED.equals('txtStatusSEARCH')}">
                                        <input checked="checked" required="required" type="radio" name="optionSEARCH" value="txtStatusSEARCH" >
                                    </c:if>
                                    <c:if test="${!optionSEARCHED.equals('txtStatusSEARCH')}">
                                        <input  required="required" type="radio" name="optionSEARCH" value="txtStatusSEARCH" >
                                    </c:if>


                                    <select  class="form-control mr-2" name="txtStatusSEARCH" >
                                        <option  value="Status">Status</option>
                                        <c:set var="statusSEARCHED" value="${requestScope.statusSEARCHED == null ? '' : requestScope.statusSEARCHED }" ></c:set>
                                        <c:forEach var="status1" items="${sessionScope.LIST_STATUS}">
                                            <c:if test="${status1.equals(statusSEARCHED)}">
                                                <option  value="${status1}" selected="selected" >${status1}</option>
                                            </c:if>
                                            <c:if test="${!status1.equals(statusSEARCHED)}">
                                                <option  value="${status1}">${status1}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </span>
                            </li>
                            <li style="margin-right: 30px" >
                                <h4 style="color: white; text-align: center">Name</h4>
                                <span>


                                    <c:if test="${optionSEARCHED.equals('txtNameSEARCH')}">
                                        <input checked="checked" required="required" type="radio" name="optionSEARCH"  value="txtNameSEARCH" >
                                    </c:if>
                                    <c:if test="${!optionSEARCHED.equals('txtNameSEARCH')}">
                                        <input required="required" type="radio" name="optionSEARCH"  value="txtNameSEARCH" >
                                    </c:if>
                                    <input class="form-control mr-2"  value="${requestScope.nameSEARCHED == null ? '' :requestScope.nameSEARCHED }"  name="txtNameSEARCH" >
                                </span>
                            </li>
                        </ul>
                        <button class=" btn btn-success mt-3" type="submit" style="margin-top: 10px">Search</button>
                    </form>
                    <p style="color: white" class="mb-0 ml-2">Welcome ${sessionScope.USER.userName} </p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>

                <div> 
                    <ul class="navbar-nav">

                        <li class="nav-item ml-md-3">
                            <c:url var="logout" value="LogoutController" >  

                            </c:url>
                            <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">MANAGE QUESTION</h1>
            </div>
        </section>
        <div style="padding-left: 1150px">

            <c:url var="createProduct" value="CreateController" >

            </c:url>
            <a style="margin-left: 300px" class="btn btn-success btn-sm ml-3" href="${createProduct}">
                ADD NEW QUESTION
            </a>

        </div>


        <!----------------------------------------PRODUCT-------------------------------------------->



        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th >Question content</th>

                                    <th>Date</th>
                                    <th style="width: 100px" class="text-center">Answer content</th>
                                    <th style="padding-left: 0" class="text-center">Correct Answer</th>
                                    <th  class="text-center">Subject</th>
                                    <th  class="text-center">Status</th>
                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>

                            </thead>
                            <tbody>

                                <c:forEach var="question" items="${sessionScope.LIST_QUESTION}">
                                <form action="UpdateController" method="POST">

                                    <tr>
                                        <td style="padding-right: 0;width: 300px;"><input required="required" pattern="[a-zA-Z-0-9<>:]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980"  title="can not empty and can not contain space at FIRST, length > 2" name="txtQuestionContent" value="${question.questionContent}" style="width: 250px; " ></td>
                                        <td style="width: 50px !important" >

                                            <p>${question.createDate}</p>

                                        </td>
                                        <td> 
                                            <ul style="text-decoration: none;  ">
                                                <c:forEach var="answer" items="${question.answer}">

                                                    <li style="display: flex" >


                                                        <c:if test="${answer.isCorrect == true}">
                                                            <input name="${answer.answerID}" value="${answer.answerContent}" required="required" pattern="[a-zA-Z-0-9<>]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at FIRST, length > 2">
                                                            <input checked="checked" style="margin-left: 50px; margin-top: 13px" name="${question.questionID}" type="radio" value="${answer.answerID}" />
                                                        </c:if>
                                                        <c:if test="${answer.isCorrect == false}">
                                                            <input  name="${answer.answerID}" value="${answer.answerContent}" required="required" pattern="[a-zA-Z-0-9<>]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at FIRST, length > 2" >
                                                            <input style="margin-left: 50px; margin-top: 13px" name="${question.questionID}" type="radio" value="${answer.answerID}" />
                                                        </c:if>

                                                    </li>
                                                </c:forEach>


                                            </ul>  
                                        </td>
                                        <td></td>
                                        <td> <select name="txtSubject" style="width: 90px">
                                                <c:forEach var="subject" items="${sessionScope.LIST_SUBJECT}" >
                                                    <c:if test="${!subject.subjectID.equals(question.subjectID)}" >
                                                        <option   value="${subject.subjectID}" >${subject.getSubjectName()}</option> 
                                                    </c:if>
                                                    <c:if test="${subject.subjectID.equals(question.subjectID)}" >
                                                        <option  selected="selected"  value="${subject.getSubjectID()}" >${subject.getSubjectName()}</option>
                                                    </c:if>

                                                </c:forEach>

                                            </select></td>
                                        <td>
                                            <select name="txtStatus" style="width: 90px">
                                                <c:forEach var="status" items="${sessionScope.LIST_STATUS}">
                                                    <c:if test="${status.equals(question.status)}">
                                                        <option  value="${status}" selected="selected" >${status}</option>
                                                    </c:if>
                                                    <c:if test="${!status.equals(question.status)}">
                                                        <option  value="${status}">${status}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        </td>

                                        <c:url var="delete" value="DeleteController" >
                                            <c:param name="questionIDDelete"  value="${question.questionID}"></c:param>
                                            <c:param name="txtSubjectSEARCH"  value="${requestScope.subjectSEARCHED}"></c:param>
                                            <c:param name="txtStatusSEARCH"  value="${requestScope.statusSEARCHED}"></c:param>
                                            <c:param name="txtNameSEARCH"  value="${requestScope.nameSEARCHED}"></c:param>
                                            <c:param name="optionSEARCH"  value="${requestScope.optionSEARCHED}"></c:param>
                                            <c:param name="activePage"  value="${requestScope.ACTIVE_PAGE}"></c:param>
                                        </c:url>

                                        <c:if test="${question.status.equals(sessionScope.LIST_STATUS[0])}">
                                            <td style="width: 100px"> <a style="width: 60px"  href="${delete}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </a> </td>
                                        </c:if>


                                    <input type="hidden" value="${requestScope.subjectSEARCHED}" name="txtSubjectSEARCH">
                                    <input type="hidden" value="${requestScope.statusSEARCHED}" name="txtStatusSEARCH">
                                    <input type="hidden" value="${requestScope.nameSEARCHED}" name="txtNameSEARCH">
                                    <input type="hidden" value="${requestScope.optionSEARCHED}" name="optionSEARCH">
                                    <input type="hidden" value="${requestScope.ACTIVE_PAGE}" name="activePage">


                                    <input type="hidden" value="${question.questionID}" name="questionIDUpdate">
                                    <input type="hidden" value="${requestScope.ACTIVE_PAGE}" name="activePage">

                                    <td style="width: 100px"> <button style="width: 60px" type="submit"  class="btn btn-sm btn-warning"><i class="fa fa-edit"></i> </button> </td>

                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    </tr>    
                                </form>

                            </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div>
                <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >

                    <c:if test="${requestScope.NUMBER_OF_PAGE!=null}">
                        <c:if test="${requestScope.NUMBER_OF_PAGE > 1}">

                            <c:forEach begin="1" end="${requestScope.NUMBER_OF_PAGE}"  varStatus="counter">
                                <li class="ml-2"><a href="AdminController?activePage=${counter.index}&txtSubjectSEARCH=${requestScope.subjectSEARCHED}&txtStatusSEARCH=${requestScope.statusSEARCHED}&txtNameSEARCH=${requestScope.nameSEARCHED}&optionSEARCH=${requestScope.optionSEARCHED}">${counter.index}</a></li>  
                                </c:forEach>
                            </c:if>
                        </c:if>
                </ul>
            </div>
        </div>

        <!-- Footer -->


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
