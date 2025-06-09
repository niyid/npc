<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>Fathers</title>
	</head>
	<body>
		<s:form id="fatherFilterForm" action="fatherSearch" namespace="/agent/ajax" theme="xhtml" method="post">
			<sjm:searchfield name="param" label="Search Parameters" />
		</s:form>
				
		<sjm:a id="fatherSearchButton" button="true" buttonIcon="gear" formIds="fatherFilterForm">Search</sjm:a>
		
		<s:url var="newFatherUrl" action="fatherRecord" namespace="/agent/ajax" />	
		<sjm:a href="%{newFatherUrl}" id="newFatherButton" button="true" buttonIcon="gear">New Father</sjm:a>
	
		<s:url var="fatherSelUrl" action="fatherRecord!select" namespace="/agent/ajax" />
		<sjm:list
		    id="fatherListView"
		    inset="true"
		    list="fathers"
		    listKey="top.id"
		    listValue="top.firstName + ' ' + top.middleName + (top.middleName != null && !top.middleName.isEmpty() ? ' ' : '') + top.lastName"
		    listHref="%{fatherSelUrl}"
		    listParam="fatherId"
		/>	
	</body>
</html>
