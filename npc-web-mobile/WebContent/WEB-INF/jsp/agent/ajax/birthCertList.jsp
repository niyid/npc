<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Birth Registrations</title>
	</head>
	<body>
		<s:form id="birthCertFilterForm" action="birthCertSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="birthCertSearchButton" button="true" buttonIcon="gear" formIds="birthCertFilterForm">Search</sjm:a>

		<s:url var="manageFatherUrl" action="fatherList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageFatherUrl}" id="manageFatherButton" button="true" buttonIcon="gear" dataTheme="a">Manage Fathers</sjm:a>
		
		<s:url var="manageMotherUrl" action="motherList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageMotherUrl}" id="manageMotherButton" button="true" buttonIcon="gear" dataTheme="a">Manage Mothers</sjm:a>
				
		<s:url var="manageInformantUrl" action="informantList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageInformantUrl}" id="manageInformantButton" button="true" buttonIcon="gear" dataTheme="a">Manage Informants</sjm:a>
			
		<s:url var="newBirthCertUrl" action="birthCertRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newBirthCertUrl}" id="newBirthCertButton" button="true" buttonIcon="gear" dataTheme="a">New Birth Registration</sjm:a>
	
		<s:url var="birthCertSelUrl" action="birthCertRecord!select" namespace="/agent/ajax" />
		<sjm:list
		    id="birthCertListView"
		    inset="true"
		    list="birthCerts"
		    listKey="top.certificateNumber"
		    listValue="top.certificateNumber + ' - ' + top.firstName + ' ' + top.middleName + (top.middleName != null && !top.middleName.isEmpty() ? ' ' : '') + top.lastName"
		    listHref="%{birthCertSelUrl}"
		    listParam="certId"
		/>	
	</body>
</html>
