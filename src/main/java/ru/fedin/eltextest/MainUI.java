package ru.fedin.eltextest;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.fedin.eltextest.money.controllers.MoneyController;
import ru.fedin.eltextest.money.models.MoneyModel;
import ru.fedin.eltextest.money.views.MoneyUI;
import ru.fedin.eltextest.visitor.models.VisitorCounter;
import ru.fedin.eltextest.visitor.views.VisitorUI;
import ru.fedin.eltextest.weather.controllers.WeatherController;
import ru.fedin.eltextest.weather.models.WeatherModel;
import ru.fedin.eltextest.weather.views.WeatherUI;

import javax.servlet.annotation.WebServlet;


@Theme("mytheme")
public class MainUI extends UI {

    private MoneyModel moneyModel;
    private MoneyUI moneyUI;
    private MoneyController moneyController;

    private WeatherModel weatherModel;
    private WeatherUI weatherUI;
    private WeatherController weatherController;

    private VisitorCounter visitorCounter;
    private VisitorUI visitorUI;

    private Label nameProject;
    private Label ipAddress;
    private Label serverTime;

    private MainModel mainModel;
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        mainModel = new MainModel();

        initMoneyMVC();
        initVisitingMVC();
        initWeatherMVC();

        final HorizontalLayout componentsLay = new HorizontalLayout();
        final VerticalLayout mainLayout = new VerticalLayout();

        componentsLay.addComponents(weatherUI,moneyUI,visitorUI);
        componentsLay.setSizeFull();

        initLables(mainModel.getIp(),mainModel.getTime());

        GridLayout gridLayout = new GridLayout(2,1);
        gridLayout.addComponent(serverTime,0,0);
        gridLayout.addComponent(ipAddress,1,0);
        gridLayout.setWidth("100%");
        gridLayout.setHeight("100%");

        mainLayout.addComponents(nameProject,componentsLay,gridLayout);
        mainLayout.setComponentAlignment(nameProject,Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(ipAddress,Alignment.BOTTOM_RIGHT);
        gridLayout.setComponentAlignment(serverTime,Alignment.BOTTOM_LEFT);

        setContent(mainLayout);
        setSizeFull();
    }

    private void initWeatherMVC(){
        weatherModel = new WeatherModel();
        weatherUI = new WeatherUI(weatherModel);
        weatherController = new WeatherController(weatherUI,weatherModel);
        weatherUI.clickOnUpdateButton();

    }

    private void initMoneyMVC(){
        moneyModel = new MoneyModel();
        moneyUI = new MoneyUI(moneyModel);
        moneyController = new MoneyController(moneyModel,moneyUI);
        moneyUI.clickOnUpdateButton();
    }

    private void initVisitingMVC(){
        visitorCounter = new VisitorCounter();
        visitorUI = new VisitorUI(visitorCounter);
    }

    private void initLables(String ip, String time){
       nameProject = new Label("Тестовое сетевое приложение");
       nameProject.setStyleName(ValoTheme.LABEL_H1);
       ipAddress = new Label("Ваш IP " + ip);
       ipAddress.setStyleName(ValoTheme.LABEL_HUGE);
       serverTime = new Label("Информация по состоянию на " + time);
       serverTime.setStyleName(ValoTheme.LABEL_HUGE);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
