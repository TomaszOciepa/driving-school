<#include "header.ftlh">

<div class="wrap">
    <div class="container">


        <div class="jumbotron jumbotron-fluid text-center"
             style="background-color: rgba(163, 177, 179, 0.7); margin-bottom: 200px">
            <div class="container">
                <h1 class="display-4">Driving School</h1>
                <p class="lead">This is an application for driving school management</p>
            </div>
        </div>

        <div class="row">
            <div class="col-4"></div>
            <div class="col-4" style="background-color: rgba(37, 33, 30, 0.7)">
                <#if giveMail ??>
                    <form method="post" action="/login" class="form-signin" style="margin: 40px;">
                        <div class="form-group">
                            <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                                   aria-describedby="emailHelp"
                                   placeholder="Enter email">
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