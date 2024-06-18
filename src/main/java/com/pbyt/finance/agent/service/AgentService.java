package com.pbyt.finance.agent.service;

import com.pbyt.finance.agent.entity.TblAgent;
import com.pbyt.finance.agent.model.AgentModel;
import com.pbyt.finance.agent.model.LoginModel;
import com.pbyt.finance.agent.model.LoginResponse;

public interface AgentService {
    public Boolean isRegistered(String mobileNUmber) throws Exception;
    public TblAgent createAgent(AgentModel agentModel) throws Exception;

    public LoginResponse login(LoginModel loginModel) throws Exception;
}
