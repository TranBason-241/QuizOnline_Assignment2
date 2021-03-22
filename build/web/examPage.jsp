


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>

    <body>


        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >


                <a class="navbar-brand" href="#">
                    <h4 class="font-weight-bold">QuizOnline</h4>
                </a>
                <div> <h1 style="color: white;"> ${sessionScope.SUBJECT_EXAM.subjectName} </h1> </div>
                <div style="display: flex;padding-top: 17px"> <h1 style="color: white;">Time: </h1> <p style="width: 250px; font-size: 35px;color: yellow" id="time"></p> </div>
                <div> <h6 style="color: white">Tran Ba Son</h6> </div>

                <div> 

                </div>
            </div>
        </nav>

        <form action="CheckExamController" id="submitForm" >
            <div style="width: 100%;height: 400px; margin: 0 auto;" id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
                <ol type="circle" class="carousel-indicators">

                    <c:if test="${sessionScope.LIST_QUESTION!=null}" >
                        <c:forEach var="question" items="${sessionScope.LIST_QUESTION}" varStatus="counter" >

                            <c:if test="${counter.index == 0}" >
                                <li data-target="#carouselExampleCaptions" data-slide-to="${counter.index}" class="active"></li>
                                </c:if>
                                <c:if test="${counter.index != 0}" >
                                <li data-target="#carouselExampleCaptions" data-slide-to="${counter.index}" ></li>
                                </c:if>
                            </c:forEach>
                    </c:if>


                </ol>
                <div class="carousel-inner">

                    <c:forEach var="question" items="${sessionScope.LIST_QUESTION}" varStatus="counter" >
                        <c:if test="${counter.index == 0 }">
                            <div class="carousel-item active ">
                                <img style="width: 500px; height: 400px;" src="images/carousel.png" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 style="color: red;">QUESTION 1</h5>
                                    <p style="text-align:  left;"> ${question.questionContent} </p>
                                    <ul style="list-style-type: upper-alpha; width: 100%; margin: 0 auto; text-align: left;">
                                        <c:forEach var="answer" items="${question.answer}">
                                            <li><input name="${question.questionID}" value="${answer.answerID}" style="margin-left: 50px;" type="radio" ><span>${answer.answerContent}</span></li>
                                                </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${counter.index != 0 }">
                            <div class="carousel-item">
                                <img style="width: 500px; height: 400px;" src="https://salt.tikicdn.com/cache/w444/ts/product/61/ef/8f/afe0220bd37333e05e1869d148150f23.PNG" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 style="color: red;">QUESTION ${counter.index + 1}</h5>
                                    <p style="text-align:  left;">${question.questionContent}</p>
                                    <ul style="list-style-type: upper-alpha; width: 100%; margin: 0 auto; text-align: left;">

                                        <c:forEach var="answer" items="${question.answer}">
                                            <li><input name="${question.questionID}" value="${answer.answerID}" style="margin-left: 50px;" type="radio" ><span>${answer.answerContent}</span></li>
                                                </c:forEach>

                                    </ul>
                                </div>
                            </div>
                        </c:if>


                    </c:forEach>









                </div>
                <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>

                <button style="margin-left:75%;margin-top: 30px;height: 60px;width: 300px" class="btn btn-success">Submit</button>
            </div>
        </form>


        <div style="width: 100%; height: 200px; background-color:rgba(136, 136, 136, 0.534); font-size: 11px;">
            <h5 style="text-align:  left; padding-top: 30px;">EXAM RULES !!!</h5>
            <p>1,No bathroom breaks. If you leave the room during the exam, your exam will be over.</p>
            <p>2,No talking, texting or other communication with anyone other than your instructor during the exam.</p>
            <p>3,Only the student taking the exam may be present in the room.</p>
            <p>4,It is your responsibility not to appear to be cheating. Exams will be recorded as necessary to enforce this policy.</p>
        </div>


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <script>
            // Set the date we're counting down to

            var distance = ${sessionScope.SUBJECT_EXAM.timeToDo} * 60 * 1000
            // Update the count down every 1 second
            var x = setInterval(function () {


                distance = distance - 1000;

                // Time calculations for days, hours, minutes and seconds
                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                // Output the result in an element with id="time"
                document.getElementById("time").innerHTML = hours + "h " +
                        minutes + "m " + seconds + "s ";

                // If the count down is over, write some text 
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("time").innerHTML = "EXPIRED";
                    document.getElementById("submitForm").submit();

                }
            }, 1000);
        </script>


    </body>

</body>
</html>
