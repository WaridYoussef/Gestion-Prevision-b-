package com.idemia.Gestion_previsions.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idemia.Gestion_previsions.entities.AffectationEntity;
import com.idemia.Gestion_previsions.entities.UserEntity;
import com.idemia.Gestion_previsions.repository.AffectationRepository;
import com.idemia.Gestion_previsions.repository.UserRepository;
import com.idemia.Gestion_previsions.services.AffectationService;
import com.idemia.Gestion_previsions.shared.dto.AffectationDto;
import com.idemia.Gestion_previsions.shared.dto.createAffDto;


@Service
public class AffectationServiceImpl implements AffectationService{

	@Autowired
	AffectationRepository affectationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public createAffDto createAffectation(createAffDto affectationDto) {
		
		
		List<AffectationEntity> Affectations = affectationRepository.findAllByUser_id(affectationDto.getUser_id());
		
		for(AffectationEntity affect : Affectations) {
			if(affect.getSemaine().equalsIgnoreCase(affectationDto.getSemaine()+""))
				throw new RuntimeException("Affectation Déja exist !");
		}
		
		
		AffectationEntity affectationEntity = new AffectationEntity();
		BeanUtils.copyProperties(affectationDto, affectationEntity);
		 
	        AffectationEntity newAffectation=affectationRepository.save(affectationEntity);
	        createAffDto affectation= new createAffDto();
	        BeanUtils.copyProperties(newAffectation, affectation);
	        		
		return affectation;
		
	}


	@Override
	public AffectationDto getAffectation(long affectationId) {
		AffectationEntity affectationEntity = affectationRepository.findById(affectationId);
		ModelMapper modelmapper=new ModelMapper();
		AffectationDto affectationDto = modelmapper.map(affectationEntity, AffectationDto.class);
		return affectationDto;
	}


	@Override
	public AffectationDto updateAffectation(long id, AffectationDto affectationDto) {
		AffectationEntity affectationEntity = affectationRepository.findById(id);
		if(affectationEntity == null) throw new RuntimeException("affectation NOT FOUND !");
		
		affectationEntity.setActivity_id(affectationDto.getActivity_id());
		affectationEntity.setUser_id(affectationDto.getUser_id());
		affectationEntity.setMois(affectationDto.getMois());
		affectationEntity.setSemaine(affectationDto.getSemaine());
		
		
		AffectationEntity upAffectation = affectationRepository.save(affectationEntity);
		
		ModelMapper modelmapper=new ModelMapper();
		AffectationDto affectation = modelmapper.map(upAffectation, AffectationDto.class);
		
		return affectation;
	}


	@Override
	public void DeleteAffectation(long id) {
		AffectationEntity affectationEntity = affectationRepository.findById(id);
		if (affectationEntity == null)
			throw new RuntimeException(id+" Not found!");
		affectationRepository.delete(affectationEntity);
	}


	@Override
	public List<AffectationDto> getAffectations(String id) {
		
		UserEntity user = userRepository.findByUserId(id);
		
		List<AffectationDto> affectationsDto = new ArrayList<>();
		List<AffectationEntity> affectationPage = affectationRepository.findAllByUser_Idd(user.getId());

		List<AffectationEntity> ffectations = affectationPage;
		for (AffectationEntity affectationEntity : ffectations) {

			ModelMapper modemapper = new ModelMapper();
			AffectationDto affectationDto = modemapper.map(affectationEntity, AffectationDto.class);

			affectationsDto.add(affectationDto);
		}

		return affectationsDto;
	}


	@Override
	public List<AffectationDto> getAffs() {
		
		List<AffectationEntity> affsEntity= affectationRepository.findAll();
		List<AffectationDto> affectationsDto = new ArrayList<>();
		
		for (AffectationEntity affectationEntity : affsEntity) {

			ModelMapper modemapper = new ModelMapper();
			AffectationDto affectationDto = modemapper.map(affectationEntity, AffectationDto.class);

			affectationsDto.add(affectationDto);
		}
		return  affectationsDto;
	}

	
	
	
	
	
	 
	
}
