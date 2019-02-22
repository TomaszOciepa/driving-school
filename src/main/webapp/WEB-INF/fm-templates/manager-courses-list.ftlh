<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="jumbotron">
        <div class="row">
            <div class="col-8">
                <h1 class="display-4">Courses list</h1>
                <p class="lead">The number of Courses: <b>${numberRecords}</b></p>
            </div>
        </div>
        <a class="btn btn-primary" href="/new-course">New Course</a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Data Start</th>
                <th scope="col">Data Finish</th>
                <th scope="col">Category</th>
            </tr>
            </thead>
            <tbody>
            <#list course as c>
                <tr>
                    <td><h5>${c.courseId}</h5></td>
                    <td><h5>${c.courseName}</h5></td>
                    <td><h5>${c.courseDataStart}</h5></td>
                    <td><h5>${c.courseDataFinish}</h5></td>
                    <td><h5>${c.courseCatrgoryDriveLicense}</h5></td>
                    <td>
                        <a href="/edit-course?id=${c.courseId}" class="btn btn-dark">Edit</a>
                    </td>
                    <td>
                        <a href="/delete-course?id=${c.courseId}" class="btn btn-dark">Delete</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
<#include "manager-footer.ftlh">