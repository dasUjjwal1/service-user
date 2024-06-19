package com.pbyt.finance.agent.model;

import com.pbyt.finance.agent.enums.AgentType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
    private String token;
    private AgentType agentType;
    private String mobileNUmber;
    private String name;

}
