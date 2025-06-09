<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Informants</title>
	</head>
	<body>
		<s:form id="informantFilterForm" action="informantSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="informantSearchButton" button="true" buttonIcon="gear" formIds="informantFilterForm">Search</sjm:a>
				
		<s:url var="newInformantUrl" action="informantRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newInformantUrl}" id="newInformantButton" button="true" buttonIcon="gear" data-ajax="false">New Informant</sjm:a>
	
		<s:url var="informantSelUrl" action="informantDetails" namespace="/agent/ajax" />
		<sjm:list
		    id="informantListView"
		    inset="true"
		    list="informants"
		    listKey="top.id"
		    listValue="top.firstName + ' ' + top.middleName + (top.middleName != null && !top.middleName.isEmpty() ? ' ' : '') + top.lastName"
		    listHref="%{informantSelUrl}"
		    listParam="informantId"
		/>	
	</body>
</html>
