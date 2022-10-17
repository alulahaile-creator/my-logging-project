package com.example.application;

import com.example.application.security.SecurityService;
import com.example.application.views.flow.DashboardView;
import com.example.application.views.flow.MainView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        //H1 logo = new H1("Daily Sales Login");
        Label logo = new Label("Daily Sales Logging");
        //logo.setWidthFull();
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Log Out", e -> securityService.logout());
        DrawerToggle drawerToggle = new DrawerToggle();

        HorizontalLayout header = new HorizontalLayout(drawerToggle, logo, logout);
        header.setWidthFull();

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        //header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.);
        header.expand(logo);
        //header.setWidth("100%");
        header.addClassNames("py-0", "px-m");


        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", MainView.class);
        listLink.setHighlightCondition(HighlightConditions.always());


        VerticalLayout verticalLayout = new VerticalLayout(
                listLink, new RouterLink("Dashboard", DashboardView.class)
        )
        ;

        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToDrawer(verticalLayout);
    }
}