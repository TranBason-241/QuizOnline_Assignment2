

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>history Detail Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >


                <a class="navbar-brand" href="#">
                    <h4 class="font-weight-bold">QuizOnline</h4>
                </a>
                <div> <h1 style="color: white;">FPT UNIVERSITY HCM </h1> </div>

                <div> 
                    <ul class="navbar-nav">
                        <li class="nav-item ml-md-3">
                            <c:url var="back" value="HistoryController" >  
                            </c:url>
                            <a class="btn btn-success" href="${back}"><i class="bx bxs-user-circle mr-1"></i>back</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th >No</th>
                                    <th >Question content</th>
                                    <th style="width: 100px" class="text-center">Answer content</th>
                                    <th style="padding-left: 0" class="text-center">Correct </th>
                                    <th  class="text-center">Your choice</th>

                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>

                            </thead>
                            <tbody>



                                <c:forEach var="question" items="${sessionScope.LIST_QUESTION_DETAIL}" varStatus="counter" >
                                    <tr>  
                                        <td style="padding-right: 0;width: 300px;"> ${counter.index + 1} </td>    
                                        <td style="padding-right: 0;width: 300px;"> ${question.questionContent} </td>    
                                        <td > 
                                            <ul style="text-decoration: none;  ">
                                                <c:forEach var="answer" items="${question.answer}">

                                                    <li style="display: flex" >


                                                        <c:if test="${answer.isCorrect == true}">
                                                            <input name="${answer.answerID}" value="${answer.answerContent}" required="required" pattern="[a-zA-Z-0-9]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at LAST and FIRST">
                                                            <input checked="checked" style="margin-left: 50px; margin-top: 13px" name="${question.questionID}" type="radio" value="${answer.answerID}" />
                                                        </c:if>
                                                        <c:if test="${answer.isCorrect == false}">
                                                            <input  name="${answer.answerID}" value="${answer.answerContent}" required="required" pattern="[a-zA-Z-0-9]{1}(?=.*[a-zA-Z0-9]).{1,}[a-zA-Z-0-9]{1}" maxlength="980" minlength="2" title="can not empty and can not contain space at LAST and FIRST, length > 2" >
                                                            <input style="margin-left: 50px; margin-top: 13px" name="${question.questionID}" type="radio" value="${answer.answerID}" />
                                                        </c:if>

                                                    </li>
                                                </c:forEach>


                                            </ul>  

                                        </td>    
                                        <td></td>
                                        <td style="padding-left: 20px">  
                                            <ul style="text-decoration: none;  ">
                                                <c:forEach var="answer" items="${question.answer}">

                                                    <li style="display: flex" >

                                                        <c:if test="${answer.isSelected != null}">
                                                            <c:if test="${answer.isSelected.equals('selected')}">

                                                                <input checked="checked" style="margin-left: 50px; margin-top: 20px" name="${counter.index}" type="radio" value="${answer.answerID}" />
                                                            </c:if>
                                                            <c:if test="${!answer.isSelected.equals('selected')}">

                                                                <input style="margin-left: 50px; margin-top: 13px" name="${counter.index}" type="radio" value="${answer.answerID}" />
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${answer.isSelected == null}">
                                                            <input style="margin-left: 50px; margin-top: 13px" name="${counter.index}" type="radio" value="${answer.answerID}" />
                                                        </c:if>


                                                    </li>
                                                </c:forEach>


                                            </ul>  
                                        </td>    
                                        <td style="padding-right: 0;width: 300px;">  </td>    
                                    </tr>
                                </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


            <!-- Optional JavaScript -->
            <!-- jQuery first, then Popper.js, then Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
