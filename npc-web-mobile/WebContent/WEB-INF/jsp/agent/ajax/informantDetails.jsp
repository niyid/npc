<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>Informant Details</title>
</head>
<body>
	<table>
		<s:if test="entity.photo != null">
		<tr>
			<td><h2>Photo</h2></td>
		</tr>
		<tr>
			<td>
				<img style='border: solid 1px #4f81bd;' src='<s:url action="imageDisplay" namespace="/download"><s:param name="fileId" value="%{entity.photo.id}" /></s:url>' />
			</td>
		</tr>
		</s:if>
		<tr>
			<td><h2>Name</h2></td>
		</tr>
		<tr>
			<td>
				<h3><s:property value="entity.firstName" /> <s:property value="entity.middleName" /> <s:property value="entity.lastName" /></h3>
			</td>
		</tr>
		<tr>
			<td><h2>Address</h2></td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td><h3><s:property value="entity.address.areaName1" /></h3></td>
					</tr>
					<tr>
						<td><h3><s:property value="entity.address.areaName2" /></h3></td>
					</tr>
					<tr>
						<td><h3><s:property value="entity.address.propertyNumber" /></h3></td>
					</tr>
					<tr>
						<td><h3><s:property value="entity.address.countryState.stateName" />, <s:property value="entity.address.countryState.country.countryName" /></h3></td>
					</tr>
				</table>
			</td>	
		</tr>
		<s:if test="entity.signature != null">
		<tr>
			<td><h2>Signature</h2></td>
		</tr>
		<tr>
			<td>
				<img style='border: solid 1px #4f81bd;' src='<s:url action="imageDisplay" namespace="/download" escapeAmp="false"><s:param name="fileId" value="%{entity.signature.id}" /></s:url>' />
			</td>
		</tr>
		</s:if>
		<tr>
			<td>
				<s:url var="informantEditUrl" action="informantRecord!select" namespace="/agent/ajax">
					<s:param name="informantId" value="%{entity.id}" />
				</s:url>
				<sjm:a button="true" buttonIcon="true" href="%{informantEditUrl}">Edit</sjm:a>
			</td>
		</tr>
	</table>
</body>
</html>

