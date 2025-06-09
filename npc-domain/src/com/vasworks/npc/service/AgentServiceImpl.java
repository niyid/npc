package com.vasworks.npc.service;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sun.media.jai.codec.SeekableStream;
import com.vasworks.entity.Country;
import com.vasworks.npc.model.BirthAttestation;
import com.vasworks.npc.model.BirthCert;
import com.vasworks.npc.model.CountryState;
import com.vasworks.npc.model.DeathCert;
import com.vasworks.npc.model.EducationLevel;
import com.vasworks.npc.model.Ethnicity;
import com.vasworks.npc.model.Father;
import com.vasworks.npc.model.HomeAddress;
import com.vasworks.npc.model.ImageFile;
import com.vasworks.npc.model.Informant;
import com.vasworks.npc.model.Kinship;
import com.vasworks.npc.model.LocalGovArea;
import com.vasworks.npc.model.Mother;
import com.vasworks.npc.model.Occupation;
import com.vasworks.npc.model.RequestReasonA;
import com.vasworks.npc.model.RequestReasonB;
import com.vasworks.service.DaoServiceImpl;

@Transactional
public class AgentServiceImpl extends DaoServiceImpl implements AgentService {
	
	private static HashMap<String, String> IMAGE_ENCODING_TYPE_MAP = new HashMap<String, String>();
	
	private static List<String> PROP_NAMES_DEATH_CERT = new ArrayList<String>();
	private static List<String> PROP_NAMES_BIRTH_CERT = new ArrayList<String>();
	private static List<String> PROP_NAMES_BIRTH_ATTN = new ArrayList<String>();
	private static List<String> PROP_NAMES_FATHER = new ArrayList<String>();
	private static List<String> PROP_NAMES_MOTHER = new ArrayList<String>();
	private static List<String> PROP_NAMES_INFORMANT = new ArrayList<String>();
	private static List<String> PROP_NAMES_ADDRESS = new ArrayList<String>();
	private static List<String> PROP_NAMES_ETHNICITY = new ArrayList<String>();
	private static List<String> PROP_NAMES_EDUCATIONAL_LEVEL = new ArrayList<String>();
	private static List<String> PROP_NAMES_LGA = new ArrayList<String>();
	private static List<String> PROP_NAMES_COUNTRY = new ArrayList<String>();
	
