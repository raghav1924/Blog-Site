package com.blog.authApp.services;

import com.blog.authApp.expceptions.ContactAlreadyRegistered;
import com.blog.authApp.expceptions.EmailAlreadyRegistered;
import com.blog.authApp.expceptions.InvalidCredentials;
import com.blog.authApp.repository.IRepository;
import com.blog.authApp.domain.SignUpData;
import com.blog.authApp.domain.User;
import com.blog.authApp.domain.UserDTO;
import com.blog.authApp.proxy.IUserProxy;
import com.blog.authApp.rabbitmq.EmailDTO;
import com.blog.authApp.rabbitmq.MailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CService implements IService{
    @Autowired
    private IRepository iRepository;

    @Autowired
    private IUserProxy iUserProxy;

    @Autowired
    private MailProducer mailProducer;

    @Override
    public User register(SignUpData signUpData)throws EmailAlreadyRegistered, ContactAlreadyRegistered {

        if (iRepository.findByEmail(signUpData.getEmail())!=null)throw new EmailAlreadyRegistered();
        if (iRepository.findByPhoneNo(signUpData.getPhoneNo())!=null)throw new EmailAlreadyRegistered();

        User user=new User();
        user.setEmail(signUpData.getEmail());
        user.setPhoneNo(signUpData.getPhoneNo());
        user.setPassword(signUpData.getPassword());
        user.setName(signUpData.getName());
        User user1=iRepository.save(user);
        iUserProxy.sendDTOToUserApp(new UserDTO(user1.getUserId(), user.getEmail(), user.getPhoneNo(), user.getName()));
        mailProducer.sendEmailToQueue(new EmailDTO(user.getEmail(), "We Welcome You to Our Site ","Registered Successfully"));
        return user;
    }

    @Override
    public User login(User user) throws InvalidCredentials {
        User user1=iRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (user1==null)throw new InvalidCredentials();
        return user1;
    }

//    @Override
//    public void deleteUser(String email) {
//        iRepository.deleteById(email);
//    }
}
