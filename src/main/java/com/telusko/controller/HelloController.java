package com.telusko.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {


    private final ChatClient chatClient;

    @Value("classpath:/prompts/celeb-details.st")
    private Resource celebPrompt;

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


    @GetMapping("/celebrity")
    public String getCelebrityDetail(@RequestParam String name) {

//
//        String message = """
//                 List the details of the Famous personality: {name}\s
//                 along with their career achievements.
//                 And Show the details in readable format\s
//                \s""";


        //creating a prompt out of this message
//        PromptTemplate template = new PromptTemplate(message);
        PromptTemplate template = new PromptTemplate(celebPrompt);
        Prompt prompt = template.create(Map.of("name", name));


        return chatClient
                .prompt(prompt)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }
}
