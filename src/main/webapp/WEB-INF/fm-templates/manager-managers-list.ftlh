<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="jumbotron">
        <div class="row">
            <div class="col-8">
                <h1 class="display-4">Managers list</h1>
                <p class="lead">The number of Managers: <b>${numberRecords}</b></p>
            </div>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Email</th>
                <th scope="col">Name</th>
                <th scope="col">Lastname</th>
            </tr>
            </thead>
            <tbody>
            <#list manager as m>
                <tr>
                    <td><h5>${m.managerId}</h5></td>
                    <td><h5>${m.managerEmail}</h5></td>
                    <td><h5>${m.managerName}</h5></td>
                    <td><h5>${m.managerLastname}</h5></td>
                    <td>
                        <a href="/" class="btn btn-dark">Edit</a>
                    </td>
                    <td>
                        <a href="/" class="btn btn-dark">Delete</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
<#include "manager-footer.ftlh">