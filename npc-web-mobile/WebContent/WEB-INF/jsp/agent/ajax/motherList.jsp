<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Mothers</title>
	</head>
	<body>
		<s:form id="motherFilterForm" action="motherSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="motherSearchButton" button="true" buttonIcon="gear" formIds="motherFilterForm">Search</sjm:a>
			
		<s:url var="newMotherUrl" action="motherRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newMotherUrl}" id="newMotherButton" button="true" buttonIcon="gear">New Mother</sjm:a>
	
		<s:url var="motherSelUrl" action="motherRecord!select" namespace="/agent/ajax" />
		<sjm:list
		    id="motherListView"
		    inset="true"
		    list="mothers"
		    listKey="top.id"
		    listValue="top.firstName + ' ' + top.middleName + (top.middleName != null && !top.middleName.isEmpty() ? ' ' : '') + top.lastName"
		    listHref="%{motherSelUrl}"
		    listParam="motherId"
		/>	
	</body>
</html>
