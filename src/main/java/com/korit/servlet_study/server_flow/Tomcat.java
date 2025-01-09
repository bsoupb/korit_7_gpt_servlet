package com.korit.servlet_study.server_flow;

public class Tomcat {
    public static void service(String url, String method) {
        Request req = new Request();
        Response resp = new Response();

        // TestServlet.doGet(req, resp);

        TestFilter.doFilter(req, resp);
    }
}
