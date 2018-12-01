<#include "base.ftl">

<#macro content>
    <tr>
        <td>
            <p>Hi ${forename},</p>
            <p>Welcome to Aviation Systems Group, your account has been successfully created. Please follow the link below to verify your email address.</p>
            <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary">
                <tbody>
                <tr>
                    <td align="left">
                        <table role="presentation" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <td> <a href=${link} target="_blank">Verify email</a> </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
            <p>Once your account has been activated you'll be sent to the login screen to get started.</p>
        </td>
    </tr>
</#macro>

<#macro footer>

</#macro>

<@shell title="Aviation Systems Group" preheader="Welcome to Aviation Systems Group, your account has been successfully created..."></@shell>