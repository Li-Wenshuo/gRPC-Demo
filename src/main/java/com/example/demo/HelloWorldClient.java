package com.example.demo;

import com.example.demo.grpc.GreeterGrpc;
import com.example.demo.grpc.HelloWorldProto;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import java.io.File;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        File certFile = new File("D:\\GitHub\\demo\\src\\main\\resources\\server.crt");

        ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 50051)
                .sslContext(GrpcSslContexts.forClient().trustManager(certFile).build())
                .build();

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        HelloWorldProto.HelloRequest request = HelloWorldProto.HelloRequest.newBuilder().setName("World").build();
        HelloWorldProto.HelloReply response = stub.sayHello(request);

        System.out.println(response.getMessage());

        channel.shutdown();
    }
}
