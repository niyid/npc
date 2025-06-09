<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Addresses</title>
	</head>
	<body>
		<s:form id="addressFilterForm" action="addressSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="addressSearchButton" button="true" buttonIcon="gear" formIds="addressFilterForm">Search</sjm:a>
				
		<s:url var="newAddressUrl" action="addressRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newAddressUrl}" id="newAddressButton" button="true" buttonIcon="gear" data-ajax="false">New Address</sjm:a>
	
		<s:url var="addressSelUrl" action="addressRecord!select" namespace="/agent/ajax" />
		<sjm:list
		    id="addressListView"
		    inset="true"
		    list="addresses"
		    listKey="top.id"
		    listValue="top.areaName1 + ', ' + top.streetName + ', ' + top.propertyNumber + ', ' + top.countryState.stateName + ', ' + top.countryState.country.countryName"
		    listHref="%{addressSelUrl}"
		    listParam="addressId"
		/>	
	</body>
</html>
