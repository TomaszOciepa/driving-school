<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Student Details</h1>
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
    <form method="get" action="/edit-student" class="padding">
        <div class="padding">
            <button type="submit" class="btn btn-primary">edit data</button>
        </div>

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
            <label for="exampleInputPesel">PESEL</label>
            <input value="${student.studentPESEL}" name="pesel" type="text" class="form-control"
                   id="exampleInputPesel" placeholder="Pesel" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputPhone">Phone</label>
            <input value="${student.studentPhone}" name="phone" type="text" class="form-control"
                   id="exampleInputPhone" placeholder="Phone" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputStreet">Street</label>
            <input value="${student.studentStreet}" name="street" type="text" class="form-control"
                   id="exampleInputStreet" placeholder="Street" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputCity">City</label>
            <input value="${student.studentCity}" name="city" type="text" class="form-control"
                   id="exampleInputCity" placeholder="City" disabled>
        </div>
        <div class="form-group">
            <label for="exampleInputDateRegistration">Date Registration</label>
            <input value="${student.studentDateRegistration}" name="registration" type="text"
                   class="form-control"
                   id="exampleInputDateRegistration" placeholder="date" disabled>
        </div>
    </form>
    <table class="table">
        <h1>List Instructors</h1>
        <a href="/add-instructor-to-student" class="btn btn-primary">Add Instructor</a>
        <a href="/delete-instructor-to-student" class="btn btn-danger">Delete Instructor</a>
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
        </tr>
        </thead>
        <tbody>
        <#list instructor as i>
            <tr>
                <td><h5>${i.instructorName}</h5></td>
                <td><h5>${i.instructorLastname}</h5></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <table class="table">
        <h1>List courses</h1>
        <a href="/add-course-to-student" class="btn btn-primary">Add course</a>
        <a href="/delete-course-to-student" class="btn btn-danger">Delete course</a>
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Hours</th>
            <th scope="col">Data Start</th>
            <th scope="col">Data Finish</th>
        </tr>
        </thead>
        <tbody>
        <#list course as c>
            <tr>
                <td><h5>${c.courseName}</h5></td>
                <td><h5>${c.courseCatrgoryDriveLicense}</h5></td>
                <td><h5>${c.courseNumberHours}</h5></td>
                <td><h5>${c.courseDataStart}</h5></td>
                <td><h5>${c.courseDataFinish}</h5></td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

</div>
</div>
</div>
<#include "manager-footer.ftlh">