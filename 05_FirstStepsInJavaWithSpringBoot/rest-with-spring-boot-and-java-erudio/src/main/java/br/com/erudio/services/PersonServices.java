package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.respositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {

		logger.info("Finding one person!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records founds for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {

		logger.info("Creating one person!");

		// Converte PersonVO para a entidade Person
		Person entity = DozerMapper.parseObject(person, Person.class);

		// Salva a entidade Person no banco e converte o retorno para PersonVO
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

		return vo; // Retorna o VO
	}

	public PersonVO update(PersonVO person) {

		logger.info("updating one person!");

		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddres(person.getAddres());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {

		logger.info("deleting one person!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records founds for this ID!"));
		repository.delete(entity);
	}

	public PersonVOV2 createV2(PersonVOV2 person) {

		logger.info("Creating one person with V2!");

		// Converte PersonVO para a entidade Person
		Person entity = mapper.convertVoToEntity(person);

		// Salva a entidade Person no banco e converte o retorno para PersonVO
		PersonVOV2 vo = mapper.convertEntityToVo(repository.save(entity));

		return vo; // Retorna o VO
	}

}
