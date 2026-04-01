package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.autotag.core.runtime.ModelBody;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.PutListAttributeModel;

import java.io.IOException;

public class PutListAttributeTag extends SimpleTagSupport {

    private final PutListAttributeModel model = new PutListAttributeModel();
    private String name;
    private String role;
    private boolean inherit;
    private boolean cascade;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isInherit() { return inherit; }
    public void setInherit(boolean inherit) { this.inherit = inherit; }
    public boolean isCascade() { return cascade; }
    public void setCascade(boolean cascade) { this.cascade = cascade; }

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
        model.execute(name, role, inherit, cascade, request, modelBody);
    }
}
