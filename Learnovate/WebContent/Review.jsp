<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<style type="text/css">
    table { border: 0; }
    table td { padding: 5px; }
</style>
</head>
<body>
<div align="center">
    <h1>Please Review Before Paying</h1>
    <form action="ExecutePayment" method="post">
    <table>
        <tr>
            <td colspan="2"><b>Transaction Details:</b></td>
            <td>
                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>${transaction.description}</td>
        </tr>
        <tr>
            <td>Subtotal:</td>
            <td>${transaction.amount.details.subtotal} EUR</td>
        </tr>
        <tr>
            <td>Shipping:</td>
            <td>${transaction.amount.details.shipping} EUR</td>
        </tr>
        <tr>
            <td>Tax:</td>
            <td>${transaction.amount.details.tax} EUR</td>
        </tr>
        <tr>
            <td>Total:</td>
            <td>${transaction.amount.total} Euro</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Payer Information:</b></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>${payer.firstName}</td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td>${payer.lastName}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>${payer.email}</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Paga Ora" />
            </td>
        </tr>    
    </table>
    </form>
</div>
</body>
</html>