<%@include file="fragments/header.jspf" %>
<%@include file="fragments/navigation.jspf" %>

<div class="container">
    <%--<div class="dropdown" href="/ada">--%>
        <%--<form:form method="post">--%>
            <%--<fieldset>--%>
                    <%--&lt;%&ndash;to be implemented in future&ndash;%&gt;--%>
                <%--<p>--%>
                    <%--<label>Select Waiter (does not work, yet):</label>--%>
                    <%--<select id="waiter">--%>
                        <%--<option value="all">all</option>--%>
                        <%--<option value="1">tom</option>--%>
                        <%--<option value="2">two</option>--%>
                        <%--<option value="3">three</option>--%>
                        <%--<option value="4">four</option>--%>

                    <%--</select>--%>
                <%--</p>--%>

            <%--</fieldset>--%>
            <%--<button type="submit" class="btn btn-info">Filter</button>--%>
        <%--</form:form>--%>
    <%--</div>--%>
    <h1 style="color:  darkgray">Orders for: ${login}</h1>
    <div class="container">
        <a class="btn btn-success" href="/add_order">Add new order</a>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Waiter</th>
            <th>Date</th>
            <th>Pizza ingredients</th>
            <th>Waiter notes</th>
            <th>Price</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${orders_list}" var="order">
            <tr>
                <td>${order.waiterName}</td>
                <td> ${order.date}</td>
                    <%--<fmt:parseDate value="${order.date}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />--%>
                    <%--<fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${ parsedDateTime }" />--%>
                <td>${order.components}</td>
                <td>${order.notes}</td>
                <td>${order.price}</td>
                <td><a type="button" class="btn btn-info" href="/update_order?id=${order.id}">Update</a></td>
                <td><a type="button" class="btn btn-warning" href="/delete_order?id=${order.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="fragments/footer.jspf" %>