<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.analytics.tracking.GoogleAnalyticsTracking" %>
<%@ page import="java.net.HttpURLConnection" %>

<html>
<head>
    <title>Tracking Demo</title>
</head>

<body>

<%
    // Place your Google Anlaytics tracking id here to test event tracking to your own account.
    GoogleAnalyticsTracking tracking = new GoogleAnalyticsTracking("UA-XXXX-Y");
    if (tracking.trackEventToGoogleAnalytics("Error", "Payment", "Amount", "100") ==
        HttpURLConnection.HTTP_OK) {
%>
<p>Posted a tracking event to Google Analytics.</p>
<%  } else { %>
<p>Unable to post a tracking event to Google Analytics.</p>
<%  } %>
</body>
</html>
