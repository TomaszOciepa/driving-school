<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Student add course</h1>
<#if SuccesUpdate ??>
    <div class="alert alert-success" role="alert">
        ${SuccesUpdate} <a href="/details-student?id=${student.studentId}">Return</a>
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
    <form method="post" action="/add-course-to-student" class="padding">
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input value="${student.studentEmail}" name="email" type="email" class="form-control"
                   id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputName">Name</label>
            <input value="${student.studentName}" name="name" type="text" class="form-control"
                   id="exampleInputName" placeholder="Name" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputLastName">Lastname</label>
            <input value="${student.studentLastname}" name="lastname" type="text" class="form-control"
                   id="exampleInputLastName" placeholder="Lastname" disabled>
        </div>
        <#if info ??>
            <div class="alert alert-danger" role="alert">
                ${info}. <a href="/details-student?id=${student.studentId}">Return</a>
            </div>
        <#else>
            <div class="form-group">
                <label for="Select2">Wybierz Kurs</label>
                <select name="course" class="form-control" id="Select2">
                    <option></option>
                    <#list courses as c>
                        <option value="${c.courseId}">${c.courseName}, <p>category: ${c.courseCatrgoryDriveLicense}</p></option>
                    </#list>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
       </#if>

    </form>
</#if>

</div>
</div>
</div>
<#include "manager-footer.ftlh">