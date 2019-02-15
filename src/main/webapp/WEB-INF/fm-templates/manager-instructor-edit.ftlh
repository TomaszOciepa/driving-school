<#include "manager-header.ftlh">
    <div class="wrap-admin">
    <div class="container">
    <div class="jumbotron">
    <div class="row">
    <div class="col-8">
<h1 class="display-4">Instructor Edit</h1>
<#if SuccesUpdate ??>
    <div class="alert alert-success" role="alert">
        ${SuccesUpdate} <a href="/edit-instructor?id=${instructor.instructorId}">Return</a>
    </div>
    </div>
    </div>
<#elseif FailedUpdate ??>
    <div class="alert alert-danger" role="alert">
        ${FailedUpdate} <a href="/edit-instructor?id=${instructor.instructorId}">Try again</a>
    </div>
    </div>
    </div>
<#else>
    </div>
    </div>
    <form method="post" action="/edit-instructor" class="padding">
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input value="${instructor.instructorEmail}" name="email" type="email" class="form-control"
                   id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required>
        </div>
        <div class="form-group">
            <label for="exampleInputName">Name</label>
            <input value="${instructor.instructorName}" name="name" type="text" class="form-control"
                   id="exampleInputName" placeholder="Name" required>
        </div>
        <div class="form-group">
            <label for="exampleInputLastName">Lastname</label>
            <input value="${instructor.instructorLastname}" name="lastname" type="text" class="form-control"
                   id="exampleInputLastName" placeholder="Lastname" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPhone">Phone</label>
            <input value="${instructor.instructorPhone}" name="phone" type="text" class="form-control"
                   id="exampleInputPhone" placeholder="Phone" required>
        </div>
        <div class="form-group">
            <label for="exampleInputStreet">Street</label>
            <input value="${instructor.instructorStreet}" name="street" type="text" class="form-control"
                   id="exampleInputStreet" placeholder="Street" required>
        </div>
        <div class="form-group">
            <label for="exampleInputCity">City</label>
            <input value="${instructor.instructorCity}" name="city" type="text" class="form-control"
                   id="exampleInputCity" placeholder="City" required>
        </div>
        <div class="form-group">
            <label for="exampleInputDateRegistration">Date Registration</label>
            <input value="${instructor.instructorDateRegistration}" name="registration" type="text"
                   class="form-control"
                   id="exampleInputDateRegistration" placeholder="date" disabled>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</#if>

</div>
</div>
</div>
<#include "manager-footer.ftlh">