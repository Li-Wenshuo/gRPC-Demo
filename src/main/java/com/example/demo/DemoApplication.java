package com.example.demo;

import com.example.demo.services.ByeService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        Server server = ServerBuilder.forPort(50051)
                .useTransportSecurity(new File("D:\\GitHub\\demo\\src\\main\\resources\\server.crt"), new File("D:\\GitHub\\demo\\src\\main\\resources\\server.key"))
//                .addService(new HelloWorldServer.GreeterImpl())
                .addService(ctx.getBean(ByeService.class))
                .build()
                .start();
        System.out.println("Server started, listening on " + server.getPort());
        server.awaitTermination();
    }

    //    @PostConstruct
//    private void post() throws IOException, InterruptedException {
//        Server server = ServerBuilder.forPort(50051)
//                .useTransportSecurity(new File("D:\\GitHub\\demo\\src\\main\\resources\\server.crt"), new File("D:\\GitHub\\demo\\src\\main\\resources\\server.key"))
////                .addService(new HelloWorldServer.GreeterImpl())
//                .addService(byeService)
//                .build()
//                .start();
//        System.out.println("Server started, listening on " + server.getPort());
//        server.awaitTermination();
//    }

}
