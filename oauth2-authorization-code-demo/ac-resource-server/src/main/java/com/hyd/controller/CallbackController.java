package com.hyd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CallbackController {
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	@Value("${security.oauth2.access-token-uri}")
	private String accessTokenUri;

	@GetMapping("/callback")
	public OAuth2AccessToken login(@RequestParam("code") String code) {
		AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
		resourceDetails.setAccessTokenUri(accessTokenUri);
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
		oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().setAuthorizationCode(code);
		oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().setPreservedState("http://127.0.0.1:9090/callback");
		oAuth2RestTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());
		return oAuth2RestTemplate.getAccessToken();
	}
}
