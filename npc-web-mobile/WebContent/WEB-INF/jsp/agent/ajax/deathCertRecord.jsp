<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			<s:if test="deathCertId == null">Add</s:if><s:else>Edit</s:else> Death Certificate
		</title>
	</head>
	<body>
		<s:form id="deathCertRecordForm" action="deathCertRecord!save" namespace="/agent/ajax" theme="xhtml" method="post">
			<s:hidden name="certId" />
			<s:hidden name="addressId" id="addressId" />
			<s:hidden name="informantId" id="informantId" />
			<sjm:textfield name="entity.firstName" label="First Name" required="true" />
			<sjm:textfield name="entity.middleName" label="Middle Name" required="false" />
			<sjm:textfield name="entity.lastName" label="Last Name" required="true" />
			<sjm:textfield name="entity.maidenName" label="Maiden Name" required="false" />
			<label for="entity.birthDate">Date of Death</label>
			<input name="entity.deathDate" title="Date of Death" id="entity.deathDate" type="date" data-role="datebox" value="<s:date name='entity.deathDate' format='dd/MM/yyyy' />" data-options='{"mode": "flipbox", "overrideDateFormat": "%d/%m/%Y"}' />
			<sjm:textarea name="entity.deathPlace" label="Place of Death" required="true" />
			<sjm:textarea name="entity.deathCause" label="Cause of Death" required="true" />
			<label for="entity.birthDate">Date of Birth</label>
			<input name="entity.birthDate" title="Date of Birth" id="entity.birthDate" type="date" data-role="datebox" value="<s:date name='entity.birthDate' format='dd/MM/yyyy' />" data-options='{"mode": "flipbox", "overrideDateFormat": "%d/%m/%Y"}' />
			<sjm:textarea name="entity.birthPlace" label="Place of Birth" required="false" />
				
			<sjm:select 
				name="entity.gender" 
				headerKey=""
				headerValue="----------Select-----------"
				list="genderLov"
				listKey="name()"
				listValue="name()"
				emptyOption="false"
				label="Gender" 
				required="true" 
			/>						
		
			<sjm:select 
				name="entity.maritalStatus" 
				headerKey=""
				headerValue="----------Select-----------"
				list="maritalStatusLov"
				listKey="name()"
				listValue="name()"
				emptyOption="false"
				label="Marital Status" 
				required="false" 
			/>	
		
			<s:url var="addressFilterUrl" action="listAddress" namespace="/json" />
		
			<sjm:searchfield id="addressSearchField" name="addressSearchField" placeholder="Search addresses..." label="Address" />
			<ul id="addressListView" data-role="listview" data-inset="true"></ul>
		
			<s:url var="informantFilterUrl" action="listInformant" namespace="/json" />
			
			<sjm:searchfield id="informantSearchField" name="informantSearchField" placeholder="Search informants..." label="Informant" />
			<ul id="informantListView" data-role="listview" data-inset="true"></ul>				
		</s:form>
		<sjm:a button="true" buttonIcon="gear" formIds="deathCertRecordForm" dataTheme="a">Save</sjm:a>
		<s:include value="/common/msg.jsp" />
		
		<script type="text/javascript">		
			$(document).on('pageinit', function(){
				
				$("#addressSearchField").on("input", function() {
					$("#addressSearchField").autocomplete("update", {
						source: '<s:property value="%{#addressFilterUrl}" />?param=' + $('#addressSearchField').val()
					});
				});

				
				$("#addressSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#addressListView'), // the listview to receive results
					source: '<s:property value="%{#addressFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#addressId').val( $a.data('autocomplete').value );
									$('#addressSearchField').val( $a.data('autocomplete').label );
									$("#addressSearchField").autocomplete('clear');
								}, // optional callback function fires upon result selection
					minLength: 3, // minimum length of search string
					transition: 'fade',// page transition, default is fade
					matchFromStart: false, // search from start, or anywhere in the string
					loadingHtml : '<li data-icon="none"><a href="#">Searching...</a></li>', // HTML to display when searching remotely
					interval: 0, // The minimum delay between server calls when using a remote "source"
					builder : null, // optional callback to build HTML for autocomplete
					klass: 'tinted', // optional class name for listview's <li> tag
					forceFirstChoiceOnEnterKey : true,
				});
			});		
		</script>
		<script type="text/javascript">		
			$(document).on('pageinit', function(){
				
				$("#informantSearchField").on("input", function() {
					$("#informantSearchField").autocomplete("update", {
						source: '<s:property value="%{#informantFilterUrl}" />?param=' + $('#informantSearchField').val()
					});
				});

				
				$("#informantSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#informantListView'), // the listview to receive results
					source: '<s:property value="%{#informantFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#informantId').val( $a.data('autocomplete').value );
									$('#informantSearchField').val( $a.data('autocomplete').label );
									$("#informantSearchField").autocomplete('clear');
								}, // optional callback function fires upon result selection
					minLength: 3, // minimum length of search string
					transition: 'fade',// page transition, default is fade
					matchFromStart: false, // search from start, or anywhere in the string
					loadingHtml : '<li data-icon="none"><a href="#">Searching...</a></li>', // HTML to display when searching remotely
					interval: 0, // The minimum delay between server calls when using a remote "source"
					builder : null, // optional callback to build HTML for autocomplete
					klass: 'tinted', // optional class name for listview's <li> tag
					forceFirstChoiceOnEnterKey : true,
				});
			});		
		</script>				
	</body>
</html>