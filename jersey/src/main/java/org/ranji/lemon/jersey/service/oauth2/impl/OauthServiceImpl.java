package org.ranji.lemon.jersey.service.oauth2.impl;

import org.ranji.lemon.jersey.model.oauth2.Client;
import org.ranji.lemon.jersey.service.oauth2.prototype.IClientService;
import org.ranji.lemon.jersey.service.oauth2.prototype.IOauthService;
import org.ranji.lemon.jersey.util.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OauthServiceImpl implements IOauthService {
	
	@Autowired
	IClientService clientService;
	
	@Override
	public String generateClientIdAndClientSecret(String thirdAppName) {
		Client c = new Client();
		c.setClientName(thirdAppName);
		c.setClientId(GuidUtil.generateClientId());
		c.setClientSecret(GuidUtil.generateClientSecret());
		clientService.save(c);
		return "{\"clientId:\"\""+c.getClientId()+"\",\"clientSecret:\"\""+c.getClientSecret()+"\"}";
	}

	@Override
	public boolean checkClientId(String clientId) {
		return clientService.findByClientId(clientId)!=null;
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		return clientService.findByClientSecret(clientSecret)!=null;
	}

}
