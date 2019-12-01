<%@include file="fragments/header.jspf" %>
<%@include file="fragments/navigation.jspf" %>
<div class="container">
    <form method="post">
        <input itemType="checkbox"/>
        <br>
        <p style="color: red">
            ${errorMessage}
        </p>
        <br>
        LOGIN: <input itemType="text" name="login">
        PASSWORD: <input itemType="password" name="password">
        <button itemType="submit" formmethod="post"> SUBMIT</button>

    </form>
</div>
<%@include file="fragments/footer.jspf" %>