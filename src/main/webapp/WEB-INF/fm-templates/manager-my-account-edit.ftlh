<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <h1 class="display-4">Your Account</h1>
                </div>
            </div>

            <form method="post" action="/my-account-edit" class="padding">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input value="${user.managerEmail}" name="email" type="email" class="form-control"
                           id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input value="${user.managerPassword}" name="password" type="text" class="form-control"
                           id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input value="${user.managerName}" name="name" type="text" class="form-control"
                           id="exampleInputName" placeholder="Name">
                </div>
                <div class="form-group">
                    <label for="exampleInputLastName">Lastname</label>
                    <input value="${user.managerLastname}" name="lastname" type="text" class="form-control"
                           id="exampleInputLastName" placeholder="Lastname">
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
<#include "manager-footer.ftlh">