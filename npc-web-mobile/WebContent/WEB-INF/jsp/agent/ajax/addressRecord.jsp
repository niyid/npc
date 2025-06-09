<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			<s:if test="addressId == null">Add</s:if><s:else>Edit</s:else> Address
		</title>
	</head>
	<body>
		<s:form id="addressRecordForm" action="addressRecord!save" namespace="/agent/ajax" theme="xhtml" method="post">
			<s:hidden name="addressId" />
			<s:hidden id="countryStateId" name="countryStateId" />
			<sjm:textfield name="entity.areaName1" label="Area Name 1" required="true" />
			<sjm:textfield name="entity.areaName2" label="Area Name 2" required="false" />
			<sjm:textfield name="entity.streetName" label="Street Name" required="true" />
			<sjm:textfield name="entity.propertyNumber" label="Property Number" required="true" />
			
		<s:url var="countryStateFilterUrl" action="listStateByCountry" namespace="/json" />
		
		<sjm:searchfield id="countryStateSearchField" name="countryStateSearchField" placeholder="Search states..." label="State" />
		<ul id="countryStateListView" data-role="listview" data-inset="true"></ul>
	
		</s:form>
		<sjm:a button="true" buttonIcon="gear" formIds="addressRecordForm" dataTheme="a">Save</sjm:a>
		<s:include value="/common/msg.jsp" />
		
		<script type="text/javascript">		
			$(document).on('pageinit', function(){
				
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
			});		
		</script>
	</body>
</html>