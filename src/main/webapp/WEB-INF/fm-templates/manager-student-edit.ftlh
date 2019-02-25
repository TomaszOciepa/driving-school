<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Student Edit</h1>
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
    <form method="post" action="/edit-student" class="padding">
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input value="${student.studentEmail}" name="email" type="email" class="form-control"
                   id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required>
        </div>
        <div class="form-group">
            <label for="exampleInputName">Name</label>
            <input value="${student.studentName}" name="name" type="text" class="form-control"
                   id="exampleInputName" placeholder="Name" required>
        </div>
        <div class="form-group">
            <label for="exampleInputLastName">Lastname</label>
            <input value="${student.studentLastname}" name="lastname" type="text" class="form-control"
                   id="exampleInputLastName" placeholder="Lastname" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPesel">PESEL</label>
            <input value="${student.studentPESEL}" name="pesel" type="text" class="form-control"
                   id="exampleInputPesel" placeholder="Pesel" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPhone">Phone</label>
            <input value="${student.studentPhone}" name="phone" type="text" class="form-control"
                   id="exampleInputPhone" placeholder="Phone" required>
        </div>
        <div class="form-group">
            <label for="exampleInputStreet">Street</label>
            <input value="${student.studentStreet}" name="street" type="text" class="form-control"
                   id="exampleInputStreet" placeholder="Street" required>
        </div>
        <div class="form-group">
            <label for="exampleInputCity">City</label>
            <input value="${student.studentCity}" name="city" type="text" class="form-control"
                   id="exampleInputCity" placeholder="City" required>
        </div>
        <div class="form-group">
            <label for="exampleInputDateRegistration">Date Registration</label>
            <input value="${student.studentDateRegistration}" name="registration" type="text"
                   class="form-control"
                   id="exampleInputDateRegistration" placeholder="date" disabled>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
    <table class="table">
        <h1>Lista kursów</h1>
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