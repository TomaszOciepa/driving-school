<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <h1 class="display-4">Your Account</h1>
                    <p class="lead">Edit your account...</p>
                </div>
            </div>

            <form method="doGet" action="/my-account-edit">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input value="${user.managerEmail}" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" disabled>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input value="${user.managerEmail}" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" disabled>
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