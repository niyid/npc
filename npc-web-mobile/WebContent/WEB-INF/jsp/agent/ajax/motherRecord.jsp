<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			<s:if test="motherId == null">Add</s:if><s:else>Edit</s:else> Mother
		</title>
	</head>
	<body>
		<s:form id="motherRecordForm" action="motherRecord!save" namespace="/agent/ajax" theme="xhtml" method="post" enctype="multipart/form-data" data-ajax="false">
			<s:hidden name="motherId" />
			<s:hidden id="occupationId" name="occupationId" />
			<s:hidden id="educationLevelId" name="educationLevelId" />
			<s:hidden id="ethnicityId" name="ethnicityId" />
			<s:hidden id="countryStateId" name="countryStateId" />
			<s:hidden id="lgaId" name="lgaId" />
			<s:hidden name="addressId" id="addressId" />
						
			<sjm:textfield name="entity.firstName" label="First Name" required="true" />
			<sjm:textfield name="entity.middleName" label="Middle Name" required="false" />
			<sjm:textfield name="entity.lastName" label="Last Name" required="true" />
			<sjm:textfield name="entity.maidenName" label="Maiden Name" required="false" />
			<label for="entity.birthDate">Date of Birth</label>
			<input name="entity.birthDate" id="entity.birthDate" type="date" data-role="datebox" value="<s:date name='entity.birthDate' format='dd/MM/yyyy' />" data-options='{"mode": "flipbox", "overrideDateFormat": "%d/%m/%Y"}' />

			<s:url var="occupationFilterUrl" action="listOccupation" namespace="/json" />			
			<sjm:searchfield id="occupationSearchField" name="occupationSearchField" placeholder="Search Occupations..." label="Occupation" />
			<ul id="occupationListView" data-role="listview" data-inset="true"></ul>

			<s:url var="educationLevelFilterUrl" action="listEducationLevel" namespace="/json" />			
			<sjm:searchfield id="educationLevelSearchField" name="educationLevelSearchField" placeholder="Search Education Levels..." label="Education Level" />
			<ul id="educationLevelListView" data-role="listview" data-inset="true"></ul>

			<s:url var="ethnicityFilterUrl" action="listEthnicity" namespace="/json" />			
			<sjm:searchfield id="ethnicitySearchField" name="ethnicitySearchField" placeholder="Search Ethnicities..." label="Ethnicity" />
			<ul id="ethnicityListView" data-role="listview" data-inset="true"></ul>

			<s:url var="countryStateFilterUrl" action="listCountryState" namespace="/json" />			
			<sjm:searchfield id="countryStateSearchField" name="countryStateSearchField" placeholder="Search States..." label="State" />
			<ul id="countryStateListView" data-role="listview" data-inset="true"></ul>

			<s:url var="lgaFilterUrl" action="listLga" namespace="/json" />			
			<sjm:searchfield id="lgaSearchField" name="lgaSearchField" placeholder="Search Local Government Areas..." label="LGA" />
			<ul id="lgaListView" data-role="listview" data-inset="true"></ul>

			<s:url var="addressFilterUrl" action="listAddress" namespace="/json" />					
			<sjm:searchfield id="addressSearchField" name="addressSearchField" placeholder="Search Addresses..." label="Address" />
			<ul id="addressListView" data-role="listview" data-inset="true"></ul>		
									
			<sjm:textfield name="entity.phoneNumber" label="Phone Number" required="true" />
			<sjm:textfield name="entity.emailAddress" label="Email Address" required="true" />
			<sjm:textfield name="entity.nationalIdNumber" label="National ID Number" required="true" />
			<sjm:textfield name="entity.town" label="Town" required="true" />
			
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
		</s:form>
		<sjm:a button="true" buttonIcon="gear" formIds="motherRecordForm" dataTheme="a">Save</sjm:a>
		<s:include value="/common/msg.jsp" />
	
		<script type="text/javascript">		
			$(document).on('pageinit', function(){
				
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
				
				$("#ethnicitySearchField").on("input", function() {
					$("#ethnicitySearchField").autocomplete("update", {
						source: '<s:property value="%{#ethnicityFilterUrl}" />?param=' + $('#ethnicitySearchField').val()
					});
				});

				$("#ethnicitySearchField").autocomplete({
					method: 'GET', // allows POST as well
					icon: 'arrow-r', // option to specify icon
					target: $('#ethnicityListView'), // the listview to receive results
					source: '<s:property value="%{#ethnicityFilterUrl}" />?param=', // URL return JSON data
					callback: function(e) {
									var $a = $(e.currentTarget);
									$('#ethnicityId').val( $a.data('autocomplete').value );
									$('#ethnicitySearchField').val( $a.data('autocomplete').label );
									$("#ethnicitySearchField").autocomplete('clear');
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
				
				$("#lgaSearchField").on("input", function() {
					$("#lgaSearchField").autocomplete("update", {
						source: '<s:property value="%{#lgaFilterUrl}" />?param=' + $('#lgaSearchField').val() + '&stateId=' + $('#stateId').val()
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
	</body>
</html>