package com.vasworks.npc.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.vasworks.entity.Country;
import com.vasworks.npc.model.BirthAttestation;
import com.vasworks.npc.model.BirthCert;
import com.vasworks.npc.model.CountryState;
import com.vasworks.npc.model.DeathCert;
import com.vasworks.npc.model.EducationLevel;
import com.vasworks.npc.model.Ethnicity;
import com.vasworks.npc.model.Father;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.model.Informant;
import com.vasworks.npc.model.LocalGovArea;
import com.vasworks.npc.model.Mother;
import com.vasworks.npc.model.Occupation;
import com.vasworks.service.DaoService;

public interface AgentService extends DaoService {
	
    static final String JAI_STREAM_ACTION = "stream";
 
    static final String JAI_SUBSAMPLE_AVERAGE_ACTION = "SubsampleAverage";
    
    static final String JAI_ENCODE_ACTION = "encode";
    
    static final double EARTH_RADIUS = 6371;
	
	static final String LIST_COUNTRY_STATES = "select o from CountryState o where o.country.countryCode = :countryCode";
	
	static final String LIST_ADDRESSES_FOR_STATE = "select o from HomeAddress o where o.countryState.id = :countryStateId";
	
	static final String LIST_DEATH_CERTS = "select o from DeathCert o where o.regDate = :regDate";
	
	static final String LIST_BIRTH_CERTS = "select o from BirthCert o where o.regDate = :regDate";
	
	static final String LIST_FATHERS = "select o from Father o where o.regDate = :regDate";
	
	static final String LIST_MOTHERS = "select o from Mother o where o.regDate = :regDate";
	
	static final String LIST_INFORMANTS = "select o from Informant o where o.regDate = :regDate";
	
	static final String FILTER_COUNTRY_STATE = "select o.id, o.stateName from CountryState o where o.country.countryCode = :countryCode and o.stateName like :param";
	
