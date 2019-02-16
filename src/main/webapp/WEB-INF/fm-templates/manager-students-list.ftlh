<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="jumbotron">
        <div class="row">
            <div class="col-8">
                <h1 class="display-4">Students list</h1>
                <p class="lead">The number of Students: <b>${numberRecords}</b></p>
            </div>
        </div>
        <a class="btn btn-primary" href="/new-student">New Student</a>
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
            <#list student as s>
                <tr>
                    <td><h5>${s.studentId}</h5></td>
                    <td><h5>${s.studentEmail}</h5></td>
                    <td><h5>${s.studentName}</h5></td>
                    <td><h5>${s.studentLastname}</h5></td>
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