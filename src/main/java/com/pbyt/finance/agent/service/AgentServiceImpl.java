package com.pbyt.finance.agent.service;

import com.pbyt.finance.agent.entity.TblAgent;
import com.pbyt.finance.agent.enums.AgentType;
import com.pbyt.finance.agent.model.AgentModel;
import com.pbyt.finance.agent.model.LoginModel;
import com.pbyt.finance.agent.model.LoginResponse;
import com.pbyt.finance.exception.InvalidCredential;
import com.pbyt.finance.exception.NotFound;
import com.pbyt.finance.repository.AgentRepository;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.user.entity.TblUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
    public LoginResponse createAgent(AgentModel agentModel) throws Exception {
        AgentType agentType = agentModel.getAgentType() == AgentType.LICENSED_AGENT ? AgentType.LICENSED_AGENT : AgentType.SOCIAL_MEDIA_AFFILIATE;
        String HashedPassword = new BCryptPasswordEncoder().encode(agentModel.getPassword());
        TblAgent agent = agentRepository.save(TblAgent.builder()
                .name(agentModel.getName())
                .agentType(agentType)
                .mobileNumber(agentModel.getMobileNumber())
                .build());
        TblUser user = userRepository.save(TblUser.builder()
                .agentId(agent.getId())
                .mobileNumber(agent.getMobileNumber())
                .name(agent.getName())
                .password(HashedPassword).build());

        String token = jwtService.GenerateToken(user.getId().toString(), user.getAgentId().toString());
        return LoginResponse.builder()
                .name(agent.getName())
                .agentType(agent.getAgentType())
                .mobileNUmber(agent.getMobileNumber())
                .token(token)
                .build();
    }

    @Override
    public LoginResponse login(LoginModel loginModel) throws Exception {
        Optional<TblUser> userDetails = userRepository.findAgentUser(loginModel.getMobileNumber());
        if (userDetails.isEmpty()) throw new NotFound("Agent Not Found");
        TblUser user = userDetails.get();
        boolean isValidPassword = new BCryptPasswordEncoder().matches(loginModel.getPassword(), user.getPassword());
        if (!isValidPassword) throw new InvalidCredential("Invalid Credential");
        TblAgent agent = agentRepository.findAgent(user.getAgentId());
        String token = jwtService.GenerateToken(user.getId().toString(), user.getAgentId().toString());
        return LoginResponse.builder()
                .name(user.getName())
                .agentType(agent.getAgentType())
                .mobileNUmber(agent.getMobileNumber())
                .token(token)
                .build();
    }
}
