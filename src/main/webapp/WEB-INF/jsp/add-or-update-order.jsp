<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">


    <form:form method="POST" modelAttribute="restaurantOrder">
        <form:hidden path="id"/>
        <fieldset class="form-group">
            <form:label path="components">Components:</form:label>
            <form:input path="components" type="text" class="form-control" required="required"/>

            <form:errors path="components" class="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="notes">Notes:</form:label>
            <form:input path="notes" type="text" cssClass="form-control"/>
        </fieldset>

        <button type="submit" class="btn btn-success">Add</button>
    </form:form>

</div>
<%@include file="common/footer.jspf"%>
