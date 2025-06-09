<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Death Registrations</title>
	</head>
	<body>
		<s:form id="deathCertFilterForm" action="deathCertSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="deathCertSearchButton" button="true" buttonIcon="gear" formIds="deathCertFilterForm" dataTheme="a">Search</sjm:a>
	
		<s:url var="manageAddressUrl" action="addressList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageAddressUrl}" id="manageAddressButton" button="true" buttonIcon="gear" dataTheme="a">Manage Addresses</sjm:a>
				
		<s:url var="manageInformantUrl" action="informantList" namespace="/agent/ajax" />	
		<sjm:a href="%{manageInformantUrl}" id="manageInformantButton" button="true" buttonIcon="gear" dataTheme="a">Manage Informants</sjm:a>

		<s:url var="newDeathCertUrl" action="deathCertRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newDeathCertUrl}" id="newDeathCertButton" button="true" buttonIcon="gear" dataTheme="a">New Death Registration</sjm:a>
		
		<s:url var="deathCertSelUrl" action="deathCertRecord!select" namespace="/agent/ajax" />
		<sjm:list
		    id="deathCertListView"
		    inset="true"
		    list="deathCerts"
		    listKey="top.id"
		    listValue="top.firstName + ' ' + top.middleName + (top.middleName != null && !top.middleName.isEmpty() ? ' ' : '') + top.lastName"
		    listHref="%{deathCertSelUrl}"
		    listParam="certId"
		/>
		<s:include value="/common/msg.jsp" />	
	</body>
</html>
