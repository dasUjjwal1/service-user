package com.pbyt.finance.agent.service;

import com.pbyt.finance.agent.entity.TblAgent;
import com.pbyt.finance.agent.enums.AgentType;
import com.pbyt.finance.agent.model.AgentModel;
import com.pbyt.finance.agent.model.LoginModel;
import com.pbyt.finance.agent.model.LoginResponse;
import com.pbyt.finance.exception.NotFound;
import com.pbyt.finance.repository.AgentRepository;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.user.entity.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Override
    public Boolean isRegistered(String mobileNUmber) throws Exception {
        return agentRepository.findAgentByMobile(mobileNUmber);
    }

    @Override
    public TblAgent createAgent(AgentModel agentModel) throws Exception {
        AgentType agentType = switch (agentModel.getAgentType()) {
            case LICENSED_AGENT -> AgentType.LICENSED_AGENT;
            default -> AgentType.SOCIAL_MEDIA_AFFILIATE;
        };
        String HashedPassword = new BCryptPasswordEncoder().encode(agentModel.getPassword());
        TblAgent agent = agentRepository.save(TblAgent.builder()
                .name(agentModel.getName())
                .agentType(agentType)
                .mobileNumber(agentModel.getMobileNumber())
                .build());
        userRepository.save(TblUser.builder()
                .agentId(agent.getId())
                .mobileNumber(agent.getMobileNumber())
                .name(agent.getName())
                .password(HashedPassword).build());
        return agent;
    }

    @Override
    public LoginResponse login(LoginModel loginModel) throws Exception {
        Optional<TblAgent> agent = agentRepository.findAgent(loginModel.getMobileNumber());
        if (agent.isEmpty()) throw new NotFound("Agent Not Found");
        Boolean isValidPassword = new BCryptPasswordEncoder().matches(loginModel.getPassword(),"" );
        return null;
    }
}
