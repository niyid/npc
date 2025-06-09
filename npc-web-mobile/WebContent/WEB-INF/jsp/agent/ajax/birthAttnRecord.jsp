<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			<s:if test="attnId == null">Add</s:if><s:else>Edit</s:else> Birth Attestation
		</title>
	</head>
	<body>	
		<s:form id="birthAttnRecordForm" action="birthAttnRecord!save" namespace="/agent/ajax" theme="xhtml" method="post" enctype="multipart/form-data" data-ajax="false">
			<s:hidden name="attnId" />
			<s:hidden name="fatherId" id="fatherId" />
			<s:hidden name="motherId" id="motherId" />
			<s:hidden name="informantId" id="informantId" />
			<s:hidden name="countryStateId" id="countryStateId" />
			<s:hidden name="reqOrgAddressId" id="reqOrgAddressId" />
			<s:hidden name="lgaId" id="lgaId" />
			<s:hidden name="addressId" id="addressId" />
			<s:hidden name="workAddressId" id="workAddressId" />
			<s:hidden name="educationLevelId" id="educationLevelId" />
			<s:hidden name="occupationId" id="occupationId" />
			<s:hidden name="requestReasonAId" id="requestReasonAId" />
			<s:hidden name="requestReasonBId" id="requestReasonBId" />
			<s:hidden name="countryId" id="countryId" />
			<s:hidden name="photoId" id="photoId" />

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
			
			<sjm:flipSwitch name="entity.employed" label="Employed" />

			<s:url var="occupationFilterUrl" action="listOccupation" namespace="/json" />			
			<sjm:searchfield id="occupationSearchField" name="occupationSearchField" placeholder="Search Occupations..." label="Occupation" />
			<ul id="occupationListView" data-role="listview" data-inset="true"></ul>
			
			<s:url var="addressFilterUrl" action="listAddress" namespace="/json" />					
			<sjm:searchfield id="workAddressSearchField" name="workAddressSearchField" placeholder="Search Addresses..." label="Work Address" />
			<ul id="workAddressListView" data-role="listview" data-inset="true"></ul>		
			
			<s:url var="educationLevelFilterUrl" action="listEducationLevel" namespace="/json" />			
			<sjm:searchfield id="educationLevelSearchField" name="educationLevelSearchField" placeholder="Search Education Levels..." label="Education Level" />
			<ul id="educationLevelListView" data-role="listview" data-inset="true"></ul>

			<s:url var="countryStateFilterUrl" action="listCountryState" namespace="/json" />			
			<sjm:searchfield id="countryStateSearchField" name="countryStateSearchField" placeholder="Search States..." label="Birth State" />
			<ul id="countryStateListView" data-role="listview" data-inset="true"></ul>

			<s:url var="lgaFilterUrl" action="listLga" namespace="/json" />			
			<sjm:searchfield id="lgaSearchField" name="lgaSearchField" placeholder="Search Local Government Areas..." label="Birth LGA" />
			<ul id="lgaListView" data-role="listview" data-inset="true"></ul>
			
			<sjm:searchfield id="addressSearchField" name="addressSearchField" placeholder="Search Addresses..." label="Address" />
			<ul id="addressListView" data-role="listview" data-inset="true"></ul>		
		
			<sjm:textfield name="entity.phoneNumber" label="Phone Number" required="true" />
			<sjm:textfield name="entity.emailAddress" label="Email Address" required="true" />
			<sjm:textfield name="entity.nationalIdNumber" label="National ID Number" required="true" />
			
			<sjm:select 
				name="entity.maritalStatus" 
				headerKey=""
				headerValue="----------Select-----------"
				list="maritalStatusLov"
				listKey="name()"
				listValue="name()"
				emptyOption="false"
				label="Marital Status" 
				required="true" 
			/>
			<s:file name="photo" label="Photo" accept="image/*" required="false" />	
					
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
			<sjm:textfield name="entity.registrationCenter" label="Registration Center" required="true" />
										
			<sjm:select 
				name="requestReasonAId" 
				headerKey=""
				headerValue="----------Select-----------"
				list="requestReasonALov"
				listKey="id"
				listValue="description"
				emptyOption="false"
				label="Reason for Request (A)" 
				required="true" 
			/>		
										
			<sjm:select 
				name="requestReasonBId" 
				headerKey=""
				headerValue="----------Select-----------"
				list="requestReasonBLov"
				listKey="id"
				listValue="description"
				emptyOption="false"
				label="Reason for Request (B)" 
				required="true" 
			/>
			<sjm:textfield name="entity.otherRequestReasonB" label="Other Reason for Request (B)" required="false" />
			
						
			<sjm:searchfield id="reqOrgAddressSearchField" name="reqOrgAddressSearchField" placeholder="Search Addresses..." label="Address" />
			<ul id="reqOrgAddressListView" data-role="listview" data-inset="true"></ul>
								
			<s:url var="countryFilterUrl" action="listCountry" namespace="/json" />			
			<sjm:searchfield id="countrySearchField" name="countrySearchField" placeholder="Search Countries..." label="Country" />
			<ul id="countryListView" data-role="listview" data-inset="true"></ul>				
		</s:form>
		<sjm:a button="true" buttonIcon="gear" formIds="birthAttnRecordForm" dataTheme="a">Save</sjm:a>
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
				
				$("#countrySearchField").on("input", function() {
					$("#countrySearchField").autocomplete("update", {
						source: '<s:property value="%{#countryFilterUrl}" />?param=' + $('#countrySearchField').val()
					});
				});
				
				$("#countrySearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#countryListView'), // the listview to receive results
					source: '<s:property value="%{#countryFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#countryId').val( $a.data('autocomplete').value );
									$('#countrySearchField').val( $a.data('autocomplete').label );
									$("#countrySearchField").autocomplete('clear');
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
				
				$("#occupationSearchField").on("input", function() {
					$("#occupationSearchField").autocomplete("update", {
						source: '<s:property value="%{#occupationFilterUrl}" />?param=' + $('#occupationSearchField').val()
					});
				});

				$("#occupationSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#occupationListView'), // the listview to receive results
					source: '<s:property value="%{#occupationFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#occupationId').val( $a.data('autocomplete').value );
									$('#occupationSearchField').val( $a.data('autocomplete').label );
									$("#occupationSearchField").autocomplete('clear');
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

				$("#educationLevelSearchField").on("input", function() {
					$("#educationLevelSearchField").autocomplete("update", {
						source: '<s:property value="%{#educationLevelFilterUrl}" />?param=' + $('#educationLevelSearchField').val()
					});
				});

				$("#educationLevelSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#educationLevelListView'), // the listview to receive results
					source: '<s:property value="%{#educationLevelFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#educationLevelId').val( $a.data('autocomplete').value );
									$('#educationLevelSearchField').val( $a.data('autocomplete').label );
									$("#educationLevelSearchField").autocomplete('clear');
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
				
				$("#workAddressSearchField").on("input", function() {
					$("#workAddressSearchField").autocomplete("update", {
						source: '<s:property value="%{#addressFilterUrl}" />?param=' + $('#workAddressSearchField').val()
					});
				});
				
				$("#workAddressSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#workAddressListView'), // the listview to receive results
					source: '<s:property value="%{#addressFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#workAddressId').val( $a.data('autocomplete').value );
									$('#workAddressSearchField').val( $a.data('autocomplete').label );
									$("#workAddressSearchField").autocomplete('clear');
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
				
				$("#countryStateSearchField").on("input", function() {
					$("#countryStateSearchField").autocomplete("update", {
						source: '<s:property value="%{#countryStateFilterUrl}" />?param=' + $('#countryStateSearchField').val()
					});
				});
				
				$("#countryStateSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#countryStateListView'), // the listview to receive results
					source: '<s:property value="%{#countryStateFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#countryStateId').val( $a.data('autocomplete').value );
									$('#countryStateSearchField').val( $a.data('autocomplete').label );
									$("#countryStateSearchField").autocomplete('clear');
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
				
				$("#reqOrgAddressSearchField").on("input", function() {
					$("#reqOrgAddressSearchField").autocomplete("update", {
						source: '<s:property value="%{#addressFilterUrl}" />?param=' + $('#reqOrgAddressSearchField').val()
					});
				});
				
				$("#reqOrgAddressSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#reqOrgAddressListView'), // the listview to receive results
					source: '<s:property value="%{#addressFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#reqOrgAddressId').val( $a.data('autocomplete').value );
									$('#reqOrgAddressSearchField').val( $a.data('autocomplete').label );
									$("#reqOrgAddressSearchField").autocomplete('clear');
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
				
				$("#lgaSearchField").on("input", function() {
					$("#lgaSearchField").autocomplete("update", {
						source: '<s:property value="%{#lgaFilterUrl}" />?param=' + $('#lgaSearchField').val() + '&stateId=' + $('#countryStateId').val()
					});
				});
				
				$("#lgaSearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#lgaListView'), // the listview to receive results
					source: '<s:property value="%{#lgaFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#lgaId').val( $a.data('autocomplete').value );
									$('#lgaSearchField').val( $a.data('autocomplete').label );
									$("#lgaSearchField").autocomplete('clear');
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