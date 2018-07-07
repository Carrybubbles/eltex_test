package ru.fedin.visitor.controllers;

import org.apache.log4j.Logger;
import ru.fedin.net.HTTPResponser;
import ru.fedin.visitor.models.VisitorCounter;
import ru.fedin.visitor.views.VisitorUI;

public class VisitController {
    private static final Logger log = Logger.getLogger(VisitController.class);

    private VisitorCounter visitorCounter;
    private VisitorUI visitorUI;

    public VisitController(VisitorCounter visitorCounter, VisitorUI visitorUI) {
        this.visitorCounter = visitorCounter;
        this.visitorUI = visitorUI;
    }

    public VisitorCounter getVisitorCounter() {
        return visitorCounter;
    }

    public void setVisitorCounter(VisitorCounter visitorCounter) {
        this.visitorCounter = visitorCounter;
    }

    public VisitorUI getVisitorUI() {
        return visitorUI;
    }

    public void setVisitorUI(VisitorUI visitorUI) {
        this.visitorUI = visitorUI;
    }


}
