package com.example.demo.services;

import com.example.demo.grpc.GreeterGrpc;
import com.example.demo.grpc.HelloWorldProto;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class ByeService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloWorldProto.HelloRequest req, StreamObserver<HelloWorldProto.HelloReply> responseObserver) {
        HelloWorldProto.HelloReply reply = HelloWorldProto.HelloReply.newBuilder().setMessage("Bye  " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        System.out.println("Server bye a call");
    }
}
