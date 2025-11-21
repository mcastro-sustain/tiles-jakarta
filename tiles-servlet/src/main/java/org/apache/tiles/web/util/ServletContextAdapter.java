/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tiles.web.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

/**
 * Adapts a servlet config and a servlet context to become a unique servlet
 * context.
 *
 * @version $Rev$ $Date$
 */
@SuppressWarnings("deprecation")
public class ServletContextAdapter implements ServletContext {

    /**
     * The root context to use.
     */
    private ServletContext rootContext;

    /**
     * The union of init parameters of {@link ServletConfig} and
     * {@link ServletContext}.
     */
    private Hashtable<String, String> initParameters;


    /**
     * Constructor.
     *
     * @param config The servlet configuration object.
     */
    @SuppressWarnings("unchecked")
    public ServletContextAdapter(ServletConfig config) {
        this.rootContext = config.getServletContext();
        initParameters = new Hashtable<String, String>();
        Enumeration<String> enumeration = rootContext
                .getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            initParameters.put(paramName, rootContext
                    .getInitParameter(paramName));
        }
        enumeration = config.getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            initParameters.put(paramName, config.getInitParameter(paramName));
        }
    }

    /** {@inheritDoc} */
    public ServletContext getContext(String string) {
        return rootContext.getContext(string);
    }

    /** {@inheritDoc} */
    public int getMajorVersion() {
        return rootContext.getMajorVersion();
    }

    /** {@inheritDoc} */
    public int getMinorVersion() {
        return rootContext.getMinorVersion();
    }

    /** {@inheritDoc} */
    public String getMimeType(String string) {
        return rootContext.getMimeType(string);
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes" })
    public Set getResourcePaths(String string) {
        return rootContext.getResourcePaths(string);
    }

    /** {@inheritDoc} */
    public URL getResource(String string) throws MalformedURLException {
        return rootContext.getResource(string);
    }

    /** {@inheritDoc} */
    public InputStream getResourceAsStream(String string) {
        return rootContext.getResourceAsStream(string);
    }

    /** {@inheritDoc} */
    public RequestDispatcher getRequestDispatcher(String string) {
        return rootContext.getRequestDispatcher(string);
    }

    /** {@inheritDoc} */
    public RequestDispatcher getNamedDispatcher(String string) {
        return rootContext.getNamedDispatcher(string);
    }

    /** {@inheritDoc} */
    public void log(String string) {
        rootContext.log(string);
    }

    /** {@inheritDoc} */
    public void log(String string, Throwable throwable) {
        rootContext.log(string, throwable);
    }

    /** {@inheritDoc} */
    public String getRealPath(String string) {
        return rootContext.getRealPath(string);
    }

    /** {@inheritDoc} */
    public String getServerInfo() {
        return rootContext.getServerInfo();
    }

    /** {@inheritDoc} */
    public String getInitParameter(String string) {
        return initParameters.get(string);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    public Enumeration getInitParameterNames() {
        return initParameters.keys();
    }

    /** {@inheritDoc} */
    public Object getAttribute(String string) {
        return rootContext.getAttribute(string);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    public Enumeration getAttributeNames() {
        return rootContext.getAttributeNames();
    }

    /** {@inheritDoc} */
    public void setAttribute(String string, Object object) {
        rootContext.setAttribute(string, object);
    }

    /** {@inheritDoc} */
    public void removeAttribute(String string) {
        rootContext.removeAttribute(string);
    }

    /** {@inheritDoc} */
    public String getServletContextName() {
        return rootContext.getServletContextName();
    }

    /** {@inheritDoc} */
    public String getContextPath() {
        return rootContext.getContextPath();
    }

    /** {@inheritDoc} */
    public int getEffectiveMajorVersion() {
        return rootContext.getEffectiveMajorVersion();
    }

    /** {@inheritDoc} */
    public int getEffectiveMinorVersion() {
        return rootContext.getEffectiveMinorVersion();
    }

    /** {@inheritDoc} */
    public boolean setInitParameter(String name, String value) {
        return rootContext.setInitParameter(name, value);
    }

    /** {@inheritDoc} */
    public String getVirtualServerName() {
        return rootContext.getVirtualServerName();
    }

    /** {@inheritDoc} */
    public int getSessionTimeout() {
        return rootContext.getSessionTimeout();
    }

    /** {@inheritDoc} */
    public void setSessionTimeout(int sessionTimeout) {
        rootContext.setSessionTimeout(sessionTimeout);
    }

    /** {@inheritDoc} */
    public String getRequestCharacterEncoding() {
        return rootContext.getRequestCharacterEncoding();
    }

    /** {@inheritDoc} */
    public void setRequestCharacterEncoding(String encoding) {
        rootContext.setRequestCharacterEncoding(encoding);
    }

    /** {@inheritDoc} */
    public String getResponseCharacterEncoding() {
        return rootContext.getResponseCharacterEncoding();
    }

    /** {@inheritDoc} */
    public void setResponseCharacterEncoding(String encoding) {
        rootContext.setResponseCharacterEncoding(encoding);
    }

    /** {@inheritDoc} */
    public void declareRoles(String... roleNames) {
        rootContext.declareRoles(roleNames);
    }

    /** {@inheritDoc} */
    public ClassLoader getClassLoader() {
        return rootContext.getClassLoader();
    }

    /** {@inheritDoc} */
    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return rootContext.addServlet(servletName, className);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        return rootContext.addServlet(servletName, servlet);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        return rootContext.addServlet(servletName, servletClass);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return rootContext.addJspFile(servletName, jspFile);
    }

    /** {@inheritDoc} */
    public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
        return rootContext.createServlet(clazz);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.ServletRegistration getServletRegistration(String servletName) {
        return rootContext.getServletRegistration(servletName);
    }

    /** {@inheritDoc} */
    public java.util.Map<String, ? extends jakarta.servlet.ServletRegistration> getServletRegistrations() {
        return rootContext.getServletRegistrations();
    }

    /** {@inheritDoc} */
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return rootContext.addFilter(filterName, className);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, jakarta.servlet.Filter filter) {
        return rootContext.addFilter(filterName, filter);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends jakarta.servlet.Filter> filterClass) {
        return rootContext.addFilter(filterName, filterClass);
    }

    /** {@inheritDoc} */
    public <T extends jakarta.servlet.Filter> T createFilter(Class<T> clazz) throws ServletException {
        return rootContext.createFilter(clazz);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.FilterRegistration getFilterRegistration(String filterName) {
        return rootContext.getFilterRegistration(filterName);
    }

    /** {@inheritDoc} */
    public java.util.Map<String, ? extends jakarta.servlet.FilterRegistration> getFilterRegistrations() {
        return rootContext.getFilterRegistrations();
    }

    /** {@inheritDoc} */
    public jakarta.servlet.SessionCookieConfig getSessionCookieConfig() {
        return rootContext.getSessionCookieConfig();
    }

    /** {@inheritDoc} */
    public void setSessionTrackingModes(java.util.Set<jakarta.servlet.SessionTrackingMode> sessionTrackingModes) {
        rootContext.setSessionTrackingModes(sessionTrackingModes);
    }

    /** {@inheritDoc} */
    public java.util.Set<jakarta.servlet.SessionTrackingMode> getDefaultSessionTrackingModes() {
        return rootContext.getDefaultSessionTrackingModes();
    }

    /** {@inheritDoc} */
    public java.util.Set<jakarta.servlet.SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return rootContext.getEffectiveSessionTrackingModes();
    }

    /** {@inheritDoc} */
    public void addListener(String className) {
        rootContext.addListener(className);
    }

    /** {@inheritDoc} */
    public <T extends java.util.EventListener> void addListener(T t) {
        rootContext.addListener(t);
    }

    /** {@inheritDoc} */
    public void addListener(Class<? extends java.util.EventListener> listenerClass) {
        rootContext.addListener(listenerClass);
    }

    /** {@inheritDoc} */
    public <T extends java.util.EventListener> T createListener(Class<T> clazz) throws ServletException {
        return rootContext.createListener(clazz);
    }

    /** {@inheritDoc} */
    public jakarta.servlet.descriptor.JspConfigDescriptor getJspConfigDescriptor() {
        return rootContext.getJspConfigDescriptor();
    }
}
