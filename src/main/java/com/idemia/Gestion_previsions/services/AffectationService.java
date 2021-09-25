package com.idemia.Gestion_previsions.services;

import java.util.List;

import com.idemia.Gestion_previsions.shared.dto.AffectationDto;
import com.idemia.Gestion_previsions.shared.dto.createAffDto;


public interface AffectationService {

	createAffDto createAffectation(createAffDto affectationDto);

	AffectationDto getAffectation(long affectationId);

	AffectationDto updateAffectation(long id, AffectationDto affectationDto);

	void DeleteAffectation(long id);

	List<AffectationDto> getAffectations(String affectationId);

	List<AffectationDto> getAffs();

}
