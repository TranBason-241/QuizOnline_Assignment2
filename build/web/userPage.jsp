
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>User Page</title>
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
                            <c:url var="logout" value="LogoutController" >  
                            </c:url>
                            <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <form action="UserController" method="POST" >
            <div style="display: flex;">
                <div style="width: 70%; height: 200px; background-color: silver;">
                    <div style="display: flex; padding-top: 30px;background-color: rgba(116, 115, 115, 0.555);">
                        <h3>Choose subject</h3>
                        <select name="subject" style="margin-left: 20px; width: 120px;" class="form-control">
                            <option value="Subject">Subject</option>
                            <c:forEach var="subject" items="${sessionScope.LIST_SUBJECT}">
F
                                <c:if test="${subject.subjectID.equals(sessionScope.SUBJECT_SELECTED.subjectID)}" > 
                                    <option selected="selected" value="${subject.subjectID}">${subject.subjectName}</option>
                                </c:if>
                                <c:if test="${!subject.subjectID.equals(sessionScope.SUBJECT_SELECTED.subjectID)}" > 
                                    <option  value="${subject.subjectID}">${subject.subjectName}</option>
                                </c:if>


                            </c:forEach>

                        </select>
                        <button type="submit" class="btn btn-success ml-3">Choose subject</button>
                    </div>

                    <div style="display: flex;">
                        <c:set var="subjectSelected" value="${sessionScope.SUBJECT_SELECTED}" ></c:set>
                        <c:if test="${subjectSelected !=null}">
                            <div style="padding-left: 30px;">
                                <h5 style="margin-top: 20px;">subject information</h5>
                                <p style="color: red;">Number of question: ${subjectSelected.numberOfQuestions}</p>
                                <p style="color: red;">Time ${subjectSelected.timeToDo} minutes</p>
                            </div>  
                            <div style="padding-top: 60px;padding-left: 60px;" > 
                                
                                <c:url var="doQuiz"  value="ExamController">
                                    <c:param name="subjectDoQuiz" value="${sessionScope.SUBJECT_SELECTED.subjectID}" ></c:param>
                                </c:url>
                                
                                <a href="${doQuiz}"  class='btn btn-success'>Do quiz now </a>
                            </div>
                        </c:if>
                        


                    </div>

                </div>


                <div style="width: 30%; height: 200px; background-color: rgba(41, 40, 40, 0.445);">
                    <h3 style="margin-top: 30px; text-align: center;" >Student Infomation</h3>
                    <ul>
                        <li>FPTU HCM </li>
                        <li>Email: ${sessionScope.USER.userID} </li>
                        <li>FullName: ${sessionScope.USER.userName} </li>
                            <c:url var="history" value="HistoryController" >
                        
                        </c:url>
                        <a href="${history}" style="margin-top:10px " class="btn - btn-warning" > HISTORY </a>
                    </ul>
                </div>

            </div>
        </form>



        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</body>
</html>
