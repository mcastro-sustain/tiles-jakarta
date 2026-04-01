package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.autotag.core.runtime.ModelBody;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.AddListAttributeModel;

import java.io.IOException;

public class AddListAttributeTag extends SimpleTagSupport {

    private final AddListAttributeModel model = new AddListAttributeModel();
    private String role;

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

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
        model.execute(role, request, modelBody);
    }
}
