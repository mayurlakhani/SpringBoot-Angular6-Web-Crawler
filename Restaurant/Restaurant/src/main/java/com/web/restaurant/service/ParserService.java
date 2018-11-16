package com.web.restaurant.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.web.restaurant.enums.CrawlerType;

@Service
public interface ParserService {
	
	public void parse(CrawlerType insert) throws IOException;
	public void downloadUsingStream(String urlStr, String file) throws IOException;
}
