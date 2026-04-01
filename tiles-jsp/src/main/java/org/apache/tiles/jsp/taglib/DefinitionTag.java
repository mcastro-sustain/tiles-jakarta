package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.autotag.core.runtime.ModelBody;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.DefinitionModel;

import java.io.IOException;

public class DefinitionTag extends SimpleTagSupport {

    private final DefinitionModel model = new DefinitionModel();
    private String name;
    private String template;
    private String role;
    private String extendsParam;
    private String preparer;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getExtends() { return extendsParam; }
    public void setExtends(String extendsParam) { this.extendsParam = extendsParam; }
    public String getPreparer() { return preparer; }
    public void setPreparer(String preparer) { this.preparer = preparer; }

    @Override
    public void doTag() throws JspException, IOException {
        JspAutotagRuntime runtime = new JspAutotagRuntime();
        if (runtime instanceof SimpleTagSupport) {
            SimpleTagSupport sts = (SimpleTagSupport) runtime;
            sts.setJspContext(getJspContext());
            sts.setJspBody(getJspBody());
            sts.setParent(getParent());
            sts.doTag();
        }
        Request request = (Request) runtime.createRequest();
        ModelBody modelBody = runtime.createModelBody();
        model.execute(name, template, role, extendsParam, preparer, request, modelBody);
    }
}
