<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="jumbotron">
        <div class="row">
            <div class="col-8">
                <#if SuccesUpdate ??>
                    <div class="alert alert-success" role="alert">
                        ${SuccesUpdate}
                    </div>
                </#if>
                <h1 class="display-4">Managers list</h1>
                <p class="lead">The number of Managers: <b>${numberRecords}</b></p>
            </div>
        </div>
        <a class="btn btn-primary" href="/new-manager">New Manager</a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Email</th>
                <th scope="col">Name</th>
                <th scope="col">Lastname</th>
            </tr>
            </thead>
            <tbody>
            <#list manager as m>
                <tr>
                    <td><h5>${m.managerEmail}</h5></td>
                    <td><h5>${m.managerName}</h5></td>
                    <td><h5>${m.managerLastname}</h5></td>
                    <td>
                        <a href="/edit-manager?id=${m.managerId}" class="btn btn-dark" type="button">Edit</a>
                    </td>
                    <td>
                        <a href="/delete-manager?id=${m.managerId}" type="button" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
</div>
<#include "manager-footer.ftlh">