	static final String FILTER_OCCUPATION = "select o.id, o.description from Occupation o where o.description like :param";

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<DeathCert> searchDeathCerts(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<BirthCert> searchBirthCerts(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Father> searchFathers(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Mother> searchMothers(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Informant> searchInformants(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<HomeAddress> searchAddresses(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Ethnicity> searchEthnicities(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<EducationLevel> searchEducationLevels(String param);

	/**
	 * 
	 * @param param
	 * @param stateId
	 * @return
	 */
	List<LocalGovArea> searchLgas(String param, Long stateId);
	
	/**
	 * 
	 * @param certId
	 * @param deathCert
	 * @param addressId
	 * @param informantId
	 * @param userId
	 * @return
	 */
	List<DeathCert> saveDeathCert(Long certId, DeathCert deathCert, Long addressId, Long informantId, Long userId);
	
	/**
	 * 
	 * @param birthCert
	 * @param fatherId
	 * @param motherId
	 * @param informantId
	 * @param countryStateId
	 * @param lgaId
	 * @param kinshipId
	 * @param userId
	 * @return
	 */
	List<BirthCert> saveBirthCert(BirthCert birthCert, Long fatherId, Long motherId, Long informantId, Long countryStateId, Long lgaId, Long kinshipId, Long userId);
	
	/**
	 * 
	 * @param fatherId
	 * @param father
	 * @param occupationId
	 * @param userId
	 * @return
	 */
	List<Father> saveFather(Long fatherId, Father father, Long occupationId, Long educationLevelId, Long ethnicityId, Long countryStateId, Long lgaId, File photo, Long photoId, Long userId);
	
	/**
	 * 
	 * @param fatherId
	 * @param mother
	 * @param userId
	 * @return
	 */
	List<Mother> saveMother(Long motherId, Mother mother, Long occupationId, Long educationLevelId, Long ethnicityId, Long countryStateId, Long lgaId, File photo, Long photoId, Long userId);
	
	/**
	 * 
	 * @param informantId
	 * @param informant
	 * @param addressId
	 * @param photo
	 * @param photoId
	 * @param signature
	 * @param signatureId
	 * @param userId
	 * @return
	 */
	List<Informant> saveInformant(Long informantId, Informant informant, Long addressId, File photo, Long photoId, String signature, Long signatureId, Long userId);

	/**
	 * 
	 * @param attnId
	 * @param birthAttn
	 * @param fatherId
	 * @param motherId
	 * @param informantId
	 * @param birthStateId
	 * @param lgaId
	 * @param addressId
	 * @param workAddressId
	 * @param educationLevelId
	 * @param occupationId
	 * @param requestReasonAId
	 * @param requestReasonBId
	 * @param requestCountryId
	 * @param userId
	 * @return
	 */
	List<BirthAttestation> saveBirthAttn(Long attnId, BirthAttestation birthAttn, Long fatherId, Long motherId, Long informantId, Long birthStateId, Long lgaId,
			Long addressId, Long workAddressId, Long educationLevelId, Long occupationId, Long requestReasonAId, Long requestReasonBId,
			String requestCountryId, File photo, Long photoId, Long userId);
	
	/**
	 * 
	 * @param addressId
	 * @param address
	 * @param countryStateId
	 * @param userId
	 * @return
	 */
	List<HomeAddress> saveHomeAddress(Long addressId, HomeAddress address, Long countryStateId, Long userId);
	
	/**
	 * 
	 * @param countryCode
	 * @return
	 */
	List<CountryState> listCountryStates(String countryCode);

	/**
	 * 
	 * @param regDate
	 * @return
	 */
	List<DeathCert> listDeathCerts(Date regDate);

	/**
	 * 
	 * @param regDate
	 * @return
	 */
	List<BirthCert> listBirthCerts(Date regDate);

	/**
	 * 
	 * @param regDate
	 * @return
	 */
	List<BirthAttestation> listBirthAttns(Date regDate);
	
	/**
	 * 
	 * @param regDate
	 * @return
	 */
	List<Father> listFathers(Date regDate);
	
	/**
	 * 
	 * @param regDate
	 * @return
	 */
	List<Mother> listMothers(Date regDate);
	
	/**
	 * 
	 * @param regDate
	 * @return
	 */
	List<Informant> listInformants(Date regDate);
	
	/**
	 * 
	 * @param countryStateId
	 * @return
	 */
	List<HomeAddress> listHomeAddresses(Long countryStateId);

	/**
	 * 
	 * @return
	 */
	List<CountryState> listDefaultCountryStates();
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteCountryStates(String param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteOccupations(String param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteFathers(String param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteMothers(String param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteInformants(String param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteAddresses(String param);
	
	/**
	 * 
	 * @return
	 */
	Country fetchDefaultCountry();

	/**
	 * 
	 * @param informant
	 * @return
	 */
	String stringifyInformant(Informant informant);

	/**
	 * 
	 * @param father
	 * @return
	 */
	String stringifyFather(Father father);

	/**
	 * 
	 * @param mother
	 * @return
	 */
	String stringifyMother(Mother mother);

	/**
	 * 
	 * @param address
	 * @return
	 */
	String stringifyAddress(HomeAddress address);

	/**
	 * 
	 * @param occupation
	 * @return
	 */
	String stringifyOccupation(Occupation occupation);

	/**
	 * 
	 * @param educationLevel
	 * @return
	 */
	String stringifyEducationLevel(EducationLevel educationLevel);

	/**
	 * 
	 * @param ethnicity
	 * @return
	 */
	String stringifyEthnicity(Ethnicity ethnicity);

	/**
	 * 
	 * @param countryState
	 * @return
	 */
	String stringifyCountryState(CountryState countryState);

	/**
	 * 
	 * @param lga
	 * @return
	 */
	String stringifyLga(LocalGovArea lga);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteEducationLevels(String param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteEthnicities(String param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	String autocompleteCountries(String param);

	/**
	 * 
	 * @param param
	 * @param stateId 
	 * @return
	 */
	String autocompleteLgas(String param, Long stateId);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<BirthAttestation> searchBirthAttns(String param);

}
