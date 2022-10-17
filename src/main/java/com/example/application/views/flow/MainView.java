package com.example.application.views.flow;

import com.example.application.MainLayout;
import com.example.application.data.entity.CustomerInfo;
import com.example.application.data.service.CustomerInfoService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;


@Route(value="", layout = MainLayout.class)
@PageTitle("Daily Sales Login | Contact Information")
@Component
@Scope("prototype")
@PermitAll
//@CssImport(
//        themeFor = "vaadin-grid",
//        value = "./recipe/dynamicgridrowbackgroundcolor/dynamic-grid-row-background-color.css"
//)
public class MainView extends HorizontalLayout {
    Grid<CustomerInfo> grid = new Grid<>(CustomerInfo.class);

    Form form;
    private CustomerInfoService service;

    public MainView(CustomerInfoService service){
        this.service = service;

        setSizeFull();
        configureGrid();

        form = new Form(service);
        form.setWidth("60em"); // formerly 40 em
        //form.setWidth("80%");
        form.addListener(Form.SaveEvent.class, this::saveCustomerInfo);


        add(grid,form);
        updateList();
        grid.asSingleSelect().addValueChangeListener(event ->
                editCustomerInfo(event.getValue()));
    }

    private void saveCustomerInfo(Form.SaveEvent event) {
        service.save(event.getCustomerInfo());
        updateList();

    }

    private void editCustomerInfo(CustomerInfo value) {

        if (value == null) {
            closeEditor();
        } else {
            form.setCustomerInfo(value);
            form.setVisible(true);
            addClassName("editing");
        }
        }

    private void closeEditor() {

        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureGrid() {

        grid.setSizeFull();
        grid.setColumns("phoneNumber", "itemType", "quantity", "totalAmount");
//        grid.addColumn("phoneNumber").setTextAlign(ColumnTextAlign.CENTER);
//        grid.addColumn("itemType").setTextAlign(ColumnTextAlign.CENTER);
//        grid.addColumn("quantity").setTextAlign(ColumnTextAlign.CENTER);
//        grid.addColumn("totalAmount").setTextAlign(ColumnTextAlign.CENTER);

    }
    private void updateList() {
        grid.setItems(service.findAll());
    }
}
