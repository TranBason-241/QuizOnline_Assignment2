

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>History Page</title>
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
                    <form action="HistoryController" method="post" class="form-inline my-2 my-lg-0 mx-auto">
                        <select class="form-control mr-2" name="subjectHistorySearch" >
                            <option >subject</option>
                            <c:forEach var="subject" items="${sessionScope.LIST_SUBJECT}" >
                                <c:if test="${subject.subjectID.equals(requestScope.SUBJECT_HISTORY_SEARCH)}" >
                                    <option selected="selected" value="${subject.subjectID}" >${subject.subjectName}</option>
                                </c:if>
                                <c:if test="${!subject.subjectID.equals(requestScope.SUBJECT_HISTORY_SEARCH)}" >
                                    <option  value="${subject.subjectID}" >${subject.subjectName}</option>
                                </c:if>


                            </c:forEach>
                        </select>



                        <button class="ml-2 btn btn-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                    <p style="color: white" class="mb-0 ml-2">Welcome  ${sessionScope.USER.userName}</p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>

                <div> 
                    <ul class="navbar-nav">

                        <li class="nav-item ml-md-3">
                            <c:url var="logout" value="UserController" >  

                            </c:url>
                            <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>back</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading"> HISTORY QUIZ</h1>
            </div>
        </section>



        <!----------------------------------------QUIZ-------------------------------------------->



        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <td></td>
                                    <th scope="col"></th>
                                    <th scope="col" class="text-center">Subject</th>
                                    <th scope="col" class="text-center">Date</th>
                                    <th scope="col" class="text-center">Correct Answer</th>
                                    <th scope="col" class="text-center">Point</th>
                                    <th scope="col" class="text-center"></th>
                                    <th scope="col" class="text-center"></th>
                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>

                            </thead>
                            <tbody>



                            <form action="MainController" method="POST">
                                <c:forEach var="history" items="${requestScope.LIST_HISTORY}">

                                    <!-- ------------------    PRODUCT--------->
                                    <tr style="text-align: center">
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h6>${history.subjectID}</h6></td>
                                        <td><h6>${history.getHistoryDate()}</h6></td>
                                        <td><h6 style="width: 200px">${history.getNumOfCorrect()}</h6></td>
                                        <td class="pl-4"><h6>${history.getTotal()}</h6></td>
                                        <td style="padding-top: 13px"> 
                                        </td>
                                        <!--                                            Giu lai search, page do:-->
                                        <c:url var="detail" value="HistoryDetailController" >
                                            <c:param name="txtHistoryID" value="${history.getHistoryID()}" ></c:param>
                                            <c:param name="txtUserID" value="${sessionScope.USER.userID}" ></c:param>
                                        </c:url>
                                        <td  style="padding-top: 7px" class="text-right"><a class="btn btn-success" href="${detail}">VIEW DETAIL</a></td>
                                    </tr>
                                </c:forEach>

                            </form>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div>
                <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >


                </ul>
            </div>
        </div>

        <div>
            <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >

                <c:if test="${requestScope.NUMBER_OF_PAGE!=null}">
                    <c:if test="${requestScope.NUMBER_OF_PAGE > 1}">
                        <c:forEach begin="1" end="${requestScope.NUMBER_OF_PAGE}"  varStatus="counter">
                            <li class="ml-2"><a href="HistoryController?activePage=${counter.index}&subjectHistorySearch=${requestScope.SUBJECT_HISTORY_SEARCH}">${counter.index}</a></li>  
                            </c:forEach>
                        </c:if>

                </c:if>
            </ul>
        </div>




        <!-- Footer -->


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
