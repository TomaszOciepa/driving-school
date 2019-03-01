<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Course Details</h1>
<#if SuccesUpdate ??>
    <div class="alert alert-success" role="alert">
        ${SuccesUpdate} <a href="/edit-student?id=${student.studentId}">Return</a>
    </div>
    </div>
    </div>
<#elseif FailedUpdate ??>
    <div class="alert alert-danger" role="alert">
        ${FailedUpdate} <a href="/edit-student?id=${student.studentId}">Try again</a>
    </div>
    </div>
    </div>
<#else>
    </div>
    </div>
    <form method="get" action="/edit-course" class="padding">
        <div class="padding">
            <button type="submit" class="btn btn-primary">edit data</button>
        </div>

        <div class="form-group">
            <label for="exampleInputName">Name</label>
            <input value="${course.courseName}" name="name" type="text" class="form-control"
                   id="exampleInputName" placeholder="Name" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputLastName">Category</label>
            <input value="${course.courseCatrgoryDriveLicense}" name="category" type="text" class="form-control"
                   id="exampleInputLastName" placeholder="Lastname" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputPesel">Start Data</label>
            <input value="${course.courseDataStart}" name="dataStart" type="text" class="form-control"
                   id="exampleInputPesel" placeholder="Pesel" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputPhone">Finish Data</label>
            <input value="${course.courseDataFinish}" name="dataFinish" type="text" class="form-control"
                   id="exampleInputPhone" placeholder="Phone" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputStreet">Hours</label>
            <input value="${course.courseNumberHours}" name="hours" type="text" class="form-control"
                   id="exampleInputStreet" placeholder="Street" disabled>
        </div>
    </form>
    <table class="table">
        <h1>Students list</h1>
        <a href="/add-student-to-course" class="btn btn-primary">Add Student</a>
        <a href="/delete-student-to-course" class="btn btn-danger">Delete Student</a>
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