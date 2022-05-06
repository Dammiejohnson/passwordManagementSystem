package com.africa.semicolon.passwordmanagerapplication.services;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.AddUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.DeleteUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.FindUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.UpdateUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.exceptions.UrlException;
import com.africa.semicolon.passwordmanagerapplication.models.Url;
import com.africa.semicolon.passwordmanagerapplication.models.User;
import com.africa.semicolon.passwordmanagerapplication.repositories.UrlRepository;
import com.africa.semicolon.passwordmanagerapplication.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UrlServiceImplementation implements  UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public AddUrlResponse addUrl(AddUrlRequest urlRequest) {
        User user = userRepository.findUserByEmailAddress(urlRequest.getUserEmailAddress())
                .orElseThrow(( )-> new UrlException("user account with email does not exists"));
        Url url = new Url();
        if (user.getPassword().equals(urlRequest.getUserPassword())) {
           modelMapper.map(urlRequest, url);
            Url saved = urlRepository.save(url);
            user.getUrls().add(saved);
            userRepository.save(user);
        } else throw  new UrlException("Invalid details");

        AddUrlResponse urlResponse = new AddUrlResponse();
        modelMapper.map(user, urlResponse);
        urlResponse.setMessage("url added successfully");
        return urlResponse;
    }

    @Override
    public FindUrlResponse findbyUrl(String urlAddress) {
        Url url = urlRepository.findUserByUrlAddress(urlAddress);
        if (url == null) throw new UrlException("Url does not exist");
        FindUrlResponse response = new FindUrlResponse();
        modelMapper.map(url, response);
        return response;
    }


    @Override
    public List<UpdateUrlResponse> updateUrl(String userEmail, UpdateUrlRequest request) {
        User user = userRepository.findUserByEmailAddress(userEmail)
                .orElseThrow(( )-> new UrlException("user account with email does not exists"));
        Set<Url> urls = user.getUrls();
        List<UpdateUrlResponse> responses = new ArrayList<>();
//        for(Url url : urls){
        urls.forEach(url -> {
            boolean isUpdated = false;
            if (url.getUrlAddress().equals(request.getUrlAddress())){
                if(!(request.getUrlpassword()==null || request.getUrlpassword().trim().equals(""))){
                    url.setPassword(request.getUrlpassword());
                    isUpdated = true;
                }
                if(!(request.getUrlEmailAddress()==null || request.getUrlEmailAddress().trim().equals(""))){
                    url.setUrlEmailAddress(request.getUrlEmailAddress());
                    isUpdated = true;
                }
                if (isUpdated){
                    url = urlRepository.save(url);
                    user.getUrls().add(url);
                    userRepository.save(user);
                }
            }
            UpdateUrlResponse urlResponse = new UpdateUrlResponse();
            modelMapper.map(request, urlResponse);
            urlResponse.setMessage("Account updated successfully");
            responses.add(urlResponse);
        });

        return responses;
    }

    @Override
    public DeleteUrlResponse deleteUrl(String urlAddress) {
        Url url = urlRepository.findUserByUrlAddress(urlAddress);
        if (url == null) throw new UrlException("Url does not exist");
        urlRepository.delete(url);
        DeleteUrlResponse deletedResponse = new DeleteUrlResponse();
        deletedResponse.setMessage("Deleted Successfully");
        return deletedResponse;
    }

    @Override
    public long count() {
        return urlRepository.count();
    }



}
