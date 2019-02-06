<#include "manager-header.ftlh">
<div class="wrap-admin">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-8">
                    <#if SuccesUpdate ??>
                        <div class="alert alert-success" role="alert">
                            ${SuccesUpdate}
                        </div>
                    </#if>
                    <#if FailedUpdate ??>
                        <div class="alert alert-danger" role="alert">
                            ${FailedUpdate}
                        </div>
                    </#if>
                    <h1 class="display-4">Change password</h1>
                </div>
            </div>

            <form method="post" action="/manager-new-password" class="padding">
                <div class="form-group">
                    <label for="exampleInputPassword">Your current password</label>
                    <input name="password" type="password" class="form-control" id="exampleInputPassword" required>
                </div>
                <button type="submit" class="btn btn-primary">Check</button>
            </form>
        </div>
    </div>
</div>
<#include "manager-footer.ftlh">