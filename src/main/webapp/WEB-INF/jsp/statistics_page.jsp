<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

<div class="container">
    <p> <a href="/statistics_today">Statistics for today</a></p>
    <p>  <a href="/statistics_this_month">Statistics for this month</a></p>
    <p> <a href="/statistics_this_year">Statistics for this year</a></p>
    <p> <a href="/statistics_all_time">Statistics for all time</a></p>

</div>

<div class="container">
    <h1>
        <p> Total orders: ${totalOrders} </p>
        <p> Total revenue: ${totalRevenue}</p>

    </h1>
</div>

<%@include file="common/footer.jspf" %>