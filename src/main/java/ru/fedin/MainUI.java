package ru.fedin;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;
import ru.fedin.money.controllers.MoneyController;
import ru.fedin.money.models.MoneyModel;
import ru.fedin.money.views.MoneyUI;
import ru.fedin.visitor.models.VisitorCounter;
import ru.fedin.visitor.views.VisitorUI;
import ru.fedin.weather.controllers.WeatherController;
import ru.fedin.weather.models.WeatherModel;
import ru.fedin.weather.views.WeatherUI;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@Theme("mytheme")
public class MainUI extends UI {
    private static final Logger log = Logger.getLogger(MainUI.class);

    private MoneyModel moneyModel;
    private MoneyUI moneyUI;
    private MoneyController moneyController;

    private WeatherModel weatherModel;
    private WeatherUI weatherUI;
    private WeatherController weatherController;

    private VisitorCounter visitorCounter;
    private VisitorUI visitorUI;

    private  Label nameProject;
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
       ipAddress = new Label("Ваш IP " + ip);
       serverTime = new Label("Информация по состоянию на " + time);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
