package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.autotag.core.runtime.ModelBody;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.InsertDefinitionModel;

import java.io.IOException;

public class InsertDefinitionTag extends SimpleTagSupport {

    private final InsertDefinitionModel model = new InsertDefinitionModel();
    private String definitionName;
    private String template;
    private String templateType;
    private String templateExpression;
    private String role;
    private String preparer;
    private boolean flush;

    public String getName() { return definitionName; }
    public void setName(String name) { this.definitionName = name; }
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    public String getTemplateType() { return templateType; }
    public void setTemplateType(String templateType) { this.templateType = templateType; }
    public String getTemplateExpression() { return templateExpression; }
    public void setTemplateExpression(String templateExpression) { this.templateExpression = templateExpression; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPreparer() { return preparer; }
    public void setPreparer(String preparer) { this.preparer = preparer; }
    public boolean isFlush() { return flush; }
    public void setFlush(boolean flush) { this.flush = flush; }

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
        model.execute(definitionName, template, templateType, templateExpression,
                role, preparer, flush, request, modelBody);
    }
}
