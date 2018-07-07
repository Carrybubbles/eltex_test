package ru.fedin.visitor.views;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;
import ru.fedin.net.HTTPResponser;
import ru.fedin.visitor.models.VisitorCounter;

public class VisitorUI extends CustomComponent {
    private static final Logger log = Logger.getLogger(VisitorUI.class);

    private Label labelCounter;
    private Label nameLabel;
    private VisitorCounter model;

    public VisitorUI(VisitorCounter model) {
        this.model = model;

        VerticalLayout verticalLayout = new VerticalLayout();

        initLabels();

        verticalLayout.addComponents(nameLabel,labelCounter);
        verticalLayout.setComponentAlignment(labelCounter, Alignment.MIDDLE_CENTER);
        setCompositionRoot(verticalLayout);

        verticalLayout.setSizeUndefined();
        setSizeFull();

    }

    private void initLabels(){
        Integer visitingValue = model.incrementCounter();
        labelCounter = new Label(visitingValue != null ? String.valueOf(visitingValue) : "Error with DB");
        nameLabel = new Label("Счетчик посещений");
    }
}

