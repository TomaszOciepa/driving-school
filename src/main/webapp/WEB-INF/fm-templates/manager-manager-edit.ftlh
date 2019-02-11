<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <#if SuccesUpdate ??>
                    <div class="alert alert-success" role="alert">
                        ${SuccesUpdate}</a>
                    </div>
                </#if>
                <#if FailedUpdate ??>
                    <div class="alert alert-danger" role="alert">
                        ${FailedUpdate}
                    </div>
                </#if>
                <div class="col-8">
                    <h1 class="display-4">Manager Edit</h1>
                </div>
            </div>

            <form method="post" action="/edit-manager" class="padding">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input value="${manager.managerEmail}" name="email" type="email" class="form-control"
                           id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input value="${manager.managerName}" name="name" type="text" class="form-control"
                           id="exampleInputName" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputLastName">Lastname</label>
                    <input value="${manager.managerLastname}" name="lastname" type="text" class="form-control"
                           id="exampleInputLastName" placeholder="Lastname" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputDateRegistration">Date Registration</label>
                    <input value="${manager.managerDateRegistration}" name="registration" type="text"
                           class="form-control"
                           id="exampleInputDateRegistration" placeholder="date" disabled>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
<#include "manager-footer.ftlh">