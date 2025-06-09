<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>
			<s:if test="informantId == null">Add</s:if><s:else>Edit</s:else> Informant
		</title>
	</head>
	<body>
		<s:form id="informantRecordForm" action="informantRecord!save" namespace="/agent/ajax" theme="xhtml" method="post" enctype="multipart/form-data" data-ajax="false">
			<s:hidden name="informantId" />
			<s:hidden name="signatureId" />
			<s:hidden name="addressId" id="addressId" />
			<s:hidden name="signature" id="signature" />
			<s:hidden name="signed" id="signed" />
			<sjm:textfield name="entity.firstName" label="First Name" required="true" />
			<sjm:textfield name="entity.middleName" label="Middle Name" required="false" />
			<sjm:textfield name="entity.lastName" label="Last Name" required="true" />
			
			<s:file name="photo" label="Photo" accept="image/*" required="false" />

			<s:url var="addressFilterUrl" action="listAddress" namespace="/json" />					
			<sjm:searchfield id="addressSearchField" name="addressSearchField" placeholder="Search addresses..." label="Address" />
			<ul id="addressListView" data-role="listview" data-inset="true"></ul>					
		</s:form>
		<s:include value="/common/msg.jsp" />
		
		<div id="signatureInformantParent">
			<h1>Informant Signature</h1>
			<div id="signatureInformant"></div>
			<sjm:a id="resetSignatureButton" button="true" buttonIcon="refresh" dataTheme="d">Clear Signature</sjm:a>
		</div>
		<s:if test="entity.signature != null">
		<div>
			<h1>Signature on File</h1>
				<div>
					<img style='border: solid 1px #4f81bd;' src='<s:url action="imageDisplay" namespace="/download"><s:param name="fileId" value="%{entity.signature.id}" /></s:url>' />
				</div>
		</div>
		</s:if>
		<sjm:a id="informantRecordSaveButton" button="true" buttonIcon="gear" formIds="informantRecordForm" dataTheme="a">Save</sjm:a>
		<script>
		
		 	$(document).on("pageinit", function() {
		        $("#signatureInformant").jSignature({width:600,height:200});
		        $("#signed").val('false');
		        
		        $("#signatureInformant").bind(
		        	    "change"
		        	    , function(event){
		        	        // 'event.target' will refer to DOM element with id "#signature"
		        	        var d = $(event.target).jSignature("getData", "native");
		        	        // if there are more than 2 strokes in the signature
		        	        // or if there is just one stroke, but it has more than 20 points
		        	        if ( d.length > 2 || ( d.length === 1 && d[0].x.length > 20 ) ){
		        	            // we show "Submit" button
		        	            // $(event.target).unbind('change')
		        	            $("#signed").val('true');
		        	        } else {
		        	            $("#signed").val('false');
		        	        }
		        	    }
		        	);
		          
		        $('#informantRecordSaveButton').on("click", function() {
		            var data = $('#signatureInformant').jSignature('getData');
		            $("#signature").val(data);
		        });

		        $('#resetSignatureButton').on("click", function() {
		        	$("#signatureInformant").jSignature("reset");
		        });
			    
			});		
		</script>					
		<script type="text/javascript">		
			$(document).on('pageinit', function() {
				
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