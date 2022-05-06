package com.africa.semicolon.passwordmanagerapplication.services;

import com.africa.semicolon.passwordmanagerapplication.dtos.requests.AddUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.requests.UpdateUrlRequest;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.AddUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.DeleteUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.FindUrlResponse;
import com.africa.semicolon.passwordmanagerapplication.dtos.responses.UpdateUrlResponse;

import java.util.List;

public interface UrlService {
    //Url addUrl(String id, String urlAddress);
    AddUrlResponse addUrl (AddUrlRequest urlRequest);
    FindUrlResponse findbyUrl(String url);
    List<UpdateUrlResponse> updateUrl(String userEmail, UpdateUrlRequest request);
    DeleteUrlResponse deleteUrl(String Url);


    long count();
}
