package ru.fedin.eltextest.visitor.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;
import ru.fedin.eltextest.visitor.models.VisitorCounter;

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
        labelCounter.setStyleName(ValoTheme.LABEL_HUGE);
        nameLabel = new Label("Счетчик посещений");
        nameLabel.setStyleName(ValoTheme.LABEL_HUGE);

    }
}

