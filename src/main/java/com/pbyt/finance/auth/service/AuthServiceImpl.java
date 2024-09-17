package com.pbyt.finance.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.applicationEntity.TblWorkArea;
import com.pbyt.finance.auth.model.AgentLoginResponse;
import com.pbyt.finance.auth.model.AgentRegisterModel;
import com.pbyt.finance.auth.model.UserLoginModel;
import com.pbyt.finance.auth.model.UserLoginResponse;
import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.exception.InvalidCredential;
import com.pbyt.finance.repository.AgentRepository;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.repository.WorkAreaRepository;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.user.entity.TblAgent;
import com.pbyt.finance.user.entity.TblUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private WorkAreaRepository workAreaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public Optional<TblUser> checkRegister(Long mobileNumber) {
        return userRepository.findUserByMobileNumber(mobileNumber);
    }

    @Override
    public UserLoginResponse login(UserLoginModel userLoginModel, TblUser user) throws InvalidCredential {
        boolean isCredentialCorrect = new BCryptPasswordEncoder().matches(userLoginModel.getPassword(), user.getPassword());
        if (!isCredentialCorrect) throw new InvalidCredential("Invalid Credential");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String role = mapper.writeValueAsString(user.getRoles());
            String token = jwtService.GenerateToken(role + "-" + user.getId().toString(), user.getMobileNumber().toString());
            Set<TblStateDistrict> areaList = workAreaRepository.findAreaListByUserId(user.getId());
            ModelMapper modelMapper = new ModelMapper();
            UserLoginResponse response = modelMapper.map(user, UserLoginResponse.class);
            response.setWorkArea(areaList);
            response.setToken(token);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TblAgent> getAgentRegister(Long mobileNumber) {
        return agentRepository.findAgentByMobileNumber(mobileNumber);
    }

    @Override
    public boolean checkAgentRegistered(Long mobileNumber) {
        return agentRepository.findAgentExists(mobileNumber);
    }


    @Override
    public AgentLoginResponse register(AgentRegisterModel registerModel) throws JsonProcessingException {
        try {
            String hashedPassword = new BCryptPasswordEncoder().encode(registerModel.getPassword());
            List<Integer> role = List.of(RoleEnum.AGENT.ordinal());
            TblAgent agent = agentRepository.save(TblAgent.builder()
                    .name(registerModel.getName())
                    .email(registerModel.getEmail())
                    .mobileNumber(registerModel.getMobileNumber())
                    .address(registerModel.getAddress())
                    .authorities(role)
                    .password(hashedPassword)
                    .dob(registerModel.getDob())
                    .build());
            String token = jwtService.GenerateToken(role + "-" + agent.getId().toString(), agent.getMobileNumber().toString());
            ModelMapper modelMapper = new ModelMapper();
            AgentLoginResponse response = modelMapper.map(agent, AgentLoginResponse.class);
            response.setToken(token);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
