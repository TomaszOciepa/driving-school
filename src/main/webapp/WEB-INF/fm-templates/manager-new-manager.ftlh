<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <#if SuccesUpdate ??>
                        <div class="alert alert-success" role="alert">
                            ${SuccesUpdate} <a href="/managers-list">Show Managers list</a>
                        </div>
                    </#if>
                    <#if FailedUpdate ??>
                        <div class="alert alert-danger" role="alert">
                            ${FailedUpdate}
                        </div>
                    </#if>
                    <h1 class="display-4">New Manager</h1>
                </div>
            </div>
            <form method="post" action="/new-manager" class="padding">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input name="name" type="text" class="form-control" id="exampleInputName" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputLastName">Lastname</label>
                    <input name="lastname" type="text" class="form-control" id="exampleInputLastName"
                           placeholder="Lastname" required>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>

        </div>
    </div>
</div>
<#include "manager-footer.ftlh">