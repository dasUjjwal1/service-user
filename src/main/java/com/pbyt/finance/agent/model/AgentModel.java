package com.pbyt.finance.agent.model;

import com.pbyt.finance.agent.enums.AgentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentModel {
    @NotNull
    @NotBlank
    private String name;

    private AgentType agentType;

    @NotNull
    @NotBlank
    private String mobileNumber;

    @NotNull
    @NotBlank
    private String password;
}
