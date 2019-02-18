<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <#if SuccesUpdate ??>
                        <div class="alert alert-success" role="alert">
                            ${SuccesUpdate} <a href="/courses-list">Show Course list</a>
                        </div>
                    </#if>
                    <#if FailedUpdate ??>
                        <div class="alert alert-danger" role="alert">
                            ${FailedUpdate}
                        </div>
                    </#if>
                    <h1 class="display-4">New Course</h1>
                </div>
            </div>
            <form method="post" action="/new-course" class="padding">
                <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input name="name" type="text" class="form-control" id="exampleInputName" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputDateStart">Date Start</label>
                    <input name="dateStart" type="text" class="form-control" id="exampleInputDateStart"
                           placeholder="Date Start" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputDateFinish">Datae Finish</label>
                    <input name="dateFinish" type="text" class="form-control" id="exampleInputDateFinish"
                           placeholder="Date FInish" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputCategory">Category</label>
                    <input name="category" type="text" class="form-control" id="exampleInputCategory"
                           placeholder="Category" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputHours">Hours</label>
                    <input name="hours" type="text" class="form-control" id="exampleInputHours"
                           placeholder="Hours" required>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>

        </div>
    </div>
</div>
<#include "manager-footer.ftlh">