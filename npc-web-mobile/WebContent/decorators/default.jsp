<!DOCTYPE html>

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<sjm:head jqueryui="true" jquerytheme="flick" />
    <title><decorator:title default="Untitled page" /> | National Population Commission</title>
	<decorator:head />
	<%@ include file="/common/meta.jsp"%>	
</head>
<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
       
       <s:url var="loginUrl" action="login" namespace="/" />
       <div data-role="page" id="main" data-theme="b">
            <div data-role="header" data-position="fixed" data-theme="e">                
				 <table style="width: 100%">
					<tr>
						<td style="width: 30%;">
							<table>
								<tr>
									<td style="vertical-align: top;">
										<table>
											<tr>
												<td style="vertical-align: top;">
													<img src="<c:url value='/img/npc_logo.png'/>" alt="Logo" style="float: left; width: 50px; height: 48px;" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td style="width: 39%; text-align: center;">
							<h1><decorator:title default="Untitled page" /> <s:if test="user != null"></s:if></h1>
						</td>						
						<td style="width: 31%; text-align: right;">
							<s:if test="user != null">
								<span style="white-space: nowrap;">
									<s:url var="logoutUrl" value="/j_spring_security_logout" />
									<sjm:a id="logoutButton" href="%{logoutUrl}" button="true" buttonIcon="gear" dataTheme="a">Logout: <s:property value="user.fullName" /></sjm:a>
								</span>
							</s:if>
							<s:else>
								<span style="white-space: nowrap;">
									<sjm:a id="toLoginButton" href="%{loginUrl}" button="true" buttonIcon="gear" dataTheme="a">Login</sjm:a>
								</span>
							</s:else>
						</td>								
					</tr>
				</table>

				<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')">
					<s:url var="deathCertListUrl" action="deathCertList" namespace="/agent/ajax" />
					<s:url var="birthCertListUrl" action="birthCertList" namespace="/agent/ajax" />
					<s:url var="birthAttnListUrl" action="birthAttnList" namespace="/agent/ajax" />
	
					<sjm:div id="mainTabs" role="navbar">
						<ul>								
							<li><sjm:a href="%{deathCertListUrl}" id="deathCertTab" dataTheme="a">Death Registration</sjm:a></li>
							
							<li><sjm:a href="%{birthCertListUrl}" id="birthCertTab" dataTheme="a">Birth Registration</sjm:a></li>
							
							<li><sjm:a href="%{birthAttnListUrl}" id="birthAttnTab" dataTheme="a">Birth Attestation</sjm:a></li>
						</ul>
					</sjm:div>
				</security:authorize>
            </div><!-- /header -->
 
            <div data-role="content" id="mainDiv" style="border: 1px solid rgb(111, 163, 223); padding: 4px; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial; height: 450px; overflow: auto;">
            	<s:if test="user != null">
					<security:authorize access="!hasAnyRole('ROLE_MEMBER')">
						<span style="font-weight: bold; color: red; font-size: 12px;">Please check your email to activate your membership; and to complete your registration.</span>
					</security:authorize>
				</s:if>
            	
                <decorator:body />
        
            </div><!-- /content -->
            <div data-role="footer">
            	<table>
            		<tr>
            			<td align="left"><h3>Locally stored items: <span id="local-count">0</span></h3></td>
            			<td align="right"><h3>You are working: <span id="connStatus" class="offline">Offline</span></h3></td>
            		</tr>
            	</table>
            </div><!-- /footer -->
        </div><!-- /page -->
         			            
		<s:if test="user == null">
		
			<div id="loginRequiredDialog" data-role="page">
				<div data-role="header">
					<h1>Login Required</h1>
				</div>
				<div data-role="content">
					<h2>You are currently not logged in.</h2>
					<sjm:a button="true" buttonIcon="gear" href="%{loginUrl}" dataTheme="a">Log in</sjm:a>
				</div>
			</div>
		</s:if>        
        
</body>
</html>
