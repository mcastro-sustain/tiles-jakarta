package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.Attribute;
import org.apache.tiles.autotag.core.runtime.ModelBody;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.GetAsStringModel;

import java.io.IOException;

public class GetAsStringTag extends SimpleTagSupport {

    private final GetAsStringModel model = new GetAsStringModel();
    private boolean ignore;
    private String preparer;
    private String role;
    private Object defaultValue;
    private String defaultValueRole;
    private String defaultValueType;
    private String name;
    private Attribute value;

    public boolean isIgnore() { return ignore; }
    public void setIgnore(boolean ignore) { this.ignore = ignore; }
    public String getPreparer() { return preparer; }
    public void setPreparer(String preparer) { this.preparer = preparer; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Object getDefaultValue() { return defaultValue; }
    public void setDefaultValue(Object defaultValue) { this.defaultValue = defaultValue; }
    public String getDefaultValueRole() { return defaultValueRole; }
    public void setDefaultValueRole(String defaultValueRole) { this.defaultValueRole = defaultValueRole; }
    public String getDefaultValueType() { return defaultValueType; }
    public void setDefaultValueType(String defaultValueType) { this.defaultValueType = defaultValueType; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Attribute getValue() { return value; }
    public void setValue(Attribute value) { this.value = value; }

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
        model.execute(ignore, preparer, role, defaultValue, defaultValueRole,
                defaultValueType, name, value, request, modelBody);
    }
}
