package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.ImportAttributeModel;

import java.io.IOException;

public class ImportAttributeTag extends SimpleTagSupport {

    private final ImportAttributeModel model = new ImportAttributeModel();
    private String name;
    private String scope;
    private String toName;
    private boolean ignore;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }
    public String getToName() { return toName; }
    public void setToName(String toName) { this.toName = toName; }
    public boolean isIgnore() { return ignore; }
    public void setIgnore(boolean ignore) { this.ignore = ignore; }

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
        model.execute(name, scope, toName, ignore, request);
    }
}
