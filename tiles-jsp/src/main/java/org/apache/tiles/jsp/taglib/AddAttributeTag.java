package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.autotag.core.runtime.ModelBody;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.AddAttributeModel;

import java.io.IOException;

public class AddAttributeTag extends SimpleTagSupport {

    private final AddAttributeModel model = new AddAttributeModel();
    private Object value;
    private String expression;
    private String role;
    private String type;

    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

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
        model.execute(value, expression, role, type, request, modelBody);
    }
}
