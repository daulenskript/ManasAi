package com.manasmiracle.demo;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatAiController {
    private final ChatClient chatClient;


    public ChatAiController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    //controller thats return data
    @GetMapping("/{chat}")
    public ResponseEntity<String> promptWithPathVariable(@PathVariable String chat) {
        try {
            String response = chatClient
                    .prompt(chat)
                    .call()
                    .content();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error , couldnt get data from server: " + e.getMessage());
        }
    }
}
