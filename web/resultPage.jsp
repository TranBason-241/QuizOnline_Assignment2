

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>

        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >


                <a class="navbar-brand" href="#">
                    <h4 class="font-weight-bold">QuizOnline</h4>
                </a>
                <div> <h1 style="color: white;"> ${sessionScope.SUBJECT_EXAM.subjectName} </h1> </div>
                <div style="display: flex;padding-top: 17px">  </div>
                <div> <h6 style="color: white">Tran Ba Son</h6> </div>

                <div> 

                </div>
            </div>
        </nav>
                <div style="height: 400px;width: 100%; background-color: slategray">
                    <h2 style="padding-top: 50px;text-align: center;color: yellow" >${requestScope.MESS}</h2>
                    <h3 style="text-align: center;color: white" >${requestScope.NUMBER_CORRECT}</h3>
                       
                    <h1 style="text-align: center;color: white"> Your Grade:  <span style="color: red">${requestScope.RESULT}</span> </h1>
                    <c:url var="back" value="UserController" ></c:url>
                    <a href="${back}" style="margin-left: 46%; margin-top: 40px; color: white" class="btn btn-success" >Back to home</a>
                </div>
             
       
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
