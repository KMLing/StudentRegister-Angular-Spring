package com.ling.backend.controller;

import javax.servlet.http.HttpServletRequest;

import com.ling.backend.util.Hashing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hashing")
public class HashingController {
	private final Hashing hashing;
	
	@PostMapping
	public ResponseEntity<?> justHash(@RequestBody String uiobj) throws ParseException, IOException {
		System.out.println("Start Hashing Primary JSON ");
		
		String hashedCode = hashing.hashCheckPrimaryJSON(uiobj);
		
		return new ResponseEntity<>("Hashed Code : "+hashedCode,HttpStatus.OK);
	}

}
