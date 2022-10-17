package com.example.application.views.flow;//package com.example.application.views.flow;
//
//import com.example.application.data.entity.CustomerInfo;
//import com.example.application.data.service.CustomerInfoService;
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.EmailField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import com.vaadin.flow.spring.annotation.UIScope;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@PageTitle("Flow")
//@Route(value = "test")
//@AnonymousAllowed
//public class FlowView extends VerticalLayout {
//
//
//    private CustomerInfoService service;
//
//    Grid<CustomerInfo> grid = new Grid<>(CustomerInfo.class);
//    private List<CustomerInfo> customerInfoList = new LinkedList<>();
//
//    Form form;
//
//    public FlowView(CustomerInfoService service) {
//        this.service = service;
//
//        form = new Form();
//
//        //form.addClickListener(Form.)
//        grid.setColumns("phoneNumber","itemType", "quantity","totalAmount");
//        grid.asSingleSelect().addValueChangeListener(e -> {
//            form.setCustomerInfo(e.getValue());
//        });
////        grid.asSingleSelect().addValueChangeListener(event ->
////                editContact(event.getValue()));
//
//        add(grid, form);
//
//        update();
//    }
//
//    void update() {
//        grid.setItems(service.findAll());
//    }
//
//    class Form extends FormLayout {
//        TextField phoneNumber = new TextField("Phone Number");
//        TextField itemType = new TextField("Item Type");
//        TextField quantity = new TextField("Quantity");
//
//        TextField totalAmount = new TextField("Total Amount");
//
//
//
//        Binder<CustomerInfo> binder = new BeanValidationBinder<>(CustomerInfo.class);
//        private CustomerInfo customerInfo = new CustomerInfo();
//
//
//        Form() {
//            var saveButton = new Button("Save");
//            saveButton.addClickShortcut(Key.ENTER);
//            binder.bindInstanceFields(this);
////            binder.forField(phoneNumber)
////                    .asRequired("phone number is required")
////                            .bind("phoneNumber");
////            binder.forField(quantity)
////                    .asRequired("quantity is required")
////                            .bind("quantity");
////            binder.forField(itemType)
////                    .asRequired("item type is required")
////                            .bind("itemType");
////            binder.forField(totalAmount)
////                    .asRequired("total amount is required")
////                            .bind("totalAmount");
//            //binder.setBean(customerInfo);
//            binder.addStatusChangeListener(status -> {saveButton.setEnabled(!status.hasValidationErrors());});
//            binder.addStatusChangeListener(e -> saveButton.setEnabled(binder.isValid()));
//            add(phoneNumber, itemType, quantity, totalAmount,saveButton);
//
//            saveButton.addClickListener(click -> {
//                try {
//
//                    CustomerInfo customerInfo = new CustomerInfo();
//                    binder.writeBean(customerInfo);
//                    binder.readBean(new CustomerInfo()); // CLEARING THE FORM VALUE
//
//                    service.save(customerInfo);
//                    update();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//
//            binder.addStatusChangeListener(e -> saveButton.setEnabled(binder.isValid()));
//        }
//
//
//        void setCustomerInfo(CustomerInfo customerInfo) {
//
//            this.customerInfo = customerInfo;
//            binder.readBean(customerInfo);
//
//
//        }
//    }
//}
