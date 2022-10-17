package com.example.application.views.flow;

import com.example.application.MainLayout;
import com.example.application.data.entity.CustomerInfo;
import com.example.application.data.service.CustomerInfoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.stream.Collectors;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Sales Info ")
@PermitAll
public class DashboardView extends VerticalLayout {

    private final CustomerInfoService customerInfoService;

    public DashboardView(CustomerInfoService customerInfoService) {
        this.customerInfoService = customerInfoService;
        addClassName("DashboardView");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getTotalSales(),getTotalNumberOfCustomers());
    }

    private Component getTotalNumberOfCustomers() {

        // take in the date to filter the values by date and return the # of customers for that day
        Span totalNumberofCustomers = new Span(String.valueOf(customerInfoService.findAll().size()));

        totalNumberofCustomers.addClassNames("text-xl", "mt-m");
        return totalNumberofCustomers;

    }

    private Component getTotalSales(){
        // take in the date to filter the values by date and return the total sales for that day
        Span totalDailySales = new Span(String.valueOf(customerInfoService.findAll().stream().collect(Collectors.summingInt(CustomerInfo::getTotalAmount))));
        totalDailySales.addClassNames("text-xl", "mt-m");
        return totalDailySales;
    }
}
