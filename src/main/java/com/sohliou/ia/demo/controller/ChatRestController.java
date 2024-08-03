package com.sohliou.ia.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sohliou.ia.demo.service.ChatAiService;

@RestController
@RequestMapping("/chat")
public class ChatRestController {
    
private ChatAiService chatAiService;

public ChatRestController (ChatAiService chatAiService) {
this.chatAiService = chatAiService;
}
@GetMapping("/ask")
public String ask (String question) {
return chatAiService.ragChat (question);
} 
    
}
