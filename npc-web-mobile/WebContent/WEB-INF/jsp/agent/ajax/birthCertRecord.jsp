<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			<s:if test="certId == null">Add</s:if><s:else>Edit</s:else> Birth Registration
		</title>
	</head>
	<body>	
		<s:form id="birthCertRecordForm" action="birthCertRecord!save" namespace="/agent/ajax" theme="xhtml" method="post">
			<s:hidden name="certId" />
			<s:hidden name="fatherId" id="fatherId" />
			<s:hidden name="motherId" id="motherId" />
			<s:hidden name="informantId" id="informantId" />
			<sjm:textfield name="entity.certificateNumber" label="Certificate Number" required="true" />
			<sjm:textfield name="entity.firstName" label="First Name" required="true" />
			<sjm:textfield name="entity.middleName" label="Middle Name" required="false" />
			<sjm:textfield name="entity.lastName" label="Last Name" required="true" />
			<label for="entity.birthDate">Date of Birth</label>
			<input name="entity.birthDate" title="Date of Birth" id="entity.birthDate" type="date" data-role="datebox" value="<s:date name='entity.birthDate' format='dd/MM/yyyy' />" data-options='{"mode": "flipbox", "overrideDateFormat": "%d/%m/%Y"}' />
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
		
			<s:url var="fatherFilterUrl" action="listFather" namespace="/json" />
			
			<sjm:searchfield id="fatherSearchField" name="fatherSearchField" placeholder="Search Fathers..." label="Father" />
			<ul id="fatherListView" data-role="listview" data-inset="true"></ul>					
					
			<s:url var="motherFilterUrl" action="listMother" namespace="/json" />
			
			<sjm:searchfield id="motherSearchField" name="motherSearchField" placeholder="Search Mothers..." label="Mother" />
			<ul id="motherListView" data-role="listview" data-inset="true"></ul>					
		
			<s:url var="informantFilterUrl" action="listInformant" namespace="/json" />
			
			<sjm:searchfield id="informantSearchField" name="informantSearchField" placeholder="Search Informants..." label="Informant" />
			<ul id="informantListView" data-role="listview" data-inset="true"></ul>
							
			<sjm:select 
				name="entity.birthPlace" 
				headerKey=""
				headerValue="----------Select-----------"
				list="birthPlaceLov"
				listKey="name()"
				listValue="name()"
				emptyOption="false"
				label="Birth Place" 
				required="true" 
			/>
			<sjm:textfield name="entity.otherBirthPlace" label="Other Birth Place" required="false" />		
							
			<sjm:select 
				name="entity.birthType" 
				headerKey=""
				headerValue="----------Select-----------"
				list="birthTypeLov"
				listKey="name()"
				listValue="name()"
				emptyOption="false"
				label="Birth Type" 
				required="true" 
			/>		
			<sjm:textfield name="entity.town" label="Birth Town" required="true" />
			<sjm:textfield name="entity.registrationCenter" label="Registration Center" required="true" />
			<sjm:textfield name="entity.registrationTown" label="Registration Town" required="true" />
							
			<sjm:select 
				name="kinshipId" 
				headerKey=""
				headerValue="----------Select-----------"
				list="kinshipLov"
				listKey="id"
				listValue="description"
				emptyOption="false"
				label="Informant Relationship to Child" 
				required="true" 
			/>					
		</s:form>
		<sjm:a button="true" buttonIcon="gear" formIds="birthCertRecordForm" dataTheme="a">Save</sjm:a>
		<s:include value="/common/msg.jsp" />
		
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
								
				$("#fatherSearchField").on("input", function() {
					$("#fatherSearchField").autocomplete("update", {
						source: '<s:property value="%{#fatherFilterUrl}" />?param=' + $('#fatherSearchField').val()
					});
				});
			
				$("#fatherSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#fatherListView'), // the listview to receive results
					source: '<s:property value="%{#fatherFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#fatherId').val( $a.data('autocomplete').value );
									$('#fatherSearchField').val( $a.data('autocomplete').label );
									$("#fatherSearchField").autocomplete('clear');
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
				
				$("#motherSearchField").on("input", function() {
					$("#motherSearchField").autocomplete("update", {
						source: '<s:property value="%{#motherFilterUrl}" />?param=' + $('#motherSearchField').val()
					});
				});

				
				$("#motherSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#motherListView'), // the listview to receive results
					source: '<s:property value="%{#motherFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#motherId').val( $a.data('autocomplete').value );
									$('#motherSearchField').val( $a.data('autocomplete').label );
									$("#motherSearchField").autocomplete('clear');
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