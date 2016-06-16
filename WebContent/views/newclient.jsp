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
		<h1>Add a new client</h1>
		<form class="simple-form">
					<label for="username">Username</label>
					<input type="text" name="receiver" class="form-control" ng-model="username" required/><br /> 
					
					<label for="password">Password</label>
					<input type="password" name="password" class="form-control" ng-model="password" required/><br /> 
					
					
					<label for="fname">First name</label>
					<input type="text" name="fname" class="form-control" ng-model="fname" required/><br /> 
					
					<label for="lname">Last name</label>
					<input type="text" name="lname" class="form-control" ng-model="lname" required/><br /> 
					
					<label for="phone">Phone</label>
					<input type="number" name="phone" class="form-control" ng-model="phone" /><br /> 

					<label for="cpr">Cpr-number</label>
					<input type="number" name="cpr" class="form-control" ng-model="cpr" required/><br />
					 
					<label for="street">Street name</label>
					<input type="text" name="street" class="form-control" ng-model="street" required/><br /> 
					
					<label for="streetno">Street number</label>
					<input type="text" name="streetno" class="form-control" ng-model="streetno" required/><br /> 
					
					<label for="zip">ZIP-code</label>
					<input type="number" name="zip" class="form-control" ng-model="zip" required/><br /> 
					
					<label for="city">City</label>
					<input type="text" name="city" class="form-control" ng-model="city" required/><br /> 
					
					<label for="country">Country</label>
					<input type="text" name="country" class="form-control" ng-model="country" required/><br /> 
					
					<div class="form-actions">
						<button type="button" ng-click="reset()" class="btn btn-default">Reset</button> 
						<button type="submit" ng-click="addNewClient()" ng-disabled="form.$invalid" class="btn btn-default">Add new client</button>
				    </div>
				</form>
		</div>	
	</div>
</div>
<!-- End Page Content -->