	static {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		
		IMAGE_ENCODING_TYPE_MAP.put("JPEG", "image/jpeg:jpg");
		IMAGE_ENCODING_TYPE_MAP.put("GIF", "image/gif:gif");
		IMAGE_ENCODING_TYPE_MAP.put("TIFF", "image/tiff:tif");
		IMAGE_ENCODING_TYPE_MAP.put("PNG", "image/png:png");
		IMAGE_ENCODING_TYPE_MAP.put("BMP", "image/bmp:bmp");
		
		PROP_NAMES_DEATH_CERT.add("regDate");
		PROP_NAMES_DEATH_CERT.add("deathPlace");
		PROP_NAMES_DEATH_CERT.add("deathCause");
		PROP_NAMES_DEATH_CERT.add("firstName");
		PROP_NAMES_DEATH_CERT.add("middleName");
		PROP_NAMES_DEATH_CERT.add("lastName");
		PROP_NAMES_DEATH_CERT.add("maidenName");
		PROP_NAMES_DEATH_CERT.add("birthDate");
		PROP_NAMES_DEATH_CERT.add("deathPlace");
		
		PROP_NAMES_BIRTH_CERT.add("regDate");
		PROP_NAMES_BIRTH_CERT.add("birthDate");
		PROP_NAMES_BIRTH_CERT.add("firstName");
		PROP_NAMES_BIRTH_CERT.add("middleName");
		PROP_NAMES_BIRTH_CERT.add("lastName");
		PROP_NAMES_BIRTH_CERT.add("informant.firstName");
		PROP_NAMES_BIRTH_CERT.add("informant.middleName");
		PROP_NAMES_BIRTH_CERT.add("informant.lastName");
		PROP_NAMES_BIRTH_CERT.add("father.firstName");
		PROP_NAMES_BIRTH_CERT.add("father.middleName");
		PROP_NAMES_BIRTH_CERT.add("father.lastName");	
		PROP_NAMES_BIRTH_CERT.add("mother.firstName");
		PROP_NAMES_BIRTH_CERT.add("mother.middleName");
		PROP_NAMES_BIRTH_CERT.add("mother.lastName");
		
		PROP_NAMES_FATHER.add("regDate");
		PROP_NAMES_FATHER.add("firstName");
		PROP_NAMES_FATHER.add("middleName");
		PROP_NAMES_FATHER.add("lastName");	
		
		PROP_NAMES_MOTHER.add("regDate");
		PROP_NAMES_MOTHER.add("firstName");
		PROP_NAMES_MOTHER.add("middleName");
		PROP_NAMES_MOTHER.add("lastName");
		PROP_NAMES_MOTHER.add("maidenName");
		
		PROP_NAMES_INFORMANT.add("regDate");
		PROP_NAMES_INFORMANT.add("firstName");
		PROP_NAMES_INFORMANT.add("middleName");
		PROP_NAMES_INFORMANT.add("lastName");	
		
		PROP_NAMES_ADDRESS.add("areaName1");
		PROP_NAMES_ADDRESS.add("areaName2");
		PROP_NAMES_ADDRESS.add("streetName");

		PROP_NAMES_ETHNICITY.add("description");
		
		PROP_NAMES_EDUCATIONAL_LEVEL.add("description");
		
		PROP_NAMES_LGA.add("lgaName");
		
		PROP_NAMES_COUNTRY.add("countryName");
		PROP_NAMES_COUNTRY.add("countryCode");
		
		PROP_NAMES_BIRTH_ATTN.add("regDate");
		PROP_NAMES_BIRTH_ATTN.add("birthDate");
		PROP_NAMES_BIRTH_ATTN.add("firstName");
		PROP_NAMES_BIRTH_ATTN.add("middleName");
		PROP_NAMES_BIRTH_ATTN.add("lastName");
		PROP_NAMES_BIRTH_ATTN.add("informant.firstName");
		PROP_NAMES_BIRTH_ATTN.add("informant.middleName");
		PROP_NAMES_BIRTH_ATTN.add("informant.lastName");
		PROP_NAMES_BIRTH_ATTN.add("father.firstName");
		PROP_NAMES_BIRTH_ATTN.add("father.middleName");
		PROP_NAMES_BIRTH_ATTN.add("father.lastName");	
		PROP_NAMES_BIRTH_ATTN.add("mother.firstName");
		PROP_NAMES_BIRTH_ATTN.add("mother.middleName");
		PROP_NAMES_BIRTH_ATTN.add("mother.lastName");
	}
	
	public static final Log LOG = LogFactory.getLog(AgentServiceImpl.class);
	
