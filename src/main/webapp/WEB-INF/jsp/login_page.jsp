<%@include file="fragments/header.jspf" %>
<%@include file="fragments/navigation.jspf" %>
<div class="container">
    <form method="post">
        <input type="checkbox"/>
        <br>
        <p style="color: red">
            ${errorMessage}
        </p>
        <br>
        LOGIN: <input type="text" name="login">
        PASSWORD: <input type="password" name="password">
        <button type="submit" formmethod="post"> SUBMIT</button>

    </form>
</div>
<%@include file="fragments/footer.jspf" %>