<%-- 
    Document   : Login
    Created on : Jun 27, 2022, 1:26:56 PM
    Author     : TGDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
            <section class="vh-100">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-6 text-black">

                            <div class="px-5 ms-xl-4">
                                <i class="fas fa-crow fa-2x me-3 pt-5 mt-xl-4" style="color: #709085;"></i>
                                <span class="h1 fw-bold mb-0">Logo</span>
                            </div>

                            <div class="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

                                <form action="LoginController" style="width: 23rem;">

                                    <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Log in</h3>

                                    <div class="form-outline mb-4">
                                        <div> ${note}<input type="email" name="email" value="${email}" id="form2Example18" class="form-control form-control-lg" /></div>
                                        <label class="form-label" for="form2Example18">Email address</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" name="pass" id="form2Example28" class="form-control form-control-lg" />
                                        <label class="form-label" for="form2Example28">Password</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <input class="btn btn-info btn-lg btn-block" type="submit" name="login" value="Login">
                                    </div>

                                    <p class="small mb-5 pb-lg-2"><a class="text-muted" href="#!">Forgot password?</a></p>
                                    <p>Don't have an account? <a href="http://localhost:9999/AssignmentPRJ/Register.jsp" class="link-info">Register here</a></p>

                                </form>

                            </div>

                        </div>
                        <div class="col-sm-6 px-0 d-none d-sm-block">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img3.webp"
                                 alt="Login image" class="w-100 vh-100" style="object-fit: cover; object-position: left;">
                        </div>
                    </div>
                </div>
            </section>
        

    </body>
</html>
