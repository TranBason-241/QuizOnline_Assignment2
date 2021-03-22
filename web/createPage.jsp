

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Create Question</title>
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
                    <h4 class="font-weight-bold">Quiz Online</h4>
                </a>



                <div class="collapse navbar-collapse">
                    <p style="color: white" class="mb-0 ml-2">Welcome ${sessionScope.USER.userName} </p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>

                <div> 
                    <ul class="navbar-nav">

                        <li class="nav-item ml-md-3">
                            <c:url var="back" value="AdminController" >  

                            </c:url>
                            <a class="btn btn-success" href="${back}"><i class="bx bxs-user-circle mr-1"></i>back</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">CREATE QUESTION</h1>
            </div>
        </section>



        <!----------------------------------------PRODUCT-------------------------------------------->

        <c:set var="warning" value="${requestScope.MESSAGE_CREATE}"> </c:set>

        <c:if test="${warning!=null}">
            <div class="alert alert-primary" role="alert">
                ${warning}
            </div>
        </c:if> 

        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th >Question content</th>


                                    <th style="width: 100px" class="text-center">Answer content</th>
                                    <th style="padding-left: 0" class="text-center">Correct Answer</th>
                                    <th  class="text-center">Subject</th>
                                    <th  class="text-center"></th>
                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>

                            </thead>
                            <tbody>








                            <form action="CreateController" method="POST">

                                <tr>
                                    <td style="padding-right: 0;width: 300px;"><input required="required" pattern="[a-zA-Z-0-9<>:]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980"  title="can not empty and can not contain space and FIRST" name="questionContentCREATE" value="${question.questionContent}" style="width: 250px; " ></td>

                                    <td> 
                                        <ul style="text-decoration: none;  ">

                                            <li style="display: flex" >
                                                <input  name="txtAnswer1CREATE" placeholder="answer1" required="required" pattern="[a-zA-Z-0-9<>:]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at  FIRST, length > 2" >
                                                <input required="required" style="margin-left: 50px; margin-top: 13px" name="txtAnswerCorrectCREATE" type="radio" value="txtAnswer1CREATE" />
                                            </li>
                                            <li style="display: flex" >
                                                <input  name="txtAnswer2CREATE" placeholder="answer2" required="required" pattern="[a-zA-Z-0-9<>:]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at FIRST, length > 2" >
                                                <input required="required" style="margin-left: 50px; margin-top: 13px" name="txtAnswerCorrectCREATE" type="radio" value="txtAnswer2CREATE" />
                                            </li>
                                            <li style="display: flex" >
                                                <input  name="txtAnswer3CREATE" placeholder="answer3" required="required" pattern="[a-zA-Z-0-9<>:]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at FIRST, length > 2" >
                                                <input required="required" style="margin-left: 50px; margin-top: 13px" name="txtAnswerCorrectCREATE" type="radio" value="txtAnswer3CREATE" />
                                            </li>
                                            <li style="display: flex" >
                                                <input  name="txtAnswer4CREATE" placeholder="answer4" required="required" pattern="[a-zA-Z-0-9<>:]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9.?; ()<>:]{1}" maxlength="980" minlength="2" title="can not emptyswer.answerContent} and can not contain space at  FIRST, length > 2" >
                                                <input required="required" style="margin-left: 50px; margin-top: 13px" name="txtAnswerCorrectCREATE" type="radio" value="txtAnswer4CREATE" />
                                            </li>

                                        </ul>  
                                    </td>
                                    <td></td>
                                    <td> <select name="txtSubjectCREATE" style="width: 90px">
                                            <c:forEach var="subject" items="${sessionScope.LIST_SUBJECT}" >
                                                <option   value="${subject.subjectID}" >${subject.getSubjectName()}</option> 
                                            </c:forEach>
                                        </select></td>
                                    <td>
                                    </td>
                                    </td>



                                    <td style="width: 100px"> <button style="width: 60px" type="submit"  class="btn btn-sm btn-success"><i class="fa fa-edit"></i> </button> </td>

                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>    
                            </form>




                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div>
                <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >

                    <c:if test="${sessionScope.NUMBER_OF_PAGE!=null}">

                        <c:forEach begin="1" end="${sessionScope.NUMBER_OF_PAGE}"  varStatus="counter">
                            <li class="ml-2"><a href="MainController?btnAction=searchAdmin&activePage=${counter.index}&txtSearch=${sessionScope.NAME_SEARCH}&txtCategory=${categoryForPaging}&txtPrice=${priceForPaging}">${counter.index}</a></li>  
                            </c:forEach>
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
