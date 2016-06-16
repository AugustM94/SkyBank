<div id="page-wrapper" ng-class="{'open': toggle}" ng-cloak>
	<!-- Sidebar -->
	<%@include file="./sidebar.jsp" %>
	<!-- End Sidebar -->

	<div class="row header">
		<div class="col-xs-12">
			<div class="meta" style="margin-bottom:20px;">
				<div class="page">Dashboard</div>
				<div class="breadcrumb-links">Home</div>
			</div>
		</div>
	
	</div>
	<div class="row">
	
		<div class="form-group col-lg-6 col-md-6 col-xs-6">
		<h1>Add a new account</h1>
		<form class="simple-form">
					<label for="accountname">Account name</label>
					<input type="text" name="accountname" class="form-control" ng-model="accountname" required/><br /> 
					
					<div class="form-actions">
						<button type="button" ng-click="reset()" class="btn btn-default">Reset</button> 
						<button type="submit" ng-click="addNewAccount()" ng-disabled="form.$invalid" class="btn btn-default">Add new account</button>
				    </div>
				</form>
		</div>	
	</div>
</div>
<!-- End Page Content -->
