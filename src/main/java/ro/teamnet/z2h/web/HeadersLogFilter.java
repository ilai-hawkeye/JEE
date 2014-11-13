/**
 * HeadersLogFilter.java
 *
 * Copyright (c) 2014 Teamnet. All Rights Reserved.
 *
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 **/

package ro.teamnet.z2h.web;

import ro.teamnet.z2h.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HeadersLogFilter implements Filter {


    private String handleRequest(HttpServletRequest req){

        //        String response = new String("Hello" + [req.get()] [req.LastName] + "! Enjoy Zero To Hero!!!");
        return "Hello " + req.getParameter("firstName") + " " + req.getParameter("lastName");
        // de printat header     request.header name....=> enumeration
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * This filter  logs current request headers to filesystem
     * @param request - Client request
     * @param response - Client response
     * @param chain - Filters chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        resp.setContentType("text/html");
//        resp.getWriter().println(handleRequest(req));
        //Map<String, String> map = new HashMap<String, String>();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            LogFileWriter.logHeader(key,value);
        }

       //TODO completeaza cu cod astfel incat sa poti loga headerele de pe request intr-un fisier.
       // Clasa care va scrie intr-un fisier de log este LogFileWriter metoda   logHeader

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
