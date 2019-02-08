<#include "header.ftlh">

    <div class="wrap">
    <div class="container">


        <div class="jumbotron jumbotron-fluid text-center"
             style="background-color: rgba(163, 177, 179, 0.7); margin-bottom: 200px">
            <div class="container">
                <h1 class="display-4">Driving School</h1>
                <p class="lead">This is an application for driving school management</p>
                <#if sendMail ??>
                    <div class="alert alert-success padding" role="alert">
                        We've reset the password. Check your mail. <a href="/start">Sing In</a>
                    </div>
                </#if>
                <#if errorSendMail ??>
                <div class="alert alert-danger" role="alert">
                    The email provided does not exist <a href="/start">Try again</a>
                    </#if>
                </div>
            </div>

            <div class="row">
                <div class="col-4"></div>
                <div class="col-4" style="background-color: rgba(37, 33, 30, 0.7)">
                    <#if giveMail ??>
                        <form method="post" action="/remember-password" class="form-signin" style="margin: 40px;">
                            <div class="form-group">
                                <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp" placeholder="Enter email" required>
                            </div>
                            <button type="submit" class="btn btn-primary">check</button>
                        </form>

                    </#if>


                </div>
                <div class="col-4"></div>
            </div>

        </div>
    </div>

<#include "footer.ftlh">