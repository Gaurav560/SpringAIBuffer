package com.telusko.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    private final ChatClient chatClient;

    public HelloController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping
    public String prompt(@RequestParam String message) {


//        return chatClient
//                .prompt(message)    // Creates a prompt object with the user's message
//                .call()             // Makes an HTTP request to the AI service/model
//                .content();         // Extracts the text response from the AI's reply
//
//


//        to get the metadata
//        return chatClient.prompt().user(message).call().chatResponse().getMetadata();


//    to get the actual respnce using chatResponse()
        return chatClient
                .prompt(message)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();


    }
}
