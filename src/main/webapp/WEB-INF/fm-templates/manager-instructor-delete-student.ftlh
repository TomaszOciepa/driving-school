<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <h1 class="display-4">Instructor delete Student</h1>
                    <#if SuccesUpdate ??>
                    <div class="alert alert-success" role="alert">
                        ${SuccesUpdate} <a href="/details-instructor?id=${instructor.instructorId}">Return</a>
                    </div>
                </div>
            </div>
            <#elseif FailedUpdate ??>
            <div class="alert alert-danger" role="alert">
                ${FailedUpdate} <a href="/details-instructor?id=${instructor.instructorId}">Try again</a>
            </div>
        </div>
    </div>
    <#else>
</div>
    </div>
    <form method="post" action="/delete-student-to-instructor"" class="padding">
        <div class="form-group">
            <label for="exampleInputName">Name</label>
            <input value="${instructor.instructorName}" name="name" type="text" class="form-control"
                   id="exampleInputName" placeholder="Name" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputLastname">Lastname</label>
            <input value="${instructor.instructorLastname}" name="lastname" type="text" class="form-control"
                   id="exampleInputLastname" disabled>
        </div>
        <div class="form-group">
            <label for="Select2">Choose a Student to delete</label>
            <select name="student" class="form-control" id="Select2">
                <option></option>
                <#list students as s>
                    <option value="${s.studentId}">${s.studentName}, ${s.studentLastname} <p>
                            Email: ${s.studentEmail}</p>
                    </option>
                </#list>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>

    </form>
</#if>

</div>
</div>
</div>
<#include "manager-footer.ftlh">