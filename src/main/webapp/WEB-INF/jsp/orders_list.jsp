<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    <h1 style="color:  darkgray">Orders for: ${login}</h1>
    <div>
        <a class="btn btn-success" href="/add_order">Add new order</a>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
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

<%@include file="common/footer.jspf"%>