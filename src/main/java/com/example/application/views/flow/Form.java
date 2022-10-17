package com.example.application.views.flow;

import com.example.application.data.entity.CustomerInfo;
import com.example.application.data.entity.Item;
import com.example.application.data.entity.Items;
import com.example.application.data.service.CustomerInfoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.shared.HasClientValidation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.*;

public class Form extends FormLayout {
    TextField phoneNumber = new TextField("Phone Number");

   //TextField itemType = new TextField("Item Type");
   ComboBox<String> itemType = new ComboBox<>("itemTypesss");
    TextField quantity = new TextField("Quantity");



//comboBox.setItems(DataService.getCountries());
//comboBox.setItemLabelGenerator(Country::getName);
//    add(comboBox);

    TextField totalAmount = new TextField("Total Amount");

    Binder<CustomerInfo> binder = new BeanValidationBinder<>(CustomerInfo.class);
    List strainTypes = new ArrayList();


   // Items items = new Items(List.of(new Item("Sativa"), new Item("dragon fruit"), new Item("indika"));

    private CustomerInfo customerInfo;

    private CustomerInfoService customerInfoService;

    public Form(CustomerInfoService customerInfoService) {



       // strainTypes.addAll(Arrays.asList("Indica", "Sativa", "dragon fruit"));

//        ComboBox<String> itemType = new ComboBox<>("itemTypesss", strainTypes);
       var items =  new ArrayList<>(Arrays.asList("Indica", "Sativa", "dragon fruit"));
       //var items = new ArrayList<>(Arrays.asList(new Item("indica"), new Item("sativa")));
        itemType.setEnabled(true);
        //itemType.setAllowCustomValue(true);
        itemType.setItems(items);
        itemType.setAllowCustomValue(true);
        itemType.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            items.add(customValue);

            itemType.setItems(items);
            itemType.setValue(customValue);
        });


        this.customerInfoService = customerInfoService;
        //strainTypes.put("")

        System.out.println(this.phoneNumber.getValue() + "the phone number");
       // binder.bindInstanceFields(this);
        //binder.setBean(customerInfo);
        phoneNumber.setWidth("10");

        //strainTypes.addAll(Arrays.asList("Indica", "Sativa", "dragon fruit"));

        binder.bindInstanceFields(this);
        //itemType.set
//        itemType.setItems(items);
        //itemType.setItems(Item::getItemName);
        H1 header = new H1("Customer Information");
        add(header, phoneNumber,itemType, quantity, totalAmount, createButtonsLayout());

    };


    private Component createButtonsLayout() {
        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> validateAndSave());

       binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));


        return new HorizontalLayout(save);
    }

    private void validateAndSave() {

        try {
            CustomerInfo customerInfo = new CustomerInfo();
            binder.writeBean(customerInfo);
            fireEvent(new SaveEvent(this, customerInfo));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
    void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
        binder.readBean(customerInfo);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<Form> {
        private CustomerInfo customerInfo;

        protected ContactFormEvent(Form source, CustomerInfo customerInfo) {
            super(source, false);
            this.customerInfo = customerInfo;
        }

        public CustomerInfo getCustomerInfo(){
            return customerInfo;
        }

    }
        public static class SaveEvent extends ContactFormEvent {
            SaveEvent(Form source, CustomerInfo customerInfo) {
                super(source, customerInfo);
            }
        }
        public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                      ComponentEventListener<T> listener) {
            return getEventBus().addListener(eventType, listener);
        }
    }


