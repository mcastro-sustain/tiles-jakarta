package org.apache.tiles.jsp.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.autotag.JspAutotagRuntime;
import org.apache.tiles.template.SetCurrentContainerModel;

import java.io.IOException;

public class SetCurrentContainerTag extends SimpleTagSupport {

    private final SetCurrentContainerModel model = new SetCurrentContainerModel();
    private String containerKey;

    public String getContainerKey() { return containerKey; }
    public void setContainerKey(String containerKey) { this.containerKey = containerKey; }

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
        model.execute(containerKey, request);
    }
}
