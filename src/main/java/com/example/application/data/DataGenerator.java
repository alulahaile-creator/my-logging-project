package com.example.application.data;//package com.example.application.data;
//
//import com.example.application.data.service.CustomerInfoRepository;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@SpringComponent
//public class DataGenerator {
//
//    @Bean
//    public CommandLineRunner loadData(CustomerInfoRepository customerInfoRepository){
//        return args -> {
//            Logger logger = LoggerFactory.getLogger(getClass());
//
//            List<String> statuses = customerInfoRepository.saveAll(Stream.of("Imported lead", "Not contacted", "Contacted", "Customer", "Closed (lost)").map(CustomerInfo)
//
//                            //.map(::new).collect(Collectors.toList()));
//        }
//    }
//
//}
