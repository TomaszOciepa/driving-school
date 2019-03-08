<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Student delete Instructor</h1>
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
    <form method="post" action="/delete-instructor-to-student" class="padding">
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

        <div class="form-group">
            <label for="Select2">Choose a Instructor to delete</label>
            <select name="instructor" class="form-control" id="Select2">
                <option></option>
                <#list instructor as i>
                    <option value="${i.instructorId}">${i.instructorName} ${i.instructorLastname}</option>
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