	private Query createSearchQuery(String className, List<String> properties, String param) {
		String[] paramTokens = param.split(" ");
		
		StringBuilder b = new StringBuilder("select o from " + className + " o");
		
		if(paramTokens != null && paramTokens.length > 0) {
			b.append(" where ");
			boolean start = true;
			for(int idx = 0; idx < paramTokens.length; idx++) {
				for(String prop : properties) {
					if(!start) {
						b.append(" or");
					} else {
						start = false;
					}
					b.append(" o.");
					b.append(prop);
					b.append(" like ");
					b.append("'%");
					b.append(paramTokens[idx]);
					b.append("%'");
				}
			}			
		}
		
		LOG.debug("createSearchQuery - " + b);
		
		Query q = entityManager.createQuery(b.toString());
	
		return q;
	}

	
	private Query createSearchQueryForLga(List<String> properties, String param, Long stateId) {
		String[] paramTokens = param.split(" ");
		
		StringBuilder b = new StringBuilder("select o from LocalGovArea o");
		
		if(paramTokens != null && paramTokens.length > 0) {
			b.append(" where (");
			boolean start = true;
			for(int idx = 0; idx < paramTokens.length; idx++) {
				for(String prop : properties) {
					if(!start) {
						b.append(" or");
					} else {
						start = false;
					}
					b.append(" o.");
					b.append(prop);
					b.append(" like ");
					b.append("'%");
					b.append(paramTokens[idx]);
					b.append("%'");
				}
			}			
		}
		
		b.append(") and countryState.id = ");
		b.append(stateId);
		
		LOG.debug("createSearchQuery - " + b);
		
		Query q = entityManager.createQuery(b.toString());
	
		return q;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DeathCert> searchDeathCerts(String param) {
		LOG.debug("searchDeathCerts - " + param);
		
		Query q = createSearchQuery("DeathCert", PROP_NAMES_DEATH_CERT, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BirthCert> searchBirthCerts(String param) {
		LOG.debug("searchBirthCerts - " + param);
		
		Query q = createSearchQuery("BirthCert", PROP_NAMES_BIRTH_CERT, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Father> searchFathers(String param) {
		LOG.debug("searchFathers - " + param);
		
		Query q = createSearchQuery("Father", PROP_NAMES_FATHER, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mother> searchMothers(String param) {
		LOG.debug("searchMothers - " + param);
		
		Query q = createSearchQuery("Mother", PROP_NAMES_MOTHER, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Informant> searchInformants(String param) {
		LOG.debug("searchInformants - " + param);
		
		Query q = createSearchQuery("Informant", PROP_NAMES_INFORMANT, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ethnicity> searchEthnicities(String param) {
		LOG.debug("searchEthnicities - " + param);
		
		Query q = createSearchQuery("Ethnicity", PROP_NAMES_ETHNICITY, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EducationLevel> searchEducationLevels(String param) {
		LOG.debug("searchEducationLevels - " + param);
		
		Query q = createSearchQuery("EducationLevel", PROP_NAMES_EDUCATIONAL_LEVEL, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalGovArea> searchLgas(String param, Long stateId) {
		LOG.debug("searchLgas - " + param + " " + stateId);
		
		Query q = createSearchQueryForLga(PROP_NAMES_LGA, param, stateId);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HomeAddress> searchAddresses(String param) {
		LOG.debug("searchAddresses - " + param);
		
		Query q = createSearchQuery("HomeAddress", PROP_NAMES_ADDRESS, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BirthAttestation> searchBirthAttns(String param) {
		LOG.debug("searchBirthAttns - " + param);
		
		Query q = createSearchQuery("BirthAttestation", PROP_NAMES_BIRTH_ATTN, param);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	private List<Country> searchCountries(String param) {
		LOG.debug("searchCountries - " + param);
		
		Query q = createSearchQuery("Country", PROP_NAMES_COUNTRY, param);
		
		return q.getResultList();
	}	
	@Override
	public List<BirthAttestation> saveBirthAttn(Long attnId, BirthAttestation birthAttn, Long fatherId, Long motherId, Long informantId, Long birthStateId, Long lgaId,
			Long addressId, Long workAddressId, Long educationLevelId, Long occupationId, Long requestReasonAId, Long requestReasonBId,
			String requestCountryId, File photo, Long photoId, Long userId) {
		LOG.debug("saveBirthAttn - " + attnId + " " + birthAttn + " " + fatherId + " " + motherId + " " + informantId + " " + userId);
		
		Father father = (Father) find(fatherId, Father.class);
		birthAttn.setFather(father);
		
		Mother mother = (Mother) find(motherId, Mother.class);
		birthAttn.setMother(mother);
		
		Informant informant = (Informant) find(informantId, Informant.class);
		birthAttn.setInformant(informant);
		
		CountryState countryState = (CountryState) find(birthStateId, CountryState.class);
		birthAttn.setBirthState(countryState);
		
		LocalGovArea lga = (LocalGovArea) find(lgaId, LocalGovArea.class);
		birthAttn.setBirthLga(lga);
		
		HomeAddress address = (HomeAddress) find(addressId, HomeAddress.class);
		birthAttn.setAddress(address);
		
		HomeAddress workAddress = (HomeAddress) find(workAddressId, HomeAddress.class);
		birthAttn.setWorkAddress(workAddress);
		
		EducationLevel educationLevel = (EducationLevel) find(educationLevelId, EducationLevel.class);
		birthAttn.setEducationLevel(educationLevel);
		
		Occupation occupation = (Occupation) find(occupationId, Occupation.class);
		birthAttn.setOccupation(occupation);
		
		RequestReasonA requestReasonA = (RequestReasonA) find(requestReasonAId, RequestReasonA.class);
		birthAttn.setRequestReasonA(requestReasonA);
		
		RequestReasonB requestReasonB = (RequestReasonB) find(requestReasonBId, RequestReasonB.class);
		birthAttn.setRequestReasonB(requestReasonB);
		
		Country country = (Country) find(requestCountryId, Country.class);
		birthAttn.setRequestCountry(country);
		
		if(attnId != null) {
			birthAttn.setId(attnId);
			update(birthAttn, userId);
		} else {
			birthAttn.setRegDate(new Date());
			insert(birthAttn, userId);
		}
		
		return listBirthAttns(new Date());
	}
	
	@Override
	public List<DeathCert> saveDeathCert(Long certId, DeathCert deathCert, Long addressId, Long informantId, Long userId) {
		LOG.debug("saveDeathCert - " + certId + " " + deathCert + " " + addressId + " " + informantId + " " + userId);
		
		HomeAddress address = (HomeAddress) find(addressId, HomeAddress.class);
		deathCert.setAddress(address);
		
		Informant informant = (Informant) find(informantId, Informant.class);
		deathCert.setInformant(informant);
		
		if(certId != null) {
			deathCert.setId(certId);
			update(deathCert, userId);
		} else {
			deathCert.setRegDate(new Date());
			insert(deathCert, userId);
		}
		
		return listDeathCerts(new Date());
	}

	@Override
	public List<BirthCert> saveBirthCert(BirthCert birthCert, Long fatherId, Long motherId, Long informantId, Long countryStateId, Long lgaId, Long kinshipId, Long userId) {
		LOG.debug("saveBirthCert - " + birthCert + " " + fatherId + " " + motherId + " " + informantId + " " + countryStateId + " " + lgaId + " " + kinshipId + " " + userId);
		
		Father father = (Father) find(fatherId, Father.class);
		birthCert.setFather(father);
		
		Mother mother = (Mother) find(motherId, Mother.class);
		birthCert.setMother(mother);
		
		Informant informant = (Informant) find(informantId, Informant.class);
		birthCert.setInformant(informant);
		
		CountryState countryState = (CountryState) find(countryStateId, CountryState.class);
		birthCert.setCountryState(countryState);
		
		LocalGovArea lga = (LocalGovArea) find(lgaId, LocalGovArea.class);
		birthCert.setLga(lga);
		
		Kinship kinship = (Kinship) find(kinshipId, Kinship.class);
		birthCert.setInformantKinship(kinship);
		
		if(birthCert.getCertificateNumber() != null) {
			update(birthCert, userId);
		} else {
			birthCert.setRegDate(new Date());
			insert(birthCert, userId);
		}
		
		return listBirthCerts(new Date());
	}

	@Override
	public List<Father> saveFather(Long fatherId, Father father, Long occupationId, Long educationLevelId, Long ethnicityId, Long countryStateId, Long lgaId, File photo, Long photoId, Long userId) {
		LOG.debug("saveFather - " + fatherId + " " + father + " " + occupationId + " " + educationLevelId + " " + ethnicityId + " " + countryStateId + " " + lgaId + " " + userId);
		
		Occupation occupation = (Occupation) find(occupationId, Occupation.class);
		father.setOccupation(occupation);
		
		CountryState countryState = (CountryState) find(countryStateId, CountryState.class);
		father.setCountryState(countryState);
		
		LocalGovArea lga = (LocalGovArea) find(lgaId, LocalGovArea.class);
		father.setLga(lga);
		
		EducationLevel educationLevel = (EducationLevel) find(educationLevelId, EducationLevel.class);
		father.setEducationLevel(educationLevel);
		
		if(photo != null) {
			father.setPhoto(saveImageFile(photoId, photo, userId));
		}
	
		if(fatherId != null) {
			father.setId(fatherId);
			update(father, userId);
		} else {
			father.setRegDate(new Date());
			insert(father, userId);
		}
		
		return listFathers(new Date());
	}

	@Override
	public List<Mother> saveMother(Long motherId, Mother mother, Long occupationId, Long educationLevelId, Long ethnicityId, Long countryStateId, Long lgaId, File photo, Long photoId, Long userId) {
		LOG.debug("saveMother - " + motherId + " " + mother + " " + occupationId + " " + educationLevelId + " " + ethnicityId + " " + countryStateId + " " + lgaId + " " + userId);
		
		Occupation occupation = (Occupation) find(occupationId, Occupation.class);
		mother.setOccupation(occupation);
		
		CountryState countryState = (CountryState) find(countryStateId, CountryState.class);
		mother.setCountryState(countryState);
		
		LocalGovArea lga = (LocalGovArea) find(lgaId, LocalGovArea.class);
		mother.setLga(lga);
		
		EducationLevel educationLevel = (EducationLevel) find(educationLevelId, EducationLevel.class);
		mother.setEducationLevel(educationLevel);
		
		if(photo != null) {
			mother.setPhoto(saveImageFile(photoId, photo, userId));
		}
		
		if(motherId != null) {
			mother.setId(motherId);
			update(mother, userId);
		} else {
			mother.setRegDate(new Date());
			insert(mother, userId);
		}
		
		return listMothers(new Date());
	}
	
	@Override
	public List<Informant> saveInformant(Long informantId, Informant informant, Long addressId, File photo, Long photoId, String signature, Long signatureId, Long userId) {
		LOG.debug("saveInformant - " + informantId + " " + informant + " " + addressId + " " + photo + " " + signature + " " + photoId + " " + signature + " " + signatureId + " " + userId);
		
		if(addressId != null) {
			HomeAddress homeAddress = (HomeAddress) find(addressId, HomeAddress.class);
			informant.setAddress(homeAddress);
		}
		
		if(photo != null) {
			informant.setPhoto(saveImageFile(photoId, photo, userId));
		}
		
		if(signature != null) {
			informant.setSignature(saveSignatureFile(signatureId, signature, userId));
		}
		
		if(informantId != null) {
			informant.setId(informantId);
			update(informant, userId);
		} else {
			informant.setRegDate(new Date());
			insert(informant, userId);
		}
		
		return listInformants(new Date());
	}
	
	@Override
	public List<HomeAddress> saveHomeAddress(Long addressId, HomeAddress address, Long countryStateId, Long userId) {
		LOG.debug("saveHomeAddress - " + addressId + " " + address + " " + countryStateId + " " + userId);
		
		CountryState countryState = (CountryState) find(countryStateId, CountryState.class);
		address.setCountryState(countryState );
		if(addressId != null) {
			address.setId(addressId);
			update(address, userId);
		} else {
			insert(address, userId);
		}
		
		return listHomeAddresses(countryStateId);
	}
	
	protected ImageFile saveImageFile(Long fileId, File image, Long userId) {
		LOG.debug("saveImageFile - " + fileId + " " + image + " " + userId);
		
		ImageFile imageFile = fileId != null ? (ImageFile) find(fileId, ImageFile.class) : new ImageFile();
		
		if(image != null) {
			try {
				imageFile.setImageData(resizeImage(Files.readAllBytes(image.toPath()), 400, "JPEG"));
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		if(fileId != null) {
			imageFile.setId(fileId);
			update(imageFile, userId);
		} else {
			imageFile.setRegDate(new Date());
			insert(imageFile, userId);
		}
		
		return imageFile;
	}
	
	
	protected ImageFile saveSignatureFile(Long fileId, String signatureData, Long userId) {
		LOG.debug("saveSignatureFile - " + fileId + " " + signatureData + " " + userId);
		
		ImageFile imageFile = fileId != null ? (ImageFile) find(fileId, ImageFile.class) : new ImageFile();
		
		String procSign = signatureData.replace("data:image/png;base64,", "");
		
		imageFile.setImageData(Base64.decodeBase64(procSign));
		
		if(fileId != null) {
			imageFile.setId(fileId);
			update(imageFile, userId);
		} else {
			imageFile.setRegDate(new Date());
			insert(imageFile, userId);
		}
		
		return imageFile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BirthAttestation> listBirthAttns(Date regDate) {
		LOG.debug("listBirthAttns - " + regDate);
		
		Query q = entityManager.createQuery(LIST_BIRTH_CERTS);
		
		q.setParameter("regDate", regDate);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeathCert> listDeathCerts(Date regDate) {
		LOG.debug("listDeathCerts - " + regDate);
		
		Query q = entityManager.createQuery(LIST_DEATH_CERTS);
		
		q.setParameter("regDate", regDate);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BirthCert> listBirthCerts(Date regDate) {
		LOG.debug("listDeathCerts - " + regDate);
		
		Query q = entityManager.createQuery(LIST_BIRTH_CERTS);
		
		q.setParameter("regDate", regDate);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Father> listFathers(Date regDate) {
		LOG.debug("listFathers - " + regDate);
		
		Query q = entityManager.createQuery(LIST_FATHERS);
		
		q.setParameter("regDate", regDate);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mother> listMothers(Date regDate) {
		LOG.debug("listMothers - " + regDate);
		
		Query q = entityManager.createQuery(LIST_MOTHERS);
		
		q.setParameter("regDate", regDate);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Informant> listInformants(Date regDate) {
		LOG.debug("listInformants - " + regDate);
		
		Query q = entityManager.createQuery(LIST_INFORMANTS);
		
		q.setParameter("regDate", regDate);
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CountryState> listCountryStates(String countryCode) {
		LOG.debug("listCountryStates - " + countryCode);
		Query q = entityManager.createQuery(LIST_COUNTRY_STATES);
		
		q.setParameter("countryCode", countryCode);
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HomeAddress> listHomeAddresses(Long countryStateId) {
		LOG.debug("listHomeAddress - " + countryStateId);
		Query q = entityManager.createQuery(LIST_ADDRESSES_FOR_STATE);
		
		q.setParameter("countryStateId", countryStateId);
		
		return q.getResultList();
	}
	
	@Override
	public Country fetchDefaultCountry() {
		LOG.debug("fetchDefaultCountry()");
		return (Country) find("NG", Country.class);
	}

	@Override
	public List<CountryState> listDefaultCountryStates() {
		LOG.debug("listDefaultCountryStates()");
		Country c = fetchDefaultCountry();
		return listCountryStates(c.getCountryCode());
	}

	@SuppressWarnings("unchecked")
	@Override
	public String autocompleteCountryStates(String param) {
		LOG.debug("autocompleteCountryStates - " + param);
		
		Country c = fetchDefaultCountry();
		
		Query q = entityManager.createQuery(FILTER_COUNTRY_STATE);
		
		q.setParameter("param", "%" + param + "%");
		q.setParameter("countryCode", c.getCountryCode());
		
		List<Object[]> results = q.getResultList();
		
		return convert2Json(results);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String autocompleteOccupations(String param) {
		LOG.debug("autocompleteOccupations - " + param);
		
		Query q = entityManager.createQuery(FILTER_OCCUPATION);
		
		q.setParameter("param", "%" + param + "%");
		
		List<Object[]> results = q.getResultList();
		
		return convert2Json(results);
	}

	@Override
	public String autocompleteFathers(String param) {
		LOG.debug("autocompleteFathers - " + param);
		
		List<Father> results = searchFathers(param);
		
		return convertFather2Json(results);
	}

	@Override
	public String autocompleteMothers(String param) {
		LOG.debug("autocompleteMothers - " + param);
		
		List<Mother> results = searchMothers(param);
		
		return convertMother2Json(results);
	}

	@Override
	public String autocompleteInformants(String param) {
		LOG.debug("autocompleteInformants - " + param);
		
		List<Informant> results = searchInformants(param);
		
		return convertInformant2Json(results);
	}

	@Override
	public String autocompleteAddresses(String param) {
		LOG.debug("autocompleteAddresses - " + param);
		
		List<HomeAddress> results = searchAddresses(param);
		
		return convertAddress2Json(results);
	}

	@Override
	public String autocompleteEducationLevels(String param) {
		LOG.debug("autocompleteEducationLevels - " + param);
		
		List<EducationLevel> results = searchEducationLevels(param);
		
		return convertEducationLevel2Json(results);
	}

	@Override
	public String autocompleteEthnicities(String param) {
		LOG.debug("autocompleteEthnicities - " + param);
		
		List<Ethnicity> results = searchEthnicities(param);
		
		return convertEthnicity2Json(results);
	}

	@Override
	public String autocompleteLgas(String param, Long stateId) {
		LOG.debug("autocompleteLgas - " + param + " " + stateId);
		
		List<LocalGovArea> results = searchLgas(param, stateId);
		
		return convertLga2Json(results);
	}

	@Override
	public String autocompleteCountries(String param) {
		LOG.debug("autocompleteCountries - " + param);
		
		List<Country> results = searchCountries(param);
		
		return convertCountry2Json(results);
	}

	private String convert2Json(List<Object[]> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(Object[] row : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(row[0]);
				b.append(",\"label\":\"");
				b.append(row[1]);
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertAddress2Json(List<HomeAddress> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(HomeAddress addr : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(addr.getId());
				b.append(",\"label\":\"");
				b.append(stringifyAddress(addr));
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertInformant2Json(List<Informant> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(Informant e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getId());
				b.append(",\"label\":\"");
				b.append(e.getFirstName());
				b.append(" ");
				b.append(e.getMiddleName());
				b.append((e.getMiddleName() != null && !e.getMiddleName().isEmpty()) ? " " : "");
				b.append(e.getLastName());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertFather2Json(List<Father> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(Father e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getId());
				b.append(",\"label\":\"");
				b.append(e.getFirstName());
				b.append(" ");
				b.append(e.getMiddleName());
				b.append((e.getMiddleName() != null && !e.getMiddleName().isEmpty()) ? " " : "");
				b.append(e.getLastName());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertMother2Json(List<Mother> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(Mother e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getId());
				b.append(",\"label\":\"");
				b.append(e.getFirstName());
				b.append(" ");
				b.append(e.getMiddleName());
				b.append((e.getMiddleName() != null && !e.getMiddleName().isEmpty()) ? " " : "");
				b.append(e.getLastName());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertEthnicity2Json(List<Ethnicity> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(Ethnicity e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getId());
				b.append(",\"label\":\"");
				b.append(e.getDescription());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertEducationLevel2Json(List<EducationLevel> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(EducationLevel e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getId());
				b.append(",\"label\":\"");
				b.append(e.getDescription());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertLga2Json(List<LocalGovArea> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(LocalGovArea e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getId());
				b.append(",\"label\":\"");
				b.append(e.getLgaName());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	private String convertCountry2Json(List<Country> results) {
		StringBuilder b = new StringBuilder("[");
		if(results != null) {
			boolean first = true;
			for(Country e : results) {
				if(!first) {
					b.append(",");
				} {
					first = false;
				}
				b.append("{\"value\":");
				b.append(e.getCountryCode());
				b.append(",\"label\":\"");
				b.append(e.getCountryName());
				b.append("\"}");
			}
		}
		b.append("]");
		
		return b.toString();
	}
	
	@Override
	public String stringifyInformant(Informant informant) {
		StringBuilder b = new StringBuilder(informant.getFirstName());
		b.append(" ");
		b.append(informant.getMiddleName());
		b.append((informant.getMiddleName() != null && !informant.getMiddleName().isEmpty()) ? " " : "");
		b.append(informant.getLastName());
		
		return b.toString();
	}
	
	@Override
	public String stringifyFather(Father father) {
		StringBuilder b = new StringBuilder(father.getFirstName());
		b.append(" ");
		b.append(father.getMiddleName());
		b.append((father.getMiddleName() != null && !father.getMiddleName().isEmpty()) ? " " : "");
		b.append(father.getLastName());
		
		return b.toString();
	}
	
	@Override
	public String stringifyMother(Mother mother) {
		StringBuilder b = new StringBuilder(mother.getFirstName());
		b.append(" ");
		b.append(mother.getMiddleName());
		b.append((mother.getMiddleName() != null && !mother.getMiddleName().isEmpty()) ? " " : "");
		b.append(mother.getLastName());
		
		return b.toString();
	}
	
	@Override
	public String stringifyAddress(HomeAddress address) {
		StringBuilder b = new StringBuilder(address.getAreaName1());
		b.append(", ");
		b.append(address.getStreetName());
		b.append(", ");
		b.append(address.getPropertyNumber());
		b.append(", ");
		b.append(address.getCountryState().getStateName());
		b.append(", ");
		b.append(address.getCountryState().getCountry().getCountryName());
		
		return b.toString();
	}

	@Override
	public String stringifyOccupation(Occupation occupation) {
		return occupation.getDescription();
	}

	@Override
	public String stringifyEducationLevel(EducationLevel educationLevel) {
		return educationLevel.getDescription();
	}

	@Override
	public String stringifyEthnicity(Ethnicity ethnicity) {
		return ethnicity.getDescription();
	}

	@Override
	public String stringifyCountryState(CountryState countryState) {
		StringBuilder b = new StringBuilder("[");
		b.append(countryState.getStateCode());
		b.append("] ");
		b.append(countryState.getStateName());
		
		return b.toString();
	}

	@Override
	public String stringifyLga(LocalGovArea lga) {
		return lga.getLgaName();
	}
	
    protected String[] mimeTypeAndFileExtension(String encodingType) {
    	LOG.debug("mimeTypeAndFileExtension - " + encodingType);
    	
    	String val = IMAGE_ENCODING_TYPE_MAP.get(encodingType);
    	
    	String[] pair = null;
    	if(val != null) {
    		pair = val.split(":");
    	}
    	
    	LOG.debug("mimeTypeAndFileExtension - " + (pair != null ? Arrays.asList(pair) : pair));
    	
    	return pair;
    }
	
	protected byte[] resizeImageUsingEncoding(byte[] pImageData, int pMaxWidth, String encodingFormat) throws IOException {
	    InputStream imageInputStream = new ByteArrayInputStream(pImageData);
	    // read in the original image from an input stream
	    SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
	    RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
	    ((OpImage) originalImage.getRendering()).setTileCache(null);
	    int origImageWidth = originalImage.getWidth();
	    // now resize the image
	    double scale = 1.0;
	    if (pMaxWidth > 0 && origImageWidth > pMaxWidth) {
	        scale = (double) pMaxWidth / originalImage.getWidth();
	    }
	    ParameterBlock paramBlock = new ParameterBlock();
	    paramBlock.addSource(originalImage); // The source image
	    paramBlock.add(scale); // The xScale
	    paramBlock.add(scale); // The yScale
	    paramBlock.add(0.0); // The x translation
	    paramBlock.add(0.0); // The y translation
	 
	    RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_RENDERING,
	        RenderingHints.VALUE_RENDER_QUALITY);
	 
	    RenderedOp resizedImage = JAI.create(JAI_SUBSAMPLE_AVERAGE_ACTION, paramBlock, qualityHints);
	 
	    // lastly, write the newly-resized image to an output stream, in a specific encoding
	    ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
	    JAI.create(JAI_ENCODE_ACTION, resizedImage, encoderOutputStream, encodingFormat, null);
	    // Export to Byte Array
	    byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
	    return resizedImageByteArray;
	}
	
	protected byte[] resizeImage(byte[] originalImageData, int maxImageWidth, String encodingFormat) {
		byte[] finalImageData = originalImageData;
		if(originalImageData.length > 10240) {
			try {
				finalImageData = resizeImageUsingEncoding(originalImageData, maxImageWidth, encodingFormat);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return finalImageData;
	}
}
