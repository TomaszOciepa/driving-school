<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <#if SuccesUpdate ??>
                        <div class="alert alert-success" role="alert">
                            ${SuccesUpdate}
                        </div>
                    </#if>
                    <#if FailedUpdate ??>
                        <div class="alert alert-danger" role="alert">
                            ${FailedUpdate}
                        </div>
                    </#if>
                    <a href="/change-password" type="button" class="btn btn-danger">Change password</a>
                    <h1 class="display-4">Your Account</h1>
                </div>
            </div>

            <form method="doGet" action="/my-account-edit" class="padding">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input value="${user.managerEmail}" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" disabled>
                </div>
                <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input value="${user.managerName}" type="text" class="form-control" id="exampleInputName" placeholder="Name" disabled>
                </div>
                <div class="form-group">
                    <label for="exampleInputLastName">Lastname</label>
                    <input value="${user.managerLastname}" type="text" class="form-control" id="exampleInputLastName" placeholder="Lastname" disabled>
                </div>
                <button type="submit" class="btn btn-primary">Edit</button>
            </form>
        </div>
    </div>
</div>
<#include "manager-footer.ftlh">