<%-- 
    Document   : Register
    Created on : Jun 27, 2022, 2:26:53 PM
    Author     : TGDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="css/Register.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>

        <section class="vh-100 bg-image"
                 style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Create an account</h2>

                                    <form action="Register">

                                        <div class="form-outline mb-4">
                                            <input type="text" name="name" value="${name}" id="form3Example1cg" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example1cg">Your Name</label>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="email" name="email" value="${email}" id="form3Example3cg" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example3cg">Your Email</label>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="password" name="pass" id="form3Example4cg" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example4cg">Password</label>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="password" name="confirmPass" id="form3Example4cdg" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example4cdg">Repeat your password</label>
                                            <br><label class="form-label" for="form3Example4cdg">${note}</label>
                                        </div>

                                        

                                        <div class="d-flex justify-content-center">
                                            <input type="submit" name="register" value="Register"
                                                   class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                                        </div>

                                        <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="http://localhost:9999/AssignmentPRJ/Login.jsp"
                                                                                                                class="fw-bold text-body"><u>Login here</u></a></p>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
