<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.demos.tracking.GoogleAnalyticsTracking" %>

<html>
<head>
    <title>Tracking Demo</title>
</head>

<body>

<%
    // Place your Google Anlaytics tracking id here to test event tracking to your own account.
    GoogleAnalyticsTracking.setGoogleAnalyticsTrackingId("UA-XXXX-Y");
    GoogleAnalyticsTracking.trackEventToGoogleAnalytics("Error", "Payment", "Amount", "100");
%>
<p>Posted a tracking event to Google Analytics.</p>
</body>
</html>
