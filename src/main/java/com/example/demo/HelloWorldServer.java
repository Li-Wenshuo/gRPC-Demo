package com.example.demo;

import com.example.demo.grpc.GreeterGrpc;
import com.example.demo.grpc.HelloWorldProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.File;

public class HelloWorldServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
                .useTransportSecurity(new File("D:\\GitHub\\demo\\src\\main\\resources\\server.crt"), new File("D:\\GitHub\\demo\\src\\main\\resources\\server.key"))
                .addService(new GreeterImpl())
                .build()
                .start();

        System.out.println("Server started, listening on " + server.getPort());
        server.awaitTermination();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloWorldProto.HelloRequest req, StreamObserver<HelloWorldProto.HelloReply> responseObserver) {
            HelloWorldProto.HelloReply reply = HelloWorldProto.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            System.out.println("Server answered a call");
        }
    }
}

