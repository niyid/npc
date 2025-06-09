<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Birth Attestations</title>
	</head>
	<body>
		<s:form id="birthAttnFilterForm" action="birthAttnSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="birthAttnSearchButton" button="true" buttonIcon="gear" formIds="birthAttnFilterForm">Search</sjm:a>

		<s:url var="manageFatherUrl" action="fatherList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageFatherUrl}" id="manageFatherButton" button="true" buttonIcon="gear" dataTheme="a">Manage Fathers</sjm:a>
		
		<s:url var="manageMotherUrl" action="motherList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageMotherUrl}" id="manageMotherButton" button="true" buttonIcon="gear" dataTheme="a">Manage Mothers</sjm:a>
				
		<s:url var="manageInformantUrl" action="informantList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageInformantUrl}" id="manageInformantButton" button="true" buttonIcon="gear" dataTheme="a">Manage Informants</sjm:a>
	
		<s:url var="manageAddressUrl" action="addressList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageAddressUrl}" id="manageAddressButton" button="true" buttonIcon="gear" dataTheme="a">Manage Addresses</sjm:a>
					
		<s:url var="newBirthAttnUrl" action="birthAttnRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newBirthAttnUrl}" id="newBirthAttnButton" button="true" buttonIcon="gear" dataTheme="a">New Birth Attestation</sjm:a>
	
		<s:url var="birthAttnSelUrl" action="birthAttnRecord!select" namespace="/agent/ajax" />
		<sjm:list
		    id="birthAttnListView"
		    inset="true"
		    list="birthAttns"
		    listKey="top.id"
		    listValue="top.firstName + ' ' + top.middleName + (top.middleName != null && !top.middleName.isEmpty() ? ' ' : '') + top.lastName"
		    listHref="%{birthAttnSelUrl}"
		    listParam="certId"
		/>	
	</body>
</html>
