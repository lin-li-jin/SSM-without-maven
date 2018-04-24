client address:request.getRemoteAddr() <%=request.getRemoteAddr()%><br/>
client host: request.getRemoteHost()<%=request.getRemoteHost()%><br/>
authentication type: request.getAuthType()<%=request.getAuthType()%><br/>
remote user: request.getRemoteUser()<%=request.getRemoteUser()%>, if tomcatAuthentication="false"<br/>
protocol: request.getProtocol()<%=request.getProtocol()%><br/>
HTTP method:request.getMethod() <%=request.getMethod()%><br/>
URI: request.getRequestURI()<%=request.getRequestURI()%><br/>
HTTPS used: request.isSecure()<%=request.isSecure()%>, request.getScheme()<%=request.getScheme()%><br/>
query string:request.getQueryString() <%=request.getQueryString()%><br/>
request.getAttribute("REMOTE_PORT"):<%=request.getAttribute("REMOTE_PORT")%><br/>
request.getAttribute("SESSIONID"):<%=session.getAttribute("SESSIONID")%><br/>