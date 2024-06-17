package com.dreamgames.backendengineeringcasestudy.config;

import com.dreamgames.backendengineeringcasestudy.scheduler.TournamentWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new TournamentWebSocketHandler(), "/tournament").setAllowedOrigins("*");
    }
}

