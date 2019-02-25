<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Course Edit</h1>
<#if SuccesUpdate ??>
    <div class="alert alert-success" role="alert">
        ${SuccesUpdate} <a href="/edit-course?id=${course.courseId}">Return</a>
    </div>
    </div>
    </div>
<#elseif FailedUpdate ??>
    <div class="alert alert-danger" role="alert">
        ${FailedUpdate} <a href="/edit-course?id=${course.courseId}">Try again</a>
    </div>
    </div>
    </div>
<#else>
    </div>
    </div>
    <form method="post" action="/edit-course" class="padding">
        <div class="form-group">
            <label for="exampleInputName">Name</label>
            <input value="${course.courseName}" name="name" type="text" class="form-control" id="exampleInputName" placeholder="Name" required>
        </div>
        <div class="form-group">
            <label for="exampleInputDateStart">Date Start</label>
            <input value="${course.courseDataStart}" name="dateStart" type="text" class="form-control" id="exampleInputDateStart"
                   placeholder="Date Start" required>
        </div>
        <div class="form-group">
            <label for="exampleInputDateFinish">Datae Finish</label>
            <input value="${course.courseDataFinish}" name="dateFinish" type="text" class="form-control" id="exampleInputDateFinish"
                   placeholder="Date FInish" required>
        </div>
        <div class="form-group">
            <label for="exampleInputCategory">Category</label>
            <input value="${course.courseCatrgoryDriveLicense}" name="category" type="text" class="form-control" id="exampleInputCategory"
                   placeholder="Category" required>
        </div>
        <div class="form-group">
            <label for="exampleInputHours">Hours</label>
            <input value="${course.courseNumberHours}" name="hours" type="text" class="form-control" id="exampleInputHours"
                   placeholder="Hours" required>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>

    <table class="table">
        <h1>Students list</h1>
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
        </tr>
        </thead>
        <tbody>
        <#list students as s>
            <tr>
                <td><h5>${s.studentName}</h5></td>
                <td><h5>${s.studentLastname}</h5></td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

</div>
</div>
</div>
<#include "manager-footer.ftlh">