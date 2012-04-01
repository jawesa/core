package com.jawesa.action.tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jawesa.annotation.controller.PersistenceControllerQualifier;
import com.jawesa.controller.common.PersistenceController;
import com.jawesa.model.tag.TagDemo;

@Path("/tag/demo/source/{demoid: [0-9]+}.xml")
public class TagDemoSourceCodeAction
{
    public static final String REST_PATH = "/tag/demo/source";
    public static final String REST_PATH_EXTENSION = ".xml";

    @Inject
    @PersistenceControllerQualifier
    private PersistenceController persistenceController;

    @GET
    @Produces("text/xml")
    public String getSourceCode(@PathParam("demoid") String demoIdString)
    {
        Long demoId = Long.parseLong(demoIdString);
        TagDemo demo = persistenceController.find(TagDemo.class, demoId);

        if (demo == null)
        {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ui:composition xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:ui=\"http://java.sun.com/jsf/facelets\" xmlns:f=\"http://java.sun.com/jsf/core\" xmlns:h=\"http://java.sun.com/jsf/html\"><h:outputText value=\"Demo not found\"/></ui:composition>";
        }
        else
        {
            return demo.getSourceCode();
        }
    }
}
