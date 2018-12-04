<#include "base.ftl">

<#macro content>
    <tr>
        <td>
            <p>Dear Aviation Systems Group customer, your payment has been accepted. Please follow the link below to return to the dashboard.</p>
            <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary">
                <tbody>
                <tr>
                    <td align="left">
                        <table role="presentation"activation.ftl border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <td> <a href="${link}" target="_blank">Dashboard</a> </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
            <p>Return to the dashboard to check your next steps</p>
        </td>
    </tr>
</#macro>

<#macro footer>

</#macro>

<@shell title="Aviation Systems Group" preheader="Welcome to Aviation Systems Group, your payment has been accepted"></@shell>