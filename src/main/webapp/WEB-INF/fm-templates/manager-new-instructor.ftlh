<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <#if SuccesUpdate ??>
                        <div class="alert alert-success" role="alert">
                            ${SuccesUpdate} <a href="/instructors-list">Show Instructors list</a>
                        </div>
                    </#if>
                    <#if FailedUpdate ??>
                        <div class="alert alert-danger" role="alert">
                            ${FailedUpdate}
                        </div>
                    </#if>
                    <h1 class="display-4">New Instructor</h1>
                </div>
            </div>
            <form method="post" action="/new-instructor" class="padding">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input name="name" type="text" class="form-control" id="exampleInputName" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputLastName">Lastname</label>
                    <input name="lastname" type="text" class="form-control" id="exampleInputLastName"
                           placeholder="Lastname" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputPhone">Phone</label>
                    <input name="phone" type="text" class="form-control" id="exampleInputPhone"
                           placeholder="Phone" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputStreet">Street</label>
                    <input name="street" type="text" class="form-control" id="exampleInputStreet"
                           placeholder="Street" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputCity">City</label>
                    <input name="city" type="text" class="form-control" id="exampleInputCity"
                           placeholder="City" required>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>

        </div>
    </div>
</div>
<#include "manager-footer.ftlh">