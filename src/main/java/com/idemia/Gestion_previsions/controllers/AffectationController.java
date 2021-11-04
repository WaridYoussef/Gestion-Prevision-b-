package com.idemia.Gestion_previsions.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idemia.Gestion_previsions.requests.AffectationRequest;
import com.idemia.Gestion_previsions.responses.AffectationResponse;
import com.idemia.Gestion_previsions.responses.createAffResponse;
import com.idemia.Gestion_previsions.services.AffectationService;
import com.idemia.Gestion_previsions.shared.dto.AffectationDto;
import com.idemia.Gestion_previsions.shared.dto.createAffDto;


@RestController
@RequestMapping("/affectation")
public class AffectationController {

	@Autowired
	AffectationService affectationService;
	
	
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AffectationResponse>> getAllAffectations(@PathVariable(name = "id") String userId){
		
		List<AffectationResponse> affectationsResponse = new ArrayList<>();
		
		List<AffectationDto> affectations = affectationService.getAffectations(userId);
		
	for(AffectationDto affectationDto : affectations) {
			
	ModelMapper modemapper=new ModelMapper();
	AffectationResponse affectation = modemapper.map(affectationDto, AffectationResponse.class);
			
	affectationsResponse.add(affectation);
	}
	return new ResponseEntity<>(affectationsResponse ,HttpStatus.OK);
		
	}
	
	@RequestMapping("/dashboard/{id}")
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AffectationResponse>> getDashBoardAffectations(@PathVariable(name = "id") String userId){
		
		List<AffectationResponse> affectationsResponse = new ArrayList<>();
		
		List<AffectationDto> affectations = affectationService.getDashBoardAffectations(userId);
		
	for(AffectationDto affectationDto : affectations) {
			
	ModelMapper modemapper=new ModelMapper();
	AffectationResponse affectation = modemapper.map(affectationDto, AffectationResponse.class);
			
	affectationsResponse.add(affectation);
	}
	return new ResponseEntity<>(affectationsResponse ,HttpStatus.OK);
		
	}
	
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<createAffResponse> createAffectation(@RequestBody AffectationRequest affectationRequest) {
		
		createAffDto affectationDto = new createAffDto();
		
		BeanUtils.copyProperties(affectationRequest, affectationDto);
		
		createAffDto createAffectation = affectationService.createAffectation(affectationDto);

		createAffResponse affectationResponse = new createAffResponse();
		BeanUtils.copyProperties(createAffectation, affectationResponse);
		
		return new ResponseEntity<createAffResponse>(affectationResponse ,HttpStatus.CREATED);
	}
	
	
	@PutMapping(path="/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AffectationResponse> updateAffectation(@PathVariable long id, @RequestBody AffectationRequest affectationRequest) {

		ModelMapper modelMapper = new ModelMapper();
		AffectationDto affectationDto = modelMapper.map(affectationRequest, AffectationDto.class);

		AffectationDto updateAffectation = affectationService.updateAffectation(id, affectationDto);

		AffectationResponse affectationResponse = modelMapper.map(updateAffectation, AffectationResponse.class);

		
		return new ResponseEntity<AffectationResponse>(affectationResponse ,HttpStatus.ACCEPTED); 

	}
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteAffectation(@PathVariable long id) {
		affectationService.DeleteAffectation(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
