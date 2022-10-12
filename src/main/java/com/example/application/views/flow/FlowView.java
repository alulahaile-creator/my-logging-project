package com.example.application.views.flow;

import com.example.application.data.entity.CustomerInfo;
import com.example.application.data.service.CustomerInfoService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Flow")
@Route(value = "test")
@AnonymousAllowed
public class FlowView extends VerticalLayout {


    private CustomerInfoService service;

    Grid<CustomerInfo> grid = new Grid<>(CustomerInfo.class);

    public FlowView(CustomerInfoService service) {
        this.service = service;

        var form = new Form();

        grid.setColumns("phoneNumber","itemType", "quantity","totalAmount");
        grid.asSingleSelect().addValueChangeListener(e -> {
            form.setCustomerInfo(e.getValue());
        });

        add(grid, form);
        update();
    }

    void update() {
        grid.setItems(service.findAll());
    }

    class Form extends FormLayout {
        TextField phoneNumber = new TextField("Phone Number");
        TextField itemType = new TextField("Item Type");
        TextField quantity = new TextField("Quantity");

        TextField totalAmount = new TextField("Total Amount");



        Binder<CustomerInfo> binder = new BeanValidationBinder<>(CustomerInfo.class);
        private CustomerInfo customerInfo;

        Form() {
            var saveButton = new Button("Save");
            saveButton.addClickShortcut(Key.ENTER);
            binder.bindInstanceFields(this);

            add(phoneNumber, itemType, quantity, totalAmount,saveButton);

            saveButton.addClickListener(click -> {
                try {
                    binder.writeBean(customerInfo);

                    service.save(customerInfo);
                    update();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            });

        }

        void setCustomerInfo(CustomerInfo customerInfo) {
            this.customerInfo = customerInfo;
            binder.readBean(customerInfo);
        }
    }
}
